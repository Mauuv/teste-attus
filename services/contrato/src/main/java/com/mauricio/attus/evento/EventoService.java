package com.mauricio.attus.evento;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventoService {

    public EventoRepository repository;
    public EventoMapper mapper;

    public Integer saveEvento(EventoRequest request) {
        var evento = mapper.toEvento(request);
        return repository.save(evento).getId();
    }

    public List<EventoResponse> findByContratoId(Integer contratoId) {
        return repository.findAllByContratoId(contratoId)
            .stream()
            .map(mapper::toEventoResponse)
            .toList();
    }
}
