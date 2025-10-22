package com.RIS.Mojirecepti;

import com.RIS.Mojirecepti.controller.ReceptiController;
import com.RIS.Mojirecepti.controller.SestavineController;
import com.RIS.Mojirecepti.controller.HranilneVrednostiController;
import com.RIS.Mojirecepti.controller.NacrtObrokovController;
import com.RIS.Mojirecepti.controller.ReceptiNacrtObrokovController;
import com.RIS.Mojirecepti.dto.HranilneVrednostiRequest;
import com.RIS.Mojirecepti.dto.MealPlanRequest;
import com.RIS.Mojirecepti.dto.MealPlanResponse;
import com.RIS.Mojirecepti.dto.SestavineRequest;
import com.RIS.Mojirecepti.entity.*;
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
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {ReceptiController.class, SestavineController.class, HranilneVrednostiController.class, NacrtObrokovController.class, ReceptiNacrtObrokovController.class})
public class AdditionalControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReceptiRepository receptiRepository;

    @MockBean
    private SestavineRepository sestavineRepository;

    @MockBean
    private HranilneVrednostiRepository hranilneVrednostiRepository;

    @MockBean
    private NacrtObrokovRepository nacrtObrokovRepository;

    @MockBean
    private ReceptiNacrtObrokovRepository receptiNacrtObrokovRepository;

    @Test
    @DisplayName("Test Fetch All Recipes (Positive)")
    void testGetAllRecipesPositive() throws Exception {
        Recepti recipe1 = new Recepti();
        recipe1.setIdRecepti(1);
        recipe1.setNaziv("Recipe 1");
        recipe1.setTip(Recepti.Tip.zajtrk);

        Recepti recipe2 = new Recepti();
        recipe2.setIdRecepti(2);
        recipe2.setNaziv("Recipe 2");
        recipe2.setTip(Recepti.Tip.kosilo);

        when(receptiRepository.findAll()).thenReturn(Arrays.asList(recipe1, recipe2));

        mockMvc.perform(get("/recepti"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idRecepti").value(1))
                .andExpect(jsonPath("$[0].naziv").value("Recipe 1"))
                .andExpect(jsonPath("$[1].idRecepti").value(2))
                .andExpect(jsonPath("$[1].naziv").value("Recipe 2"));
    }

    @Test
    @DisplayName("Test Create Ingredient (Positive)")
    void testCreateSestavinePositive() throws Exception {
        SestavineRequest request = new SestavineRequest();
        request.setIdRecepti(1L);
        request.setNaziv("Sugar");
        request.setKolicina(BigDecimal.valueOf(100));
        request.setEnota("g");

        Recepti recepti = new Recepti();
        recepti.setIdRecepti(1);

        when(receptiRepository.findById(1L)).thenReturn(Optional.of(recepti));

        Sestavine sestavina = new Sestavine();
        sestavina.setNaziv("Sugar");
        sestavina.setKolicina(BigDecimal.valueOf(100));
        sestavina.setEnota(Sestavine.Enota.G);
        sestavina.setRecepti(recepti);

        when(sestavineRepository.save(any(Sestavine.class))).thenReturn(sestavina);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/sestavine")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.naziv").value("Sugar"))
                .andExpect(jsonPath("$.kolicina").value(100))
                .andExpect(jsonPath("$.enota").value("G"));
    }

    @Test
    @DisplayName("Test Fetch Ingredients by Recipe ID (Positive)")
    void testGetSestavineByReceptiPositive() throws Exception {
        Sestavine sestavina1 = new Sestavine();
        sestavina1.setNaziv("Flour");
        sestavina1.setKolicina(BigDecimal.valueOf(200));
        sestavina1.setEnota(Sestavine.Enota.G);

        Sestavine sestavina2 = new Sestavine();
        sestavina2.setNaziv("Sugar");
        sestavina2.setKolicina(BigDecimal.valueOf(100));
        sestavina2.setEnota(Sestavine.Enota.G);

        when(sestavineRepository.findByRecepti_IdRecepti(1)).thenReturn(Arrays.asList(sestavina1, sestavina2));

        mockMvc.perform(get("/sestavine/recepti/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].naziv").value("Flour"))
                .andExpect(jsonPath("$[1].naziv").value("Sugar"));
    }

    @Test
    @DisplayName("Test Fetch Nutritional Values by Recipe ID (Positive)")
    void testGetHranilneVrednostiByReceptiPositive() throws Exception {
        HranilneVrednosti hranilna1 = new HranilneVrednosti();
        hranilna1.setNaziv("Calories");
        hranilna1.setKolicina(BigDecimal.valueOf(500));
        hranilna1.setEnota(HranilneVrednosti.Enota.KJ);

        HranilneVrednosti hranilna2 = new HranilneVrednosti();
        hranilna2.setNaziv("Protein");
        hranilna2.setKolicina(BigDecimal.valueOf(20));
        hranilna2.setEnota(HranilneVrednosti.Enota.G);

        when(hranilneVrednostiRepository.findByRecepti_IdRecepti(1)).thenReturn(Arrays.asList(hranilna1, hranilna2));

        mockMvc.perform(get("/hranilne-vrednosti/recepti/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].naziv").value("Calories"))
                .andExpect(jsonPath("$[1].naziv").value("Protein"));
    }

    @Test
    @DisplayName("Test Create Meal Plan (Positive)")
    void testCreateNacrtObrokovPositive() throws Exception {
        NacrtObrokov nacrtObrokov = new NacrtObrokov();
        nacrtObrokov.setDatum(LocalDate.now());

        when(nacrtObrokovRepository.save(any(NacrtObrokov.class))).thenReturn(nacrtObrokov);

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        mockMvc.perform(post("/nacrt-obrokov")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(nacrtObrokov)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.datum").value(LocalDate.now().toString()));
    }

    @Test
    @DisplayName("Test Fetch All Meal Plans (Positive)")
    void testGetAllMealPlansPositive() throws Exception {
        NacrtObrokov mealPlan1 = new NacrtObrokov();
        mealPlan1.setIdNacrtObrokov(1);
        mealPlan1.setDatum(LocalDate.now());

        ReceptiNacrtObrokov link1 = new ReceptiNacrtObrokov();
        Recepti recepti1 = new Recepti();
        recepti1.setIdRecepti(1);
        recepti1.setNaziv("Breakfast Recipe");
        link1.setRecepti(recepti1);
        link1.setMealType(MealType.ZAJTRK);
        link1.setNacrtObrokov(mealPlan1);

        when(nacrtObrokovRepository.findAll()).thenReturn(Arrays.asList(mealPlan1));
        when(receptiNacrtObrokovRepository.findByNacrtObrokov(mealPlan1)).thenReturn(Arrays.asList(link1));

        mockMvc.perform(get("/meal-plans"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idNacrtObrokov").value(1))
                .andExpect(jsonPath("$[0].datum").value(LocalDate.now().toString()))
                .andExpect(jsonPath("$[0].recipes[0].idRecepti").value(1))
                .andExpect(jsonPath("$[0].recipes[0].naziv").value("Breakfast Recipe"))
                .andExpect(jsonPath("$[0].recipes[0].mealType").value("ZAJTRK"));
    }
    @Test
    @DisplayName("Test Fetch All Recipes (Negative - Empty List)")
    void testGetAllRecipesEmptyList() throws Exception {
        when(receptiRepository.findAll()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/recepti"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @DisplayName("Test Create Ingredient (Negative - Non-existent Recipe ID)")
    void testCreateSestavineNonExistentRecipe() throws Exception {
        SestavineRequest request = new SestavineRequest();
        request.setIdRecepti(999L);
        request.setNaziv("Sugar");
        request.setKolicina(BigDecimal.valueOf(100));
        request.setEnota("g");

        when(receptiRepository.findById(999L)).thenReturn(Optional.empty());

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/sestavine")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Test Fetch Ingredients by Recipe ID (Negative - No Ingredients Found)")
    void testGetSestavineByReceptiNotFound() throws Exception {
        when(sestavineRepository.findByRecepti_IdRecepti(999)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/sestavine/recepti/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Test Create Nutritional Value (Negative - Invalid Enum Value)")
    void testCreateHranilneVrednostiInvalidEnum() throws Exception {
        HranilneVrednostiRequest request = new HranilneVrednostiRequest();
        request.setIdRecepti(1L);
        request.setNaziv("Calories");
        request.setKolicina(BigDecimal.valueOf(500));
        request.setEnota("INVALID"); // Invalid enum value

        when(receptiRepository.findById(1L)).thenReturn(Optional.of(new Recepti()));

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(post("/hranilne-vrednosti")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Test Create Meal Plan (Negative - Invalid Date)")
    void testCreateNacrtObrokovInvalidDate() throws Exception {
        // Create a JSON with an invalid date format
        String invalidDateJson = "{\"datum\": \"invalid-date\"}";

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        mockMvc.perform(post("/nacrt-obrokov")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidDateJson))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Test Fetch Meal Plan Ingredients (Negative - Non-existent Meal Plan)")
    void testGetMealPlanIngredientsNonExistent() throws Exception {
        when(nacrtObrokovRepository.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/meal-plans/{id}/ingredients", 999L))
                .andExpect(status().isNotFound());
    }
}