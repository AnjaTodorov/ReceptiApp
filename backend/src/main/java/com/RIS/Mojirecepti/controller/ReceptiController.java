package com.RIS.Mojirecepti.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.RIS.Mojirecepti.entity.Recepti;
import com.RIS.Mojirecepti.repository.ReceptiRepository;

// Matej
@RestController
@RequestMapping("/recepti")
@CrossOrigin(origins = "http://localhost:3000")
public class ReceptiController {

    @Autowired
    private ReceptiRepository receptiRepository;

    private final String uploadDir = "C:/Users/Ile/Desktop/3.semestar/RIS/ReceptiApp/frontend/sliki/";

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
            @RequestParam("opis") String opis,
            @RequestParam("tip") Recepti.Tip tip,
            @RequestParam("osebe") int osebe,
            @RequestParam("picture") MultipartFile picture
    ) throws IOException {
        String pictureFileName = picture.getOriginalFilename();

       
        if (!"test".equals(System.getProperty("spring.profiles.active"))) {
            File file = new File(uploadDir + pictureFileName);
            picture.transferTo(file);  // Line 63
        }

        Recepti recepti = new Recepti();
        recepti.setNaziv(naziv);
        recepti.setSlika(pictureFileName);
        recepti.setOpis(opis);
        recepti.setTip(tip);
        recepti.setOsebe(osebe);

        return receptiRepository.save(recepti);
    }




    @PutMapping("/{id}")
    public ResponseEntity<Recepti> updateRecept(@PathVariable Long id, @RequestBody Recepti receptiDetails) {
        Recepti recepti = receptiRepository.findById(id)
                .orElse(null);

        if (recepti == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        recepti.setNaziv(receptiDetails.getNaziv());
        recepti.setOpis(receptiDetails.getOpis());
        recepti.setTip(receptiDetails.getTip());
        recepti.setOsebe(receptiDetails.getOsebe()); // Update the number of people

        Recepti updatedRecepti = receptiRepository.save(recepti);
        return ResponseEntity.ok(updatedRecepti);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecept(@PathVariable Long id) {
        if (!receptiRepository.existsById(id)) {  //
            return ResponseEntity.notFound().build();
        }
        receptiRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
