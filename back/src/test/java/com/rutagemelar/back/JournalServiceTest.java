package com.rutagemelar.back;

import java.util.List;
import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.rutagemelar.back.model.JournalEntry;
import com.rutagemelar.back.model.User;
import com.rutagemelar.back.repository.JournalRepository;
import com.rutagemelar.back.service.JournalService;
import com.rutagemelar.back.service.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




import java.time.LocalDate;
import java.util.Optional;

public class JournalServiceTest {

    private JournalRepository journalRepository;
    private JwtService jwtService;
    private JournalService journalService;

    @BeforeEach
    void setUp() {
        journalRepository = mock(JournalRepository.class);
        jwtService = mock(JwtService.class);
        journalService = new JournalService(journalRepository, jwtService);
    }

    @Test
    void debeCrearEntradaDiario() {
        //arrenge
        User usuaria = User.builder()
                .id(1L)
                .email("ana@gmail.com")
                .build();

        JournalEntry entrada = JournalEntry.builder()
                .fecha(LocalDate.of(2025, 05, 11))
                .emocion("esperanzada")
                .nota("Hoy me siento mas fuerte")
                .usuaria(usuaria)
                .build();

        //con JWT simulamos que  tiene la ID usuaria
        when(jwtService.getUserIdFromToken("Bearer token"))
                .thenReturn(1L);

        //simulams que save devuelve la mism entrada
        when(journalRepository.save(any(JournalEntry.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        //Act
        JournalEntry resultado = journalService.crearEntrada(entrada, "Bearer Token");

        //Assert
        assertNotNull(resultado);
        assertEquals("esperanzada", resultado.getEmocion());
        assertEquals("Hoy me siento mas fuerte", resultado.getNota());
        assertEquals(usuaria.getId(), resultado.getUsuaria().getId());

        //verificamos que se llamo a .save exactamente una vez
        verify(journalRepository, times(1)).save(any(JournalEntry.class));
    }

    @Test
    void debeObtenerEntradaDeUsuaria() {
        //arrange
        User usuaria = User.builder()
                .id(1L)
                .email("ana@gmail.com")
                .build();

        JournalEntry entrada1 = JournalEntry.builder()
                .fecha(LocalDate.of(2025, 5, 11))
                .emocion("tranquila")
                .nota("Día estable")
                .usuaria(usuaria)
                .build();

        JournalEntry entrada2 = JournalEntry.builder()
                .fecha(LocalDate.of(2025, 05, 10))
                .emocion("agotada")
                .nota("mucho cansancio")
                .usuaria(usuaria)
                .build();

        List<JournalEntry> entradas = List.of(entrada1, entrada2);


        when(jwtService.getUserIdFromToken("Bearer token"))
                .thenReturn(1L);

        when(journalRepository.findByUsuariaOrderByFechaDesc(usuaria))
                .thenReturn(entradas);

        //act
        List<JournalEntry> resultado = journalService.obtenerEntradas("Bearer token");

        //assert
        assertEquals(2, resultado.size());
        assertEquals("tranquila", resultado.get(0).getEmocion());
        assertEquals("agotada", resultado.get(1).getEmocion());

        verify(journalRepository, times(1)).findByUsuariaOrderByFechaDesc(any(User.class));
    }

    @Test
    void debeActualizarEntradaPropia() {
        //arrange
        User usuaria = User.builder()
                .id(1L)
                .email("ana@gmail.com")
                .build();

        JournalEntry entradaExiste = JournalEntry.builder()
                .id(22L)
                .fecha(LocalDate.of(2025, 05, 10))
                .emocion("agotada")
                .nota("Dia muy duro")
                .usuaria(usuaria)
                .build();

        JournalEntry nuevaEntrada = JournalEntry.builder()
                .fecha(LocalDate.of(2025, 5, 11))
                .emocion("mas tranquila")
                .nota("mejore un poco")
                .build();

        //simulamos que el token pertenene a la usuaria 1
        when(jwtService.getUserIdFromToken("Bearer token"))
                .thenReturn(1L);

        //simulamos que encontramos la entrada y le pertenece
        when(journalRepository.findById(22L))
                .thenReturn(Optional.of(entradaExiste));

        //similamos que guardamos la entrada actualizda
        when(journalRepository.save(any(JournalEntry.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        //act
        JournalEntry resultado = journalService.actualizarEntrada(22L, nuevaEntrada, "Bearer token");
        //assert
        assertEquals((LocalDate.of(2025, 5, 11)), resultado.getFecha());
        assertEquals("mas tranquila", resultado.getEmocion());
        assertEquals("mejoré un poco", resultado.getNota());
        assertEquals(1L, resultado.getUsuaria().getId());

        verify(journalRepository, times(1)).save(any(JournalEntry.class));
    }

    @Test
    void debeEliminarEntradaPropia() {
        //arrange
        User usuaria = User.builder()
                .id(1L)
                .email("ana@gmail.com")
                .build();

        JournalEntry entradaExiste = JournalEntry.builder()
                .id(22L)
                .fecha(LocalDate.of(2025, 5, 10))
                .emocion("agotada")
                .nota("Día duro")
                .usuaria(usuaria)
                .build();

        when(jwtService.getUserIdFromToken("Bearer token"))
                .thenReturn(1L);
        when(journalRepository.findById(22L))
                .thenReturn(Optional.of(entradaExiste));

        //act
        journalService.eliminarEntrada(22L, "Bearer token");
        //assert
        verify(journalRepository, times(1)).delete(entradaExiste);
    }
    void debeLanzarErrorSiEntradaNoExiste() {
        when(jwtService.getUserIdFromToken("Bearer tpken"));

        //simulamos que la entrada no existe
        when(journalRepository.findById(99L))
                .thenReturn(Optional.empty());

        Exception ex = assertThrows(IllegalArgumentException.class, () -> {
            journalService.eliminarEntrada(99L, "Bearer token");
        });
        assertEquals("Entrada no encontrada", ex.getMessage());
    }
}

