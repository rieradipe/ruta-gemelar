package com.rutagemelar.back.service;

import com.rutagemelar.back.model.JournalEntry;
import com.rutagemelar.back.repository.JournalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.rutagemelar.back.model.User;
import com.rutagemelar.back.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class JournalService {
    private final UserRepository userRepository;
    private final JournalRepository journalRepository;
    private final JwtService jwtService;

    public JournalEntry crearEntrada(JournalEntry entry, String token) {
        Long userId = jwtService.getUserIdFromToken(token);
        User usuaria = new User();
        usuaria.setId(userId);
        entry.setUsuaria(usuaria);
        return journalRepository.save(entry);
    }


    public List<JournalEntry> obtenerEntradas(String token) {
        Long userId = jwtService.getUserIdFromToken(token);

        User usuaria = new User();
        usuaria.setId(userId);

        return journalRepository.findByUsuariaOrderByFechaDesc(usuaria);
    }

    public JournalEntry actualizarEntrada(Long id, JournalEntry nuevaEntrada, String token) {
        Long userId = jwtService.getUserIdFromToken(token);

        JournalEntry entradaExiste = journalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Entrada no encontrada"));

        if (!entradaExiste.getUsuaria().getId().equals(userId)) {
            throw new IllegalArgumentException("No tienes permiso para editar esta entrada");
        }
        entradaExiste.setFecha(nuevaEntrada.getFecha());
        entradaExiste.setEmocion(nuevaEntrada.getEmocion());
        entradaExiste.setNota(nuevaEntrada.getNota());

        return journalRepository.save(entradaExiste);
    }

    public void  eliminarEntrada(Long id, String token) {
        Long userId = jwtService.getUserIdFromToken(token);

        JournalEntry entrada = journalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Entrada no encontrada"));

        if (!entrada.getUsuaria().getId().equals(userId)) {
            throw new IllegalArgumentException("No tienes permiso para eliminar esta entrada");
        }
        journalRepository.delete(entrada);
    }
}
