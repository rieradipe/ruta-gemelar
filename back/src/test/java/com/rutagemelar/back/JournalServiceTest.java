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
}
