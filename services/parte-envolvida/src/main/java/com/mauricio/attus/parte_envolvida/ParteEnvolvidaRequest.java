package com.mauricio.attus.parte_envolvida;

import jakarta.validation.constraints.NotNull;

public record ParteEnvolvidaRequest (
    Long id,
    @NotNull(message = "O nome deve ser informado")
    String nome,
    @NotNull(message = "O CPF/CNPJ deve ser informado")
    String cpfCnpj,
    @NotNull(message = "O tipo da parte envolvida deve ser informado")
    TIPO_PARTE_ENVOLVIDA tipo,
    @NotNull(message = "As informações de contato da parte envolvida devem ser informadas")
    Contato contato
  ) {
}
