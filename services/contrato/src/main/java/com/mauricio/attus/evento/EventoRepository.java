package com.mauricio.attus.evento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Integer> {
    List<Evento> findAllByContratoId(Integer contratoId);
}
