package dev.adriangabas.gymroutine.service;

import dev.adriangabas.gymroutine.entity.GrupoMuscular;
import dev.adriangabas.gymroutine.exception.GrupoMuscularDuplicadoException;
import dev.adriangabas.gymroutine.exception.GrupoMuscularEnUsoException;
import dev.adriangabas.gymroutine.exception.GrupoMuscularNoEncontradoException;
import dev.adriangabas.gymroutine.repository.EjercicioRepository;
import dev.adriangabas.gymroutine.repository.GrupoMuscularRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GrupoMuscularServiceTest {

    private GrupoMuscularRepository grupoMuscularRepository;
    private EjercicioRepository ejercicioRepository;
    private GrupoMuscularService grupoMuscularService;

    @BeforeEach
    void setUp() {

        grupoMuscularRepository =
                Mockito.mock(GrupoMuscularRepository.class);

        ejercicioRepository =
                Mockito.mock(EjercicioRepository.class);

        grupoMuscularService =
                new GrupoMuscularService(
                        grupoMuscularRepository,
                        ejercicioRepository
                );
    }

    @Test
    void guardarGrupoMuscularNuevo() {

        GrupoMuscular grupo = new GrupoMuscular();
        grupo.setNombre("Pecho");

        Mockito.when(
                grupoMuscularRepository.existsByNombreIgnoreCase("Pecho")
        ).thenReturn(false);

        Mockito.when(
                grupoMuscularRepository.save(grupo)
        ).thenReturn(grupo);

        GrupoMuscular resultado =
                grupoMuscularService.guardar(grupo);

        assertEquals(grupo, resultado);
    }

    @Test
    void noDebeGuardarGrupoMuscularDuplicado() {

        GrupoMuscular grupo = new GrupoMuscular();
        grupo.setNombre("Pecho");

        Mockito.when(
                grupoMuscularRepository.existsByNombreIgnoreCase("Pecho")
        ).thenReturn(true);

        assertThrows(
                GrupoMuscularDuplicadoException.class,
                () -> grupoMuscularService.guardar(grupo)
        );
        Mockito.verify(
                grupoMuscularRepository,
                Mockito.never()
        ).save(grupo);
    }

    @Test
    void editarGrupoManteniendoMismoNombre() {

        GrupoMuscular grupo = new GrupoMuscular();
        grupo.setId(1L);
        grupo.setNombre("Pecho");

        Mockito.when(
                grupoMuscularRepository.existsByNombreIgnoreCaseAndIdNot(
                        "Pecho",
                        1L
                )
        ).thenReturn(false);
        Mockito.when(
                grupoMuscularRepository.save(grupo)
        ).thenReturn(grupo);

    }

    @Test
    void noDebeEditarGrupoConNombreDuplicado() {

        GrupoMuscular grupo = new GrupoMuscular();
        grupo.setId(2L);
        grupo.setNombre("Pecho");

        Mockito.when(
                grupoMuscularRepository.existsByNombreIgnoreCaseAndIdNot(
                        "Pecho",
                        2L
                )
        ).thenReturn(true);

        assertThrows(
                GrupoMuscularDuplicadoException.class,
                () -> grupoMuscularService.guardar(grupo)
        );

        Mockito.verify(
                grupoMuscularRepository,
                Mockito.never()
        ).save(grupo);
    }

    @Test
    void obtenerGrupoPorIdExistente() {

        GrupoMuscular grupo = new GrupoMuscular();
        grupo.setId(1L);
        grupo.setNombre("Pecho");

        Mockito.when(
                grupoMuscularRepository.findById(1L)
        ).thenReturn(java.util.Optional.of(grupo));

        GrupoMuscular resultado =
                grupoMuscularService.obtenerPorId(1L);

        assertEquals(grupo, resultado);
    }

    @Test
    void obtenerGrupoPorIdInexistente() {

        Mockito.when(
                grupoMuscularRepository.findById(999L)
        ).thenReturn(java.util.Optional.empty());

        assertThrows(
                GrupoMuscularNoEncontradoException.class,
                () -> grupoMuscularService.obtenerPorId(999L)
        );
    }

    @Test
    void noDebeEliminarGrupoMuscularEnUso() {

        Long id = 1L;

        Mockito.when(
                ejercicioRepository.existsByMusculoPrincipalId(id)
        ).thenReturn(true);

        assertThrows(
                GrupoMuscularEnUsoException.class,
                () -> grupoMuscularService.eliminar(id)
        );

        Mockito.verify(
                grupoMuscularRepository,
                Mockito.never()
        ).deleteById(id);
    }

    @Test
    void debeEliminarGrupoMuscularSinEjerciciosAsociados() {

        Long id = 1L;

        Mockito.when(
                ejercicioRepository.existsByMusculoPrincipalId(id)
        ).thenReturn(false);

        grupoMuscularService.eliminar(id);

        Mockito.verify(
                grupoMuscularRepository
        ).deleteById(id);
    }

}