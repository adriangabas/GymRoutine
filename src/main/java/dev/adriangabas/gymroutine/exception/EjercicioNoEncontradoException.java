package dev.adriangabas.gymroutine.exception;

public class EjercicioNoEncontradoException extends RuntimeException{
    public EjercicioNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
