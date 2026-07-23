package dev.adriangabas.gymroutine.service;

import dev.adriangabas.gymroutine.entity.GrupoMuscular;
import dev.adriangabas.gymroutine.repository.GrupoMuscularRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoMuscularService {

    private final GrupoMuscularRepository repository;

    public GrupoMuscularService(GrupoMuscularRepository repository) {
        this.repository = repository;
    }

    public List<GrupoMuscular> obtenerTodos() {
        return repository.findAll();
    }

    public GrupoMuscular guardar(GrupoMuscular grupoMuscular) {
        return repository.save(grupoMuscular);
    }
}