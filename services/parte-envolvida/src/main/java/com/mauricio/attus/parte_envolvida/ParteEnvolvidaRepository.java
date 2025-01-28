package com.mauricio.attus.parte_envolvida;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParteEnvolvidaRepository extends JpaRepository<ParteEnvolvida, Integer> {

}
