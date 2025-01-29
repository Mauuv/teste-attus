package com.mauricio.attus.parte_envolvida;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import com.mauricio.attus.exception.ParteEnvolvidaNotFoundException;

import java.util.*;

class ParteEnvolvidaServiceTest {

    @Mock
    private ParteEnvolvidaRepository repository;

    @Mock
    private ParteEnvolvidaMapper mapper;

    @InjectMocks
    private ParteEnvolvidaService service;

    private ParteEnvolvidaRequest request;
    private ParteEnvolvidaResponse response;
    private ParteEnvolvida parteEnvolvida;
    private Contato contato;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Inicializando objetos de teste
        contato = new Contato(1, "test@example.com", "123456789");
        parteEnvolvida = new ParteEnvolvida(1, "Nome", "123456789", contato);
        request = new ParteEnvolvidaRequest(1, "Nome", "123456789", contato);
        response = new ParteEnvolvidaResponse(1, "Nome", "123456789", contato);
    }

    @Test
    void testCreateParteEnvolvida() {
        // Mockando comportamentos
        when(mapper.toParteEnvolvida(request)).thenReturn(parteEnvolvida);
        when(repository.save(parteEnvolvida)).thenReturn(parteEnvolvida);
        when(mapper.toParteEnvolvidaResponse(parteEnvolvida)).thenReturn(response);

        // Chamando o método de serviço
        ParteEnvolvidaResponse result = service.createParteEnvolvida(request);

        // Verificando os resultados
        assertNotNull(result);
        assertEquals("Nome", result.nome());
        verify(repository).save(parteEnvolvida);
    }

    @Test
    void testUpdateParteEnvolvida() {
        when(repository.findById(1)).thenReturn(Optional.of(parteEnvolvida));
        when(repository.save(parteEnvolvida)).thenReturn(parteEnvolvida);
        when(mapper.toParteEnvolvidaResponse(parteEnvolvida)).thenReturn(response);

        ParteEnvolvidaResponse result = service.updateParteEnvolvida(request);

        assertNotNull(result);
        assertEquals("Nome", result.nome());
        verify(repository).save(parteEnvolvida);
    }

    @Test
    void testUpdateParteEnvolvida_NotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        ParteEnvolvidaNotFoundException exception = assertThrows(ParteEnvolvidaNotFoundException.class, () -> {
            service.updateParteEnvolvida(request);
        });

        assertEquals("Parte envolvida de id 1 não encontrada", exception.getMessage());
    }

    @Test
    void testFindById() {
        when(repository.findById(1)).thenReturn(Optional.of(parteEnvolvida));
        when(mapper.toParteEnvolvidaResponse(parteEnvolvida)).thenReturn(response);

        ParteEnvolvidaResponse result = service.findById(1);

        assertNotNull(result);
        assertEquals("Nome", result.nome());
    }

    @Test
    void testFindById_NotFound() {
        when(repository.findById(1)).thenReturn(Optional.empty());

        ParteEnvolvidaNotFoundException exception = assertThrows(ParteEnvolvidaNotFoundException.class, () -> {
            service.findById(1);
        });

        assertEquals("Parte envolvida de id 1 não encontrada", exception.getMessage());
    }

    @Test
    void testFindByCpfCnpj() {
        when(repository.findByCpfCnpj("123456789")).thenReturn(Optional.of(parteEnvolvida));
        when(mapper.toParteEnvolvidaResponse(parteEnvolvida)).thenReturn(response);

        ParteEnvolvidaResponse result = service.findByCpfCnpj("123456789");

        assertNotNull(result);
        assertEquals("Nome", result.nome());
    }

    @Test
    void testFindByCpfCnpj_NotFound() {
        when(repository.findByCpfCnpj("123456789")).thenReturn(Optional.empty());

        ParteEnvolvidaNotFoundException exception = assertThrows(ParteEnvolvidaNotFoundException.class, () -> {
            service.findByCpfCnpj("123456789");
        });

        assertEquals("Parte envolvida de CPF/CNPJ 123456789 não encontrada", exception.getMessage());
    }

    @Test
    void testFindAllPartesInteressadas() {
        List<ParteEnvolvida> partesEnvolvidas = Arrays.asList(parteEnvolvida);
        when(repository.findAll()).thenReturn(partesEnvolvidas);
        when(mapper.toParteEnvolvidaResponse(parteEnvolvida)).thenReturn(response);

        List<ParteEnvolvidaResponse> result = service.findAllPartesInteressadas();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Nome", result.get(0).nome());
    }

    @Test
    void testExistsById() {
        when(repository.findById(1)).thenReturn(Optional.of(parteEnvolvida));

        Boolean exists = service.existsById(1);

        assertTrue(exists);
    }

    @Test
    void testExistsByIdList() {
        List<Integer> ids = Arrays.asList(1, 2);
        when(repository.countByIds(ids)).thenReturn(2L);

        Boolean exists = service.existsByIdList(ids);

        assertTrue(exists);
    }

    @Test
    void testDeleteParteEnvolvida() {
        doNothing().when(repository).deleteById(1);

        service.deleteParteEnvolvida(1);

        verify(repository).deleteById(1);
    }
}