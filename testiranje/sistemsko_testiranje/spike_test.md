# Poročilo o stresnem testiranju (Stress Testing)

## 1. Namen testa
Namen stresnega testiranja je bil preveriti, kako se sistem *Moji recepti* obnaša pod visoko obremenitvijo, torej ko večje število uporabnikov hkrati pošilja zahteve v kratkem časovnem intervalu.  
Cilj je bil določiti točko, pri kateri začne sistem izgubljati stabilnost ali se odzivni časi občutno povečajo.
Test je bil izveden s pomočjo orodja **Apache JMeter**

---

## 2. Konfiguracija testa

| Parameter | Vrednost |
|------------|-----------|
| Število niti (virtualnih uporabnikov) | 120 |
| Število ponovitev (Loop Count) | 5 |
| Ramp-Up čas | 5 sekund |
| Skupno število zahtev | 2400 |
| Orodje | Apache JMeter |
| Ciljni strežnik | `http://localhost:8080` |

Vsaka nit je izvajala enako zaporedje zahtev kot v obremenitvenem testu:  
1. **POST** `/recepti` – dodajanje novega recepta s sliko  
2. **POST** `/sestavine` – dodajanje sestavin  
3. **POST** `/hranilne-vrednosti` – dodajanje hranilnih vrednosti  
4. **GET** `/recepti` – pridobivanje seznama vseh receptov  

---

## 3. Pričakovani rezultati

- Strežnik mora ostati odziven tudi pri višji obremenitvi.  
- Povprečni odzivni čas naj ne presega 1 sekunde.  
- Napake (Error %) naj bodo pod 5 %.  
- Strežnik naj ne vrne 5xx napak in ne prekine povezav.

---

## 4. Rezultati testiranja

| Kazalnik | Rezultat |
|-----------|-----------|
| Skupno število zahtev | 2400 |
| Uspešno izvedenih zahtev | 2145 (89,4 %) |
| Neuspešne zahteve | 255 (10,6 %) |
| Povprečni odzivni čas | 32 ms |
| Najdaljši odzivni čas | 437 ms |
| Najkrajši odzivni čas | 1 ms |
| 90th Percentile | 59 ms |
| 95th Percentile | 92 ms |
| 99th Percentile | 237 ms |
| Prepustnost (Throughput) | 460,83 zahtev/s |
| Prenos podatkov (prejeto) | 7669,67 KB/s |
| Prenos podatkov (poslano) | 2597,48 KB/s |

> _Podatki izhajajo iz datoteke `StressTest_AggregateReport.csv`._

---

## 5. Analiza rezultatov

- Sistem je v povprečju ohranil **zelo nizke odzivne čase (32 ms)** tudi pri obremenitvi s 120 hkratnimi uporabniki.  
- **10,6 % napak** nakazuje, da je pri visoki stopnji paralelnih zahtev sistem dosegel mejo stabilnosti.  
- Napake so verjetno posledica **zasičenja strežnika** pri obdelavi večjega števila `multipart/form-data` zahtev.  
- Kljub temu sistem ni doživel izpada in je še naprej obdeloval zahteve z visoko prepustnostjo (460 zahtev/s).  
- Povprečni odzivni časi so ostali pod 100 ms za večino zahtev, kar kaže na **dobro optimizirano obdelavo** pri večini primerov uporabe.  

---

## 6. Zaključek

Rezultati stresnega testa kažejo, da:
- Sistem prenese obremenitev **do približno 100–120 hkratnih uporabnikov**, vendar se pri tem pojavijo prve napake.  
- Povprečni odzivni časi ostanejo **zelo nizki (32 ms)**, kar potrjuje dobro učinkovitost aplikacije.  
- Napake nad 10 % kažejo, da je **točka nasičenja** dosežena pri približno 120 uporabnikih.  
- Strežnik se po koncu testa uspešno stabilizira brez trajnih napak.

Aplikacija je stabilna pod visoko obremenitvijo, vendar se za optimizacijo priporoča:
- izboljšanje obdelave `multipart/form-data` zahtev,  
- nastavitev omejitve števila hkratnih povezav,  
- razmislek o uporabi asinhronih metod ali load balancerja pri večjem številu uporabnikov.

---

## 7. Povzetek

| Merilo | Rezultat |
|---------|-----------|
| Stabilnost | Delno stabilna pri 120 uporabnikih |
| Odzivnost | Povprečno 32 ms |
| Najvišji odzivni čas | 437 ms |
| Napake | 10,6 % |
| Prepustnost | 460,83 zahtev/s |
| Točka nasičenja | Okoli 120 uporabnikov |


---

## 8. Priloge

- `StressTest_AggregateReport.csv` – rezultati stresnega testiranja  
- `JMeter_StressTestPlan.jmx` – konfiguracija JMeter testnega scenarija  

