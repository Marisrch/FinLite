
package br.com.finlite.controller;

import br.com.finlite.model.Empreendedor;
import br.com.finlite.model.Venda;
import br.com.finlite.service.EmpreendedorService;
import br.com.finlite.service.VendaService;
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
    public String listar(Model model) {
        model.addAttribute("vendas", vendaService.listarTodos());
        return "vendas/index";
    }

    @GetMapping("/nova")
    public String novaVenda(Model model) {
        List<Empreendedor> empreendedores =
                empreendedorService.listarTodos();

        model.addAttribute("venda", new Venda());

        if (empreendedores.isEmpty()) {
            model.addAttribute("erro",
                    "Nenhum empreendedor cadastrado. Cadastre um empreendedor antes de registrar vendas.");
        } else {
            model.addAttribute(
                    "idEmpreendedor",
                    empreendedores.get(0).getIdEmpreendedor()
            );
        }

        return "vendas/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute Venda venda,
            @RequestParam Integer idEmpreendedor,
            RedirectAttributes redirectAttributes) {

        Optional<Empreendedor> empreendedor =
                empreendedorService.buscarPorId(idEmpreendedor);

        if (empreendedor.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Empreendedor não encontrado. Verifique o cadastro."
            );
            return "redirect:/vendas/nova";
        }

        venda.setEmpreendedor(empreendedor.get());
        vendaService.salvar(venda);

        redirectAttributes.addFlashAttribute(
                "sucesso",
                "Venda cadastrada com sucesso!"
        );

        return "redirect:/vendas";
    }
}
