package org.example.repository;

import org.example.model.Saga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SagaRepository extends JpaRepository<Saga, Long> {

    List<Saga> findByTitulo(String nome);
}
