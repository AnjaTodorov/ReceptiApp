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

        // Existing recipe mock
        Recepti existingRecipe = new Recepti();
        existingRecipe.setNaziv("Old Recipe");
        existingRecipe.setOpis("Old Description");
        existingRecipe.setTip(Recepti.Tip.zajtrk);
        existingRecipe.setOsebe(2); // Existing number of people

        when(receptiRepository.findById(recipeId)).thenReturn(Optional.of(existingRecipe));

        // Updated recipe mock
        Recepti updatedRecipe = new Recepti();
        updatedRecipe.setNaziv("Updated Recipe");
        updatedRecipe.setOpis("Updated Description");
        updatedRecipe.setTip(Recepti.Tip.zajtrk);
        updatedRecipe.setOsebe(4); // Updated number of people

        when(receptiRepository.save(any(Recepti.class))).thenReturn(updatedRecipe);

        // Updated recipe JSON
        String updatedRecipeJson = "{"
                + "\"naziv\": \"Updated Recipe\","
                + "\"opis\": \"Updated Description\","
                + "\"tip\": \"zajtrk\","
                + "\"osebe\": 4"
                + "}";

        mockMvc.perform(put("/recepti/{id}", recipeId)
                        .contentType(MediaType.APPLICATION_JSON) // Set content type to JSON
                        .content(updatedRecipeJson)) // Set the body content as JSON
                .andExpect(status().isOk()) // Should return 200 OK
                .andExpect(jsonPath("$.naziv").value("Updated Recipe"))
                .andExpect(jsonPath("$.opis").value("Updated Description"))
                .andExpect(jsonPath("$.tip").value("zajtrk"))
                .andExpect(jsonPath("$.osebe").value(4)); // Assert 'osebe' field
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
