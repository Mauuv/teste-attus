package com.mauricio.attus.parte_envolvida;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.validation.annotation.Validated;

import com.mauricio.attus.contrato.Contrato;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table
@Validated
public class ParteEnvolvidaContrato {
    @Id
    @GeneratedValue
    @Column
    public Integer id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public TIPO_PARTE_ENVOLVIDA tipoParteEnvolvida;

    @Column(nullable = false)
    public Integer parteEnvolvidaId;

    @ManyToOne
    @JoinColumn(name = "contrato_id", nullable = false)
    public Contrato contrato;
}
