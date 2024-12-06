package com.RIS.Mojirecepti;

import com.RIS.Mojirecepti.entity.Recepti;
import com.RIS.Mojirecepti.repository.ReceptiRepository;
import com.RIS.Mojirecepti.controller.ReceptiController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.mock.web.MockMultipartFile;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ReceptiController.class)
public class ReceptiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReceptiRepository receptiRepository;


    @Test
    @DisplayName("Test Recipe Creation (Positive)")
    void testCreateRecipePositive() throws Exception {
        MockMultipartFile picture = new MockMultipartFile(
                "picture",
                "image.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                "mock file content".getBytes()
        );

        Recepti newRecipe = new Recepti();
        newRecipe.setNaziv("Test Recipe");
        newRecipe.setOpis("Test Description");
        newRecipe.setTip(Recepti.Tip.zajtrk);
        newRecipe.setSlika("image.jpg");

        when(receptiRepository.save(any(Recepti.class))).thenReturn(newRecipe);

        mockMvc.perform(multipart("/recepti")
                        .file(picture)  // Add the mock file to the request
                        .param("naziv", "Test Recipe")
                        .param("sestavine", "Ingredient 1, Ingredient 2")
                        .param("opis", "Test Description")
                        .param("tip", "zajtrk")
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.naziv").value("Test Recipe"))
                .andExpect(jsonPath("$.slika").value("image.jpg"));
    }

    // Negative Test for Recipe Creation (Missing required fields)
    @Test
    @DisplayName("Test Recipe Creation (Negative - Missing fields)")
    void testCreateRecipeNegative() throws Exception {
        mockMvc.perform(post("/recepti")
                        .param("naziv", "") // Missing name
                        .param("sestavine", "Ingredient 1, Ingredient 2")
                        .param("opis", "Test Description")
                        .param("tip", "zajtrk")
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .param("picture", "image.jpg"))
                .andExpect(status().isBadRequest()); // Should return 400 Bad Request
    }


    // Test for Deleting a Recipe
    @Test
    @DisplayName("Test Deleting a Recipe (Positive)")
    void testDeleteRecipe() throws Exception {
        Long recipeId = 1L;

        Recepti existingRecipe = new Recepti();
        existingRecipe.setNaziv("Recipe to Delete");
        existingRecipe.setOpis("Recipe description");
        existingRecipe.setTip(Recepti.Tip.zajtrk);

        when(receptiRepository.findById(recipeId)).thenReturn(Optional.of(existingRecipe));

        doNothing().when(receptiRepository).deleteById(recipeId);

        mockMvc.perform(delete("/recepti/{id}", recipeId))
                .andExpect(status().isNoContent());  // Should return 204 No Content
    }

    @Test
    @DisplayName("Test Updating a Recipe (Negative - Non-existent Recipe ID)")
    void testUpdateRecipeNegative() throws Exception {
        Long recipeId = 999L; // Non-existent recipe ID

        when(receptiRepository.findById(recipeId)).thenReturn(Optional.empty());

        String recipeJson = "{"
                + "\"naziv\": \"Updated Recipe\","
                + "\"sestavine\": \"Updated Ingredient 1, Ingredient 2\","
                + "\"opis\": \"Updated Description\","
                + "\"tip\": \"zajtrk\""
                + "}";

        mockMvc.perform(put("/recepti/{id}", recipeId)
                        .contentType(MediaType.APPLICATION_JSON)  // Set content type to JSON
                        .content(recipeJson))  // Set the body content as JSON
                .andExpect(status().isNotFound());  // Should return 404 Not Found
    }

    @Test
    @DisplayName("Test Updating a Recipe (Positive)")
    void testUpdateRecipe() throws Exception {
        Long recipeId = 1L;

        Recepti existingRecipe = new Recepti();
        existingRecipe.setNaziv("Old Recipe");
        existingRecipe.setOpis("Old Description");
        existingRecipe.setTip(Recepti.Tip.zajtrk);

        when(receptiRepository.findById(recipeId)).thenReturn(Optional.of(existingRecipe));

        Recepti updatedRecipe = new Recepti();
        updatedRecipe.setNaziv("Updated Recipe");
        updatedRecipe.setOpis("Updated Description");
        updatedRecipe.setTip(Recepti.Tip.zajtrk);

        when(receptiRepository.save(any(Recepti.class))).thenReturn(updatedRecipe);

        String updatedRecipeJson = "{"
                + "\"naziv\": \"Updated Recipe\","
                + "\"sestavine\": \"Updated Ingredient 1, Ingredient 2\","
                + "\"opis\": \"Updated Description\","
                + "\"tip\": \"zajtrk\""
                + "}";

        mockMvc.perform(put("/recepti/{id}", recipeId)
                        .contentType(MediaType.APPLICATION_JSON)  // Set content type to JSON
                        .content(updatedRecipeJson))  // Set the body content as JSON
                .andExpect(status().isOk())  // Should return 200 OK
                .andExpect(jsonPath("$.naziv").value("Updated Recipe"))
                .andExpect(jsonPath("$.sestavine").value("Updated Ingredient 1, Ingredient 2"))
                .andExpect(jsonPath("$.opis").value("Updated Description"))
                .andExpect(jsonPath("$.tip").value("zajtrk"));
    }

    // Test for Fetching Recipe by ID
    @Test
    @DisplayName("Test Get Recipe by ID (Positive)")
    void testGetRecipeByIdPositive() throws Exception {
        Long recipeId = 1L;
        Recepti recipe = new Recepti();
        recipe.setNaziv("Test Recipe");

        when(receptiRepository.findById(recipeId)).thenReturn(Optional.of(recipe));

        mockMvc.perform(get("/recepti/{id}", recipeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.naziv").value("Test Recipe"));
    }

    // Test for Fetching Recipes by Meal Type
    @ParameterizedTest
    @ValueSource(strings = {"zajtrk", "kosilo", "večerja"})
    @DisplayName("Test Fetch Recipes by Meal Type")
    void testGetRecipesByMealTypePositive(String mealType) throws Exception {
        Recepti recipe = new Recepti();
        recipe.setNaziv("Test Recipe");
        recipe.setTip(Recepti.Tip.valueOf(mealType));

        when(receptiRepository.findByTip(Recepti.Tip.valueOf(mealType))).thenReturn(List.of(recipe));

        mockMvc.perform(get("/recepti/tip/{tip}", mealType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].naziv").value("Test Recipe"))
                .andExpect(jsonPath("$[0].tip").value(mealType));
    }
}
