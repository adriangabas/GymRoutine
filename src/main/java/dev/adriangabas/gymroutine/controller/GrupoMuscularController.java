package dev.adriangabas.gymroutine.controller;

import dev.adriangabas.gymroutine.entity.GrupoMuscular;
import dev.adriangabas.gymroutine.service.GrupoMuscularService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/grupos")
public class GrupoMuscularController {

    private final GrupoMuscularService grupoMuscularService;

    public GrupoMuscularController(GrupoMuscularService grupoMuscularService) {
        this.grupoMuscularService = grupoMuscularService;
    }

    @GetMapping
    public String obtenerTodos(Model model) {

        model.addAttribute(
                "grupos",
                grupoMuscularService.obtenerTodos()
        );

        return "grupos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {

        model.addAttribute(
                "grupo",
                new GrupoMuscular()
        );

        return "grupos/formulario";
    }

    @PostMapping
    public String guardar(
            @Valid @ModelAttribute("grupo") GrupoMuscular grupoMuscular,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "grupos/formulario";
        }

        grupoMuscularService.guardar(grupoMuscular);

        return "redirect:/grupos";
    }

    @GetMapping("/{id}")
    public String obtenerGrupoDetalle(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "grupo",
                grupoMuscularService.obtenerPorId(id)
        );

        return "grupos/detalle";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditar(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "grupo",
                grupoMuscularService.obtenerPorId(id)
        );

        return "grupos/editar";
    }

    @PostMapping("/{id}")
    public String actualizar(
            @PathVariable Long id,
            @Valid @ModelAttribute("grupo") GrupoMuscular grupoMuscular,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "grupos/editar";
        }

        grupoMuscular.setId(id);

        grupoMuscularService.guardar(grupoMuscular);

        return "redirect:/grupos";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {

        grupoMuscularService.eliminar(id);

        return "redirect:/grupos";
    }
}