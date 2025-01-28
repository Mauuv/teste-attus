package com.mauricio.attus.contrato;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.validation.annotation.Validated;

import com.mauricio.attus.evento.Evento;
import com.mauricio.attus.parte_envolvida.ParteEnvolvidaContrato;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "contrato")
@Validated
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Integer id;

    @Column(unique = true)
    public Integer numero;

    @CreatedDate
    @Column(updatable = false)
    public LocalDateTime dataCriacao;

    @Column
    public String descricao;

    @Column
    @Enumerated(EnumType.STRING)
    ESTADO_CONTRATO statusContrato;

    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL)
    private List<Evento> events;

    @OneToMany(mappedBy = "parteEnvolvida", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ParteEnvolvidaContrato> contratos;

}
