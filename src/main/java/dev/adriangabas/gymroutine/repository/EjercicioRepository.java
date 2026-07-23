package dev.adriangabas.gymroutine.repository;

import dev.adriangabas.gymroutine.entity.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EjercicioRepository
        extends JpaRepository<Ejercicio, Long> {
}
