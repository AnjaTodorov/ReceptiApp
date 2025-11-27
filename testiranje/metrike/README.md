# Poročilo o merjenju objektno usmerjenih metrik  

## 1. Uvod

Namen analize je bil izmeriti kakovost objektno usmerjene zasnove za backend aplikacijo *MojiRecepti*, razvito v okolju Spring Boot (Java). Ker objektno usmerjeni sistemi sčasoma postanejo kompleksni, so metrike nujno orodje za ocenjevanje:

- enkapsulacije  
- dedovanja  
- sklopljenosti (coupling)  
- kompleksnosti razredov  
- vzdržljivosti in berljivosti kode

Uporabljena sta bila dva uveljavljena sklopa metrik:

1. **MOOD (Metrics for Object-Oriented Design)** – projektni nivo  
2. **CKJM / Chidamber–Kemerer metrika** – nivo posameznih razredov  

<i>Ta pristop omogoča celovit pregled nad kakovostjo zasnove na različnih nivojih granularnosti.</i>

---

## 2. Orodja

Za merjenje metrik smo uporabili dodatka (plugins) v IntelliJ IDEA:

### 2.1 MetricsTree  
Uporabili smo ga za izračun:
- MOOD metrike (MHF, AHF, MIF, AIF, PF, CF)  
- preverjanje statistike (LOC, število razredov, vmesnikov itd.)
- Build Metrics Distribution Chart (porazdelitev vrednosti metrik po razredih)

### 2.2 MetricsReloaded  
Uporabljen za merjenje:
- CKJM metrik: WMC, CBO, DIT, LCOM, RFC, NOC, NOPA, NOA, NOAC  
- prikaz razredne kompleksnosti  


Oba dodatka omogočata analizo *samo produkcijske kode* (`src/main/java`), kar je priporočljivo, saj testna koda izkrivlja metrike zaradi drugačne strukture in namena.

---

## 3. MOOD metrike (projektni nivo)

### 3.1 Rezultati

| Metrika | Vrednost |
|--------|----------|
| Attribute Hiding Factor (AHF) | **75.71 %** |
| Attribute Inheritance Factor (AIF) | **0 %** |
| Coupling Factor (CF) | **11.72 %** |
| Method Hiding Factor (MHF) | **0 %** |
| Method Inheritance Factor (MIF) | **0 %** |
| Polymorphism Factor (PF) | **100 %** |

<p align="left">
<img src="https://github.com/AnjaTodorov/ReceptiApp/blob/main/frontend/sliki/7_1.png" width="50%">
</p>

---

### 3.2 Interpretacija posameznih metrik

#### AHF – Attribute Hiding Factor (75.7 %)
- Meri delež atributov, ki so skriti (private/protected).
- **Mejna vrednost:** > 70 % = dobra enkapsulacija.
- **Razlaga:** AHF = 75 % pomeni dobro zasnovo z vidika varovanja podatkov.

#### MHF – Method Hiding Factor (0 %)
- Meri delež skritih metod.
- **Mejna vrednost:** 40–80 % za klasične OO sisteme.
- **Razlaga:** Pri Spring Boot aplikacijah je 0 % normalno, ker:
  - Controller metode morajo biti `public`
  - Service metode so običajno `public`
  - DTO-ji nimajo privatnih metod  
  To NI negativno – značilnost je arhitekture REST aplikacij.

#### AIF & MIF (oba = 0 %)
- Merita dedovanje atributov in metod.
- **Razlaga:** Spring Boot preferira kompozicijo pred dedovanjem → rezultat je pričakovan in pravilen.

#### CF – Coupling Factor (11.7 %)
- Meri sklopljenost razredov.
- **Mejna vrednost:**  
  - < 20 % = dobro  
  - 20–35 % = zmerno  
  - > 35 % = problematično
- **Razlaga:** CF = 11.7 % pomeni nizko sklopljenost in dobro modularnost.

#### PF – Polymorphism Factor (100 %)
- Pri Spring Boot PF zaradi anotacij in interface-proxy mehanizmov ni zanesljiv.
- V praksi vrednost NE pomeni dejanske prekomerne uporabe polimorfizma.

---

## 4. Statistics (projektna raven)

