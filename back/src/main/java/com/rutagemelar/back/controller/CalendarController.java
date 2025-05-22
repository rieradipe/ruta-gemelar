package com.rutagemelar.back.controller;

import com.rutagemelar.back.model.CalendarEvent;
import com.rutagemelar.back.service.CalendarService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calendar")
@RequiredArgsConstructor
public class CalendarController {

    private final CalendarService calendarService;

    @GetMapping
    public ResponseEntity<List<CalendarEvent>> obtenerEventos(HttpServletRequest request) {
        String token = extraerToken(request);
        return ResponseEntity.ok(calendarService.obtenerEventos(token));
    }

    @PostMapping
    public ResponseEntity<CalendarEvent> crearEvento(@RequestBody CalendarEvent evento, HttpServletRequest request) {
        String token = extraerToken(request);
        return ResponseEntity.ok(calendarService.crearEvento(evento, token));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CalendarEvent> actualizarEvento(@PathVariable Long id, @RequestBody CalendarEvent evento, HttpServletRequest request) {
        String token = extraerToken(request);
        return ResponseEntity.ok(calendarService.actualizarEvento(id, evento, token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEvento(@PathVariable Long id, HttpServletRequest request) {
        String token = extraerToken(request);
        calendarService.eliminarEvento(id, token);
        return ResponseEntity.ok("Evento eliminado correctamente");
    }

    private String extraerToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return header.replace("Bearer ", "");
    }
}

