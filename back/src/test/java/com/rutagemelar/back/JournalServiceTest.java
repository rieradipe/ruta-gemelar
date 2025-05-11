package com.rutagemelar.back;

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
}
