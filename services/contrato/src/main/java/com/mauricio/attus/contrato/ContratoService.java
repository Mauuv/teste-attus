package com.mauricio.attus.contrato;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mauricio.attus.evento.EventoRequest;
import com.mauricio.attus.evento.EventoService;
import com.mauricio.attus.exception.ContratoNotFoundException;
import com.mauricio.attus.parte_envolvida.ParteEnvolvidaContratoRequest;
import com.mauricio.attus.parte_envolvida.ParteEnvolvidaContratoService;
import com.mauricio.attus.parte_envolvida.ParteEnvolvidaResponse;
import com.mauricio.attus.parte_envolvida.TIPO_PARTE_ENVOLVIDA;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContratoService {

    private final ContratoRepository repository;
    private final ContratoMapper mapper;
    private final ParteEnvolvidaContratoService parteEnvolvidaContratoService;
    private final EventoService eventoService;

    public ContratoResponse createContrato(ContratoRequest request) {
        Map<Integer, TIPO_PARTE_ENVOLVIDA> tipoPartesEnvolvidas = request.partesEnvolvidas().stream()
            .collect(Collectors.toMap(
                ParteEnvolvidaContratoRequest::id,
                ParteEnvolvidaContratoRequest::tipoParteEnvolvida
            ));

        List<ParteEnvolvidaResponse> partesEnvolvidas = parteEnvolvidaContratoService.findAllPartesEnvolvidas(request.partesEnvolvidas());

        Contrato contrato = repository.save(mapper.toContrato(request));

        partesEnvolvidas.forEach(parteEnvolvida -> {
            parteEnvolvidaContratoService.saveParteEnvolvida(
                new ParteEnvolvidaContratoRequest(null, contrato.getId(), parteEnvolvida.id(), tipoPartesEnvolvidas.get(parteEnvolvida.id()))
            );
        });

        request.eventos().forEach(evento -> {
            eventoService.saveEvento(
                new EventoRequest(null, evento.tipo(), evento.dataRegistro(), evento.descricao(), evento.contratoId())
            );
        });

        return mapper.toContratoResponse(contrato);
    }

    public ContratoResponse updateContrato(ContratoRequest request) {
        Contrato contrato = repository.findById(request.id())
                .orElseThrow(() -> new ContratoNotFoundException(
                        "Contrato de id " + request.id() + " não encontrado"));
        mergeContrato(contrato, request);
        return mapper.toContratoResponse(repository.save(contrato));
    }

    private void mergeContrato(Contrato contrato, ContratoRequest request) {
        if (StringUtils.isNotBlank(request.descricao())) {
            contrato.setDescricao(request.descricao());
        }
        if (request.numero() != null) {
            contrato.setNumero(request.numero());
        }
        if (request.statusContrato() != null) {
            contrato.setStatusContrato(request.statusContrato());
        }
    }

    public List<ContratoResponse> findAllContratos() {
        return repository.findAll()
                .stream()
                .map(mapper::toContratoResponse)
                .toList();
    }

    public Boolean existsById(Integer contratoId) {
        return repository.findById(contratoId).isPresent();
    }

    public ContratoResponse findById(Integer contratoId) {
        return repository.findById(contratoId).map(mapper::toContratoResponse)
                .orElseThrow(
                        () -> new ContratoNotFoundException("Contrato de id " + contratoId + " não encontrado"));
    }

    public void deleteContrato(Integer contratoId) {
        repository.deleteById(contratoId);
    }
}
