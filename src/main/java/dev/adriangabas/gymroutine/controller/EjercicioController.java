package dev.adriangabas.gymroutine.controller;

import dev.adriangabas.gymroutine.service.EjercicioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ejercicios")
public class EjercicioController {

    private final EjercicioService service;

    public EjercicioController(EjercicioService service) {
        this.service = service;
    }

    @GetMapping
    public String obtenerTodos(Model model) {
        model.addAttribute("ejercicios", service.obtenerTodos());
            return "ejercicios/lista";
    }

    @GetMapping("/{id}")
    public String obtenerDetalle(@PathVariable Long id, Model model) {
        model.addAttribute("ejercicio", service.obtenerPorId(id));

        return "ejercicios/detalle";
    }
}
