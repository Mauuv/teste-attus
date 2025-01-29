package com.mauricio.attus.parte_envolvida;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ParteEnvolvidaControllerTest {

    @Mock
    private ParteEnvolvidaService service;

    @InjectMocks
    private ParteEnvolvidaController controller;

    @Test
    void createParteEnvolvida_ShouldReturnResponse() {
        ParteEnvolvidaRequest request = mock(ParteEnvolvidaRequest.class);
        ParteEnvolvidaResponse response = mock(ParteEnvolvidaResponse.class);
        when(service.createParteEnvolvida(request)).thenReturn(response);

        ResponseEntity<ParteEnvolvidaResponse> result = controller.createParteEnvolvida(request);

        assertNotNull(result);
        assertEquals(response, result.getBody());
        verify(service).createParteEnvolvida(request);
    }

    @Test
    void findById_ShouldReturnParteEnvolvidaResponse() {
        Integer id = 1;
        ParteEnvolvidaResponse response = mock(ParteEnvolvidaResponse.class);
        when(service.findById(id)).thenReturn(response);

        ResponseEntity<ParteEnvolvidaResponse> result = controller.findById(id);

        assertNotNull(result);
        assertEquals(response, result.getBody());
        verify(service).findById(id);
    }

    @Test
    void findAll_ShouldReturnList() {
        List<ParteEnvolvidaResponse> responses = List.of(mock(ParteEnvolvidaResponse.class));
        when(service.findAllPartesInteressadas()).thenReturn(responses);

        ResponseEntity<List<ParteEnvolvidaResponse>> result = controller.findAll();

        assertNotNull(result);
        assertEquals(responses, result.getBody());
        verify(service).findAllPartesInteressadas();
    }

    @Test
    void existsById_ShouldReturnBoolean() {
        Integer id = 1;
        when(service.existsById(id)).thenReturn(true);

        ResponseEntity<Boolean> result = controller.existsById(id);

        assertNotNull(result);
        assertTrue(result.getBody());
        verify(service).existsById(id);
    }

    @Test
    void deleteById_ShouldReturnAccepted() {
        Integer id = 1;

        ResponseEntity<Void> result = controller.deleteById(id);

        assertNotNull(result);
        assertEquals(202, result.getStatusCode().value());
    }
}

