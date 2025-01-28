package com.mauricio.attus.evento;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public record EventoRequest (
    Integer id,
    @NotNull(message = "Tipo do evento é obrigatório")
    TIPO_EVENTO tipo,
    @NotNull(message = "A data de registro do contrato é obrigatória")
    LocalDateTime dataRegistro,
    String descricao,
    @NotNull(message = "O contrato associado ao evento deve ser informado!")
    Integer contratoId) {
}
