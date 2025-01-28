package com.mauricio.attus.evento;

import org.springframework.stereotype.Service;

import com.mauricio.attus.contrato.Contrato;

@Service
public class EventoMapper {

    public Evento toEvento(EventoRequest request) {
        return Evento.builder()
        .id(request.id())
        .dataRegistro(request.dataRegistro())
        .contrato(
            Contrato.builder()
                    .id(request.contratoId())
                    .build()
            )
        .tipo(request.tipo())
        .descricao(request.descricao())
        .build();
    }

    public EventoResponse toEventoResponse(Evento evento) {
        if (evento == null) {
            return null;
        }
        return new EventoResponse(
            evento.getId(),
            evento.getTipo(),
            evento.getDataRegistro(),
            evento.getDescricao(),
            evento.getContrato()
        );
    }
}