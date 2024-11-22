package com.RIS.Mojirecepti.dto;


import com.RIS.Mojirecepti.entity.MealType;

import java.time.LocalDate;
import java.util.List;

public class MealPlanRequest {

    private LocalDate datum;  // The date for the meal plan
    private List<MealTypeRecipe> recipes;  // List of recipes with meal types

    // Getters and setters

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public List<MealTypeRecipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<MealTypeRecipe> recipes) {
        this.recipes = recipes;
    }

    // Inner class for handling meal type and recipe ID
    public static class MealTypeRecipe {
        private String mealType;  // Meal type (e.g., Zajtrk, Kosilo, Večerja)
        private int recipeId;     // Recipe ID

        // Getters and setters
        public String getMealType() {
            return mealType;
        }

        public void setMealType(String mealType) {
            this.mealType = mealType;
        }

        public long getRecipeId() {
            return recipeId;
        }

        public void setRecipeId(int recipeId) {
            this.recipeId = recipeId;
        }
    }
}