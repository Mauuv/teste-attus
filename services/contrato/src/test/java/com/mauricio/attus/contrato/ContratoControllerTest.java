package com.mauricio.attus.contrato;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mauricio.attus.ContratoApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

@ContextConfiguration(classes = ContratoApplication.class)
@WebMvcTest(ContratoController.class)
class ContratoControllerTest {

    private static final String BASE_URL = "/api/v1/contrato";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    private ContratoService contratoService;

    @Autowired
    private MockMvc mockMvc;

    private ContratoRequest contratoRequest;
    private ContratoResponse contratoResponse;

    @BeforeEach
    void setUp() {
        contratoRequest = new ContratoRequest(
            1, 123, ESTADO_CONTRATO.ATIVO, "Contrato de Teste", null, null
        );
        contratoResponse = new ContratoResponse(
            1, 123, null, ESTADO_CONTRATO.ATIVO, "Contrato de Teste"
        );
    }

    @Test
    void testCreateContrato() throws Exception {
        when(contratoService.createContrato(any(ContratoRequest.class))).thenReturn(contratoResponse);

        mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contratoRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.numero").value(123))
                .andExpect(jsonPath("$.statusContrato").value("ATIVO"));
    }

    @Test
    void testUpdateContrato() throws Exception {
        when(contratoService.updateContrato(any(ContratoRequest.class))).thenReturn(contratoResponse);

        mockMvc.perform(put(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(contratoRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.numero").value(123))
                .andExpect(jsonPath("$.statusContrato").value("ATIVO"));
    }

    @Test
    void testFindAll() throws Exception {
        when(contratoService.findAllContratos()).thenReturn(List.of(contratoResponse));

        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].numero").value(123))
                .andExpect(jsonPath("$[0].statusContrato").value("ATIVO"));
    }

    @Test
    void testFindById() throws Exception {
        when(contratoService.findById(1)).thenReturn(contratoResponse);

        mockMvc.perform(get(BASE_URL + "/{contrato-id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.numero").value(123))
                .andExpect(jsonPath("$.statusContrato").value("ATIVO"));
    }

    @Test
    void testExistsById() throws Exception {
        when(contratoService.existsById(1)).thenReturn(true);
    
        mockMvc.perform(get(BASE_URL + "/exists/{contrato-id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }    

    @Test
    void testFindByStatusAndDataCriacao() throws Exception {
        when(contratoService.findByStatusOrDataCriacao(eq("ATIVO"), any(LocalDateTime.class)))
                .thenReturn(List.of(contratoResponse));

        mockMvc.perform(get(BASE_URL + "/find")
                .param("status_contrato", "ATIVO")
                .param("data_criacao", "2025-01-28T10:00:00"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].numero").value(123))
                .andExpect(jsonPath("$[0].statusContrato").value("ATIVO"));
    }
}
