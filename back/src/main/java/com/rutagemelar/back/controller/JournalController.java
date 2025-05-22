package com.rutagemelar.back.controller;

import com.rutagemelar.back.model.JournalEntry;
import com.rutagemelar.back.service.JournalService;
import com.rutagemelar.back.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
@RequiredArgsConstructor
public class JournalController {

    private final JournalService journalService;
    private final JwtService jwtService;

    // 🔹 GET /journal → obtener entradas
    @GetMapping
    public ResponseEntity<List<JournalEntry>> obtenerEntradas(HttpServletRequest request) {
        String token = extraerToken(request);
        return ResponseEntity.ok(journalService.obtenerEntradas(token));
    }

    // 🔹 POST /journal → crear entrada
    @PostMapping
    public ResponseEntity<JournalEntry> crearEntrada(@RequestBody JournalEntry nuevaEntrada, HttpServletRequest request) {
        String token = extraerToken(request);
        JournalEntry entradaGuardada = journalService.crearEntrada(nuevaEntrada, token);
        return ResponseEntity.ok(entradaGuardada);
    }

    // 🔹 PUT /journal/{id} → editar entrada
    @PutMapping("/{id}")
    public ResponseEntity<JournalEntry> actualizarEntrada(@PathVariable Long id, @RequestBody JournalEntry nuevaEntrada, HttpServletRequest request) {
        String token = extraerToken(request);
        JournalEntry actualizada = journalService.actualizarEntrada(id, nuevaEntrada, token);
        return ResponseEntity.ok(actualizada);
    }

    // 🔹 DELETE /journal/{id} → eliminar entrada
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarEntrada(@PathVariable Long id, HttpServletRequest request) {
        String token = extraerToken(request);
        journalService.eliminarEntrada(id, token);
        return ResponseEntity.ok("Entrada eliminada correctamente");
    }

    // 🔸 Extrae el token del header
    private String extraerToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        return header.replace("Bearer ", "");
    }
}


