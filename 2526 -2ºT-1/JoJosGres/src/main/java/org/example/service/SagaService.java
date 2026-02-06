package org.example.service;

import org.example.model.Saga;
import org.example.repository.SagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SagaService {

    @Autowired
    private SagaRepository sagaRepository;

    public List<Saga> findAll() {
        return sagaRepository.findAll();
    }

    public Optional<Saga> findById(Long id) {
        return sagaRepository.findById(id);
    }

    public List<Saga> findByTitulo(String nome){
        return sagaRepository.findByTitulo(nome);
    }

    public Saga save(Saga saga) {
        return sagaRepository.save(saga);
    }

    public boolean existsById(Long id) {
        return sagaRepository.existsById(id);
    }

    public void deleteById(Long id) {
        sagaRepository.deleteById(id);
    }

}
