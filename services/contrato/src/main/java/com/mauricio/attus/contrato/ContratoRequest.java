package com.mauricio.attus.contrato;

import java.util.List;

import com.mauricio.attus.evento.EventoRequest;
import com.mauricio.attus.parte_envolvida.ParteEnvolvidaContratoRequest;

import jakarta.validation.constraints.NotNull;

public record ContratoRequest (
    Integer id,
    @NotNull(message = "Número do contrato é obrigatório")
    Integer numero,
    ESTADO_CONTRATO statusContrato,
    String descricao,
    List<EventoRequest> eventos,
    List<ParteEnvolvidaContratoRequest> partesEnvolvidas
    ) {
}
