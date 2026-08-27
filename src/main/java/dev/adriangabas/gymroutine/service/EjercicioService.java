package dev.adriangabas.gymroutine.service;

import dev.adriangabas.gymroutine.entity.Ejercicio;
import dev.adriangabas.gymroutine.exception.EjercicioDuplicadoException;
import dev.adriangabas.gymroutine.exception.GrupoMuscularEnUsoException;
import dev.adriangabas.gymroutine.repository.EjercicioRepository;
import dev.adriangabas.gymroutine.repository.GrupoMuscularRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EjercicioService {

    private final EjercicioRepository repository;
    private final EjercicioRepository ejercicioRepository;

    public EjercicioService (EjercicioRepository repository, EjercicioRepository ejercicioRepository) {
        this.repository = repository;
        this.ejercicioRepository = ejercicioRepository;
    }

    public List<Ejercicio> buscarPorNombre(String nombre) {
        return repository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Ejercicio> buscarPorGrupoMuscular(Long id) {
        return repository.findByMusculoPrincipalId(id);
    }

    public List<Ejercicio> buscarPorNombreYGrupoMuscular(
            String nombre,
            Long id) {

        return repository
                .findByNombreContainingIgnoreCaseAndMusculoPrincipalId(
                        nombre,
                        id
                );

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

        if (ejercicio.getId() == null) {

            if (ejercicioRepository.existsByNombreIgnoreCase(
                    ejercicio.getNombre())) {

                throw new EjercicioDuplicadoException(
                        "Ya existe un ejercicio con ese nombre."
                );
            }

        } else {

            if (ejercicioRepository.existsByNombreIgnoreCaseAndIdNot(
                    ejercicio.getNombre(),
                    ejercicio.getId())) {

                throw new EjercicioDuplicadoException(
                        "Ya existe un ejercicio con ese nombre."
                );
            }
        }

        return ejercicioRepository.save(ejercicio);
    }
}
