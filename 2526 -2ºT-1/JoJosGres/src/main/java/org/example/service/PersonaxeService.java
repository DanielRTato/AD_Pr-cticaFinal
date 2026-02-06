package org.example.service;

import org.example.model.Personaxe;
import org.example.repository.PersonaxeRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class PersonaxeService {

    @Autowired
    private PersonaxeRepository personaxeRepository;

    public List<Personaxe> findAll() {
        return personaxeRepository.findAll();
    }

    public Optional<Personaxe> findById(Long id) {
        return personaxeRepository.findById(id);
    }

    public Optional<List<Personaxe>> findByNombreUsuario(String nome){
        return personaxeRepository.findByNome(nome);
    }

    public Personaxe save(Personaxe personaxe) {
        return personaxeRepository.save(personaxe);
    }

    public boolean existsById(Long id) {
        return personaxeRepository.existsById(id);
    }

    public void deleteById(Long id) {
        personaxeRepository.deleteById(id);
    }
}
