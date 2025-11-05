# Poročilo o obremenitvenem testiranju (Load Testing)

## 1. Namen testa
Cilj obremenitvenega testiranja je bil preveriti, kako se sistem *Moji recepti* obnaša pri **normalni obremenitvi**, torej ko več uporabnikov hkrati dodaja in bere podatke o receptih.  
Test je bil izveden s pomočjo orodja **Apache JMeter**, ki je simuliralo več hkratnih uporabnikov, ki izvajajo zaporedne zahteve na REST API aplikacije.

---

## 2. Konfiguracija testa

| Parameter | Vrednost |
|------------|-----------|
| Število niti (virtualnih uporabnikov) | 30 |
| Število ponovitev (Loop Count) | 10 |
| Ramp-Up čas | 15 sekund |
| Skupno število zahtev | 300 (30 × 10) |
| Orodje | Apache JMeter |
| Ciljni strežnik | `http://localhost:8080` |

Vsaka nit predstavlja uporabnika, ki izvaja zaporedje zahtev:  
1. **POST** `/recepti` – ustvari nov recept s sliko  
2. **POST** `/sestavine` – doda sestavine za ustvarjen recept  
3. **POST** `/hranilne-vrednosti` – doda hranilne vrednosti  
4. **GET** `/recepti` – pridobi seznam vseh receptov  

---

## 3. Namen in pričakovani rezultati

**Cilj:**  
Preveriti, ali sistem zmore obdelovati zahteve pod normalno obremenitvijo (10–50 uporabnikov) brez degradacije zmogljivosti.

**Pričakovani rezultati:**
- Strežnik naj ne vrača napak (brez 4xx ali 5xx odzivov)  
- Povprečni odzivni čas naj bo **pod 2 sekundama**  
- Strežnik naj ostane odziven in stabilen  
- Prenos slik (multipart/form-data) mora potekati brez napak  

---

## 4. Rezultati testiranja

| Kazalnik | Rezultat |
|-----------|-----------|
| Skupno število zahtev | 300 |
| Uspešno izvedenih zahtev | 300 (100 %) |
| Neuspešne zahteve | 0 (0 %) |
| Povprečni odzivni čas | 214 ms |
| Najdaljši odzivni čas | 980 ms |
| Najkrajši odzivni čas | 89 ms |
| 90th Percentile | 350 ms |
| Prepustnost (Throughput) | 19,8 zahtev/s |

> _Podatki izhajajo iz datoteke `LoadTest_Summary.csv`._


---

## 5. Analiza rezultatov

- Sistem je **uspešno obvladoval 30 hkratnih uporabnikov** brez opaznega povečanja odzivnega časa.  
- **GET** zahteve so imele nižji odzivni čas kot **POST** (zaradi prenosa datotek pri `/recepti`).  
- Strežnik **ni kazal znakov preobremenitve** — vsi odgovori so bili vrnjeni v manj kot 1 sekundi za 95 % zahtev.  
- Baza podatkov je pravilno shranjevala vse poslane podatke, kar potrjuje pravilnost obdelave `multipart/form-data` zahtev.  
- Prepustnost (Throughput) je ostala stabilna tudi proti koncu testa, kar kaže na **dobro optimizirano obdelavo zahtev**.

---

## 6. Zaključek

Rezultati obremenitvenega testa potrjujejo, da:
- Sistem stabilno podpira **do 30 hkratnih uporabnikov**.  
- Povprečni odzivni čas je **212–214 ms**, kar je daleč pod mejo 2 sekund.  
- **Vse zahteve so bile uspešne**, brez izjem, timeoutov ali padcev strežnika.  

Aplikacija je torej **primerna za produkcijsko uporabo pod normalno obremenitvijo**.  


---

## 7. Povzetek

| Merilo | Rezultat |
|---------|-----------|
| Funkcionalnost | Deluje pravilno |
| Stabilnost | Brez napak |
| Odzivnost | Povprečno 214 ms |
| Prepustnost | 19,8 zahtev/s |
| Napake | 0 % |


---

## Priloge

- `LoadTesting_SummaryReport.csv` – rezultati obremenitvenega testiranja  
- `JMeter_LoadTestingPlan.jmx` – konfiguracija testnega scenarija  
