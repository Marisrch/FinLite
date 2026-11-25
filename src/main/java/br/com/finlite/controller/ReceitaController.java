
package br.com.finlite.controller;

import br.com.finlite.model.Empreendedor;
import br.com.finlite.model.LancamentoFinanceiro;
import br.com.finlite.service.EmpreendedorService;
import br.com.finlite.service.LancamentoFinanceiroService;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/receitas")
public class ReceitaController {

    private final LancamentoFinanceiroService lancamentoService;
    private final EmpreendedorService empreendedorService;

    public ReceitaController(
            LancamentoFinanceiroService lancamentoService,
            EmpreendedorService empreendedorService) {

        this.lancamentoService = lancamentoService;
        this.empreendedorService = empreendedorService;
    }

    // Lista somente receitas
    @GetMapping
    public String listar(Model model) {

        model.addAttribute(
                "lancamentos",
                lancamentoService.listarTodos()
                        .stream()
                        .filter(l -> "RECEITA".equalsIgnoreCase(
                                l.getTipoLancamento()
                        ))
                        .toList()
        );

        return "receitas/index";
    }

    // Abre o formulário de nova receita
    @GetMapping("/novo")
    public String novaReceita(Model model) {

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
                    "Cadastre um empreendedor antes de registrar receitas."
            );
            return "receitas/formulario";
        }

        model.addAttribute(
                "idEmpreendedor",
                empreendedor.getIdEmpreendedor()
        );

        return "receitas/formulario";
    }

    // Abre o formulário de edição
    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Integer id,
            Model model,
            RedirectAttributes redirectAttributes) {

        LancamentoFinanceiro lancamento = lancamentoService
                .buscarPorId(id)
                .orElse(null);

        if (lancamento == null ||
                !"RECEITA".equalsIgnoreCase(
                        lancamento.getTipoLancamento())) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Receita não encontrada."
            );

            return "redirect:/receitas";
        }

        model.addAttribute("lancamento", lancamento);
        model.addAttribute(
                "idEmpreendedor",
                lancamento.getEmpreendedor().getIdEmpreendedor()
        );

        return "receitas/formulario";
    }

    // Salva ou atualiza uma receita
    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute("lancamento") LancamentoFinanceiro lancamento,
            BindingResult resultado,
            @RequestParam Integer idEmpreendedor,
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
            model.addAttribute("idEmpreendedor", idEmpreendedor);
            return "receitas/formulario";
        }

        lancamento.setEmpreendedor(empreendedor);
        lancamento.setTipoLancamento("RECEITA");
        lancamento.setMaterial(null);

        boolean edicao = lancamento.getIdLancamento() != null;

        try {
            lancamentoService.salvar(lancamento);

            redirectAttributes.addFlashAttribute(
                    "sucesso",
                    edicao
                            ? "Receita atualizada com sucesso!"
                            : "Receita registrada com sucesso!"
            );

            return "redirect:/receitas";

        } catch (IllegalArgumentException e) {

            String mensagem = e.getMessage();

            if (mensagem != null) {

                if (mensagem.contains("data")) {
                    resultado.rejectValue(
                            "dataLancamento",
                            "receita.data",
                            mensagem
                    );

                } else if (mensagem.contains("descrição")) {
                    resultado.rejectValue(
                            "descricaoLancamento",
                            "receita.descricao",
                            mensagem
                    );

                } else if (mensagem.contains("categoria")) {
                    resultado.rejectValue(
                            "categoriaLancamento",
                            "receita.categoria",
                            mensagem
                    );

                } else if (mensagem.contains("valor total")) {
                    resultado.rejectValue(
                            "valorTotalLancamento",
                            "receita.valor",
                            mensagem
                    );

                } else {
                    model.addAttribute("erro", mensagem);
                }
            }

            model.addAttribute("idEmpreendedor", idEmpreendedor);

            return "receitas/formulario";

        } catch (EntityNotFoundException e) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    "A receita ou algum dado relacionado não foi encontrado."
            );

            return "redirect:/receitas";
        }
    }

    // Exclui uma receita
    @PostMapping("/excluir/{id}")
    public String excluir(
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {

        try {
            LancamentoFinanceiro lancamento = lancamentoService
                    .buscarPorId(id)
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Receita não encontrada."
                    ));

            if (!"RECEITA".equalsIgnoreCase(
                    lancamento.getTipoLancamento())) {

                throw new IllegalArgumentException(
                        "O lançamento informado não é uma receita."
                );
            }

            lancamentoService.excluir(id);

            redirectAttributes.addFlashAttribute(
                    "sucesso",
                    "Receita excluída com sucesso!"
            );

        } catch (IllegalStateException |
                 IllegalArgumentException |
                 EntityNotFoundException e) {

            redirectAttributes.addFlashAttribute(
                    "erro",
                    e.getMessage()
            );
        }

        return "redirect:/receitas";
    }
}
