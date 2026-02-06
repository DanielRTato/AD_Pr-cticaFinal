package org.example.repository;

import org.example.model.LosJojos;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LosJojosRepository extends MongoRepository<LosJojos, String> {
}
