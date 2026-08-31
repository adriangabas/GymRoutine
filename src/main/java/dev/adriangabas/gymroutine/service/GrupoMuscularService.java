package dev.adriangabas.gymroutine.service;

import dev.adriangabas.gymroutine.entity.GrupoMuscular;
import dev.adriangabas.gymroutine.exception.GrupoMuscularDuplicadoException;
import dev.adriangabas.gymroutine.exception.GrupoMuscularEnUsoException;
import dev.adriangabas.gymroutine.exception.GrupoMuscularNoEncontradoException;
import dev.adriangabas.gymroutine.repository.EjercicioRepository;
import dev.adriangabas.gymroutine.repository.GrupoMuscularRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoMuscularService {

    private final GrupoMuscularRepository grupoMuscularRepository;
    private final EjercicioRepository ejercicioRepository;

    public GrupoMuscularService(
            GrupoMuscularRepository grupoMuscularRepository,
            EjercicioRepository ejercicioRepository) {

        this.grupoMuscularRepository = grupoMuscularRepository;
        this.ejercicioRepository = ejercicioRepository;
    }

    public List<GrupoMuscular> obtenerTodos() {
        return grupoMuscularRepository.findAll();
    }

    public GrupoMuscular obtenerPorId(Long id) {
        return grupoMuscularRepository.findById(id)
                .orElseThrow(() ->
                        new GrupoMuscularNoEncontradoException(
                                "No existe un grupo muscular con el ID: " + id
                        )
                );
    }

    public GrupoMuscular guardar(GrupoMuscular grupoMuscular) {

        boolean duplicado;

        if (grupoMuscular.getId() == null) {

            duplicado =
                    grupoMuscularRepository.existsByNombreIgnoreCase(
                            grupoMuscular.getNombre()
                    );

        } else {

            duplicado =
                    grupoMuscularRepository.existsByNombreIgnoreCaseAndIdNot(
                            grupoMuscular.getNombre(),
                            grupoMuscular.getId()
                    );
        }

        if (duplicado) {
            throw new GrupoMuscularDuplicadoException(
                    "Ya existe un grupo muscular con ese nombre."
            );
        }

        return grupoMuscularRepository.save(grupoMuscular);
    }

    public void eliminar(Long id) {

        if (ejercicioRepository.existsByMusculoPrincipalId(id)) {

            throw new GrupoMuscularEnUsoException(
                    "No se puede eliminar este grupo muscular porque tiene ejercicios asociados."
            );
        }

        grupoMuscularRepository.deleteById(id);
    }
}