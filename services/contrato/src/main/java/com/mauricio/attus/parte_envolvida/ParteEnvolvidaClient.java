package com.mauricio.attus.parte_envolvida;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mauricio.attus.exception.BussinesException;
import com.mauricio.attus.exception.InvalidParteEnvolvidaException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParteEnvolvidaClient {

    @Value("${application.config.parte-envolvida-url}")
    private String parteEnvolvidaUrl;
    private final RestTemplate restTemplate;

    public List<ParteEnvolvidaResponse> findAllPartesEnvolvidas(List<ParteEnvolvidaContratoRequest> requestBody) {
        var partesEnvolvidasId = requestBody.stream().map(ParteEnvolvidaContratoRequest::id).toList();
        if (isAnyParteEnvolvidaInvalid(partesEnvolvidasId)) {
            throw new InvalidParteEnvolvidaException("Algumas partes envolvidas adicionadas ao contrato são inválidas");
        }
        
        HttpHeaders headers = new HttpHeaders();
        String url = parteEnvolvidaUrl + "/find-by-ids";
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<List<ParteEnvolvidaContratoRequest>> requestEntity = new HttpEntity<>(requestBody, headers);
        
        ParameterizedTypeReference<List<ParteEnvolvidaResponse>> responseType = new ParameterizedTypeReference<>() {};

        ResponseEntity<List<ParteEnvolvidaResponse>> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, responseType);
        
        if (responseEntity.getStatusCode().isError()) {
            throw new BussinesException("Erro ao buscar/processar as partes envolvidas. Status code: " + responseEntity.getStatusCode());
        }

        return responseEntity.getBody();
    }

    public boolean isAnyParteEnvolvidaInvalid(List<Integer> partesEnvolvidasId) {
        HttpHeaders headers = new HttpHeaders();
        String url = parteEnvolvidaUrl + "/exists-all";
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<List<Integer>> requestEntity = new HttpEntity<>(partesEnvolvidasId, headers);
        
        ParameterizedTypeReference<Boolean> responseType = new ParameterizedTypeReference<>() {};

        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, responseType);

        return responseEntity.getStatusCode().isError() || !responseEntity.getBody();
    }

    public Integer findIdByCpfCnpj(String cpfCnpj) {
        HttpHeaders headers = new HttpHeaders();
        String url = parteEnvolvidaUrl + "/find-by/" + cpfCnpj;

        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ParameterizedTypeReference<ParteEnvolvidaResponse> responseType = new ParameterizedTypeReference<>() {};

        ResponseEntity<ParteEnvolvidaResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, responseType);

        if (!responseEntity.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("Error fetching data from external service");
        }

        return responseEntity.getBody().id();
    }
    
}
