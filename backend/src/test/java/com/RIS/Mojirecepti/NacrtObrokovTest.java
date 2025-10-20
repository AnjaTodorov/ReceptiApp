package com.RIS.Mojirecepti;

import com.RIS.Mojirecepti.controller.ReceptiNacrtObrokovController;
import com.RIS.Mojirecepti.dto.MealPlanRequest;
import com.RIS.Mojirecepti.entity.NacrtObrokov;
import com.RIS.Mojirecepti.entity.Recepti;
import com.RIS.Mojirecepti.entity.ReceptiNacrtObrokov;
import com.RIS.Mojirecepti.repository.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReceptiNacrtObrokovController.class)
public class NacrtObrokovTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NacrtObrokovRepository nacrtObrokovRepository;

    @MockBean
    private ReceptiNacrtObrokovRepository receptiNacrtObrokovRepository;

    @MockBean
    private ReceptiRepository receptiRepository;

    @MockBean
    private SestavineRepository sestavineRepository;

    @MockBean
    private HranilneVrednostiRepository hranilneVrednostiRepository;

    @Test
    @DisplayName("Test Create Meal Plan with Recipes (Positive)")
    void testCreateMealPlanWithRecipes() throws Exception {
        // Create request
        MealPlanRequest request = new MealPlanRequest();
        request.setDatum(LocalDate.now());
        request.setRecipes(Arrays.asList(
                new MealPlanRequest.MealTypeRecipe("ZAJTRK", 1),
                new MealPlanRequest.MealTypeRecipe("KOSILO", 2),
                new MealPlanRequest.MealTypeRecipe("VEČERJA", 3)
        ));


        when(nacrtObrokovRepository.save(any(NacrtObrokov.class)))
                .thenAnswer(invocation -> {
                    NacrtObrokov nacrt = invocation.getArgument(0);  // Get controller's object
                    nacrt.setIdNacrtObrokov(1);                      // Set ID=1!
                    return nacrt;                                    // Return SAME object
                });

        // Mock recipes
        Recepti r1 = new Recepti(); r1.setIdRecepti(1);
        Recepti r2 = new Recepti(); r2.setIdRecepti(2);
        Recepti r3 = new Recepti(); r3.setIdRecepti(3);
        when(receptiRepository.findById(1L)).thenReturn(Optional.of(r1));
        when(receptiRepository.findById(2L)).thenReturn(Optional.of(r2));
        when(receptiRepository.findById(3L)).thenReturn(Optional.of(r3));

        // Mock link saves
        when(receptiNacrtObrokovRepository.save(any(ReceptiNacrtObrokov.class)))
                .thenReturn(new ReceptiNacrtObrokov());

        // JSON setup
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // Perform test
        mockMvc.perform(post("/meal-plans/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idNacrtObrokov").value(1))
                .andExpect(jsonPath("$.datum").value(LocalDate.now().toString()));
    }

    @Test
    @DisplayName("Test Create Meal Plan with Recipes (Negative) - Recipe Not Found")
    void testCreateMealPlanWithRecipesNegative() throws Exception {
        // Create request with invalid recipe ID
        MealPlanRequest request = new MealPlanRequest();
        request.setDatum(LocalDate.now());
        request.setRecipes(Arrays.asList(
                new MealPlanRequest.MealTypeRecipe("ZAJTRK", 1),
                new MealPlanRequest.MealTypeRecipe("KOSILO", 2),
                new MealPlanRequest.MealTypeRecipe("VEČERJA", 999)
        ));

        // Mock meal plan save
        when(nacrtObrokovRepository.save(any(NacrtObrokov.class))).thenReturn(new NacrtObrokov());

        // Mock valid recipes
        when(receptiRepository.findById(1L)).thenReturn(Optional.of(new Recepti()));
        when(receptiRepository.findById(2L)).thenReturn(Optional.of(new Recepti()));
        // Mock invalid recipe
        when(receptiRepository.findById(999L)).thenReturn(Optional.empty());

        // JSON setup
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // Perform test
        mockMvc.perform(post("/meal-plans/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Recipe not found with id: 999"));
    }
}