package dev.adriangabas.gymroutine.service;

import dev.adriangabas.gymroutine.entity.Ejercicio;
import dev.adriangabas.gymroutine.repository.EjercicioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EjercicioService {

    private final EjercicioRepository repository;

    public EjercicioService (EjercicioRepository repository) {
        this.repository = repository;
    }

    public List<Ejercicio> obtenerTodos() {
        return repository.findAll();
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }


    public Ejercicio obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "No existe un ejercicio con el ID: " + id
                        )
                );
    }

    public Ejercicio guardar(Ejercicio ejercicio) {
        return repository.save(ejercicio);
    }
}
