package com.example.beadando.controller;

import com.example.beadando.entity.Diak;
import com.example.beadando.repository.DiakRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/diakok")
public class RestApiController {

    @Autowired
    private DiakRepository diakRepository;

    @GetMapping
    public List<Diak> getAll() {
        return diakRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diak> getOne(@PathVariable Long id) {
        return diakRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Diak create(@RequestBody Diak diak) {
        return diakRepository.save(diak);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Diak> update(@PathVariable Long id,
                                       @RequestBody Diak diak) {
        if (!diakRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        diak.setId(id);
        return ResponseEntity.ok(diakRepository.save(diak));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!diakRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        diakRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}