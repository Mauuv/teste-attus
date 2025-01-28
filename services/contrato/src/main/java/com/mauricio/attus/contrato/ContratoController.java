package com.mauricio.attus.contrato;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/contrato")
@RequiredArgsConstructor
public class ContratoController {

    private final ContratoService service;

    @PostMapping()
    public ResponseEntity<ContratoResponse> createContrato(@RequestBody @Valid ContratoRequest request) {
        return ResponseEntity.ok(service.createContrato(request));
    }

    @PutMapping()
    public ResponseEntity<ContratoResponse> updateContrato(@RequestBody @Valid ContratoRequest request) {        
        return ResponseEntity.ok(service.updateContrato(request));
    }

    @GetMapping()
    public ResponseEntity<List<ContratoResponse>> findAll() {
        return ResponseEntity.ok(service.findAllContratos());
    }
    
    @GetMapping("/exists/{contrato-id}")
    public ResponseEntity<Boolean> existsById(@PathVariable("contrato-id") Integer contratoId) {
        return ResponseEntity.ok(service.existsById(contratoId));
    }
    
    @GetMapping("/{contrato-id}")
    public ResponseEntity<ContratoResponse> findById(@PathVariable("contrato-id") Integer contratoId) {
        return ResponseEntity.ok(service.findById(contratoId));
    }

    @GetMapping("/find")
    public ResponseEntity<List<ContratoResponse>> findByStatusAndDataCriacao(
        @RequestParam(name = "status_contrato") String statusContrato,
        @RequestParam(name = "data_criacao") String dataCriacao) {
        
        LocalDateTime data = dataCriacao != null ? LocalDateTime.parse(dataCriacao) : null;
        List<ContratoResponse> contratos = service.findByStatusOrDataCriacao(statusContrato, data);
        return ResponseEntity.ok(contratos);
    }
}
