package org.example.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "LosJojos")
public class LosJojos {

    @Id
    private String id;

    private List<Saga> sagas;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Saga> getSagas() {
        return sagas;
    }

    public void setSagas(List<Saga> sagas) {
        this.sagas = sagas;
    }
}
