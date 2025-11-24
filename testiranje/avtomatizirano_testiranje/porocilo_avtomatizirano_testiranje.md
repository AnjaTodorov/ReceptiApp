# Avtomatizirano testiranje aplikacije MojiRecepti

Avtor: Konstantin Mihajlov  
Datum: 20.11.2025  
Orodje: Katalon Studio Enterprise  
Aplikacija: MojiRecepti (verzija 1.0)

---

## 1. Uvod

Namen dokumenta je predstaviti izvedbo in rezultate avtomatiziranega testiranja aplikacije MojiRecepti.  
Testiranje je vključevalo tako API kot UI avtomatizacijo v orodju Katalon Studio.

Izvedeni so bili trije testni scenariji:

- TS001 – Ustvarjanje novega recepta  
- TS002 – Ustvarjanje novega načrta obrokov  
- TS003 – Preračun sestavin in hranilnih vrednosti

Vsi scenariji so bili uspešno avtomatizirani in izvedeni.

---

## 2. Uporabljena orodja in pristop

### 2.1 Uporabljena orodja

- Katalon Studio Enterprise 10.4.1
- Chrome WebDriver
- Spring Boot backend (localhost:8080)
- Frontend aplikacije (localhost:3000)

### 2.2 Pristop k avtomatizaciji

- API testiranje (POST, GET, preverjanje response kode, preverjanje JSON property-jev)
- UI testiranje (interakcija s stranjo, modalnimi okni, validacija vsebine)
- Uporaba Object Repository za vsak testni scenarij (mape TS001, TS002, TS003)

---

## 3. Testni scenariji

# TS001 – Ustvarjanje novega recepta

### Namen:
Preveriti, ali sistem omogoča pravilno ustvarjanje recepta skupaj s slikami, sestavinami in hranilnimi vrednostmi.

### Avtomatizacija vključuje:
- Izpolnjevanje obrazca Dodaj recept
- Dodajanje sestavin
- Dodajanje hranilnih vrednosti
- Pošiljanje POST zahtev: /recepti, /sestavine, /hranilne-vrednosti
- Validacija zapisa v aplikaciji

### Rezultat:
Test uspešno opravljen.
Status: 🟢 Test OK

![TS001-1](https://github.com/AnjaTodorov/ReceptiApp/blob/main/frontend/sliki/6_1.png)
![TS001-2](https://github.com/AnjaTodorov/ReceptiApp/blob/main/frontend/sliki/6_2.png)

---

# TS002 – Ustvarjanje novega načrta obrokov

### Namen:
Preveriti ustvarjanje jedilnika za izbran datum brez podvajanja in z vsemi tremi obroki.

### Avtomatizacija vključuje:
- Pridobivanje receptov za zajtrk, kosilo in večerjo
- Preverjanje obstoja jedilnika za izbran datum
- Kreiranje novega jedilnika z API POST /meal-plans/create
- Preverjanje shranjenega jedilnika

### Rezultat:
Test uspešno opravljen.
Status: 🟢 Test OK

![TS002-1](https://github.com/AnjaTodorov/ReceptiApp/blob/main/frontend/sliki/6_3.png)  
![TS002-2](https://github.com/AnjaTodorov/ReceptiApp/blob/main/frontend/sliki/6_4.png)  
![TS002-3](https://github.com/AnjaTodorov/ReceptiApp/blob/main/frontend/sliki/6_5.png)

---

# TS003 – Preračun sestavin in hranilnih vrednosti

### Namen:
Preveriti matematično pravilnost preračuna vrednosti ob spremembi števila porcij.

### Avtomatizacija vključuje:
- Odprtje recepta
- Odprtje modalnega izračuna
- Vnos novega števila porcij
- Preverjanje preračunanih sestavin in hranilnih vrednosti
- Preverjanje zapiranja modala

### Rezultat:
Test uspešno opravljen.
Status: 🟢 Test OK

![TS003-1](https://github.com/AnjaTodorov/ReceptiApp/blob/main/frontend/sliki/6_6.png)  
![TS003-2](https://github.com/AnjaTodorov/ReceptiApp/blob/main/frontend/sliki/6_7.png)  


---

## 4. Rezultati testiranja

| Testni scenarij | Opis | Rezultat |
|-----------------|-------|----------|
| TS001 | Ustvarjanje novega recepta | Uspešno |
| TS002 | Ustvarjanje novega načrta obrokov | Uspešno |
| TS003 | Preračun sestavin in hranilnih vrednosti | Uspešno |

---

## 5. Zaključek

Avtomatizacija testiranja aplikacije MojiRecepti je bila izvedena uspešno.  
Vsi trije testni scenariji so bili implementirani, izvedeni in potrjeni kot pravilni.  





