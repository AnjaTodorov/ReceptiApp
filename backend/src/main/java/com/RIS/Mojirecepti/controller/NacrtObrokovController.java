package com.RIS.Mojirecepti.controller;

import com.RIS.Mojirecepti.entity.NacrtObrokov;
import com.RIS.Mojirecepti.repository.NacrtObrokovRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nacrt-obrokov")
@CrossOrigin(origins = "http://localhost:3000")
public class NacrtObrokovController {

    @Autowired
    private NacrtObrokovRepository nacrtObrokovRepository;

    @GetMapping
    public List<NacrtObrokov> getAllNacrtiObrokov() {
        return nacrtObrokovRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<NacrtObrokov> getNacrtObrokovById(@PathVariable Long id) {
        return nacrtObrokovRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public NacrtObrokov createNacrtObrokov(@RequestBody NacrtObrokov nacrtObrokov) {
        return nacrtObrokovRepository.save(nacrtObrokov);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NacrtObrokov> updateNacrtObrokov(
            @PathVariable Long id,
            @RequestBody NacrtObrokov nacrtObrokovDetails
    ) {
        NacrtObrokov nacrtObrokov = nacrtObrokovRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nacrt obrokov not found with id: " + id));

        nacrtObrokov.setDatum(nacrtObrokovDetails.getDatum());
        NacrtObrokov updatedNacrtObrokov = nacrtObrokovRepository.save(nacrtObrokov);

        return ResponseEntity.ok(updatedNacrtObrokov);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNacrtObrokov(@PathVariable Long id) {
        nacrtObrokovRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}