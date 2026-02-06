package org.example.service;

import org.example.model.LosJojos;
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
public class MongoConexionService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String MONGO_BASE_URL_SAGA = "http://localhost:8095/mongo/sagas";
    private static final String MONGO_BASE_URL_LOSJOJOS = "http://localhost:8095/mongo/losjojos";

    public List<Saga> buscarSagas() {
        try {
            ResponseEntity<List<Saga>> response = restTemplate.exchange(
                    MONGO_BASE_URL_SAGA, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Saga>>() {}
            );
            return response.getBody() != null ? response.getBody() : Collections.emptyList();
        } catch (HttpClientErrorException e) {
            System.out.println("Erro buscando sagas en Mongo: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public Saga crearSaga(Saga saga) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Saga> request = new HttpEntity<>(saga, headers);

            ResponseEntity<Saga> response = restTemplate.exchange(
                    MONGO_BASE_URL_SAGA, HttpMethod.POST, request, Saga.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Erro al crear saga en Mongo: " + e.getMessage());
            return null;
        }
    }

    public boolean borrarSaga(Long id) {
        try {
            String url = MONGO_BASE_URL_SAGA + "/" + id;
            restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
            return true;
        } catch (HttpClientErrorException e) {
            System.out.println("Erro ao borrar saga en Mongo: " + e.getMessage());
            return false;
        }
    }

    public LosJojos guardarLosJojos(LosJojos losJojos) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<LosJojos> request = new HttpEntity<>(losJojos, headers);

            ResponseEntity<LosJojos> response = restTemplate.exchange(
                    MONGO_BASE_URL_LOSJOJOS, HttpMethod.POST, request, LosJojos.class
            );
            return response.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println("Erro ao gardar LosJojos en Mongo: " + e.getMessage());
            return null;
        }
    }

    public List<LosJojos> buscarLosJojos() {
        try {
            ResponseEntity<List<LosJojos>> response = restTemplate.exchange(
                    MONGO_BASE_URL_LOSJOJOS, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<LosJojos>>() {}
            );
            return response.getBody() != null ? response.getBody() : Collections.emptyList();
        } catch (HttpClientErrorException e) {
            System.out.println("Erro buscando LosJojos en Mongo: " + e.getMessage());
            return Collections.emptyList();
        }
    }
}
