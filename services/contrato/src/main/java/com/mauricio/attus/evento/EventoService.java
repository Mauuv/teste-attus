package com.mauricio.attus.evento;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.mauricio.attus.exception.EventoNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventoService {

    private final EventoRepository repository;
    private final EventoMapper mapper;

    public EventoResponse saveEvento(EventoRequest request) {
        var evento = mapper.toEvento(request);
        return mapper.toEventoResponse(repository.save(evento));
    }

    public List<EventoResponse> findByContratoId(Integer contratoId) {
        return repository.findAllByContratoId(contratoId)
            .stream()
            .map(mapper::toEventoResponse)
            .toList();
    }

    public void deleteEvento(Integer eventoId) {
        repository.deleteById(eventoId);
    }

    public EventoResponse updateEvento(EventoRequest request) {
        Evento evento = repository.findById(request.id())
            .orElseThrow(() -> new EventoNotFoundException("Evento de id " + request.id() + " não encontrado"));
        mergeEvento(evento, request);
        evento = repository.save(evento);
        return mapper.toEventoResponse(evento);
    }

    private void mergeEvento(Evento evento, EventoRequest request) {
        if (StringUtils.isNotBlank(request.descricao())) {
            evento.setDescricao(request.descricao());
        }
    }

    public List<EventoResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toEventoResponse)
                .toList();
    }
}
