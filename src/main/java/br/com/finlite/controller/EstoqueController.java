
package br.com.finlite.controller;

import br.com.finlite.service.MaterialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/estoque")
public class EstoqueController {

    private final MaterialService materialService;

    public EstoqueController(MaterialService materialService) {
        this.materialService = materialService;
    }

    @GetMapping
    public String listar(Model model) {

        model.addAttribute(
                "materiais",
                materialService.listarTodos()
        );

        return "estoque/index";
    }
}
