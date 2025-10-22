# Test Summary Table

This document provides a concise overview of all 49 tests executed across the three main testing files.  
Each entry summarizes the purpose of the test, categorized by functionality and type (Positive/Negative).  
The goal is to demonstrate both coverage and variation across all tested modules.

| # | Test Name | Module | Type | Short Description |
|---|------------|---------|------|------------------|
| 1 | testCreateRecipePositive | Recepti | Positive | Verifies successful recipe creation with all required fields. |
| 2 | testCreateRecipeNegative | Recepti | Negative | Ensures validation catches missing recipe name field. |
| 3 | testUpdateRecipePositive | Recepti | Positive | Confirms existing recipe can be updated with new data. |
| 4 | testUpdateRecipeNegative | Recepti | Negative | Checks behavior when updating a non-existent recipe ID. |
| 5 | testDeleteRecipePositive | Recepti | Positive | Validates successful deletion of an existing recipe. |
| 6 | testDeleteRecipeNegative | Recepti | Negative | Tests deletion attempt for recipe ID that doesn’t exist. |
| 7 | testGetRecipeByIdPositive | Recepti | Positive | Confirms correct retrieval of recipe by valid ID. |
| 8 | testGetRecipeByIdNegative | Recepti | Negative | Verifies system returns 404 for missing recipe ID. |
| 9 | testFetchRecipesByType | Recepti | Positive | Checks filtering of recipes based on meal type parameter. |
| 10 | testGetAllRecipesPositive | Recepti | Positive | Retrieves full recipe list ensuring repository consistency. |
| 11 | testGetAllRecipesEmptyList | Recepti | Negative | Validates empty list is returned when no recipes exist. |
| 12 | testCreateSestavinePositive | Sestavine | Positive | Confirms ingredient creation with valid fields and units. |
| 13 | testCreateSestavineNonExistentRecipe | Sestavine | Negative | Checks rejection when creating ingredient for invalid recipe ID. |
| 14 | testGetSestavineByReceptiPositive | Sestavine | Positive | Ensures correct ingredients are fetched for given recipe. |
| 15 | testGetSestavineByReceptiNotFound | Sestavine | Negative | Confirms 404 is returned when recipe has no ingredients. |
| 16 | testCreateHranilneVrednostiPositive | Hranilne vrednosti | Positive | Validates nutritional values are added for a valid recipe. |
| 17 | testCreateHranilneVrednostiInvalidEnum | Hranilne vrednosti | Negative | Ensures invalid nutritional unit is properly rejected. |
| 18 | testGetHranilneVrednostiByReceptiPositive | Hranilne vrednosti | Positive | Fetches nutritional data accurately for selected recipe. |
| 19 | testGetHranilneVrednostiNotFound | Hranilne vrednosti | Negative | Checks response when nutritional data is unavailable. |
| 20 | testCreateNacrtObrokovPositive | Načrt obrokov | Positive | Verifies successful creation of meal plan with valid date. |
| 21 | testCreateNacrtObrokovInvalidDate | Načrt obrokov | Negative | Ensures system rejects malformed or invalid date input. |
| 22 | testGetAllMealPlansPositive | Načrt obrokov | Positive | Confirms retrieval of all stored meal plans works correctly. |
| 23 | testGetMealPlanByIdPositive | Načrt obrokov | Positive | Retrieves single meal plan by ID and checks integrity. |
| 24 | testGetMealPlanByIdNegative | Načrt obrokov | Negative | Verifies 404 returned when fetching non-existent meal plan. |
| 25 | testCreateMealPlanWithRecipes | Načrt obrokov | Positive | Tests creating meal plan including linked recipes. |
| 26 | testCreateMealPlanWithRecipesNegative | Načrt obrokov | Negative | Checks handling when one of linked recipes is missing. |
| 27 | testGetMealPlanIngredientsNonExistent | Načrt obrokov | Negative | Ensures system returns not found for invalid meal plan ID. |
| 28 | testMealPlanRepeatedFetch | Načrt obrokov | Positive | Confirms stability by repeating meal plan fetch operations. |
| 29 | testMealPlanDynamicDates | Načrt obrokov | Positive | Tests validation logic for dynamically generated meal plan dates. |
| 30 | testMealPlanTimeout | Načrt obrokov | Positive | Ensures retrieval completes within expected time limit. |
| 31 | testCreateMealPlanRepositoryIntegration | Načrt obrokov | Positive | Validates correct persistence between controller and repository layers. |
| 32 | testMealPlanExistenceCheckPositive | Načrt obrokov | Positive | Confirms existing plan for specific date is recognized. |
| 33 | testMealPlanExistenceCheckNegative | Načrt obrokov | Negative | Ensures missing meal plan is correctly identified as non-existent. |
| 34 | testIngredientValidationEdgeCase | Sestavine | Negative | Verifies validation for unusual ingredient quantities (e.g., zero). |
| 35 | testIngredientUnitCaseSensitivity | Sestavine | Positive | Checks if units like “g” or “G” are normalized properly. |
| 36 | testEmptyIngredientListHandling | Sestavine | Negative | Ensures system safely handles recipes without any ingredients. |
| 37 | testRecipeImageUploadPositive | Recepti | Positive | Confirms image uploads attach correctly to recipes. |
| 38 | testRecipeImageUploadInvalidType | Recepti | Negative | Ensures invalid file types are rejected during upload. |
| 39 | testRecipeListPagination | Recepti | Positive | Validates pagination returns expected number of recipes per page. |
| 40 | testRecipeSearchByKeyword | Recepti | Positive | Tests search functionality filtering recipes by name keyword. |
| 41 | testInvalidRecipeSearchParameter | Recepti | Negative | Ensures invalid query parameters are handled gracefully. |
| 42 | testNutritionalCalculationConsistency | Hranilne vrednosti | Positive | Validates consistent total nutrition calculations for recipes. |
| 43 | testNutritionalValueMissingField | Hranilne vrednosti | Negative | Checks validation when nutritional field like ‘kolicina’ is missing. |
| 44 | testMealPlanRecipeMapping | Načrt obrokov | Positive | Confirms correct mapping of multiple recipes into daily plan. |
| 45 | testMealPlanEmptyRecipeArray | Načrt obrokov | Negative | Ensures system rejects creation with empty recipe list. |
| 46 | testRepositoryMockIntegration | Splošno | Positive | Confirms repository mocks interact correctly with controller tests. |
| 47 | testControllerErrorHandling | Splošno | Negative | Tests global exception handling for invalid HTTP requests. |
| 48 | testDataValidationAcrossModules | Splošno | Positive | Ensures cross-module validation logic performs consistently. |
| 49 | testApplicationStartupContextLoads | Splošno | Positive | Confirms application context initializes correctly for all tests. |
