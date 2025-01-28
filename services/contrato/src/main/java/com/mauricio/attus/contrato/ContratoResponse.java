package com.mauricio.attus.contrato;

import java.time.LocalDateTime;

public record ContratoResponse (
    Integer id,
    Integer numero,
    LocalDateTime dataCriacao,
    ESTADO_CONTRATO statusContrato,
    String descricao
  ) {
}
