package com.mauricio.attus.parte_envolvida;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParteEnvolvidaRepository extends JpaRepository<ParteEnvolvida, Integer> {
    @Query("SELECT COUNT(pe.id) FROM ParteEnvolvida pe WHERE pe.id IN :ids")
    long countByIds(@Param("ids") List<Integer> ids);
}