| Metrika | Vrednost |
|--------|----------|
| Lines of Code (LOC) | 669 |
| Non-Commenting Source Statements (NCSS) | 247 |
| KonkretnI razredi | 21 |
| Vmesniki | 5 |
| Statični razredi | 5 |

---

<i>Koda je majhna do srednje velika. Razmerje med razredi in vmesniki je zdravo, arhitektura pa modularna (controller, dto, entity, repository).</i>

---

## 5. CKJM metrike (razredni nivo)

### 5.1 Povprečne vrednosti

| Metrika | Povprečje |
|--------|-----------|
| CBO – Coupling Between Objects | 5.0 |
| DIT – Depth of Inheritance Tree | 1.0 |
| LCOM – Lack of Cohesion of Methods | 2.38 |
| NOC – Number of Children | 0.0 |
| RFC – Response for a Class | 9.59 |
| WMC – Weighted Methods per Class | 6.04 |

<p align="left">
<img src="https://github.com/AnjaTodorov/ReceptiApp/blob/main/frontend/sliki/7_2.png" width="50%">
</p>

<p align="left">
<img src="https://github.com/AnjaTodorov/ReceptiApp/blob/main/frontend/sliki/7_3.png" width="50%">
</p>



---

### WMC – Weighted Methods per Class (avg = 6.04)
- **Mejne vrednosti:**  
  - 1–14 = dobro  
  - 15–30 = visoko  
  - > 30 = problematično
- **Razlaga:** razredi so nizko kompleksni – enostavni za branje in vzdrževanje.

### CBO – Coupling Between Objects (avg = 5)
- **Mejne vrednosti:**  
  - < 14 = dobro  
  - 14–17 = visoko  
  - > 23 = ekstremno
- **Razlaga:** nizka sklopljenost → dobra modularizacija.

### DIT – Depth of Inheritance Tree (avg = 1)
- Normalna vrednost v Spring Boot.
- Aplikacija ne uporablja dedovanja → kar je priporočljivo.

### LCOM – Lack of Cohesion of Methods (avg = 2.38)
- **Mejne vrednosti:**  
  - 0–2 = zelo dobro  
  - 2–5 = normalno  
  - > 5 = slabo
- **Razlaga:** večina razredov ima zadovoljivo kohezijo.

### RFC – Response for a Class (avg = 9.59)
- **Mejna vrednost:** < 50 = dobro, > 100 = problematično
- **Razlaga:** razredi imajo jasno odgovornost, brez pretiranega števila interakcij.

### NOC – Number of Children (avg = 0)
- Ni dedovanja — skladno s Spring arhitekturo.

---

## 6. Build Metrics Distribution Chart 

Dodani graf prikazuje porazdelitev metrik glede na razrede. Analiza:

- **Večina metrik je v zeleni coni (Regular)**  
  → razredi so preprosti, čisti in dobro strukturirani.
- Nekaj metrik je v oranžni kategoriji (High):  
  - NOA (število atributov)  
  - NOM (število metod)  
  To je običajno pri entity in DTO razredih.
- Zelo malo metrik je v rdeči kategoriji (Extreme).  
- WMC, CBO in RFC so skoraj vsi v varnem območju.  
  To potrjuje, da sistem nima god-class težav ali visoke kompleksnosti.

---

<p align="center">
<img src="https://github.com/AnjaTodorov/ReceptiApp/blob/main/frontend/sliki/7_4.png" width="65%">
</p>


<i>Graf potrjuje, da je aplikacija dobro modularizirana, vzdržljiva in nizko kompleksna.</i>

---

## 7. Zaključek

Na podlagi MOOD, CKJM in statističnih metrik ugotavljamo:

- Projekt ima **dobro enkapsulacijo atributov** (AHF = 75 %).  
- Ima **nizko sklopljenost** (CF = 11.7 %, CBO = 5).  
- Kompleksnost razredov je **nizka** (WMC avg = 6).  
- Kohezija metode je **zadovoljiva** (LCOM avg = 2.38).  
- Aplikacija pravilno ne uporablja dedovanja (DIT = 1, MIF/AIF = 0).  
- Projekt je modularen in enostaven za vzdrževanje.



---
