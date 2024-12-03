package com.RIS.Mojirecepti;

import com.RIS.Mojirecepti.controller.NacrtObrokovController;
import com.RIS.Mojirecepti.entity.NacrtObrokov;
import com.RIS.Mojirecepti.repository.NacrtObrokovRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NacrtObrokovControllerTest {

    @Mock
    private NacrtObrokovRepository nacrtObrokovRepository;

    @InjectMocks
    private NacrtObrokovController nacrtObrokovController;

    @Test
    @DisplayName("Test Get Meal Plan By ID - Positive")
    void testGetMealPlanByIdSuccess() {
        NacrtObrokov mockMealPlan = new NacrtObrokov();
        mockMealPlan.setIdNacrtObrokov(1);
        mockMealPlan.setDatum(LocalDate.of(2024, 1, 1));

        when(nacrtObrokovRepository.findById(1L)).thenReturn(Optional.of(mockMealPlan));

        ResponseEntity<NacrtObrokov> response = nacrtObrokovController.getNacrtObrokovById(1L);

        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(1L, response.getBody().getIdNacrtObrokov());
        verify(nacrtObrokovRepository, times(1)).findById(1L);
    }
    @Test
    @DisplayName("Test Get NacrtObrokov by ID - Not Found")
    void testGetNacrtObrokovByIdNotFound() {
        Long id = 99L;
        when(nacrtObrokovRepository.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<NacrtObrokov> response = nacrtObrokovController.getNacrtObrokovById(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(nacrtObrokovRepository, times(1)).findById(id);
    }

    @RepeatedTest(5)
    @DisplayName("Repeated Test for Fetching Meal Plans")
    void repeatedTestFetchMealPlans() {
        when(nacrtObrokovRepository.findAll()).thenReturn(Stream.of(
                new NacrtObrokov(1, LocalDate.now()),
                new NacrtObrokov(2, LocalDate.now().plusDays(1))
        ).toList());

        assertDoesNotThrow(() -> nacrtObrokovController.getAllNacrtiObrokov());
        verify(nacrtObrokovRepository, atLeastOnce()).findAll();
    }

//Preverja pravilnost datumov v načrtih obrokov.
    @TestFactory
    @DisplayName("Dynamic Test for Validating Meal Plan Dates")
    Stream<DynamicTest> dynamicTestMealPlanDates() {
        return IntStream.rangeClosed(1, 5).mapToObj(i -> {
            LocalDate date = LocalDate.now().plusDays(i);
            NacrtObrokov mealPlan = new NacrtObrokov(i, date);

            return DynamicTest.dynamicTest("Test meal plan date validation for day " + i,
                    () -> assertEquals(date, mealPlan.getDatum()));
        });
    }

    @ParameterizedTest
    @DisplayName("Test Fetch by ID for multiple IDs")
    @ValueSource(longs = {1L, 2L, 3L})
    void testTemplateFetchById(Long testId) {
        when(nacrtObrokovRepository.findById(testId))
                .thenReturn(Optional.of(new NacrtObrokov(Math.toIntExact(testId), LocalDate.now())));

        ResponseEntity<NacrtObrokov> response = nacrtObrokovController.getNacrtObrokovById(testId);

        assertEquals(testId.intValue(), response.getBody().getIdNacrtObrokov());
        verify(nacrtObrokovRepository, times(1)).findById(testId);
    }

    @Test
    @Timeout(1)
    @DisplayName("Timeout Test for Meal Plan Fetch")
    void timeoutTestFetchAllMealPlans() {
        assertDoesNotThrow(() -> {
            nacrtObrokovController.getAllNacrtiObrokov();
        });
    }


}
