package org.example.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.model.LosJojos;
import org.example.model.Saga;
import org.example.repository.LosJojosRepository;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
public class LosJoJosService {

    private LosJojosRepository lolsJojosRepository;

        public LosJoJosService(LosJojosRepository lolsJojosRepository) {
            this.lolsJojosRepository = lolsJojosRepository;
        }

        public LosJojos save(LosJojos losJojos){
            return lolsJojosRepository.save(losJojos);
        }

    public void exportarJson(){
        List<LosJojos> lista = lolsJojosRepository.findAll();

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
