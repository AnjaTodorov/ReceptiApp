# Poročilo o Testiranju

## Opis Testov

### 1. ReceptiControllerTest
- **Test Recipe Creation (Positive and Negative):**  
  Preverja uspešnost ustvarjanja novega recepta. Pozitiven test preverja pravilno shranjevanje recepta, medtem ko negativni test preverja obnašanje pri manjkajočih podatkih (npr. ime recepta).  
  **Pomembnost:** Zagotavlja, da so recepti ustvarjeni le z veljavnimi podatki.

- **Test Deleting a Recipe:**  
  Preverja, ali lahko obstoječ recept pravilno izbrišemo.  
  **Pomembnost:** Preverja skladnost funkcionalnosti brisanja s pričakovanji.

- **Test Updating a Recipe (Positive and Negative):**  
  Preverja posodobitev obstoječega recepta, vključno z obravnavo neobstoječih receptov.  
  **Pomembnost:** Zagotavlja pravilnost funkcionalnosti za posodobitev.

- **Test Get Recipe by ID:**  
  Preverja, ali je mogoče pridobiti recept na podlagi ID-ja.  
  **Pomembnost:** Kritična funkcionalnost za pridobivanje podrobnosti recepta.

- **Test Fetch Recipes by Meal Type:**  
  Preverja pridobivanje receptov po tipu obroka (npr. zajtrk, kosilo).  
  **Pomembnost:** Zagotavlja pravilno delovanje filtriranja receptov.

---

### 2. ReceptiNacrtObrokovControllerTest
- **Test Check Meal Plan Existence (Positive and Negative):**  
  Preverja obstoj načrta obrokov za določen datum.  
  **Pomembnost:** Kritično za validacijo obstoja načrtov obrokov.

- **Test Create Meal Plan with Recipes (Positive and Negative):**  
  Preverja ustvarjanje načrta obrokov z recepti. Negativni test preverja, ali je obravnavan manjkajoč recept.  
  **Pomembnost:** Zagotavlja, da so načrti obrokov ustvarjeni pravilno in vsebujejo samo obstoječe recepte.

---

### 3. NacrtObrokovControllerTest
- **Test Get Meal Plan By ID (Positive and Negative):**  
  Preverja, ali lahko pridobimo načrt obrokov na podlagi ID-ja.  
  **Pomembnost:** Kritična funkcionalnost za pridobivanje podrobnosti načrtov obrokov.

- **Repeated Test for Fetching Meal Plans:**  
  Preverja stabilnost vračanja seznama načrtov obrokov.  
  **Pomembnost:** Testira konsistentnost funkcionalnosti.

- **Dynamic Test for Validating Meal Plan Dates:**  
  Preverja pravilnost datumov v načrtih obrokov.  
  **Pomembnost:** Validacija pravilnosti podatkov.

- **Timeout Test:**  
  Preverja, da se pridobivanje načrtov obrokov zaključi v določenem času.  
  **Pomembnost:** Zagotavlja optimalno odzivnost aplikacije.

---

## Imena Članov Skupine in Odgovornost za Teste
1. **Konstantin Mihajlov:**  
   - ReceptiControllerTest (ustvarjanje, posodabljanje in brisanje receptov).
2. **Anastasija Todorov:**  
   - ReceptiNacrtObrokovControllerTest (ustvarjanje in validacija načrtov obrokov).
3. **Matej Filipov:**  
   - NacrtObrokovControllerTest (pridobivanje in validacija načrtov obrokov).

---

## Analiza Uspešnosti Testov
### Uspehi:
- Večina testov je uspešno preverila, da aplikacija deluje po pričakovanjih, kar je pomagalo pri zgodnjem odkrivanju napak.
  - Test **"Test Create Meal Plan with Recipes (Negative)"** je odkril napako pri preverjanju ID-jev receptov, ki je bila odpravljena z dodajanjem ustrezne validacije v kontroler.
  - Test **"Test Recipe Creation (Negative)"** je pokazal, da ni bilo pravilno obravnavano manjkajoče ime recepta. Težavo smo rešili z dodajanjem `@NotBlank` validacije v DTO.

### Napake odpravljene s testi:
1. Dodana validacija za neobstoječe recepte v **ReceptiNacrtObrokovController**.
2. Dodana validacija vhodnih podatkov (ime, sestavine) za **ReceptiController**.
3. Popravljen **NacrtObrokovRepository** za pravilno vračanje praznih vrednosti.
