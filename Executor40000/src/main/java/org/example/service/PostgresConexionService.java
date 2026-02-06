package org.example.service;

import org.example.model.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class PostgresConexionService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String POSTGRES_BASE_URL_SAGA = "http://localhost:8081/postgres/sagas";

    public List<Saga> buscarSagas() {
        try {
            ResponseEntity<List<Saga>> response = restTemplate.exchange(
                    POSTGRES_BASE_URL_SAGA, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Saga>>() {}
            );
            return response.getBody() != null ? response.getBody() : Collections.emptyList();
        } catch (HttpClientErrorException e) {
            System.out.println("Erro: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public Saga crearSaga(Saga saga) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Saga> request = new HttpEntity<>(saga, headers);

            ResponseEntity<Saga> response = restTemplate.exchange(
                    POSTGRES_BASE_URL_SAGA, HttpMethod.POST, request, Saga.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Erro al crear: " + e.getMessage());
            return null;
        }
    }

    public Saga getSagById(Long id) {
        try {
            String url = POSTGRES_BASE_URL_SAGA + "/" + id;
            ResponseEntity<Saga> response = restTemplate.exchange(
                    url, HttpMethod.GET, null, Saga.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Erro conectando por id " + e.getMessage());
            return null;
        }
    }

    public Saga sagaPorTitulo(String titulo) {
        try {
            String url = POSTGRES_BASE_URL_SAGA + "/titulo/" + titulo;
            HttpEntity<List<Saga>> response = restTemplate.exchange(
                    url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Saga>>() {}
            );
            List<Saga> s = response.getBody();
            return s.get(0);
        } catch (HttpClientErrorException e) {
            System.out.println("Mensaxe xenerica " + e.getMessage());
            return null;
        }
    }

    public boolean borrarSaga(Long id) {
        try {
            String url = POSTGRES_BASE_URL_SAGA + "/" + id;
            restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
            return true;
        } catch (HttpClientErrorException e) {
            System.out.println("NonNonNon non dixeche-la palabra maxica jajaja jajaja " + e.getMessage());
            return false;
        }
    }
}
