package dev.adriangabas.gymroutine.controller;

import dev.adriangabas.gymroutine.entity.Ejercicio;
import dev.adriangabas.gymroutine.entity.GrupoMuscular;
import dev.adriangabas.gymroutine.service.EjercicioService;
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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/ejercicios")
public class EjercicioController {

    private final EjercicioService ejercicioService;
    private final GrupoMuscularService grupoMuscularService;

    public EjercicioController(EjercicioService ejercicioService,
                               GrupoMuscularService grupoMuscularService) {
        this.ejercicioService = ejercicioService;
        this.grupoMuscularService = grupoMuscularService;
    }

    @GetMapping
    public String obtenerTodos(
        @RequestParam(required = false) String buscar,
        Model model) {

        if (buscar == null || buscar.isBlank()) {
            model.addAttribute(
                    "ejercicios",
                    ejercicioService.obtenerTodos()
                    );

        }else{
            model.addAttribute(
                    "ejercicios",
                    ejercicioService.buscarPorNombre(buscar)
            );


        }
        return "ejercicios/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {

        model.addAttribute(
                "ejercicio",
                new Ejercicio()
        );

        model.addAttribute(
                "gruposMusculares",
                grupoMuscularService.obtenerTodos()
        );

        return "ejercicios/nuevo";
    }

    @PostMapping
    public String guardar(
            @Valid @ModelAttribute("ejercicio") Ejercicio ejercicio,
            BindingResult bindingResult,
            @RequestParam(required = false) Long musculoPrincipalId,
            Model model) {

        if (musculoPrincipalId == null) {
            bindingResult.rejectValue(
                    "musculoPrincipal",
                    "NotNull",
                    "El grupo muscular principal es obligatorio"
            );
        } else {
            GrupoMuscular grupoMuscular =
                    grupoMuscularService.obtenerPorId(musculoPrincipalId);

            ejercicio.setMusculoPrincipal(grupoMuscular);
        }

        if (bindingResult.hasErrors()) {

            model.addAttribute(
                    "gruposMusculares",
                    grupoMuscularService.obtenerTodos()
            );

            return "ejercicios/nuevo";
        }

        ejercicioService.guardar(ejercicio);

        return "redirect:/ejercicios";
    }

    @PostMapping("/{id}")
    public String actualizar(
            @PathVariable Long id,
            @Valid @ModelAttribute("ejercicio") Ejercicio ejercicio,
            BindingResult bindingResult,
            @RequestParam(required = false) Long musculoPrincipalId,
            Model model) {

        if (musculoPrincipalId == null) {
            bindingResult.rejectValue(
                    "musculoPrincipal",
                    "NotNull",
                    "El grupo muscular principal es obligatorio"
            );
        } else {
            GrupoMuscular grupoMuscular =
                    grupoMuscularService.obtenerPorId(musculoPrincipalId);

            ejercicio.setMusculoPrincipal(grupoMuscular);
        }

        if (bindingResult.hasErrors()) {

            ejercicio.setId(id);

            model.addAttribute(
                    "gruposMusculares",
                    grupoMuscularService.obtenerTodos()
            );

            return "ejercicios/editar";
        }

        ejercicio.setId(id);

        ejercicioService.guardar(ejercicio);

        return "redirect:/ejercicios/" + id;
    }

    @GetMapping("/{id}")
    public String obtenerDetalle(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "ejercicio",
                ejercicioService.obtenerPorId(id)
        );

        return "ejercicios/detalle";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        model.addAttribute(
                "ejercicio",
                ejercicioService.obtenerPorId(id)
        );

        model.addAttribute(
                "gruposMusculares",
                grupoMuscularService.obtenerTodos()
        );

        return "ejercicios/editar";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminar(@PathVariable Long id) {

        ejercicioService.eliminar(id);

        return "redirect:/ejercicios";
    }
}