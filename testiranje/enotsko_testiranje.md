# ENOTSKO TESTIRANJE (UNIT TESTS)

---

## **FUNKCIONALNOST: Ustvarjanje recepta**

## **TEST 1: Ustvarjanje recepta (Pozitivno)**

**Ime testa:** `testCreateRecipePositive`  
**Namen testa:** Preveriti, da se recept pravilno ustvari z vsemi polji.  

**Vhodni podatki:**  
- Multipart POST `/recepti`  
- `naziv=Test Recipe`  
- `opis=Test Description`  
- `tip=zajtrk`  
- `osebe=2`  
- `picture=recipe_image.jpg`  

**Pričakovani rezultati:**  
- **200 OK**  
- JSON: `{"idRecepti":1, "naziv":"Test Recipe", "opis":"Test Description", "tip":"zajtrk", "osebe":2, "slika":"recipe_image.jpg"}`

---

## **TEST 2: Ustvarjanje recepta (Negativno)**

**Ime testa:** `testCreateRecipeNegative`  
**Namen testa:** Preveriti validacijo ob manjkajočem obveznem polju `naziv`.  

**Vhodni podatki:**  
- Multipart POST `/recepti`  
- **Brez `naziv`**  
- `opis=Test Description`  
- `tip=zajtrk`  
- `osebe=2`  
- `picture=image.jpg`  

**Pričakovani rezultati:**  
- **400 Bad Request** (Validation error)

---

## **FUNKCIONALNOST: Posodabljanje recepta**

## **TEST 3: Posodabljanje recepta (Pozitivno)**

**Ime testa:** `testUpdateRecipePositive`  
**Namen testa:** Preveriti, da se obstoječi recept pravilno posodobi.  

**Vhodni podatki:**  
- PUT `/recepti/1`  
- JSON: `{"naziv":"Updated Recipe", "opis":"Updated Description", "tip":"zajtrk", "osebe":4}`  

**Pričakovani rezultati:**  
- **200 OK**  
- JSON: `{"idRecepti":1, "naziv":"Updated Recipe", "opis":"Updated Description", "tip":"zajtrk", "osebe":4}`

---

## **TEST 4: Posodabljanje recepta (Negativno)**

**Ime testa:** `testUpdateRecipeNegative`  
**Namen testa:** Preveriti obravnavo neobstoječega ID-ja.  

**Vhodni podatki:**  
- PUT `/recepti/999`  
- Valid JSON z vsemi polji  

**Pričakovani rezultati:**  
- **404 Not Found**

---

## **FUNKCIONALNOST: Brisanje recepta**

## **TEST 5: Brisanje recepta (Pozitivno)**

**Ime testa:** `testDeleteRecipePositive`  
**Namen testa:** Preveriti, da se obstoječi recept pravilno izbriše.  

**Vhodni podatki:**  
- DELETE `/recepti/1` (obstoječi ID)  

**Pričakovani rezultati:**  
- **204 No Content**

---

## **TEST 6: Brisanje recepta (Negativno)**

**Ime testa:** `testDeleteRecipeNegative`  
**Namen testa:** Preveriti obravnavo neobstoječega ID-ja.  

**Vhodni podatki:**  
- DELETE `/recepti/999` (neobstoječi ID)  

**Pričakovani rezultati:**  
- **404 Not Found**

---

## **FUNKCIONALNOST: Pridobivanje recepta**

## **TEST 7: Pridobivanje recepta (Pozitivno)**

**Ime testa:** `testGetRecipeByIdPositive`  
**Namen testa:** Preveriti, da se recept pravilno pridobi z ID-jem.  

**Vhodni podatki:**  
- GET `/recepti/1` (obstoječi ID)  

**Pričakovani rezultati:**  
- **200 OK**  
- JSON: `{"idRecepti":1, "naziv":"Test Recipe", "opis":"Test Description", "tip":"zajtrk", "osebe":2, "slika":"image.jpg"}`

---

## **TEST 8: Pridobivanje recepta (Negativno)**

**Ime testa:** `testGetRecipeByIdNegative`  
**Namen testa:** Preveriti obravnavo neobstoječega ID-ja.  

**Vhodni podatki:**  
- GET `/recepti/999` (neobstoječi ID)  

**Pričakovani rezultati:**  
- **404 Not Found**

---

## **FUNKCIONALNOST: Ustvarjanje načrta obrokov**

## **TEST 9: Ustvarjanje načrta obrokov (Pozitivno)**

**Ime testa:** `testCreateMealPlanWithRecipes`  
**Namen testa:** Preveriti, da se načrt obrokov ustvari z veljavnimi recepti.  

**Vhodni podatki:**  
- POST `/meal-plans/create`  
- JSON: `{"datum":"2025-10-20", "recipes":[{"mealType":"ZAJTRK","recipeId":1}, {"mealType":"KOSILO","recipeId":2}, {"mealType":"VEČERJA","recipeId":3}]}`  

**Pričakovani rezultati:**  
- **200 OK**  
- JSON: `{"idNacrtObrokov":1, "datum":"2025-10-20"}`

---

## **TEST 10: Ustvarjanje načrta obrokov (Negativno)**

**Ime testa:** `testCreateMealPlanWithRecipesNegative`  
**Namen testa:** Preveriti obravnavo neobstoječega recepta.  

**Vhodni podatki:**  
- POST `/meal-plans/create`  
- JSON: **recipeId=999** (neobstoječi)  

**Pričakovani rezultati:**  
- **404 Not Found**  
- JSON: `{"error":"Recipe not found with id: 999"}`

---

## **POVZETEK**

| **Modul** | **Skupaj** | **Pozitivno** | **Negativno** |
|-----------|------------|---------------|---------------|
| **Recepti CRUD** | 8 | 4 | 4 
| **Načrt obrokov** | 2 | 1 | 1 
| **SKUPAJ** | **10** | **5** | **5** | 

**Vsi testi uspešno opravljeni z `@WebMvcTest`!** 
