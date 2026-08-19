package dev.adriangabas.gymroutine.repository;

import dev.adriangabas.gymroutine.entity.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EjercicioRepository
        extends JpaRepository<Ejercicio, Long> {

    boolean existsByMusculoPrincipalId(Long id);

    List<Ejercicio> findByNombreContainingIgnoreCase(String nombre);
}
