
package br.com.finlite.controller;

import br.com.finlite.model.Empreendedor;
import br.com.finlite.model.LancamentoFinanceiro;
import br.com.finlite.model.Material;

import br.com.finlite.service.EmpreendedorService;
import br.com.finlite.service.LancamentoFinanceiroService;
import br.com.finlite.service.MaterialService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/despesas")
public class DespesaController {

    private final LancamentoFinanceiroService lancamentoService;
    private final EmpreendedorService empreendedorService;
    private final MaterialService materialService;

    public DespesaController(
            LancamentoFinanceiroService lancamentoService,
            EmpreendedorService empreendedorService,
            MaterialService materialService) {
        this.lancamentoService = lancamentoService;
        this.empreendedorService = empreendedorService;
        this.materialService = materialService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute(
                "lancamentos",
                lancamentoService.listarPorTipoOrdenadoPorData("DESPESA")
        );

        return "despesas/index";
    }

    // Abre o formulário de nova despesa
    @GetMapping("/novo")
    public String novaDespesa(Model model) {

        Empreendedor empreendedor = empreendedorService
                .listarTodos()
                .stream()
                .findFirst()
                .orElse(null);

        model.addAttribute(
                "lancamento",
                new LancamentoFinanceiro()
        );

        if (empreendedor == null) {
            model.addAttribute(
                    "erro",
                    "Cadastre um empreendedor antes de registrar despesas."
            );
            model.addAttribute("materiais", java.util.List.of());
            return "despesas/formulario";
        }

        prepararFormulario(
                model,
                empreendedor.getIdEmpreendedor()
        );

        return "despesas/formulario";
    }

    // Abre o formulário de edição de uma despesa
    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Integer id,
            Model model,
            RedirectAttributes redirectAttributes) {

        LancamentoFinanceiro lancamento = lancamentoService
                .buscarPorId(id)
                .orElse(null);

        if (lancamento == null || !"DESPESA".equalsIgnoreCase(lancamento.getTipoLancamento())) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Despesa não encontrada."
            );

            return "redirect:/despesas";
        }

        model.addAttribute("lancamento", lancamento);

        prepararFormulario(
                model,
                lancamento.getEmpreendedor().getIdEmpreendedor()
        );

        return "despesas/formulario";
    }

    // Salva uma despesa nova ou atualiza uma existente
    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute("lancamento") LancamentoFinanceiro lancamento,
            BindingResult resultado,
            @RequestParam Integer idEmpreendedor,
            @RequestParam(required = false) Integer idMaterial,
            Model model,
            RedirectAttributes redirectAttributes) {

        Empreendedor empreendedor = empreendedorService
                .buscarPorId(idEmpreendedor)
                .orElse(null);

        if (empreendedor == null) {
            model.addAttribute(
                    "erro",
                    "Empreendedor não encontrado."
            );

            prepararFormulario(model, idEmpreendedor);
            return "despesas/formulario";
        }

        lancamento.setEmpreendedor(empreendedor);
        lancamento.setTipoLancamento("DESPESA");

        // Material é opcional
        if (idMaterial != null) {

            Material material = materialService
                    .buscarPorId(idMaterial)
                    .orElse(null);

            if (material == null ||
                    material.getEmpreendedor() == null ||
                    !material.getEmpreendedor()
                            .getIdEmpreendedor()
                            .equals(empreendedor.getIdEmpreendedor())) {

                model.addAttribute(
                        "erro",
                        "O material selecionado não é válido."
                );

                prepararFormulario(model, idEmpreendedor);
                return "despesas/formulario";
            }

            lancamento.setMaterial(material);

        } else {
            lancamento.setMaterial(null);
        }

        boolean edicao = lancamento.getIdLancamento() != null;

        try {
            lancamentoService.salvar(lancamento);

            redirectAttributes.addFlashAttribute(
                    "sucesso",
                    edicao
                            ? "Despesa atualizada com sucesso!"
                            : "Despesa registrada com sucesso!"
            );

            return "redirect:/despesas";

        } catch (IllegalArgumentException e) {

            String mensagem = e.getMessage();

            if (mensagem != null) {

                if (mensagem.contains("data")) {
                    resultado.rejectValue(
                            "dataLancamento", "despesa.data", mensagem
                    );

                } else if (mensagem.contains("descrição")) {
                    resultado.rejectValue(
                            "descricaoLancamento", "despesa.descricao", mensagem
                    );

                } else if (mensagem.contains("categoria")) {
                    resultado.rejectValue(
                            "categoriaLancamento", "despesa.categoria", mensagem
                    );

                } else if (mensagem.contains("quantidade")) {
                    resultado.rejectValue(
                            "quantidadeLancamento", "despesa.quantidade", mensagem
                    );

                } else if (mensagem.contains("custo unitário")) {
                    resultado.rejectValue(
                            "custoUnitarioLancamento", "despesa.custo", mensagem
                    );

                } else if (mensagem.contains("valor total")) {
                    resultado.rejectValue(
                            "valorTotalLancamento", "despesa.valor", mensagem
                    );

                } else {
                    model.addAttribute("erro", mensagem);
                }
            }

            prepararFormulario(model, idEmpreendedor);

            return "despesas/formulario";

        } catch (EntityNotFoundException e) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "O lançamento ou algum dado relacionado não foi encontrado."
            );

            return "redirect:/despesas";
        }
    }

    // Prepara os dados necessários para o formulário
    private void prepararFormulario(
            Model model,
            Integer idEmpreendedor) {

        model.addAttribute("idEmpreendedor", idEmpreendedor);

        model.addAttribute(
                "materiais",
                materialService.listarTodos()
                        .stream()
                        .filter(m -> m.getEmpreendedor() != null &&
                                m.getEmpreendedor()
                                        .getIdEmpreendedor()
                                        .equals(idEmpreendedor))
                        .toList()
        );
    }

    // Exclui uma despesa e reverte eventual movimentação de estoque
    @PostMapping("/excluir/{id}")
    public String excluir(
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {

        try {
            lancamentoService.excluir(id);

            redirectAttributes.addFlashAttribute(
                    "sucesso",
                    "Despesa excluída com sucesso!"
            );

        } catch (IllegalStateException |
                 IllegalArgumentException |
                 EntityNotFoundException e) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    e.getMessage()
            );
        }

        return "redirect:/despesas";
    }
}
