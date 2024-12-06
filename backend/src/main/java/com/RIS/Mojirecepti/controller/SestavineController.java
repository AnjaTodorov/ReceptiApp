package com.RIS.Mojirecepti.controller;

import com.RIS.Mojirecepti.dto.SestavineRequest;
import com.RIS.Mojirecepti.entity.Sestavine;
import com.RIS.Mojirecepti.entity.Sestavine.Enota;
import com.RIS.Mojirecepti.entity.Recepti;
import com.RIS.Mojirecepti.repository.SestavineRepository;
import com.RIS.Mojirecepti.repository.ReceptiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/sestavine")
public class SestavineController {

    @Autowired
    private SestavineRepository sestavineRepository;

    @Autowired
    private ReceptiRepository receptiRepository;

    // Create a new Sestavine using a JSON payload
    @PostMapping
    public ResponseEntity<Sestavine> createSestavine(@RequestBody SestavineRequest sestavineRequest) {

        // Find the recipe by ID
        Recepti recepti = receptiRepository.findById(sestavineRequest.getIdRecepti()).orElse(null);
        if (recepti == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Recipe not found
        }

        // Convert enota to uppercase to match the enum value
        Enota parsedEnota = Enota.valueOf(sestavineRequest.getEnota().toUpperCase());

        // Create a new ingredient (Sestavine) and save it
        Sestavine sestavina = new Sestavine(
                sestavineRequest.getNaziv(),
                sestavineRequest.getKolicina(),
                parsedEnota,
                recepti
        );
        return ResponseEntity.ok(sestavineRepository.save(sestavina));
    }
    // Get all Sestavine
    @GetMapping
    public ResponseEntity<List<Sestavine>> getAllSestavine() {
        List<Sestavine> sestavine = sestavineRepository.findAll();
        return ResponseEntity.ok(sestavine);
    }

    // Get Sestavine by Recepti ID

    @GetMapping("/recepti/{idRecepti}")
    public ResponseEntity<List<Sestavine>> getSestavineByRecepti(@PathVariable long idRecepti) {
        List<Sestavine> sestavine = sestavineRepository.findByRecepti_IdRecepti((int) idRecepti);
        if (sestavine.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // No ingredients found for the given recipe ID
        }
        return ResponseEntity.ok(sestavine);
    }
}
