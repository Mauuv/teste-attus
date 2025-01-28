package com.mauricio.attus.parte_envolvida;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParteEnvolvidaContratoService {

    private final ParteEnvolvidaContratoRepository repository;
    private final ParteEnvolvidaContratoMapper mapper;
    private final ParteEnvolvidaClient parteEnvolvidaClient;

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

    public List<ParteEnvolvidaContratoResponse> findByParteEnvolvidaCpfCnpj(String cpfCnpj) {
        Integer parteEnvolvidaId = parteEnvolvidaClient.findIdByCpfCnpj(cpfCnpj);
        return repository.findAllByParteEnvolvidaId(parteEnvolvidaId)
            .stream()
            .map(mapper::toParteEnvolvidaContratoResponse)
            .toList();
    }

    public List<ParteEnvolvidaResponse> findAllPartesEnvolvidas(List<ParteEnvolvidaContratoRequest> partesEnvolvidas) {
        return parteEnvolvidaClient.findAllPartesEnvolvidas(partesEnvolvidas);
    }
}
