package dev.adriangabas.gymroutine.repository;

import dev.adriangabas.gymroutine.entity.Ejercicio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EjercicioRepository
        extends JpaRepository<Ejercicio, Long> {

    boolean existsByMusculoPrincipalId(Long id);

    boolean existsByNombreIgnoreCase(String nombre);

    boolean existsByNombreIgnoreCaseAndIdNot(String nombre, Long id);

    List<Ejercicio> findByNombreContainingIgnoreCase(String nombre);

    List<Ejercicio> findByMusculoPrincipalId(Long id);

    List<Ejercicio> findByNombreContainingIgnoreCaseAndMusculoPrincipalId(String nombre, Long id);
}
