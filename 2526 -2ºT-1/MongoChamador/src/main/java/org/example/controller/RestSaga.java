package org.example.controller;

import org.example.model.Saga;
import org.example.service.SagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RestSaga.MAPPING)
public class RestSaga {

    public static final String MAPPING = "/mongo/sagas";

    @Autowired
    private SagaService sagaService;

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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        sagaService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<Saga>> getByTitulo(@PathVariable String titulo) {
        List<Saga> sagas = sagaService.findAll().stream()
                .filter(s -> s.getTitulo() != null && s.getTitulo().equals(titulo))
                .toList();
        return ResponseEntity.ok(sagas);
    }
}
