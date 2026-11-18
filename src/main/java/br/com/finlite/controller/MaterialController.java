
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
@RequestMapping("/materiais")
public class MaterialController {

    private final MaterialService materialService;
    private final EmpreendedorService empreendedorService;

    public MaterialController(
            MaterialService materialService,
            EmpreendedorService empreendedorService) {
        this.materialService = materialService;
        this.empreendedorService = empreendedorService;
    }
    
    @GetMapping("/novo")
    public String novoMaterial(Model model) {

        model.addAttribute("material", new Material());

        List<Empreendedor> empreendedores =
                empreendedorService.listarTodos();

        if (empreendedores.isEmpty()) {
            model.addAttribute(
                    "erro",
                    "Nenhum empreendedor cadastrado. Cadastre um empreendedor antes de registrar materiais."
            );
        } else {
            model.addAttribute(
                    "idEmpreendedor",
                    empreendedores.get(0).getIdEmpreendedor()
            );
        }

        return "materiais/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute Material material,
            @RequestParam Integer idEmpreendedor,
            RedirectAttributes redirectAttributes,
            Model model) {

        Optional<Empreendedor> empreendedor =
                empreendedorService.buscarPorId(idEmpreendedor);

        if (empreendedor.isEmpty()) {
            model.addAttribute(
                    "erro",
                    "Empreendedor não encontrado. Verifique o cadastro."
            );
            model.addAttribute("material", material);
            return prepararFormulario(model);
        }

        material.setEmpreendedor(empreendedor.get());

        try {
            materialService.salvar(material);

            redirectAttributes.addFlashAttribute(
                    "sucesso",
                    "Material cadastrado com sucesso!"
            );

            return "redirect:/estoque";

        } catch (IllegalArgumentException e) {

            model.addAttribute("erro", e.getMessage());
            model.addAttribute("material", material);

            return prepararFormulario(model);
        }
    }

    private String prepararFormulario(Model model) {

        List<Empreendedor> empreendedores =
                empreendedorService.listarTodos();

        if (!empreendedores.isEmpty()) {
            model.addAttribute(
                    "idEmpreendedor",
                    empreendedores.get(0).getIdEmpreendedor()
            );
        }

        return "materiais/formulario";
    }
}
