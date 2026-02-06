package org.example.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.model.Saga;
import org.example.repository.SagaRepository;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class SagaService {

    private final SagaRepository sagaRepository;

    public SagaService(SagaRepository sagaRepository) {
        this.sagaRepository = sagaRepository;
    }

    public List<Saga> findAll(){
        return sagaRepository.findAll();
    }

    public Optional<Saga> findById(Long id){
        return sagaRepository.findById(id);
    }

    public void save(Saga saga){
        sagaRepository.save(saga);
    }

    public void deleteById(Long id) {
        sagaRepository.deleteById(id);
    }

    public void borrarAll(){
        sagaRepository.deleteAll();
    }

    public void exportarJson(){
        List<Saga> lista = sagaRepository.findAll();

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String nombreArchivo = "exportadosMongo.json";
        try(FileWriter escritor = new FileWriter(nombreArchivo)){
            gson.toJson(lista,escritor);
            System.out.println("Se generó correctamente ");
        } catch (IOException e) {
            System.out.println("Error al generar archivo JSON: " + e.getMessage());;
        }
    }

}
