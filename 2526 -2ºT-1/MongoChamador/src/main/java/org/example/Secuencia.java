package org.example;

import org.example.model.LosJojos;
import org.example.model.Personaxe;
import org.example.model.Saga;
import org.example.service.ConexionService;
import org.example.service.LosJoJosService;
import org.example.service.SagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Secuencia {

    @Autowired
    private ConexionService conexionService;
    @Autowired
    private SagaService sagaService;
    @Autowired
    private LosJoJosService losJoJosService;

    public void executar() {

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

        saga = conexionService.crearSaga(saga);


        Saga saga2 = conexionService.getSagById(2L);
        sagaService.save(saga2);

        Saga sagaC = conexionService.sagaPorTitulo("Stardust Crusaders");
        sagaService.save(sagaC);

        List<Saga> sagas = conexionService.buscarSagas();
        for (Saga s:sagas){
            sagaService.save(s);
        }

        LosJojos losjojos = new LosJojos();
        losjojos.setSagas(sagas);
        losJoJosService.save(losjojos);

        losJoJosService.exportarJson();
        sagaService.exportarJson();

        conexionService.borrarSaga(saga.getId());
        sagaService.deleteById(saga.getId());



    }
}
