package com.mauricio.attus.evento;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/evento")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService service;

    @GetMapping("/contrato/{contrato-id}")
    public ResponseEntity<List<EventoResponse>> findByContratoId(@PathVariable("contrato-id") Integer contratoId) {
        return ResponseEntity.ok(service.findByContratoId(contratoId));
    }
    
}
