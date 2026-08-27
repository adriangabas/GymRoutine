package dev.adriangabas.gymroutine.repository;

import dev.adriangabas.gymroutine.entity.GrupoMuscular;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrupoMuscularRepository
        extends JpaRepository<GrupoMuscular, Long> {

    boolean existsByNombreIgnoreCase(String nombre);

    boolean existsByNombreIgnoreCaseAndIdNot(String nombre, Long id);
}