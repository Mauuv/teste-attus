package com.mauricio.attus.evento;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.validation.annotation.Validated;

import com.mauricio.attus.contrato.Contrato;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table
@Validated
public class Evento {
    @Id
    @GeneratedValue
    @Column
    public Integer id;

    @Enumerated(EnumType.STRING)
    @Column
    public TIPO_EVENTO tipo;

    @Column(updatable = false)
    public LocalDateTime dataRegistro;

    @Column
    public String descricao;

    @ManyToOne
    @JoinColumn(name = "contrato_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Contrato contrato;
}
