package com.RIS.Mojirecepti.controller;

import com.RIS.Mojirecepti.repository.ReceptiNacrtObrokovRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meal-plans")
@CrossOrigin(origins = "http://localhost:3000")
public class ReceptiNacrtObrokovController {

    @Autowired
    private ReceptiNacrtObrokovRepository mealPlanRepository;

    @GetMapping
    public List<com.RIS.Mojirecepti.entity.ReceptiNacrtObrokov> getAllMealPlans() {
        return mealPlanRepository.findAll();
    }

    @PostMapping
    public com.RIS.Mojirecepti.entity.ReceptiNacrtObrokov createMealPlan(@RequestBody com.RIS.Mojirecepti.entity.ReceptiNacrtObrokov mealPlan) {
        return mealPlanRepository.save(mealPlan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMealPlan(@PathVariable Long id) {
        mealPlanRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}