# Poročilo o obremenitvenem testiranju (Load Testing)

## 1. Namen testiranja
Cilj obremenitvenega testiranja je preveriti, kako se aplikacija **MojiRecepti** odziva pod različnimi stopnjami obremenitve, ko hkrati več uporabnikov pošilja zahtevke na končno točko **POST `/recepti`**, ki dodaja nov recept skupaj s sliko.  
Testi merijo stabilnost, odzivni čas in prepustnost sistema pri naraščajočem številu sočasnih uporabnikov.

Testi so bili izvedeni z orodjem **Apache JMeter**.

---

## 2. Konfiguracija testnega okolja

| Parameter | Vrednost |
|-----------|----------|
| Orodje | Apache JMeter |
| API Endpoint | `http://localhost:8080/recepti` |
| Metoda | POST (multipart/form-data) |
| Baza podatkov | MySQL |
| Backend | Spring Boot |
| Frontend lokalno | Node.js strežnik |

Vsak uporabnik pošilja **zahtevo za ustvarjanje recepta**, ki vsebuje:
- naziv recepta
- opis
- tip (enum)
- število oseb
- sliko (`image/jpeg`)

---

## 3. Testni scenariji

Izvedeni so bili štirje testi:

| Test | Opis | Obremenitev |
|------|------|------------|
| **Baseline** | osnovna obremenitev | 15 uporabnikov |
| **Moderate Load** | realna obremenitev | 100 uporabnikov |
| **Heavy Load** | visoka obremenitev | 500 uporabnikov |
| **Extreme Load** | stresna obremenitev | 750 uporabnikov |

---

---

## 🔹 *Test 1: Baseline Load*

### **Konfiguracija**
| Parameter | Vrednost |
|-----------|----------|
| Uporabniki | 15 |
| Loop Count | 2 |
| Ramp-Up | 10 s |
| Skupaj zahtev | 45 |

### **Pričakovani rezultat**
Sistem naj deluje brez napak, odzivni časi naj bodo nizki in stabilni.

### **Dejanski rezultati**
## Summary Report

| Kazalnik | Vrednost |
|----------|----------|
| Število zahtev | 45 |
| Povprečni odzivni čas | **34 ms** |
| Najkrajši odzivni čas | 19 ms |
| Najdaljši odzivni čas | 51 ms |
| Standardni odklon | 7.68 ms |
| Napake | **0.00 %** |
| Prepustnost | 0.197 req/s |
| Povprečna velikost odgovora | 356.9 B |

## Aggregate Report

| Kazalnik | Vrednost |
|----------|----------|
| Median | 31 ms |
| 90% Line | 43 ms |
| 95% Line | 43 ms |
| 99% Line | 51 ms |
| Min | 19 ms |
| Max | 51 ms |
| Napake | 0% |
| Prepustnost | 3.19 req/s |


### **Interpretacija**
Sistem se pri osnovni obremenitvi obnaša normalno. Odzivi so hitri (povprečno 34 ms), napak ni. To potrjuje pravilno delovanje API-ja pri tipični uporabi.

---

## 🔹 *Test 2: Moderate Load*

### **Konfiguracija**
| Parameter | Vrednost |
|-----------|----------|
| Uporabniki | 100 |
| Loop Count | 3 |
| Ramp-Up | 10 s |
| Skupaj zahtev | 300 |

### **Pričakovani rezultat**
Rahlo povečanje odzivnih časov, brez napak.

### **Dejanski rezultati**
## Summary Report

| Kazalnik | Vrednost |
|----------|----------|
| Število zahtev | 300 |
| Povprečni odzivni čas | **27 ms** |
| Najkrajši odzivni čas | 15 ms |
| Najdaljši odzivni čas | 48 ms |
| Standardni odklon | 6.33 ms |
| Napake | **0.00 %** |
| Prepustnost | 15.09 req/s |
| Povprečna velikost odgovora | 357.7 B |

## Aggregate Report

