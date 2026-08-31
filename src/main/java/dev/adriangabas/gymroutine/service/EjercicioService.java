package dev.adriangabas.gymroutine.service;

import dev.adriangabas.gymroutine.entity.Ejercicio;
import dev.adriangabas.gymroutine.exception.EjercicioDuplicadoException;
import dev.adriangabas.gymroutine.exception.EjercicioNoEncontradoException;
import dev.adriangabas.gymroutine.repository.EjercicioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EjercicioService {

    private final EjercicioRepository ejercicioRepository;

    public EjercicioService(EjercicioRepository ejercicioRepository) {
        this.ejercicioRepository = ejercicioRepository;
    }

    public List<Ejercicio> buscarPorNombre(String nombre) {
        return ejercicioRepository.findByNombreContainingIgnoreCase(nombre);
    }

    public List<Ejercicio> buscarPorGrupoMuscular(Long id) {
        return ejercicioRepository.findByMusculoPrincipalId(id);
    }

    public List<Ejercicio> buscarPorNombreYGrupoMuscular(
            String nombre,
            Long id) {

        return ejercicioRepository
                .findByNombreContainingIgnoreCaseAndMusculoPrincipalId(
                        nombre,
                        id
                );
    }

    public List<Ejercicio> obtenerTodos() {
        return ejercicioRepository.findAll();
    }

    public Ejercicio obtenerPorId(Long id) {
        return ejercicioRepository.findById(id)
                .orElseThrow(() ->
                        new EjercicioNoEncontradoException(
                                "No existe un ejercicio con el ID: " + id
                        )
                );
    }

    public Ejercicio guardar(Ejercicio ejercicio) {

        boolean duplicado;

        if (ejercicio.getId() == null) {

            duplicado =
                    ejercicioRepository.existsByNombreIgnoreCase(
                            ejercicio.getNombre()
                    );

        } else {

            duplicado =
                    ejercicioRepository.existsByNombreIgnoreCaseAndIdNot(
                            ejercicio.getNombre(),
                            ejercicio.getId()
                    );
        }

        if (duplicado) {
            throw new EjercicioDuplicadoException(
                    "Ya existe un ejercicio con ese nombre."
            );
        }

        return ejercicioRepository.save(ejercicio);
    }

    public void eliminar(Long id) {
        ejercicioRepository.deleteById(id);
    }
}