package com.mauricio.attus.contrato;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContratoService {

    private final ContratoRepository repository;
    private final ContratoMapper mapper;
    private final ParteEnvolvidaContratoService parteEnvolvidaContratoService;
    private final EventoService eventoService;
    private final EntityManager entityManager;

    public ContratoResponse createContrato(ContratoRequest request) {
        Map<Integer, TIPO_PARTE_ENVOLVIDA> tipoPartesEnvolvidas = request.partesEnvolvidas().stream()
            .collect(Collectors.toMap(
                ParteEnvolvidaContratoRequest::id,
                ParteEnvolvidaContratoRequest::tipoParteEnvolvida
            ));
        List<ParteEnvolvidaResponse> partesEnvolvidas = new ArrayList<>();
        if (!request.partesEnvolvidas().isEmpty()) {
            partesEnvolvidas = parteEnvolvidaContratoService.findAllPartesEnvolvidas(request.partesEnvolvidas());
        }

        Contrato contratoSalvar = mapper.toContrato(request);
        contratoSalvar.setStatusContrato(contratoSalvar.getStatusContrato() == null ? ESTADO_CONTRATO.ATIVO : contratoSalvar.getStatusContrato());
        Contrato contrato = repository.save(contratoSalvar);

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

    public List<ContratoResponse> findByStatusOrDataCriacao(String statusContrato, LocalDateTime dataCriacao) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Contrato> criteriaQuery = criteriaBuilder.createQuery(Contrato.class);
        
        Root<Contrato> contratoRoot = criteriaQuery.from(Contrato.class);

        Predicate[] predicates = new Predicate[2];
        int index = 0;

        if (statusContrato != null) {
            predicates[index++] = criteriaBuilder.equal(contratoRoot.get("statusContrato"), statusContrato);
        }
        if (dataCriacao != null) {
            LocalDateTime startOfDay = dataCriacao.toLocalDate().atStartOfDay();
            LocalDateTime endOfDay = dataCriacao.toLocalDate().atTime(23, 59, 59, 999999999);

            predicates[index++] = criteriaBuilder.between(contratoRoot.get("dataCriacao"), startOfDay, endOfDay);
        }

        if (index > 0) {
            criteriaQuery.where(criteriaBuilder.and(predicates));
        }

        List<Contrato> contratos = entityManager.createQuery(criteriaQuery).getResultList();

        return contratos.stream()
                .map(mapper::toContratoResponse)
                .toList();
    }
}
