package dev.adriangabas.gymroutine.controller;

import dev.adriangabas.gymroutine.service.GrupoMuscularService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/grupos")
public class GrupoMuscularController {

    private final GrupoMuscularService service;

    public GrupoMuscularController(GrupoMuscularService service) {
        this.service = service;
    }

    @GetMapping
    public String obtenerTodos(Model model) {

        model.addAttribute("grupos", service.obtenerTodos());

        return "grupos/lista";
    }
}