| Kazalnik | Vrednost |
|----------|----------|
| Median | 26 ms |
| 90% Line | 37 ms |
| 95% Line | 40 ms |
| 99% Line | 43 ms |
| Min | 15 ms |
| Max | 48 ms |
| Napake | 0% |
| Prepustnost | 15.09 req/s |

### **Interpretacija**
Sistem ostaja stabilen, celo hitrejši povprečni odziv kaže na učinkovito paralelizacijo. Sistem pravilno procesira vse podatke.

---

## 🔹 *Test 3: Heavy Load*

### **Konfiguracija**
| Parameter | Vrednost |
|-----------|----------|
| Uporabniki | 500 |
| Loop Count | 2 |
| Ramp-Up | 30 s |
| Skupaj zahtev | 1000 |

### **Pričakovani rezultat**
Možne začasne zakasnitve, manjši delež napak.

### **Dejanski rezultati**
## Summary Report

| Kazalnik | Vrednost |
|----------|----------|
| Število zahtev | 1000 |
| Povprečni odzivni čas | **28 ms** |
| Najkrajši odzivni čas | 15 ms |
| Najdaljši odzivni čas | 1632 ms |
| Standardni odklon | 52.18 ms |
| Napake | **0.001 %** |
| Prepustnost | 31.57 req/s |
| Povprečna velikost odgovora | 357.887 B |

## Aggregate Report

| Kazalnik | Vrednost |
|----------|----------|
| Median | 26 ms |
| 90% Line | 30 ms |
| 95% Line | 31 ms |
| 99% Line | 90 ms |
| Min | 15 ms |
| Max | 1632 ms |
| Napake | 0.001% |
| Prepustnost | 31.57 req/s |


### **Interpretacija**
Sistem zdrži 500 uporabnikov brez pomembne degradacije. Prisotni so redki vrhovi latence zaradi nalaganja slik. Napake so minimalne.

---

## 🔹 *Test 4: Extreme Load*

### **Konfiguracija**
| Parameter | Vrednost |
|-----------|----------|
| Uporabniki | 750 |
| Loop Count | 2 |
| Ramp-Up | 25 s |
| Skupaj zahtev | 1500 |

### **Pričakovani rezultat**
Sistem naj pokaže meje zmogljivosti; pričakovan delež napak.

### **Dejanski rezultati**
## Summary Report

| Kazalnik | Vrednost |
|----------|----------|
| Število zahtev | 1500 |
| Povprečni odzivni čas | **28 ms** |
| Najkrajši odzivni čas | 16 ms |
| Najdaljši odzivni čas | 105 ms |
| Standardni odklon | 7.38 ms |
| Napake | **0.031 %** |
| Prepustnost | 60.52 req/s |
| Povprečna velikost odgovora | 357.7 B |

## Aggregate Report

| Kazalnik | Vrednost |
|----------|----------|
| Median | 27 ms |
| 90% Line | 35 ms |
| 95% Line | 43 ms |
| 99% Line | 54 ms |
| Min | 16 ms |
| Max | 105 ms |
| Napake | 0.031% |
| Prepustnost | 60.52 req/s |


### **Interpretacija**
Pri ekstremni obremenitvi se pojavi ~3% napak, kar pomeni, da je sistem dosegel svojo zmogljivostno mejo. Prepustnost je zelo visoka, odzivnost pa ostaja presenetljivo dobra.

---

## 4. Zaključek

Rezultati kažejo, da je aplikacija **zelo stabilna in odzivna** pri večini obremenitev:

- Do **500 sočasnih uporabnikov** deluje brez degradacije in skoraj brez napak.
- Pri **750 uporabnikih** postane vidna zgornja meja zmogljivosti.
- Povprečni odzivni čas ostaja nizko med **20–35 ms**.
- Backend in MySQL uspešno obdelujeta zaporedne POST zahtevke z datotekami.

Aplikacija je primerna za realno uporabo, podpira visoko stopnjo sočasnih zahtevkov in je primerno optimizirana.

---

## 5. Priloge
- `summary-report.csv`
- `loadTests.jmx`
