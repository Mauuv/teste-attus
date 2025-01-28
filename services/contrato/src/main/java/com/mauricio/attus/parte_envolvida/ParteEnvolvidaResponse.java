package com.mauricio.attus.parte_envolvida;

public record ParteEnvolvidaResponse (
    Integer id,
    String nome,
    String cpfCnpj,
    ContatoResponse contato
  ) {
}
