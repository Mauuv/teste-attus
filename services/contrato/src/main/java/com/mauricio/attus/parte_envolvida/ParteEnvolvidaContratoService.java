package com.mauricio.attus.parte_envolvida;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParteEnvolvidaContratoService {

    public ParteEnvolvidaContratoRepository repository;
    public ParteEnvolvidaContratoMapper mapper;

    public Integer saveParteEnvolvida(ParteEnvolvidaContratoRequest request) {
        var parteEnvolvidaContrato = mapper.toParteEnvolvidaContrato(request);
        return repository.save(parteEnvolvidaContrato).getId();
    }

    public List<ParteEnvolvidaContratoResponse> findByContratoId(Integer contratoId) {
        return repository.findAllByContratoId(contratoId)
            .stream()
            .map(mapper::toParteEnvolvidaContratoResponse)
            .toList();
    }
}
