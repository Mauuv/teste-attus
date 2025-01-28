package com.mauricio.attus.parte_envolvida;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParteEnvolvidaContratoRepository extends JpaRepository<ParteEnvolvidaContrato, Integer> {

    List<ParteEnvolvidaContrato> findAllByContratoId(Integer contratoId);

}
