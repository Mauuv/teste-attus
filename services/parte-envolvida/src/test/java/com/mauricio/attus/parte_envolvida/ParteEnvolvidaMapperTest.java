package com.mauricio.attus.parte_envolvida;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParteEnvolvidaMapperTest {

    private ParteEnvolvidaMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ParteEnvolvidaMapper();
    }

    @Test
    void toParteEnvolvida_ShouldMapRequestToEntity() {
        ParteEnvolvidaRequest request = new ParteEnvolvidaRequest(1, "Nome Teste", "123456789", new Contato());
        
        ParteEnvolvida result = mapper.toParteEnvolvida(request);
        
        assertNotNull(result);
        assertEquals(request.id(), result.getId());
        assertEquals(request.nome(), result.getNome());
        assertEquals(request.cpfCnpj(), result.getCpfCnpj());
        assertEquals(request.contato(), result.getContato());
    }

    @Test
    void toParteEnvolvida_ShouldReturnNull_WhenRequestIsNull() {
        assertNull(mapper.toParteEnvolvida(null));
    }

    @Test
    void toParteEnvolvidaResponse_ShouldMapEntityToResponse() {
        ParteEnvolvida entity = new ParteEnvolvida(1, "Nome Teste", "123456789", new Contato());
        
        ParteEnvolvidaResponse result = mapper.toParteEnvolvidaResponse(entity);
        
        assertNotNull(result);
        assertEquals(entity.getId(), result.id());
        assertEquals(entity.getNome(), result.nome());
        assertEquals(entity.getCpfCnpj(), result.cpfCnpj());
        assertEquals(entity.getContato(), result.contato());
    }

    @Test
    void toParteEnvolvidaResponse_ShouldReturnNull_WhenEntityIsNull() {
        assertNull(mapper.toParteEnvolvidaResponse(null));
    }
}
