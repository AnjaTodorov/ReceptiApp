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


    String uploadDir = "C:/Users/anjat/Desktop/RIS/Recepti/frontend/sliki/";


    @GetMapping
    public List<Recepti> getAllRecepti() {
        return receptiRepository.findAll();
    }

    @GetMapping("/{id}")
    public Recepti getReceptiById(@PathVariable Long id) {
        return receptiRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Recepti createRecept(
            @RequestParam("naziv") String naziv,
            @RequestParam("sestavine") String sestavine,
            @RequestParam("opis") String opis,
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

        return receptiRepository.save(recepti);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recepti> updateRecept(@PathVariable Long id, @RequestBody Recepti receptiDetails) {
        Recepti recepti = receptiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recept not found with id: " + id));

        // Update the fields
        recepti.setNaziv(receptiDetails.getNaziv());
        recepti.setSestavine(receptiDetails.getSestavine());
        recepti.setOpis(receptiDetails.getOpis());

        // Save the updated recipe
        Recepti updatedRecepti = receptiRepository.save(recepti);
        return ResponseEntity.ok(updatedRecepti); // Return updated recipe with 200 OK
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecept(@PathVariable Long id) {
        receptiRepository.findById(id).orElseThrow(() -> new RuntimeException("Recept not found with id: " + id));
        receptiRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
