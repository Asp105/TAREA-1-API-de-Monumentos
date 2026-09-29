package com.salesianos.dam.ejercicio1;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/monument")
public class MonumentController {

    @Autowired
    private MonumentRepository monumentRepository;

    // GET /monument
    @GetMapping
    public ResponseEntity<List<Monument>> getAllMonuments() {
        List<Monument> monuments = monumentRepository.findAll();
        if (monuments.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(monuments);
    }

    // GET /monument/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Monument> getMonumentById(@PathVariable Long id) {
        return ResponseEntity.of(monumentRepository.findById(id));
    }

    // POST /monument
    @PostMapping
    public ResponseEntity<Monument> addMonument(@RequestBody Monument monument) {
        if (StringUtils.hasText(monument.getName())) {
            return ResponseEntity.status(201)
                    .body(monumentRepository.save(monument));
        }

        return ResponseEntity.badRequest().build();

    }

    // PUT /monument/{id}
    @PutMapping("/{id}")
    public ResponseEntity<Monument> updateMonument(@PathVariable Long id, @RequestBody Monument monument) {
        return monumentRepository.findById(id)
                .map(m -> {
                    m.setName(monument.getName());
                    m.setCode(monument.getCode());
                    m.setImagen(monument.getImagen());
                    return ResponseEntity.ok(monumentRepository.save(m));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // DELETE /monument/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMonument(@PathVariable Long id) {
        monumentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

