package com.mauricio.attus.contrato;

import org.springframework.stereotype.Service;

@Service
public class ContratoMapper {

  public Contrato toContrato(ContratoRequest request) {
    if (request == null) {
      return null;
    }

    return Contrato.builder()
      .id(request.id())
      .numero(request.numero())
      .statusContrato(request.statusContrato())
      .descricao(request.descricao())
      .build();
  }

  public ContratoResponse toContratoResponse(Contrato parteEnvolvida) {
    if (parteEnvolvida == null) {
      return null;
    }
    
    return new ContratoResponse(parteEnvolvida.getId(), parteEnvolvida.getNumero(), parteEnvolvida.getDataCriacao(), parteEnvolvida.getStatusContrato(), parteEnvolvida.getDescricao());
  }
}
