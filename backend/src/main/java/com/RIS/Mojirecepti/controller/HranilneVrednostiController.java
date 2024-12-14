package com.RIS.Mojirecepti.controller;

import com.RIS.Mojirecepti.dto.HranilneVrednostiRequest;
import com.RIS.Mojirecepti.entity.HranilneVrednosti;
import com.RIS.Mojirecepti.entity.HranilneVrednosti.Enota;
import com.RIS.Mojirecepti.entity.Recepti;
import com.RIS.Mojirecepti.entity.Sestavine;
import com.RIS.Mojirecepti.repository.HranilneVrednostiRepository;
import com.RIS.Mojirecepti.repository.ReceptiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hranilne-vrednosti")
@CrossOrigin(origins = "http://localhost:3000")
public class HranilneVrednostiController {

    @Autowired
    private HranilneVrednostiRepository hranilneVrednostiRepository;

    @Autowired
    private ReceptiRepository receptiRepository;

    // Create a new HranilneVrednosti
    @PostMapping
    public ResponseEntity<HranilneVrednosti> createHranilneVrednosti(@RequestBody HranilneVrednostiRequest hranilneVrednostiRequest) {

        // Find the associated recipe
        Recepti recepti = receptiRepository.findById(hranilneVrednostiRequest.getIdRecepti()).orElse(null);
        if (recepti == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Recipe not found
        }

        // Convert enota string to Enota enum
        Enota parsedEnota;
        try {
            parsedEnota = Enota.valueOf(hranilneVrednostiRequest.getEnota().toUpperCase());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Invalid enum value
        }

        // Create and save HranilneVrednosti
        HranilneVrednosti hranilneVrednosti = new HranilneVrednosti(
                hranilneVrednostiRequest.getNaziv(),
                hranilneVrednostiRequest.getKolicina(),
                parsedEnota,
                recepti
        );
        return ResponseEntity.ok(hranilneVrednostiRepository.save(hranilneVrednosti));
    }



    @GetMapping
    public ResponseEntity<List<HranilneVrednosti>> getAllHranilneVrednosti() {
        List<HranilneVrednosti> hranilneVrednosti = hranilneVrednostiRepository.findAll();
        return ResponseEntity.ok(hranilneVrednosti);
    }

    @GetMapping("/recepti/{idRecepti}")
    public ResponseEntity<List<HranilneVrednosti>> getHranilneVrednostiByRecepti(@PathVariable long idRecepti) {
        List<HranilneVrednosti> hranilneVrednosti = hranilneVrednostiRepository.findByRecepti_IdRecepti((int) idRecepti);
        if (hranilneVrednosti.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // No nutritional values found for the given recipe ID
        }
        return ResponseEntity.ok(hranilneVrednosti);
    }
}
