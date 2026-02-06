package org.example.repository;

import org.example.model.Saga;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SagaRepository extends MongoRepository<Saga,Long> {
}
