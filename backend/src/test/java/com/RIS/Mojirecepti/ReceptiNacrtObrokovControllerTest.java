package com.RIS.Mojirecepti;

import com.RIS.Mojirecepti.controller.ReceptiNacrtObrokovController;
import com.RIS.Mojirecepti.dto.MealPlanRequest;
import com.RIS.Mojirecepti.entity.MealType;
import com.RIS.Mojirecepti.entity.NacrtObrokov;
import com.RIS.Mojirecepti.entity.Recepti;
import com.RIS.Mojirecepti.entity.ReceptiNacrtObrokov;
import com.RIS.Mojirecepti.repository.NacrtObrokovRepository;
import com.RIS.Mojirecepti.repository.ReceptiNacrtObrokovRepository;
import com.RIS.Mojirecepti.repository.ReceptiRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;


import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ReceptiNacrtObrokovControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ReceptiNacrtObrokovRepository receptiNacrtObrokovRepository;

    @Mock
    private NacrtObrokovRepository nacrtObrokovRepository;

    @Mock
    private ReceptiRepository receptiRepository;

    @InjectMocks
    private ReceptiNacrtObrokovController receptiNacrtObrokovController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(receptiNacrtObrokovController).build();
    }

    @Test
    @DisplayName("Test Check Meal Plan Existence (Positive)")
    void testCheckMealPlanExistencePositive() throws Exception {
        String date = LocalDate.now().toString();
        when(nacrtObrokovRepository.existsByDatum(LocalDate.parse(date))).thenReturn(true);

        mockMvc.perform(get("/meal-plans/check")
                        .param("datum", date))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.exists").value(true));
    }

    @Test
    @DisplayName("Test Check Meal Plan Existence (Negative)")
    void testCheckMealPlanExistenceNegative() throws Exception {
        String date = LocalDate.now().toString();
        when(nacrtObrokovRepository.existsByDatum(LocalDate.parse(date))).thenReturn(false);

        mockMvc.perform(get("/meal-plans/check")
                        .param("datum", date))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.exists").value(false));
    }

    @Test
    @DisplayName("Test Create Meal Plan with Recipes (Positive)")
    void testCreateMealPlanWithRecipes() throws Exception {
        MealPlanRequest mealPlanRequest = new MealPlanRequest();
        mealPlanRequest.setDatum(LocalDate.now());
        mealPlanRequest.setRecipes(Arrays.asList(
                new MealPlanRequest.MealTypeRecipe("ZAJTRK", 1),  // breakfast
                new MealPlanRequest.MealTypeRecipe("KOSILO", 2),  // lunch
                new MealPlanRequest.MealTypeRecipe("VEČERJA", 3)  // dinner
        ));

        NacrtObrokov mealPlan = new NacrtObrokov(LocalDate.now());

        when(nacrtObrokovRepository.save(any(NacrtObrokov.class))).thenReturn(mealPlan);

        Recepti recepti1 = new Recepti();
        recepti1.setIdRecepti(1);
        Recepti recepti2 = new Recepti();
        recepti2.setIdRecepti(2);
        Recepti recepti3 = new Recepti();
        recepti3.setIdRecepti(3);

        when(receptiRepository.findById(1L)).thenReturn(java.util.Optional.of(recepti1));
        when(receptiRepository.findById(2L)).thenReturn(java.util.Optional.of(recepti2));
        when(receptiRepository.findById(3L)).thenReturn(java.util.Optional.of(recepti3)); // Mock dinner recipe

        ReceptiNacrtObrokov receptiNacrtObrokov1 = new ReceptiNacrtObrokov();
        receptiNacrtObrokov1.setRecepti(recepti1);
        receptiNacrtObrokov1.setMealType(MealType.ZAJTRK);
        receptiNacrtObrokov1.setNacrtObrokov(mealPlan);

        ReceptiNacrtObrokov receptiNacrtObrokov2 = new ReceptiNacrtObrokov();
        receptiNacrtObrokov2.setRecepti(recepti2);
        receptiNacrtObrokov2.setMealType(MealType.KOSILO);
        receptiNacrtObrokov2.setNacrtObrokov(mealPlan);

        ReceptiNacrtObrokov receptiNacrtObrokov3 = new ReceptiNacrtObrokov();
        receptiNacrtObrokov3.setRecepti(recepti3);
        receptiNacrtObrokov3.setMealType(MealType.VEČERJA);
        receptiNacrtObrokov3.setNacrtObrokov(mealPlan);

        when(receptiNacrtObrokovRepository.save(any(ReceptiNacrtObrokov.class)))
                .thenReturn(receptiNacrtObrokov1)
                .thenReturn(receptiNacrtObrokov2)
                .thenReturn(receptiNacrtObrokov3);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        mockMvc.perform(post("/meal-plans/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mealPlanRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.datum[0]").value(mealPlan.getDatum().getYear()))
                .andExpect(jsonPath("$.datum[1]").value(mealPlan.getDatum().getMonthValue()))
                .andExpect(jsonPath("$.datum[2]").value(mealPlan.getDatum().getDayOfMonth()));
    }
    @Test
    @DisplayName("Test Create Meal Plan with Recipes (Negative) - Recipe Not Found")
    void testCreateMealPlanWithRecipesNegative() throws Exception {
        MealPlanRequest mealPlanRequest = new MealPlanRequest();
        mealPlanRequest.setDatum(LocalDate.now());
        mealPlanRequest.setRecipes(Arrays.asList(
                new MealPlanRequest.MealTypeRecipe("ZAJTRK", 1),
                new MealPlanRequest.MealTypeRecipe("KOSILO", 2),
                new MealPlanRequest.MealTypeRecipe("VEČERJA", 999)
        ));

        NacrtObrokov mealPlan = new NacrtObrokov(LocalDate.now());

        when(nacrtObrokovRepository.save(any(NacrtObrokov.class))).thenReturn(mealPlan);

        Recepti recepti1 = new Recepti();
        recepti1.setIdRecepti(1);
        Recepti recepti2 = new Recepti();
        recepti2.setIdRecepti(2);

        when(receptiRepository.findById(1L)).thenReturn(java.util.Optional.of(recepti1));
        when(receptiRepository.findById(2L)).thenReturn(java.util.Optional.of(recepti2));
        when(receptiRepository.findById(999L)).thenReturn(java.util.Optional.empty());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        mockMvc.perform(post("/meal-plans/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mealPlanRequest)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Recipe not found with id: 999"));
    }


}
