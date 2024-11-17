package com.RIS.Mojirecepti.controller;

import com.RIS.Mojirecepti.entity.Recepti;
import com.RIS.Mojirecepti.repository.ReceptiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
//matej
@RestController
@RequestMapping("/recepti")
@CrossOrigin(origins = "http://localhost:3000")
public class ReceptiController {

    @Autowired
    private ReceptiRepository receptiRepository;

    private final String uploadDir = "C:/Users/Ile/Desktop/3.semestar/RIS/ReceptiApp/frontend/sliki";

    @GetMapping
    public List<Recepti> getAllRecepti() {
        return receptiRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recepti> getReceptiById(@PathVariable Long id) {
        return receptiRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/tip/{tip}")
    public List<Recepti> getReceptiByTip(@PathVariable Recepti.Tip tip) {
        return receptiRepository.findByTip(tip);
    }

    @PostMapping
    public Recepti createRecept(
            @RequestParam("naziv") String naziv,
            @RequestParam("sestavine") String sestavine,
            @RequestParam("opis") String opis,
            @RequestParam("tip") Recepti.Tip tip,
            @RequestParam("picture") MultipartFile picture
    ) throws IOException {
        String pictureFileName = picture.getOriginalFilename();
        File file = new File(uploadDir + pictureFileName);
        picture.transferTo(file);

        Recepti recepti = new Recepti();
        recepti.setNaziv(naziv);
        recepti.setSlika(pictureFileName);
        recepti.setSestavine(sestavine);
        recepti.setOpis(opis);
        recepti.setTip(tip);

        return receptiRepository.save(recepti);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recepti> updateRecept(@PathVariable Long id, @RequestBody Recepti receptiDetails) {
        Recepti recepti = receptiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recept not found with id: " + id));

        recepti.setNaziv(receptiDetails.getNaziv());
        recepti.setSestavine(receptiDetails.getSestavine());
        recepti.setOpis(receptiDetails.getOpis());
        recepti.setTip(receptiDetails.getTip());

        Recepti updatedRecepti = receptiRepository.save(recepti);
        return ResponseEntity.ok(updatedRecepti);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecept(@PathVariable Long id) {
        receptiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recept not found with id: " + id));
        receptiRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
