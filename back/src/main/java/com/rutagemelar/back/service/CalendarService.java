package com.rutagemelar.back.service;

import com.rutagemelar.back.model.CalendarEvent;
import com.rutagemelar.back.model.User;
import com.rutagemelar.back.repository.CalendarEventRepository;
import com.rutagemelar.back.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalendarService {

    private final CalendarEventRepository calendarRepo;
    private final UserRepository userRepo;
    private final JwtService jwtService;

    public CalendarEvent crearEvento(CalendarEvent evento, String token) {
        Long userId = jwtService.getUserIdFromToken(token);
        User usuaria = new User();
        usuaria.setId(userId);
        evento.setUsuaria(usuaria);
        return calendarRepo.save(evento);
    }

    public List<CalendarEvent> obtenerEventos(String token) {
        Long userId = jwtService.getUserIdFromToken(token);
        User usuaria = new User();
        usuaria.setId(userId);
        return calendarRepo.findByUsuariaOrderByFechaAsc(usuaria);
    }

    public CalendarEvent actualizarEvento(Long id, CalendarEvent nuevo, String token) {
        Long userId = jwtService.getUserIdFromToken(token);
        CalendarEvent evento = calendarRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado"));
        if (!evento.getUsuaria().getId().equals(userId)) {
            throw new SecurityException("No tienes permiso para editar este evento");
        }

        evento.setTitulo(nuevo.getTitulo());
        evento.setDescripcion(nuevo.getDescripcion());
        evento.setFecha(nuevo.getFecha());

        return calendarRepo.save(evento);
    }

    public void eliminarEvento(Long id, String token) {
        Long userId = jwtService.getUserIdFromToken(token);
        CalendarEvent evento = calendarRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado"));
        if (!evento.getUsuaria().getId().equals(userId)) {
            throw new SecurityException("No tienes permiso para eliminar este evento");
        }
        calendarRepo.delete(evento);
    }
}

