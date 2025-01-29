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

import org.springframework.validation.annotation.Validated;

import com.mauricio.attus.evento.Evento;
import com.mauricio.attus.parte_envolvida.ParteEnvolvidaContrato;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "contrato", indexes = {
    @Index(name = "idx_status_contrato", columnList = "status_contrato"),
    @Index(name = "idx_data_criacao", columnList = "data_criacao")
})
@Validated
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Integer id;

    @Column(unique = true)
    public Integer numero;

    @Column(updatable = false)
    public LocalDateTime dataCriacao;

    @Column
    public String descricao;

    @Column(name = "status_contrato")
    @Enumerated(EnumType.STRING)
    ESTADO_CONTRATO statusContrato;

    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL)
    private List<Evento> events;

    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ParteEnvolvidaContrato> contratos;

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }
}
