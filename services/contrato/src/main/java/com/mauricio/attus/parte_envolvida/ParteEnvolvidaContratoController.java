package com.mauricio.attus.parte_envolvida;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/parte-envolvida-contrato")
@RequiredArgsConstructor
public class ParteEnvolvidaContratoController {

    private final ParteEnvolvidaContratoService service;

    @GetMapping("/contrato/{contrato-id}")
    public ResponseEntity<List<ParteEnvolvidaContratoResponse>> findByContratoId(@PathVariable("contrato-id") Integer contratoId) {
        return ResponseEntity.ok(service.findByContratoId(contratoId));
    }

    @GetMapping("/parte-envolvida/{cpf-cnpj}")
    public ResponseEntity<List<ParteEnvolvidaContratoResponse>> findByParteEnvolvidaCpfCnpj(@PathVariable("cpf-cnpj") String cpfCnpj) {
        return ResponseEntity.ok(service.findByParteEnvolvidaCpfCnpj(cpfCnpj));
    }
    
}
