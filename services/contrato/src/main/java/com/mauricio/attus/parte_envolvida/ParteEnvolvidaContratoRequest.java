package com.mauricio.attus.parte_envolvida;

import jakarta.validation.constraints.NotNull;

public record ParteEnvolvidaContratoRequest (
    Integer id,
    @NotNull(message = "O contrato deve ser informado")
    Integer contratoId,
    @NotNull(message = "A parte envolvida deve ser informada")
    Integer idParteEnvolvida,
    @NotNull(message = "O tipo da parte envolvida deve ser informada")
    TIPO_PARTE_ENVOLVIDA tipoParteEnvolvida
  ) {
}
