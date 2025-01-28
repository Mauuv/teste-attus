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

    public ParteEnvolvida createParteEnvolvida(ParteEnvolvidaRequest request) {
        ParteEnvolvida parteEnvolvida = repository.save(mapper.toParteEnvolvida(request));
        return parteEnvolvida;
    }

    public ParteEnvolvida updateParteEnvolvida(ParteEnvolvidaRequest request) {
        ParteEnvolvida parteEnvolvida = repository.findById(request.id())
            .orElseThrow(() -> new ParteEnvolvidaNotFoundException("Parte envolvida de id " + request.id() + " não encontrada"));
        mergeParteEnvolvida(parteEnvolvida, request);
                return repository.save(parteEnvolvida);
            }
        
    private void mergeParteEnvolvida(ParteEnvolvida parteEnvolvida, ParteEnvolvidaRequest request) {
        if (StringUtils.isNotBlank(request.nome())) {
            parteEnvolvida.setNome(request.nome());
        }
        if (StringUtils.isNotBlank(request.cpfCnpj())) {
            parteEnvolvida.setCpfCnpj(request.cpfCnpj());
        }
        if (request.tipo() != null) {
            parteEnvolvida.setTipo(request.tipo());
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

    public ParteEnvolvidaResponse findById(Integer parteEnvolvidaId) {
      return repository.findById(parteEnvolvidaId).map(mapper::toParteEnvolvidaResponse)
          .orElseThrow(() -> new ParteEnvolvidaNotFoundException("Parte envolvida de id " + parteEnvolvidaId + " não encontrada"));
    }

    public void deleteParteEnvolvida(Integer parteEnvolvidaId) {
        repository.deleteById(parteEnvolvidaId);
    }
}
