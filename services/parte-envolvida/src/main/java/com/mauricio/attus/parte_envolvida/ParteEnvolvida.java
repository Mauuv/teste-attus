package com.mauricio.attus.parte_envolvida;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "parte_envolvida")
public class ParteEnvolvida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public Long id;

    @Column
    public String nome;

    @Column(name = "cpj_cnpj")
    public String cpfCnpj;

    @Column
    @Enumerated(EnumType.STRING)
    public TIPO_PARTE_ENVOLVIDA tipo;

    @OneToOne
    @JoinColumn(name = "contato_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Contato contato;
}
