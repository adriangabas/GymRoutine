package dev.adriangabas.gymroutine.service;

import dev.adriangabas.gymroutine.entity.Ejercicio;
import dev.adriangabas.gymroutine.exception.EjercicioDuplicadoException;
import dev.adriangabas.gymroutine.exception.EjercicioNoEncontradoException;
import dev.adriangabas.gymroutine.repository.EjercicioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EjercicioServiceTest {

    private EjercicioRepository ejercicioRepository;
    private EjercicioService ejercicioService;

    @BeforeEach
    void setUp() {

        ejercicioRepository =
                Mockito.mock(EjercicioRepository.class);

        ejercicioService =
                new EjercicioService(ejercicioRepository);
    }

    @Test
    void guardarEjercicioNuevo() {

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setNombre("Press banca");

        Mockito.when(
                ejercicioRepository.existsByNombreIgnoreCase("Press banca")
        ).thenReturn(false);

        Mockito.when(
                ejercicioRepository.save(ejercicio)
        ).thenReturn(ejercicio);

        Ejercicio resultado =
                ejercicioService.guardar(ejercicio);

        assertEquals(ejercicio, resultado);

        Mockito.verify(
                ejercicioRepository
        ).save(ejercicio);
    }

    @Test
    void noDebeGuardarEjercicioDuplicado() {

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setNombre("Press banca");

        Mockito.when(
                ejercicioRepository.existsByNombreIgnoreCase("Press banca")
        ).thenReturn(true);

        assertThrows(
                EjercicioDuplicadoException.class,
                () -> ejercicioService.guardar(ejercicio)
        );

        Mockito.verify(
                ejercicioRepository,
                Mockito.never()
        ).save(ejercicio);
    }

    @Test
    void editarEjercicioManteniendoMismoNombre() {

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setId(1L);
        ejercicio.setNombre("Press banca");

        Mockito.when(
                ejercicioRepository.existsByNombreIgnoreCaseAndIdNot(
                        "Press banca",
                        1L
                )
        ).thenReturn(false);

        Mockito.when(
                ejercicioRepository.save(ejercicio)
        ).thenReturn(ejercicio);

        Ejercicio resultado =
                ejercicioService.guardar(ejercicio);

        assertEquals(ejercicio, resultado);

        Mockito.verify(
                ejercicioRepository
        ).save(ejercicio);
    }

    @Test
    void noDebeEditarEjercicioConNombreDuplicado() {

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setId(2L);
        ejercicio.setNombre("Press banca");

        Mockito.when(
                ejercicioRepository.existsByNombreIgnoreCaseAndIdNot(
                        "Press banca",
                        2L
                )
        ).thenReturn(true);

        assertThrows(
                EjercicioDuplicadoException.class,
                () -> ejercicioService.guardar(ejercicio)
        );

        Mockito.verify(
                ejercicioRepository,
                Mockito.never()
        ).save(ejercicio);
    }

    @Test
    void obtenerEjercicioPorIdExistente() {

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setId(1L);
        ejercicio.setNombre("Dominadas");

        Mockito.when(
                ejercicioRepository.findById(1L)
        ).thenReturn(Optional.of(ejercicio));

        Ejercicio resultado =
                ejercicioService.obtenerPorId(1L);

        assertEquals(ejercicio, resultado);
    }

    @Test
    void obtenerEjercicioPorIdInexistente() {

        Mockito.when(
                ejercicioRepository.findById(999L)
        ).thenReturn(Optional.empty());

        assertThrows(
                EjercicioNoEncontradoException.class,
                () -> ejercicioService.obtenerPorId(999L)
        );
    }

    @Test
    void obtenerTodosLosEjercicios() {

        Ejercicio ejercicio1 = new Ejercicio();
        ejercicio1.setNombre("Press banca");

        Ejercicio ejercicio2 = new Ejercicio();
        ejercicio2.setNombre("Dominadas");

        List<Ejercicio> ejercicios =
                List.of(ejercicio1, ejercicio2);

        Mockito.when(
                ejercicioRepository.findAll()
        ).thenReturn(ejercicios);

        List<Ejercicio> resultado =
                ejercicioService.obtenerTodos();

        assertEquals(ejercicios, resultado);
    }

    @Test
    void buscarEjerciciosPorNombre() {

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setNombre("Press banca");

        List<Ejercicio> ejercicios =
                List.of(ejercicio);

        Mockito.when(
                ejercicioRepository.findByNombreContainingIgnoreCase("press")
        ).thenReturn(ejercicios);

        List<Ejercicio> resultado =
                ejercicioService.buscarPorNombre("press");

        assertEquals(ejercicios, resultado);
    }

    @Test
    void buscarEjerciciosPorGrupoMuscular() {

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setNombre("Press banca");

        List<Ejercicio> ejercicios =
                List.of(ejercicio);

        Mockito.when(
                ejercicioRepository.findByMusculoPrincipalId(1L)
        ).thenReturn(ejercicios);

        List<Ejercicio> resultado =
                ejercicioService.buscarPorGrupoMuscular(1L);

        assertEquals(ejercicios, resultado);
    }

    @Test
    void buscarEjerciciosPorNombreYGrupoMuscular() {

        Ejercicio ejercicio = new Ejercicio();
        ejercicio.setNombre("Press banca");

        List<Ejercicio> ejercicios =
                List.of(ejercicio);

        Mockito.when(
                ejercicioRepository
                        .findByNombreContainingIgnoreCaseAndMusculoPrincipalId(
                                "press",
                                1L
                        )
        ).thenReturn(ejercicios);

        List<Ejercicio> resultado =
                ejercicioService.buscarPorNombreYGrupoMuscular(
                        "press",
                        1L
                );

        assertEquals(ejercicios, resultado);
    }

    @Test
    void eliminarEjercicio() {

        Long id = 1L;

        ejercicioService.eliminar(id);

        Mockito.verify(
                ejercicioRepository
        ).deleteById(id);
    }
}