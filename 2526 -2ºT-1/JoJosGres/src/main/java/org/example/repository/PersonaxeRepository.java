package org.example.repository;


import org.example.model.Personaxe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonaxeRepository  extends JpaRepository<Personaxe, Long> {

    Optional<List<Personaxe>> findByNome(String nome);
    void deleteByNome(String nome);

}
