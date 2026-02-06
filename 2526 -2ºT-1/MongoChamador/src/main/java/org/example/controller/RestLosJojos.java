package org.example.controller;

import org.example.model.LosJojos;
import org.example.service.LosJoJosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(RestLosJojos.MAPPING)
public class RestLosJojos {

    public static final String MAPPING = "/mongo/losjojos";

    @Autowired
    private LosJoJosService losJoJosService;

    @PostMapping
    public ResponseEntity<LosJojos> create(@RequestBody LosJojos losJojos) {
        LosJojos gardado = losJoJosService.save(losJojos);
        return ResponseEntity.ok(gardado);
    }

    @GetMapping
    public List<LosJojos> getAll() {
        return losJoJosService.findAll();
    }
}
