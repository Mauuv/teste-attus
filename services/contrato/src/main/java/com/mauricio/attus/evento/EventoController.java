package com.mauricio.attus.evento;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/evento")
@RequiredArgsConstructor
public class EventoController {

    private final EventoService service;

    @GetMapping("/contrato/{contrato-id}")
    public ResponseEntity<List<EventoResponse>> findByContratoId(@PathVariable("contrato-id") Integer contratoId) {
        return ResponseEntity.ok(service.findByContratoId(contratoId));
    }

    @PostMapping("/")
    public ResponseEntity<EventoResponse> createEvento(@RequestBody @Valid EventoRequest request) {
        return ResponseEntity.ok(service.saveEvento(request));
    }

    @GetMapping("/")
    public ResponseEntity<List<EventoResponse>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PutMapping("/")
    public ResponseEntity<EventoResponse> updateEvento(@RequestBody @Valid EventoRequest request) {
        return ResponseEntity.ok(service.updateEvento(request));
    }
    
    @DeleteMapping("/{evento-id}")
    public ResponseEntity<Void> deleteEvento(@PathVariable("evento-id") Integer eventoId) {
        service.deleteEvento(eventoId);
        return ResponseEntity.ok().build();
    }
}
