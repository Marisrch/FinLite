
package br.com.finlite.controller;

import br.com.finlite.model.Empreendedor;
import br.com.finlite.model.Venda;
import br.com.finlite.service.EmpreendedorService;
import br.com.finlite.service.VendaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/vendas")
public class VendaController {

    private final VendaService vendaService;
    private final EmpreendedorService empreendedorService;

    public VendaController(
            VendaService vendaService,
            EmpreendedorService empreendedorService) {
        this.vendaService = vendaService;
        this.empreendedorService = empreendedorService;
    }

    @GetMapping
    public String listar(
            @RequestParam(required = false, defaultValue = "") String cliente,
            @RequestParam(required = false, defaultValue = "") String status,
            Model model) {

        model.addAttribute("vendas", vendaService.pesquisar(cliente, status));

        model.addAttribute("clienteFiltro", cliente);
        model.addAttribute("statusFiltro", status);

        return "vendas/index";
    }

    @GetMapping("/novo")
    public String novaVenda(Model model) {

        List<Empreendedor> empreendedores =
                empreendedorService.listarTodos();

        model.addAttribute("venda", new Venda());
        model.addAttribute("modoEdicao", false);

        if (empreendedores.isEmpty()) {
            model.addAttribute("erro",
                    "Nenhum empreendedor cadastrado. "
                            + "Cadastre um empreendedor antes de registrar vendas.");
        } else {
            model.addAttribute("idEmpreendedor",
                    empreendedores.get(0).getIdEmpreendedor());
        }

        return "vendas/formulario";
    }

    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Integer id,
            Model model,
            RedirectAttributes redirectAttributes) {

        Optional<Venda> vendaOptional = vendaService.buscarPorId(id);

        if (vendaOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    "erro", "Venda não encontrada.");
            return "redirect:/vendas";
        }

        Venda venda = vendaOptional.get();

        model.addAttribute("venda", venda);
        model.addAttribute("idEmpreendedor",
                venda.getEmpreendedor().getIdEmpreendedor());
        model.addAttribute("modoEdicao", true);

        return "vendas/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute("venda") Venda venda,
            @RequestParam Integer idEmpreendedor,
            RedirectAttributes redirectAttributes) {

        // Identifica a operação antes de salvar.
        boolean edicao = venda.getIdVenda() != null;

        try {
            Optional<Empreendedor> empreendedor =
                    empreendedorService.buscarPorId(idEmpreendedor);

            if (empreendedor.isEmpty()) {
                throw new IllegalArgumentException(
                        "Empreendedor não encontrado.");
            }

            // Verifica se a venda existe quando for uma edição.
            if (edicao
                    && vendaService.buscarPorId(venda.getIdVenda()).isEmpty()) {
                throw new IllegalArgumentException(
                        "A venda que você tentou editar não foi encontrada.");
            }

            venda.setEmpreendedor(empreendedor.get());

            vendaService.salvar(venda);

            redirectAttributes.addFlashAttribute(
                    "sucesso",
                    edicao
                            ? "Venda atualizada com sucesso!"
                            : "Venda cadastrada com sucesso!");

            return "redirect:/vendas";

        } catch (IllegalArgumentException e) {

            redirectAttributes.addFlashAttribute("erro", e.getMessage());

            if (edicao) {
                return "redirect:/vendas/editar/" + venda.getIdVenda();
            }

            return "redirect:/vendas/novo";
        }
    }

    @PostMapping("/excluir/{id}")
    public String excluir(
            @PathVariable Integer id,
            RedirectAttributes redirectAttributes) {

        try {
            vendaService.excluir(id);

            redirectAttributes.addFlashAttribute(
                    "sucesso", "Venda excluída com sucesso!");

        } catch (EntityNotFoundException e) {

            redirectAttributes.addFlashAttribute(
                    "erro", e.getMessage());
        }

        return "redirect:/vendas";
    }
}
