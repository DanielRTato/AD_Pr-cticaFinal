package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.model.LosJojos;
import org.example.model.Personaxe;
import org.example.model.Saga;
import org.example.service.MongoConexionService;
import org.example.service.PostgresConexionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class Secuencia {

    @Autowired
    private PostgresConexionService postgresService;

    @Autowired
    private MongoConexionService mongoService;

    public void executar() {

        // 1. Crear saga "Vento Aureo" en Postgres
        ArrayList<Personaxe> ps = new ArrayList<>();
        Personaxe p1 = new Personaxe();
        p1.setNome("Giorno Giovanna");
        p1.setStand("Gold Experience");
        ps.add(p1);

        Personaxe p2 = new Personaxe();
        p2.setNome("Bruno Bucciarati");
        p2.setStand("Sticky Fingers");
        ps.add(p2);

        Personaxe p3 = new Personaxe();
        p3.setNome("Guido Mista");
        p3.setStand("Sex Pistols");
        ps.add(p3);

        Saga saga = new Saga();
        saga.setTitulo("Vento Aureo");
        saga.setParte(5);
        saga.setAmbientacion("Italia");
        saga.setAnoinicio(2001);
        saga.setPersonaxes(ps);

        saga = postgresService.crearSaga(saga);

        // 2. Obtener saga por ID de Postgres -> guardar en Mongo
        Saga saga2 = postgresService.getSagById(2L);
        mongoService.crearSaga(saga2);

        // 3. Obtener saga por titulo de Postgres -> guardar en Mongo
        Saga sagaC = postgresService.sagaPorTitulo("Stardust Crusaders");
        mongoService.crearSaga(sagaC);

        // 4. Obtener todas las sagas de Postgres -> guardar cada una en Mongo
        List<Saga> sagas = postgresService.buscarSagas();
        for (Saga s : sagas) {
            mongoService.crearSaga(s);
        }

        // 5. Crear LosJojos con todas las sagas -> guardar en Mongo
        LosJojos losjojos = new LosJojos();
        losjojos.setSagas(sagas);
        mongoService.guardarLosJojos(losjojos);

        // 6. Exportar JSONs localmente (fetch data via REST + Gson)
        exportarSagasJson();
        exportarLosJojosJson();

        // 7. Borrar saga de Postgres y de Mongo
        postgresService.borrarSaga(saga.getId());
        mongoService.borrarSaga(saga.getId());
    }

    private void exportarSagasJson() {
        List<Saga> sagas = mongoService.buscarSagas();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String nombreArchivo = "exportadosMongo.json";
        try (FileWriter escritor = new FileWriter(nombreArchivo)) {
            gson.toJson(sagas, escritor);
            System.out.println("Se generó correctamente " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al generar archivo JSON: " + e.getMessage());
        }
    }

    private void exportarLosJojosJson() {
        List<LosJojos> losJojos = mongoService.buscarLosJojos();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String nombreArchivo = "exportadosLosJojos.json";
        try (FileWriter escritor = new FileWriter(nombreArchivo)) {
            gson.toJson(losJojos, escritor);
            System.out.println("Se generó correctamente " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al generar archivo JSON: " + e.getMessage());
        }
    }
}
