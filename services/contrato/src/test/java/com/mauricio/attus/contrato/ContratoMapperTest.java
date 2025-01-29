package com.mauricio.attus.contrato;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

@SpringBootTest(classes = ContratoMapper.class)
class ContratoMapperTest {

    private ContratoMapper contratoMapper;

    @BeforeEach
    void setUp() {
        contratoMapper = new ContratoMapper();
    }

    @Test
    void testToContrato() {
        ContratoRequest request = new ContratoRequest(
            1, 
            1234, 
            ESTADO_CONTRATO.ATIVO, 
            "Test Contract", 
            null,
            null 
        );

        Contrato contrato = contratoMapper.toContrato(request);

        assertNotNull(contrato);
        assertEquals(1, contrato.getId());
        assertEquals(1234, contrato.getNumero());
        assertEquals(ESTADO_CONTRATO.ATIVO, contrato.getStatusContrato());
        assertEquals("Test Contract", contrato.getDescricao());
    }

    @Test
    void testToContratoResponse() {
        Contrato contrato = Contrato.builder()
            .id(1)
            .numero(1234)
            .statusContrato(ESTADO_CONTRATO.ATIVO)
            .descricao("Test Contract")
            .dataCriacao(LocalDateTime.now())
            .build();

        ContratoResponse contratoResponse = contratoMapper.toContratoResponse(contrato);

        assertNotNull(contratoResponse);
        assertEquals(1, contratoResponse.id());
        assertEquals(1234, contratoResponse.numero());
        assertEquals(contrato.getDataCriacao(), contratoResponse.dataCriacao());
        assertEquals(ESTADO_CONTRATO.ATIVO, contratoResponse.statusContrato());
        assertEquals("Test Contract", contratoResponse.descricao());
    }

    @Test
    void testToContratoResponseWithNull() {
        ContratoResponse contratoResponse = contratoMapper.toContratoResponse(null);

        assertNull(contratoResponse);
    }

    @Test
    void testToContratoWithNull() {
        Contrato contrato = contratoMapper.toContrato(null);

        assertNull(contrato);
    }
}
