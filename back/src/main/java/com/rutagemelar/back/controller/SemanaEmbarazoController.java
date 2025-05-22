package com.rutagemelar.back.controller;
import org.springframework.http.ResponseEntity;

import com.rutagemelar.back.model.SemanaEmbarazo;
import com.rutagemelar.back.repository.SemanaEmbarazoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.Optional;
@RestController
@RequestMapping
@CrossOrigin(origins ="*")



public class SemanaEmbarazoController {
    @Autowired
    private SemanaEmbarazoRepository semanaRepo;

    @GetMapping("/{numero}/{tipo}")
    public ResponseEntity<SemanaEmbarazo> obtenerPorNumeroYTipo(
            @PathVariable int numero,
            @PathVariable String tipo) {

        return semanaRepo.findByNumeroSemanaAndTipoEmbarazo(numero, tipo)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
