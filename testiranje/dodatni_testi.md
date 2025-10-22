# Dodatni testi



### **TEST 1: Pridobivanje vseh receptov (Pozitivno)**
**Ime testa:** `testGetAllRecipesPositive`  
**Namen testa:** Preveriti, da se vsi recepti pravilno pridobijo iz repozitorija.  

**Vhodni podatki:**
```http
GET /recepti
```

**Pričakovani rezultati:**
```http
200 OK
```
```json
[
  {"idRecepti": 1, "naziv": "Recipe 1", "tip": "zajtrk"},
  {"idRecepti": 2, "naziv": "Recipe 2", "tip": "kosilo"}
]
```

---

### **TEST 2: Pridobivanje vseh receptov (Negativno)**
**Ime testa:** `testGetAllRecipesEmptyList`  
**Namen testa:** Preveriti, da se vrne prazen seznam, če ni receptov.  

**Vhodni podatki:**
```http
GET /recepti
```

**Pričakovani rezultati:**
```http
200 OK
```
```json
[]
```

---

## **FUNKCIONALNOST: Ustvarjanje sestavine**

### **TEST 3: Ustvarjanje sestavine (Pozitivno)**
**Ime testa:** `testCreateSestavinePositive`  
**Namen testa:** Preveriti, da se sestavina pravilno ustvari z vsemi polji.  

**Vhodni podatki:**
```http
POST /sestavine
```
```json
{"idRecepti": 1, "naziv": "Sugar", "kolicina": 100, "enota": "g"}
```

**Pričakovani rezultati:**
```http
200 OK
```
```json
{"naziv": "Sugar", "kolicina": 100, "enota": "G"}
```

---

### **TEST 4: Ustvarjanje sestavine (Negativno)**
**Ime testa:** `testCreateSestavineNonExistentRecipe`  
**Namen testa:** Preveriti obravnavo neobstoječega recepta.  

**Vhodni podatki:**
```http
POST /sestavine
```
```json
{"idRecepti": 999, "naziv": "Sugar", "kolicina": 100, "enota": "g"}
```

**Pričakovani rezultati:**
```http
404 Not Found
```

---

## **FUNKCIONALNOST: Pridobivanje sestavin**

### **TEST 5: Pridobivanje sestavin po receptu (Pozitivno)**
**Ime testa:** `testGetSestavineByReceptiPositive`  
**Namen testa:** Preveriti, da se sestavine pravilno pridobijo za določen recept.  

**Vhodni podatki:**
```http
GET /sestavine/recepti/1
```

**Pričakovani rezultati:**
```http
200 OK
```
```json
[
  {"naziv": "Flour", "kolicina": 200, "enota": "G"},
  {"naziv": "Sugar", "kolicina": 100, "enota": "G"}
]
```

---

### **TEST 6: Pridobivanje sestavin po receptu (Negativno)**
**Ime testa:** `testGetSestavineByReceptiNotFound`  
**Namen testa:** Preveriti obravnavo recepta brez sestavin.  

**Vhodni podatki:**
```http
GET /sestavine/recepti/999
```

**Pričakovani rezultati:**
```http
404 Not Found
```

---

## **FUNKCIONALNOST: Pridobivanje hranilnih vrednosti**

### **TEST 7: Pridobivanje hranilnih vrednosti po receptu (Pozitivno)**
**Ime testa:** `testGetHranilneVrednostiByReceptiPositive`  
**Namen testa:** Preveriti, da se hranilne vrednosti pravilno pridobijo za določen recept.  

**Vhodni podatki:**
```http
GET /hranilne-vrednosti/recepti/1
```

**Pričakovani rezultati:**
```http
200 OK
```
```json
[
  {"naziv": "Calories", "kolicina": 500, "enota": "KCAL"},
  {"naziv": "Protein", "kolicina": 20, "enota": "G"}
]
```

---

### **TEST 8: Ustvarjanje hranilne vrednosti (Negativno)**
**Ime testa:** `testCreateHranilneVrednostiInvalidEnum`  
**Namen testa:** Preveriti obravnavo neveljavne enote.  

**Vhodni podatki:**
```http
POST /hranilne-vrednosti
```
```json
{"idRecepti": 1, "naziv": "Calories", "kolicina": 500, "enota": "INVALID"}
```

**Pričakovani rezultati:**
```http
400 Bad Request
```

---

## **FUNKCIONALNOST: Ustvarjanje načrta obrokov**

### **TEST 9: Ustvarjanje načrta obrokov (Pozitivno)**
**Ime testa:** `testCreateNacrtObrokovPositive`  
**Namen testa:** Preveriti, da se načrt obrokov pravilno ustvari.  

**Vhodni podatki:**
```http
POST /nacrt-obrokov
```
```json
{"datum": "2025-10-22"}
```

**Pričakovani rezultati:**
```http
200 OK
```
```json
{"datum": "2025-10-22"}
```

---

### **TEST 10: Ustvarjanje načrta obrokov (Negativno)**
**Ime testa:** `testCreateNacrtObrokovInvalidDate`  
**Namen testa:** Preveriti obravnavo neveljavnega datuma.  

**Vhodni podatki:**
```http
POST /nacrt-obrokov
```
```json
{"datum": "invalid-date"}
```

**Pričakovani rezultati:**
```http
400 Bad Request
```

---

## **FUNKCIONALNOST: Pridobivanje načrtov obrokov**

### **TEST 11: Pridobivanje vseh načrtov obrokov (Pozitivno)**
**Ime testa:** `testGetAllMealPlansPositive`  
**Namen testa:** Preveriti, da se vsi načrti obrokov pravilno pridobijo.  

**Vhodni podatki:**
```http
GET /meal-plans
```

**Pričakovani rezultati:**
```http
200 OK
```
```json
[
  {
    "idNacrtObrokov": 1,
    "datum": "2025-10-22",
    "recipes": [
      {"idRecepti": 1, "naziv": "Breakfast Recipe", "mealType": "ZAJTRK"}
    ]
  }
]
```

---

## **FUNKCIONALNOST: Pridobivanje sestavin načrta obrokov**

### **TEST 12: Pridobivanje sestavin načrta obrokov (Negativno)**
**Ime testa:** `testGetMealPlanIngredientsNonExistent`  
**Namen testa:** Preveriti obravnavo neobstoječega načrta obrokov.  

**Vhodni podatki:**
```http
GET /meal-plans/999/ingredients
```

**Pričakovani rezultati:**
```http
404 Not Found
```

---

## **POVZETEK**

| Modul | Skupaj | Pozitivno | Negativno |
|:------|:-------:|:----------:|:-----------:|
| Pridobivanje receptov | 2 | 1 | 1 |
| Ustvarjanje sestavine | 2 | 1 | 1 |
| Pridobivanje sestavin | 2 | 1 | 1 |
| Pridobivanje hranilnih vrednosti | 2 | 1 | 1 |
| Ustvarjanje načrta obrokov | 2 | 1 | 1 |
| Pridobivanje načrtov obrokov | 1 | 1 | 0 |
| Pridobivanje sestavin načrta obrokov | 1 | 0 | 1 |
| **SKUPAJ** | **12** | **6** | **6** |

✅ Vsi testi uspešno opravljeni z `@WebMvcTest`!
