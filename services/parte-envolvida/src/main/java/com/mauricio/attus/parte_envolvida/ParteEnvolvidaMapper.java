package com.mauricio.attus.parte_envolvida;

import org.springframework.stereotype.Service;

@Service
public class ParteEnvolvidaMapper {

  public ParteEnvolvida toParteEnvolvida(ParteEnvolvidaRequest request) {
    if (request == null) {
      return null;
    }

    return ParteEnvolvida.builder()
      .id(request.id())
      .nome(request.nome())
      .cpfCnpj(request.cpfCnpj())
      .tipo(request.tipo())
      .contato(request.contato())
      .build();
  }

  public ParteEnvolvidaResponse toParteEnvolvidaResponse(ParteEnvolvida parteEnvolvida) {
    if (parteEnvolvida == null) {
      return null;
    }
    
    return new ParteEnvolvidaResponse(parteEnvolvida.getId(), parteEnvolvida.getNome(), parteEnvolvida.getCpfCnpj(), parteEnvolvida.getTipo(), parteEnvolvida.getContato());
  }

}
