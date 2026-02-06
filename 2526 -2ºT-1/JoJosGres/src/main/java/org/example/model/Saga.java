package org.example.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sagas")
public class Saga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "idsaga")
    private Long id;

    @Column(name = "titulo", length = 150)
    private String titulo;

    @Column(name = "parte")
    private int parte;

    @Column(name = "anoinicio")
    private int anoinicio;

    @Column(name = "ambientacion", length = 100)
    private String ambientacion;

    @OneToMany(mappedBy = "saga", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Personaxe> personaxes;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getParte() {
        return parte;
    }

    public void setParte(int parte) {
        this.parte = parte;
    }

    public void setTitulo(String titulo) {
        this.titulo= titulo;
    }

    public String getAmbientacion() {
        return ambientacion;
    }

    public void setAmbientacion(String ambientacion) {
        this.ambientacion = ambientacion;
    }

    public int getAnoinicio() {
        return anoinicio;
    }

    public void setAnoinicio(int anoinicio) {
        this.anoinicio = anoinicio;
    }

    public List<Personaxe> getPersonaxes() {
        return personaxes;
    }

    public void setPersonaxes(List<Personaxe> personaxes) {
        this.personaxes = personaxes;
    }
}
