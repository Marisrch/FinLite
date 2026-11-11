
package br.com.finlite.controller;

import br.com.finlite.model.Empreendedor;
import br.com.finlite.model.Material;
import br.com.finlite.service.EmpreendedorService;
import br.com.finlite.service.MaterialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/estoque")
public class EstoqueController {

    private final MaterialService materialService;
    private final EmpreendedorService empreendedorService;

    public EstoqueController(
            MaterialService materialService,
            EmpreendedorService empreendedorService) {
        this.materialService = materialService;
        this.empreendedorService = empreendedorService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("materiais", materialService.listarTodos());
        return "estoque/index";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        List<Empreendedor> empreendedores =
                empreendedorService.listarTodos();

        model.addAttribute("material", new Material());

        if (empreendedores.isEmpty()) {
            model.addAttribute(
                    "erro",
                    "Cadastre um empreendedor antes de cadastrar materiais."
            );
        } else {
            model.addAttribute(
                    "idEmpreendedor",
                    empreendedores.get(0).getIdEmpreendedor()
            );
        }

        return "estoque/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute Material material,
            @RequestParam Integer idEmpreendedor,
            RedirectAttributes redirectAttributes) {

        Optional<Empreendedor> empreendedor =
                empreendedorService.buscarPorId(idEmpreendedor);

        if (empreendedor.isEmpty()) {
            redirectAttributes.addFlashAttribute(
                    "erro",
                    "Empreendedor não encontrado."
            );
            return "redirect:/estoque/novo";
        }

        material.setEmpreendedor(empreendedor.get());
        materialService.salvar(material);

        redirectAttributes.addFlashAttribute(
                "sucesso",
                "Material cadastrado com sucesso!"
        );

        return "redirect:/estoque";
    }
}
