
package br.com.finlite.controller;

import br.com.finlite.model.Material;
import br.com.finlite.model.MovimentacaoEstoque;
import br.com.finlite.service.MaterialService;
import br.com.finlite.service.MovimentacaoEstoqueService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/estoque/movimentacoes")
public class MovimentacaoEstoqueController {

    private final MovimentacaoEstoqueService movimentacaoService;
    private final MaterialService materialService;

    public MovimentacaoEstoqueController(
            MovimentacaoEstoqueService movimentacaoService,
            MaterialService materialService) {

        this.movimentacaoService = movimentacaoService;
        this.materialService = materialService;
    }

    // Histórico de movimentações
    @GetMapping
    public String listar(Model model) {
        model.addAttribute(
                "movimentacoes",
                movimentacaoService.listarTodos()
        );

        return "estoque/movimentacoes";
    }

    // Formulário de saída de material
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("movimentacao", new MovimentacaoEstoque());
        model.addAttribute("materiais", materialService.listarTodos());

        return "estoque/movimentacao-formulario";
    }

    // Salvar saída de material
    @PostMapping("/salvar")
    public String salvar(
            @RequestParam Integer idMaterial,
            @RequestParam BigDecimal quantidadeMovimentacao,
            @RequestParam String motivoOrigem,
            RedirectAttributes redirectAttributes) {

        try {
            Optional<Material> materialOptional =
                    materialService.buscarPorId(idMaterial);

            if (materialOptional.isEmpty()) {
                throw new IllegalArgumentException(
                        "Material não encontrado."
                );
            }

            MovimentacaoEstoque movimentacao = new MovimentacaoEstoque();

            movimentacao.setMaterial(materialOptional.get());
            movimentacao.setTipoMovimentacao("SAIDA");
            movimentacao.setQuantidadeMovimentacao(quantidadeMovimentacao);
            movimentacao.setMotivoOrigem(motivoOrigem);

            movimentacaoService.salvar(movimentacao);

            redirectAttributes.addFlashAttribute(
                    "sucesso",
                    "Saída de material registrada com sucesso!"
            );

            return "redirect:/estoque/movimentacoes";

        } catch (IllegalArgumentException | IllegalStateException e) {

            redirectAttributes.addFlashAttribute("erro", e.getMessage());

            return "redirect:/estoque/movimentacoes/novo";
        }
    }
}
