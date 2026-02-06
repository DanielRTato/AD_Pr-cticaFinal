package org.example.model;

import java.util.List;

public class LosJojos {

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
