package com.RIS.Mojirecepti.dto;


import com.RIS.Mojirecepti.entity.MealType;
import jakarta.validation.constraints.NotEmpty;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MealPlanRequest {
    @NotNull
    private LocalDate datum;  // The date for the meal
    @NotEmpty
    private List<MealTypeRecipe> recipes;  // List of recipes with meal types

    public <E> MealPlanRequest(String date, ArrayList<E> es) {
    }

    public MealPlanRequest() {

    }

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

        public MealTypeRecipe(String mealType, int recipeId) {
            this.mealType = mealType;
            this.recipeId = recipeId;
        }

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