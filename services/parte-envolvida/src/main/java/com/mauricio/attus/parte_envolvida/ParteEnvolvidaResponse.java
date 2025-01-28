package com.mauricio.attus.parte_envolvida;

public record ParteEnvolvidaResponse (
    Integer id,
    String nome,
    String cpfCnpj,
    TIPO_PARTE_ENVOLVIDA tipo,
    Contato contato
  ) {
}
