package com.RIS.Mojirecepti.controller;

import com.RIS.Mojirecepti.dto.MealPlanExistenceResponse;
import com.RIS.Mojirecepti.entity.MealType;
import com.RIS.Mojirecepti.entity.NacrtObrokov;
import com.RIS.Mojirecepti.entity.Recepti;
import com.RIS.Mojirecepti.entity.ReceptiNacrtObrokov;
import com.RIS.Mojirecepti.dto.MealPlanRequest;
import com.RIS.Mojirecepti.repository.NacrtObrokovRepository;
import com.RIS.Mojirecepti.repository.ReceptiNacrtObrokovRepository;
import com.RIS.Mojirecepti.repository.ReceptiRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.RIS.Mojirecepti.dto.MealPlanResponse;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/meal-plans")
@CrossOrigin(origins = "http://localhost:3000")
public class ReceptiNacrtObrokovController {

    @Autowired
    private ReceptiNacrtObrokovRepository receptiNacrtObrokovRepository;

    @Autowired
    private NacrtObrokovRepository nacrtObrokovRepository;

    @Autowired
    private ReceptiRepository receptiRepository;

    @GetMapping
    public List<MealPlanResponse> getAllMealPlans() {
        // Fetch all meal plans from the repository
        List<NacrtObrokov> mealPlans = nacrtObrokovRepository.findAll();

        // Map each meal plan to a structured response
        return mealPlans.stream().map(mealPlan -> {
            // Fetch recipes for the current meal plan
            List<ReceptiNacrtObrokov> recipesForMealPlan = receptiNacrtObrokovRepository.findByNacrtObrokov(mealPlan);

            // Map recipes to a list of structured responses
            List<MealPlanResponse.RecipeDetail> recipeDetails = recipesForMealPlan.stream()
                    .map(recipeLink -> new MealPlanResponse.RecipeDetail(
                            (long) recipeLink.getRecepti().getIdRecepti(),
                            recipeLink.getRecepti().getNaziv(),
                            recipeLink.getMealType().toString()
                    ))
                    .collect(Collectors.toList());

            // Return a response for the current meal plan
            return new MealPlanResponse(
                    (long) mealPlan.getIdNacrtObrokov(),
                    mealPlan.getDatum(),
                    recipeDetails
            );
        }).collect(Collectors.toList());
    }


    // Check if a meal plan already exists for the given date
    @GetMapping("/check")
    public ResponseEntity<?> checkMealPlanExistence(@RequestParam("datum") String datum) {
        LocalDate date = LocalDate.parse(datum);
        // Check if a meal plan already exists for this date
        boolean exists = nacrtObrokovRepository.existsByDatum(date);
        return ResponseEntity.ok(new MealPlanExistenceResponse(exists));
    }

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createMealPlan(@RequestBody @Valid MealPlanRequest request) {
        // Create a new NacrtObrokov (meal plan) with the date
        NacrtObrokov nacrtObrokov = new NacrtObrokov();
        nacrtObrokov.setDatum(request.getDatum());  // Set the date of the meal plan
        nacrtObrokovRepository.save(nacrtObrokov);  // Save the new meal plan and get the ID

        // Save the recipes linked to the new meal plan
        for (MealPlanRequest.MealTypeRecipe mealTypeRecipe : request.getRecipes()) {
            // Find the recipe by ID
            Recepti recepti = receptiRepository.findById(mealTypeRecipe.getRecipeId())
                    .orElse(null); // Get the recipe or null if not found

            if (recepti == null) {
                // Return a 404 Not Found with a custom error message if recipe is not found
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("error", "Recipe not found with id: " + mealTypeRecipe.getRecipeId());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);  // Return 404 with error message
            }

            // Create a new link between the recipe and the meal plan
            ReceptiNacrtObrokov receptiNacrtObrokov = new ReceptiNacrtObrokov();
            receptiNacrtObrokov.setRecepti(recepti);
            receptiNacrtObrokov.setNacrtObrokov(nacrtObrokov);  // Link the new meal plan
            receptiNacrtObrokov.setMealType(MealType.valueOf(mealTypeRecipe.getMealType()));  // Set the meal type (e.g., breakfast, lunch)

            // Save the link between recipe and meal plan
            receptiNacrtObrokovRepository.save(receptiNacrtObrokov);
        }

        // Build a response with the ID of the newly created meal plan
        Map<String, Object> response = new HashMap<>();
        response.put("idNacrtObrokov", nacrtObrokov.getIdNacrtObrokov());
        response.put("datum", nacrtObrokov.getDatum());

        return ResponseEntity.ok(response);  // Return the response as JSON
    }
/*
    @GetMapping("/meal-plan/{id}/ingredients")
    public List<String> getIngredientsByMealPlan(@PathVariable("id") int nacrtObrokovId) {
        List<ReceptiNacrtObrokov> receptiNacrtObrokovList = receptiNacrtObrokovRepository.findByNacrtObrokovId(nacrtObrokovId);

        return receptiNacrtObrokovList.stream()
                .map(receptiNacrtObrokov -> receptiNacrtObrokov.getRecepti().getSestavine())
                .collect(Collectors.toList());
    }*/

}



