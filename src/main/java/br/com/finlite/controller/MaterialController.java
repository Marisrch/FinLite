
package br.com.finlite.controller;

import br.com.finlite.model.Empreendedor;
import br.com.finlite.model.Material;
import br.com.finlite.service.EmpreendedorService;
import br.com.finlite.service.MaterialService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
            @ModelAttribute("material") Material material,
            BindingResult resultado,
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

            boolean edicao=  material.getIdMaterial()!= null;
            materialService.salvar(material);

            redirectAttributes.addFlashAttribute(
                    "sucesso",
                    "Material cadastrado com sucesso!"
            );

            return "redirect:/estoque";

        } catch (IllegalArgumentException e) {

            String mensagem = e.getMessage();

            if (mensagem != null) {
                if (mensagem.contains("nome")) {
                    resultado.rejectValue(
                            "nomeMaterial", "material.nome", mensagem
                    );
                } else if (mensagem.contains("categoria")) {
                    resultado.rejectValue(
                            "categoriaMaterial", "material.categoria", mensagem
                    );
                } else if (mensagem.contains("unidade de medida")) {
                    resultado.rejectValue(
                            "unidadeMedidaMaterial", "material.unidade", mensagem
                    );
                } else if (mensagem.contains("quantidade mínima")) {
                    resultado.rejectValue(
                            "quantidadeMinimaMaterial", "material.quantidade", mensagem
                    );
                } else if (mensagem.contains("custo unitário")) {
                    resultado.rejectValue(
                            "custoUnitarioMaterial", "material.custo", mensagem
                    );
                } else {
                    model.addAttribute("erro", mensagem);
                }
            }

            model.addAttribute("material", material);
            return prepararFormulario(model);
        }


    }

    @GetMapping("/editar/{id}")

    public String editar(@PathVariable Integer id, Model model,   RedirectAttributes redirectAttributes){
        Material material = materialService.buscarPorId(id).orElse(null);

        if (material == null) {
            redirectAttributes.addFlashAttribute("erro", "Material não encontrado.");
            return "redirect:/estoque";
        }

        model.addAttribute("material", material);
        model.addAttribute("idEmpreendedor",
                material.getEmpreendedor().getIdEmpreendedor());

        return "materiais/formulario";

    }


    @PostMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id,
                          RedirectAttributes redirectAttributes) {
        try {
            materialService.excluir(id);
            redirectAttributes.addFlashAttribute("sucesso",
                    "Material excluído com sucesso!");
        } catch (IllegalStateException | EntityNotFoundException e) {
            redirectAttributes.addFlashAttribute("erro", e.getMessage());
        }
        return "redirect:/estoque";
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
