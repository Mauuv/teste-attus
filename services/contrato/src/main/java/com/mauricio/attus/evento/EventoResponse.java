package com.mauricio.attus.evento;

import java.time.LocalDateTime;

import com.mauricio.attus.contrato.ContratoResponse;

public record EventoResponse (Integer id,
    TIPO_EVENTO tipo,
    LocalDateTime dataRegistro,
    String descricao,
    ContratoResponse contrato) {

}
