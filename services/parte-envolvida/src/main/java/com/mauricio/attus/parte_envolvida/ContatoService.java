package com.mauricio.attus.parte_envolvida;

import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ContatoService {
    
    private final ContatoRepository contatoRepository;

    public Contato save(Contato request) {
        return contatoRepository.save(request);
    }

    public Optional<Contato> findById(Integer id) {
        return contatoRepository.findById(id);
    }
}
