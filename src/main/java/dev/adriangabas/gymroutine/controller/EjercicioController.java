package dev.adriangabas.gymroutine.controller;

import dev.adriangabas.gymroutine.entity.Ejercicio;
import dev.adriangabas.gymroutine.entity.GrupoMuscular;
import dev.adriangabas.gymroutine.service.EjercicioService;
import dev.adriangabas.gymroutine.service.GrupoMuscularService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String obtenerTodos(Model model) {

        model.addAttribute(
                "ejercicios",
                ejercicioService.obtenerTodos()
        );

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
            @ModelAttribute("ejercicio") Ejercicio ejercicio,
            @RequestParam Long musculoPrincipalId) {

        GrupoMuscular grupoMuscular =
                grupoMuscularService.obtenerPorId(musculoPrincipalId);

        ejercicio.setMusculoPrincipal(grupoMuscular);

        ejercicioService.guardar(ejercicio);

        return "redirect:/ejercicios";
    }

    @PostMapping("/{id}")
    public String actualizar (
            @PathVariable Long id,
            @ModelAttribute("ejercicio") Ejercicio ejercicio,
            @RequestParam Long musculoPrincipalId
    ){
        GrupoMuscular grupoMuscular =
                grupoMuscularService.obtenerPorId(musculoPrincipalId);

        ejercicio.setId(id);
        ejercicio.setMusculoPrincipal(grupoMuscular);

        ejercicioService.guardar(ejercicio);

        return "redirect:/ejercicios";
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