
package br.com.finlite.controller;

import br.com.finlite.model.Empreendedor;
import br.com.finlite.service.EmpreendedorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/empreendedores")
public class EmpreendedorController {

    private final EmpreendedorService empreendedorService;

    public EmpreendedorController(EmpreendedorService empreendedorService) {
        this.empreendedorService = empreendedorService;
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("empreendedor", new Empreendedor());
        return "empreendedor/formulario";
    }

    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute Empreendedor empreendedor,
            Model model,
            RedirectAttributes redirectAttributes) {

        try {
            empreendedorService.salvar(empreendedor);

            redirectAttributes.addFlashAttribute(
                    "sucesso",
                    "Empreendedor cadastrado com sucesso!"
            );

            return "redirect:/empreendedores/novo";

        } catch (IllegalArgumentException e) {

            model.addAttribute("empreendedor", empreendedor);
            model.addAttribute("erro", e.getMessage());

            return "empreendedor/formulario";
        }
    }
}
