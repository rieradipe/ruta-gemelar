package com.rutagemelar.back.service;

import com.rutagemelar.back.model.JournalEntry;
import com.rutagemelar.back.repository.JournalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.rutagemelar.back.model.User;

@Service
@RequiredArgsConstructor
public class JournalService {
    private final JournalRepository journalRepository;
    private final JwtService jwtService;

    public JournalEntry crearEntrada(JournalEntry entrada, String token) {
        Long userId = jwtService.getUserIdFromToken(token);

        User usuaria = new User();
        usuaria.setId(userId);
        entrada.setUsuaria(usuaria);

        return journalRepository.save(entrada);
    }
}
