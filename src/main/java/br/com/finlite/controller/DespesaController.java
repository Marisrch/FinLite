
package br.com.finlite.controller;

import br.com.finlite.model.Empreendedor;
import br.com.finlite.model.LancamentoFinanceiro;
import br.com.finlite.model.Material;

import br.com.finlite.service.EmpreendedorService;
import br.com.finlite.service.LancamentoFinanceiroService;
import br.com.finlite.service.MaterialService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    // Lista somente os lançamentos do tipo DESPESA
    @GetMapping
    public String listar(Model model) {

        model.addAttribute(
                "lancamentos",
                lancamentoService.listarTodos()
                        .stream()
                        .filter(l -> "DESPESA".equalsIgnoreCase(
                                l.getTipoLancamento()
                        ))
                        .toList()
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

    // Salva uma despesa
    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute("lancamento") LancamentoFinanceiro lancamento,
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

                prepararFormulario(
                        model,
                        idEmpreendedor
                );

                return "despesas/formulario";
            }

            lancamento.setMaterial(material);

        } else {

            lancamento.setMaterial(null);
        }

        try {

            lancamentoService.salvar(lancamento);

            redirectAttributes.addFlashAttribute(
                    "sucesso",
                    "Despesa registrada com sucesso!"
            );

            return "redirect:/despesas";

        } catch (IllegalArgumentException e) {

            model.addAttribute("erro", e.getMessage());

            prepararFormulario(
                    model,
                    idEmpreendedor
            );

            return "despesas/formulario";
        }
    }

    // Prepara os dados necessários para o formulário
    private void prepararFormulario(
            Model model,
            Integer idEmpreendedor) {

        model.addAttribute(
                "idEmpreendedor",
                idEmpreendedor
        );

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
}
