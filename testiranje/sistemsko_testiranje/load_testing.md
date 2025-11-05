# Poročilo o obremenitvenem testiranju (Load Testing)

## 1. Namen testa
Cilj obremenitvenega testiranja je bil preveriti, kako se sistem *Moji recepti* obnaša pri **normalni obremenitvi**, ko več uporabnikov hkrati dodaja in bere podatke o receptih.  
Test je bil izveden z orodjem **Apache JMeter**, ki je simuliralo več hkratnih uporabnikov, ki izvajajo zaporedne zahteve na REST API strežnika aplikacije.

---

## 2. Konfiguracija testa

| Parameter | Vrednost |
|------------|-----------|
| Število niti (virtualnih uporabnikov) | 30 |
| Število ponovitev (Loop Count) | 10 |
| Ramp-Up čas | 15 sekund |
| Skupno število zahtev | 1200 |
| Orodje | Apache JMeter |
| Ciljni strežnik | `http://localhost:8080` |

Vsaka nit predstavlja uporabnika, ki izvaja zaporedje zahtev:  
1. **POST** `/recepti` – ustvari nov recept s sliko  
2. **POST** `/sestavine` – doda sestavine za ustvarjen recept  
3. **POST** `/hranilne-vrednosti` – doda hranilne vrednosti recepta  
4. **GET** `/recepti` – pridobi seznam vseh receptov  

---

## 3. Namen in pričakovani rezultati

**Cilj:**  
Preveriti, ali sistem zmore obdelovati zahteve pod normalno obremenitvijo (10–50 uporabnikov) brez degradacije zmogljivosti.

**Pričakovani rezultati:**
- Strežnik ne vrača napak (brez 4xx ali 5xx odzivov)  
- Povprečni odzivni čas naj bo **pod 2 sekundama**  
- Strežnik naj ostane odziven in stabilen  
- Prenos slik (multipart/form-data) mora potekati brez napak  

---

## 4. Rezultati testiranja

| Kazalnik | Rezultat |
|-----------|-----------|
| Skupno število zahtev | 1200 |
| Uspešno izvedenih zahtev | 1200 (100 %) |
| Neuspešne zahteve | 0 (0 %) |
| Povprečni odzivni čas | **13 ms** |
| Najdaljši odzivni čas | **214 ms** |
| Najkrajši odzivni čas | **4 ms** |
| Standardni odklon | **12,38 ms** |
| Prepustnost (Throughput) | **80,1 zahtev/s** |
| Prenos podatkov (prejeto) | **8368,71 KB/s** |
| Prenos podatkov (poslano) | **36,01 KB/s** |
| Povprečna velikost odgovora | **106.976 B** |

> _Podatki izhajajo iz datoteke `LoadTesting_SummaryReport.csv`._

---

## 5. Analiza rezultatov

- Sistem je **uspešno obvladoval 30 hkratnih uporabnikov** brez opaznega povečanja odzivnega časa.  
- **Vse zahteve (1200)** so bile uspešno izvedene brez napak ali izjem.  
- Povprečni odzivni čas **13 ms** kaže na izjemno hitro odzivnost API-ja tudi pri vzporedni obremenitvi.  
- Najvišji odzivni čas (**214 ms**) je bistveno pod pričakovano mejo 2 sekund.  
- Prepustnost **80 zahtev na sekundo** dokazuje, da sistem učinkovito upravlja s povezavami in podatkovnim prometom.  
- Strežnik ni kazal znakov preobremenitve, kar potrjuje stabilnost in pravilno delovanje aplikacije.

---

## 6. Zaključek

Rezultati obremenitvenega testa potrjujejo, da:
- Sistem stabilno podpira **do 30 hkratnih uporabnikov**.  
- Povprečni odzivni čas **13 ms** je daleč pod mejno vrednostjo.  
- Vse zahteve so bile uspešno izvedene brez napak, timeoutov ali padcev strežnika.  

Aplikacija je torej **primerna za produkcijsko uporabo pod normalno obremenitvijo**.



---

## 7. Povzetek

| Merilo | Rezultat |
|---------|-----------|
| Funkcionalnost | Deluje pravilno |
| Stabilnost | Brez napak |
| Odzivnost | Povprečno 13 ms |
| Prepustnost | 80,1 zahtev/s |
| Napake | 0 % |


---

## Priloge

- `LoadTesting_SummaryReport.csv` – rezultati obremenitvenega testiranja  
- `JMeter_LoadTestingPlan.jmx` – konfiguracija testnega scenarija  


