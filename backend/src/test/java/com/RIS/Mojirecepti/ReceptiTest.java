package com.RIS.Mojirecepti;

import com.RIS.Mojirecepti.entity.Recepti;
import com.RIS.Mojirecepti.repository.ReceptiRepository;
import com.RIS.Mojirecepti.controller.ReceptiController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReceptiController.class)
public class ReceptiTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReceptiRepository receptiRepository;

    // Positive Test for Recipe Creation
    @Test
    @DisplayName("Test Recipe Creation (Positive - All fields provided)")
    void testCreateRecipePositive() throws Exception {
        // Mock file
        MockMultipartFile picture = new MockMultipartFile(
                "picture", "recipe_image.jpg", MediaType.IMAGE_JPEG_VALUE, "mock image content".getBytes());

        Recepti savedRecipe = new Recepti();
        savedRecipe.setIdRecepti(1);
        savedRecipe.setNaziv("Test Recipe");  // ✅ FIXED: "naziv" not "naziv"
        savedRecipe.setOpis("Test Description");
        savedRecipe.setTip(Recepti.Tip.zajtrk);
        savedRecipe.setOsebe(2);
        savedRecipe.setSlika("recipe_image.jpg");

        when(receptiRepository.save(any(Recepti.class))).thenReturn(savedRecipe);

        mockMvc.perform(multipart("/recepti")
                        .file(picture)
                        .param("naziv", "Test Recipe")  // ✅ FIXED: "naziv" not "naziv"
                        .param("opis", "Test Description")
                        .param("tip", "zajtrk")
                        .param("osebe", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idRecepti").value(1))
                .andExpect(jsonPath("$.naziv").value("Test Recipe"))
                .andExpect(jsonPath("$.opis").value("Test Description"))
                .andExpect(jsonPath("$.tip").value("zajtrk"))
                .andExpect(jsonPath("$.osebe").value(2))
                .andExpect(jsonPath("$.slika").value("recipe_image.jpg"));
    }

    // Negative Test for Recipe Creation (Missing required fields)
    @Test
    @DisplayName("Test Recipe Creation (Negative - Missing naziv)")
    void testCreateRecipeNegative() throws Exception {
        MockMultipartFile picture = new MockMultipartFile(
                "picture", "image.jpg", MediaType.IMAGE_JPEG_VALUE, new byte[0]);

        mockMvc.perform(multipart("/recepti")
                        .file(picture)
                        .param("opis", "Test Description")  // NO naziv!
                        .param("tip", "zajtrk")
                        .param("osebe", "2"))
                .andExpect(status().isBadRequest()); // ✅ TRUE 400!
    }

    @Test
    @DisplayName("Test Updating a Recipe (Positive)")
    void testUpdateRecipePositive() throws Exception {
        Long recipeId = 1L;  // ✅ FIXED: Long for repository

        // Existing recipe mock
        Recepti existingRecipe = new Recepti();
        existingRecipe.setIdRecepti(1);  // ✅ int for entity field
        existingRecipe.setNaziv("Old Recipe");
        existingRecipe.setOpis("Old Description");
        existingRecipe.setTip(Recepti.Tip.zajtrk);
        existingRecipe.setOsebe(2);
        existingRecipe.setSlika("old_image.jpg");

        when(receptiRepository.findById(recipeId)).thenReturn(Optional.of(existingRecipe));

        // Updated recipe mock
        Recepti updatedRecipe = new Recepti();
        updatedRecipe.setIdRecepti(1);
        updatedRecipe.setNaziv("Updated Recipe");
        updatedRecipe.setOpis("Updated Description");
        updatedRecipe.setTip(Recepti.Tip.zajtrk);
        updatedRecipe.setOsebe(4);
        updatedRecipe.setSlika("old_image.jpg");

        when(receptiRepository.save(any(Recepti.class))).thenReturn(updatedRecipe);

        // Updated recipe JSON
        String updatedRecipeJson = "{"
                + "\"naziv\": \"Updated Recipe\","
                + "\"opis\": \"Updated Description\","
                + "\"tip\": \"zajtrk\","
                + "\"osebe\": 4"
                + "}";

        mockMvc.perform(put("/recepti/{id}", recipeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatedRecipeJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idRecepti").value(1))
                .andExpect(jsonPath("$.naziv").value("Updated Recipe"))
                .andExpect(jsonPath("$.opis").value("Updated Description"))
                .andExpect(jsonPath("$.tip").value("zajtrk"))
                .andExpect(jsonPath("$.osebe").value(4))
                .andExpect(jsonPath("$.slika").value("old_image.jpg"));
    }

    @Test
    @DisplayName("Test Updating a Recipe (Negative - Non-existent Recipe ID)")
    void testUpdateRecipeNegative() throws Exception {
        Long recipeId = 999L;  //

        when(receptiRepository.findById(recipeId)).thenReturn(Optional.empty());

        String recipeJson = "{"
                + "\"naziv\": \"Updated Recipe\","
                + "\"opis\": \"Updated Description\","
                + "\"tip\": \"zajtrk\","
                + "\"osebe\": 4"
                + "}";

        mockMvc.perform(put("/recepti/{id}", recipeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(recipeJson))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Test Deleting a Recipe (Positive)")
    void testDeleteRecipePositive() throws Exception {
        Long recipeId = 1L;

        Recepti existingRecipe = new Recepti();
        existingRecipe.setIdRecepti(1);
        existingRecipe.setNaziv("Recipe to Delete");
        existingRecipe.setOpis("Recipe description");
        existingRecipe.setTip(Recepti.Tip.zajtrk);
        existingRecipe.setOsebe(2);
        existingRecipe.setSlika("image.jpg");

        when(receptiRepository.findById(recipeId)).thenReturn(Optional.of(existingRecipe));
        when(receptiRepository.existsById(recipeId)).thenReturn(true);  // 🔥 FIXED: ADD THIS!
        doNothing().when(receptiRepository).deleteById(recipeId);

        mockMvc.perform(delete("/recepti/{id}", recipeId))
                .andExpect(status().isNoContent());  // ✅ 204!
    }

    @Test
    @DisplayName("Test Deleting a Recipe (Negative - Non-existent ID)")
    void testDeleteRecipeNegative() throws Exception {
        Long recipeId = 999L;

        when(receptiRepository.existsById(recipeId)).thenReturn(false);

        mockMvc.perform(delete("/recepti/{id}", recipeId))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Test Getting a Recipe by ID (Positive)")
    void testGetRecipeByIdPositive() throws Exception {
        Long recipeId = 1L;

        Recepti existingRecipe = new Recepti();
        existingRecipe.setIdRecepti(1);
        existingRecipe.setNaziv("Test Recipe");
        existingRecipe.setOpis("Test Description");
        existingRecipe.setTip(Recepti.Tip.zajtrk);
        existingRecipe.setOsebe(2);
        existingRecipe.setSlika("image.jpg");

        when(receptiRepository.findById(recipeId)).thenReturn(Optional.of(existingRecipe));

        mockMvc.perform(get("/recepti/{id}", recipeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idRecepti").value(1))
                .andExpect(jsonPath("$.naziv").value("Test Recipe"))
                .andExpect(jsonPath("$.opis").value("Test Description"))
                .andExpect(jsonPath("$.tip").value("zajtrk"))
                .andExpect(jsonPath("$.osebe").value(2))
                .andExpect(jsonPath("$.slika").value("image.jpg"));
    }

    @Test
    @DisplayName("Test Getting a Recipe by ID (Negative - Non-existent ID)")
    void testGetRecipeByIdNegative() throws Exception {
        Long recipeId = 999L;

        when(receptiRepository.findById(recipeId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/recepti/{id}", recipeId))
                .andExpect(status().isNotFound());
    }





}