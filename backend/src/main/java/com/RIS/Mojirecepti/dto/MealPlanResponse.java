package com.RIS.Mojirecepti.dto;

import java.time.LocalDate;
import java.util.List;

public class MealPlanResponse {

    private Long idNacrtObrokov;
    private LocalDate datum;
    private List<RecipeDetail> recipes;

    public MealPlanResponse(Long idNacrtObrokov, LocalDate datum, List<RecipeDetail> recipes) {
        this.idNacrtObrokov = idNacrtObrokov;
        this.datum = datum;
        this.recipes = recipes;
    }

    // Getters and setters
    public Long getIdNacrtObrokov() {
        return idNacrtObrokov;
    }

    public void setIdNacrtObrokov(Long idNacrtObrokov) {
        this.idNacrtObrokov = idNacrtObrokov;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    public List<RecipeDetail> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeDetail> recipes) {
        this.recipes = recipes;
    }

    public static class RecipeDetail {
        private Long idRecepti;
        private String naziv;
        private String mealType;

        public RecipeDetail(Long idRecepti, String naziv, String mealType) {
            this.idRecepti = idRecepti;
            this.naziv = naziv;
            this.mealType = mealType;
        }

        // Getters and setters
        public Long getIdRecepti() {
            return idRecepti;
        }

        public void setIdRecepti(Long idRecepti) {
            this.idRecepti = idRecepti;
        }

        public String getNaziv() {
            return naziv;
        }

        public void setNaziv(String naziv) {
            this.naziv = naziv;
        }

        public String getMealType() {
            return mealType;
        }

        public void setMealType(String mealType) {
            this.mealType = mealType;
        }
    }
}
