
# Poročilo o napakah



**Avtor:** Anastasija Todorov <br>
**Datum:** 2. november 2025 
## 🔹 Napaka 1 – Spremembe recepta se ne posodobijo v obstoječih načrtih obrokov  

| Atribut | Opis |
|----------|------|
| **ID napake** | MP-001 |
| **Naslov** | Spremenjen recept se ne posodobi v obstoječih načrtih obrokov |
| **Datum prijave** | 2. 11. 2025 |
| **Poročal** | Anastasija Todorov |
| **Opis incidenta** | Ko uporabnik spremeni obstoječ recept (npr. spremeni sestavino ali hranilne vrednosti), se spremembe ne prikažejo v že ustvarjenih načrtih obrokov, ki ta recept vsebujejo. |
| **Koraki za ponovitev** | 1. Ustvari recept “Piščanec z rižem”.<br>2. Dodaj recept v načrt obrokov za 3. 11. 2025.<br>3. Uredi recept (zamenjaj “riž” z “rjavi riž”).<br>4. Odpri načrt obrokov → prikazan je stari recept. |
| **Pričakovani rezultat** | Načrt obrokov bi moral samodejno prikazovati posodobljen recept. |
| **Dejanski rezultat** | Načrt obrokov prikazuje staro različico recepta. |
| **Kritičnost (Severity)** | Srednja |
| **Prioriteta (Priority)** | Visoka |
| **Status** | Zaprto |
| **Odgovorna oseba** | Anastasija Todorov |
| **Rešitev in pojasnilo** | Koda je posodobljena – zdaj se podatki o receptu vedno berejo neposredno iz baze. Preverjeno, deluje pravilno. |

---


## 🔹 Napaka 2 – Ni mogoče ustvariti načrta obrokov z le dvema obrokoma  

| Atribut | Opis |
|----------|------|
| **ID napake** | MP-002 |
| **Naslov** | Sistem ne dovoli ustvariti načrta obrokov z dvema obrokoma |
| **Datum prijave** | 2. 11. 2025 |
| **Poročal** | Anastasija Todorov |
| **Opis incidenta** | Pri ustvarjanju načrta obrokov za določen dan mora uporabnik dodati tri obroke. Če želi ustvariti načrt z manj kot 3, sistem javi napako. |
| **Koraki za ponovitev** | 1. Odpri stran »Ustvari načrt obrokov«.<br>2. Izberi datum (npr. 5. 11. 2025).<br>3. Dodaj 2 obroka (npr. brunch in večerja).<br>4. Klikni “Shrani”. |
| **Pričakovani rezultat** | Sistem bi moral omogočiti shranjevanje načrta z dvema obrokoma. |
| **Dejanski rezultat** | Prikazana je napaka: »Načrt obrokov mora imeti vsaj 3 obroke.« |
| **Kritičnost (Severity)** | Nizka |
| **Prioriteta (Priority)** | Srednja |
| **Status** | Zaprto |
| **Odgovorna oseba** | Anastasija Todorov |
| **Predlagana rešitev** | Odstrani omejitev minimalnega števila obrokov in omogoči shranjevanje načrta z 1–6 obroki na dan. |
| **Rešitev in pojasnilo** | Koda je posodobljena – zdaj lahko uporabnik v načrt prehrane doda manj obrokov in v enem dnevu več načrtov prehrane. Preverjeno, deluje pravilno. |

---

## 🔹 Napaka 3 – Če uporabnik pozabi vnesti število porcij, se shrani kot 0  

| Atribut | Opis |
|----------|------|
| **ID napake** | MP-003 |
| **Naslov** | Recept s številom porcij = 0 onemogoča izračun hranilnih vrednosti |
| **Datum prijave** | 2. 11. 2025 |
| **Poročal** | Anastasija Todorov |
| **Opis incidenta** | Če uporabnik pozabi vnesti polje »Število porcij« pri ustvarjanju recepta, se vrednost shrani kot 0. To povzroči napako pri funkciji “Izračunaj za X porcij”, saj se hranilne vrednosti ne morejo pravilno pomnožiti. |
| **Koraki za ponovitev** | 1. Ustvari nov recept brez vnosa v polje »Število porcij«.<br>2. Shrani recept.<br>3. Odpri recept in klikni »Izračunaj za 2 porcije«. |
| **Pričakovani rezultat** | Sistem bi moral uporabnika opozoriti, da mora vnesti število porcij, ali nastaviti privzeto vrednost 1. |
| **Dejanski rezultat** | Recept se shrani s 0 porcijami in funkcija za izračun ne deluje. |
| **Kritičnost (Severity)** | Srednja |
| **Prioriteta (Priority)** | Visoka |
| **Status** | Zaprto |
| **Odgovorna oseba** | Anastasija Todorov |
| **Predlagana rešitev** | Dodaj preverjanje pri shranjevanju recepta: če polje “porcije” ni vnešeno ali = 0 → nastavi privzeto vrednost 1 in prikaži opozorilo uporabniku. |
| **Rešitev in pojasnilo** | Koda je posodobljena – zdaj če uporabnik ne doda števila porcij, je privzeto število porcij 1. Preverjeno, deluje pravilno. |

---

## 📊 Povzetek

| Metrični podatek | Vrednost |
|------------------|-----------|
| Število prijavljenih napak | 3 |
| Število odprtih napak | 3 |
| Povprečna kritičnost | Srednja |
| Povprečna prioriteta | Visoka |
| Povprečni čas do rešitve | 07.11.2025 |

---

## 📋 Sklep
Napake so bile identificirane med testiranjem funkcionalnosti ustvarjanja in urejanja receptov ter načrtov obrokov. Vse tri napake vplivajo na pravilnost podatkov in uporabniško izkušnjo.
