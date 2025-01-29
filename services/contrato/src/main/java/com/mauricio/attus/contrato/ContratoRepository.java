package com.mauricio.attus.contrato;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Integer> {

    List<Contrato> findByStatusContratoAndDataCriacao(String statusContrato, LocalDateTime dataCriacao);

}
