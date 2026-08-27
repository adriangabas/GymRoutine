package dev.adriangabas.gymroutine.exception;

public class EjercicioDuplicadoException extends RuntimeException {
    public EjercicioDuplicadoException(String mensaje){
        super(mensaje);
    }
}