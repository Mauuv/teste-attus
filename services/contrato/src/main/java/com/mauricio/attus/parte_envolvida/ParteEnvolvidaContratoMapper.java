package com.mauricio.attus.parte_envolvida;

import org.springframework.stereotype.Service;

import com.mauricio.attus.contrato.Contrato;

@Service
public class ParteEnvolvidaContratoMapper {

    public ParteEnvolvidaContrato toParteEnvolvidaContrato(ParteEnvolvidaContratoRequest request) {
        return ParteEnvolvidaContrato.builder()
        .contrato(
            Contrato.builder()
                    .id(request.contratoId())
                    .build()
            )
        .tipoParteEnvolvida(request.tipoParteEnvolvida())
        .parteEnvolvidaId(request.idParteEnvolvida())
        .build();
    }

    public ParteEnvolvidaContratoResponse toParteEnvolvidaContratoResponse(ParteEnvolvidaContrato parteEnvolvidaContrato) {
        if (parteEnvolvidaContrato == null) {
            return null;
        }
        return new ParteEnvolvidaContratoResponse(
            parteEnvolvidaContrato.getId(),
            parteEnvolvidaContrato.getParteEnvolvidaId(),
            parteEnvolvidaContrato.getTipoParteEnvolvida(),
            parteEnvolvidaContrato.getContrato()
        );
    }

}
