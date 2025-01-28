package com.mauricio.attus.parte_envolvida;

import jakarta.validation.constraints.NotNull;

public record ParteEnvolvidaRequest (
  Integer id,
    @NotNull(message = "O nome deve ser informado")
    String nome,
    @NotNull(message = "O CPF/CNPJ deve ser informado")
    String cpfCnpj,
    @NotNull(message = "As informações de contato da parte envolvida devem ser informadas")
    Contato contato
  ) {
}
