package com.mauricio.attus.parte_envolvida;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.mauricio.attus.exception.ParteEnvolvidaNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParteEnvolvidaService {

    private final ParteEnvolvidaRepository repository;
    private final ParteEnvolvidaMapper mapper;

    public ParteEnvolvidaResponse createParteEnvolvida(ParteEnvolvidaRequest request) {
        ParteEnvolvida parteEnvolvida = repository.save(mapper.toParteEnvolvida(request));
        return mapper.toParteEnvolvidaResponse(parteEnvolvida);
    }

    public ParteEnvolvidaResponse updateParteEnvolvida(ParteEnvolvidaRequest request) {
        ParteEnvolvida parteEnvolvida = repository.findById(request.id())
            .orElseThrow(() -> new ParteEnvolvidaNotFoundException("Parte envolvida de id " + request.id() + " não encontrada"));
        mergeParteEnvolvida(parteEnvolvida, request);
        parteEnvolvida = repository.save(parteEnvolvida);
        return mapper.toParteEnvolvidaResponse(parteEnvolvida);
    }
        
    private void mergeParteEnvolvida(ParteEnvolvida parteEnvolvida, ParteEnvolvidaRequest request) {
        if (StringUtils.isNotBlank(request.nome())) {
            parteEnvolvida.setNome(request.nome());
        }
        if (StringUtils.isNotBlank(request.cpfCnpj())) {
            parteEnvolvida.setCpfCnpj(request.cpfCnpj());
        }
        if (request.contato() != null) {
            parteEnvolvida.setContato(request.contato());
        }
    }

    public List<ParteEnvolvidaResponse> findAllPartesInteressadas() {
        return repository.findAll()
                .stream()
                .map(mapper::toParteEnvolvidaResponse)
                .toList();
    }

    public Boolean existsById(Integer parteEnvolvidaId) {
        return repository.findById(parteEnvolvidaId).isPresent();
    }

    public Boolean existsByIdList(List<Integer> partesEnvolvidasIds) {
        long count = repository.countByIds(partesEnvolvidasIds);
        return count == partesEnvolvidasIds.size();
    }

    public ParteEnvolvidaResponse findById(Integer parteEnvolvidaId) {
      return repository.findById(parteEnvolvidaId).map(mapper::toParteEnvolvidaResponse)
          .orElseThrow(() -> new ParteEnvolvidaNotFoundException("Parte envolvida de id " + parteEnvolvidaId + " não encontrada"));
    }

    public List<ParteEnvolvidaResponse> findByIds(List<Integer> partesEnvolvidasIds) {
        if (partesEnvolvidasIds == null || partesEnvolvidasIds.isEmpty()) {
            throw new IllegalArgumentException("The list of IDs cannot be null or empty");
        }

        return repository.findAllById(partesEnvolvidasIds)
                .stream()
                .map(mapper::toParteEnvolvidaResponse)
                .toList();
    }

    public void deleteParteEnvolvida(Integer parteEnvolvidaId) {
        repository.deleteById(parteEnvolvidaId);
    }

    public ParteEnvolvidaResponse findByCpfCnpj(String cpfCnpj) {
        var parteEnvolvida = repository.findByCpfCnpj(cpfCnpj)
            .orElseThrow(() -> new ParteEnvolvidaNotFoundException("Parte envolvida de CPF/CNPJ " + cpfCnpj + " não encontrada"));

        return mapper.toParteEnvolvidaResponse(parteEnvolvida);
    }
}
