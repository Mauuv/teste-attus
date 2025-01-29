package com.mauricio.attus.parte_envolvida;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/parte-envolvida")
@RequiredArgsConstructor
public class ParteEnvolvidaController {

    private final ParteEnvolvidaService service;

    @PostMapping()
    public ResponseEntity<ParteEnvolvidaResponse> createParteEnvolvida(@RequestBody @Valid ParteEnvolvidaRequest request) {        
        return ResponseEntity.ok(service.createParteEnvolvida(request));
    }

    @PutMapping()
    public ResponseEntity<ParteEnvolvidaResponse> updateParteEnvolvida(@RequestBody @Valid ParteEnvolvidaRequest request) {        
        return ResponseEntity.ok(service.updateParteEnvolvida(request));
    }

    @GetMapping()
    public ResponseEntity<List<ParteEnvolvidaResponse>> findAll() {
        return ResponseEntity.ok(service.findAllPartesInteressadas());
    }
    
    @GetMapping("/exists/{parte-envolvida-id}")
    public ResponseEntity<Boolean> existsById(@PathVariable("parte-envolvida-id") Integer parteEnvolvidaId) {
        return ResponseEntity.ok(service.existsById(parteEnvolvidaId));
    }

    @PostMapping("/exists-all")
    public ResponseEntity<Boolean> existsByIdList(@RequestBody List<Integer> partesEnvolvidasIds) {
        return ResponseEntity.ok(service.existsByIdList(partesEnvolvidasIds));
    }
    
    @GetMapping("/{parte-envolvida-id}")
    public ResponseEntity<ParteEnvolvidaResponse> findById(@PathVariable("parte-envolvida-id") Integer parteEnvolvidaId) {
        return ResponseEntity.ok(service.findById(parteEnvolvidaId));
    }

    @PostMapping("/find-by-ids")
    public ResponseEntity<List<ParteEnvolvidaResponse>> findByIds(@RequestBody List<Integer> partesEnvolvidasIds) {
        return ResponseEntity.ok(service.findByIds(partesEnvolvidasIds));
    }

    @GetMapping("/find-by/{cpf-cnpj}")
    public ResponseEntity<ParteEnvolvidaResponse> findByCpfCnpj(@PathVariable("cpf-cnpj") String cpfCnpj) {
        return ResponseEntity.ok(service.findByCpfCnpj(cpfCnpj));
    }

    @DeleteMapping("/{parte-envolvida-id}")
    public ResponseEntity<Void> deleteById(@PathVariable("parte-envolvida-id") Integer parteEnvolvidaId) {
        return ResponseEntity.accepted().build();
    }
}
