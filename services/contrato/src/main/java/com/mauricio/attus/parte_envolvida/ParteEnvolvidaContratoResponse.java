package com.mauricio.attus.parte_envolvida;

import com.mauricio.attus.contrato.ContratoResponse;

public record ParteEnvolvidaContratoResponse (
    Integer id,
    Integer idParteEnvolvida,
    TIPO_PARTE_ENVOLVIDA tipoParteEnvolvida,
    ContratoResponse contrato
    ) {
}
