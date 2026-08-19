package dev.adriangabas.gymroutine.exception;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GrupoMuscularEnUsoException.class)
    public String manejarGrupoMuscularEnUso(
            GrupoMuscularEnUsoException exception,
            Model model) {

        model.addAttribute(
                "mensajeError",
                exception.getMessage()
        );

        return "error/grupo-muscular-en-uso";
    }
}