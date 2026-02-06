package org.example.controller;

import org.example.model.Saga;
import org.example.service.PersonaxeService;
import org.example.service.SagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(RestSagas.MAPPING)
public class RestSagas {

    public static final String MAPPING = "/postgres/sagas";

    @Autowired
    private SagaService sagaService;
    @Autowired
    private PersonaxeService personaxeService;

    @GetMapping
    public List<Saga> getAll() {
        return sagaService.findAll();
    }

    @PostMapping()
    public ResponseEntity<Saga> create(@RequestBody Saga saga) {
        Saga gardada = sagaService.save(saga);
        return ResponseEntity.ok(gardada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Saga> getById(@PathVariable Long id) {
        return sagaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/titulo/{nome}")
    public ResponseEntity<List<Saga>> findByTitulo(@PathVariable String nome) {
        List<Saga> sagas = sagaService.findByTitulo(nome);
        if (sagas == null || sagas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sagas);
    }

//    @PostMapping("/save")
//    public ResponseEntity<Saga> save(@RequestBody Saga saga) {
//        if (saga.getPersonaxes()!=null) {
//            saga.getPersonaxes().forEach(personaxe -> personaxe.setSaga(saga));
//        }
//        return ResponseEntity.ok(sagaService.save(saga));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!sagaService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        sagaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }



}
