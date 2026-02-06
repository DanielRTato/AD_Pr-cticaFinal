package org.example.controller;

import org.example.model.Saga;
import org.example.model.Personaxe;
import org.example.service.SagaService;
import org.example.service.PersonaxeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(RestPersonaxes.MAPPING)
public class RestPersonaxes {

    public static final String MAPPING = "/postgres/personaxes";

    @Autowired
    private SagaService sagaService;
    @Autowired
    private PersonaxeService personaxeService;


    @GetMapping
    public List<Personaxe> getAll() {
        return personaxeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personaxe> getById(@PathVariable Long id) {
        return personaxeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public Optional<List<Personaxe>> findByNombreUsuario(@PathVariable String nome){
        return personaxeService.findByNombreUsuario(nome);
    }

    @PostMapping
    public ResponseEntity<Personaxe> create(@RequestBody Personaxe personaxe) {
        if (personaxe.getSaga() != null && personaxe.getSaga().getId() != null) {
            Saga eq = sagaService.findById(personaxe.getSaga().getId())
                    .orElse(null);
            if (eq == null) {
                return ResponseEntity.badRequest().build();
            }
            personaxe.setSaga(eq);
        }
        Personaxe gardado = personaxeService.save(personaxe);
        return ResponseEntity.ok(gardado);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!personaxeService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        personaxeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}