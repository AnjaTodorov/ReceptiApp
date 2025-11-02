# *Poročilo o napaki (BUG REPORT)*
---

## Napaka MR-001 – Aplikacija dovoljuje vnos negativne teže pri izračunu vnosa kalorij

| Atribut              | Vsebina |
|----------------------|---------|
| **ID hrošča**        | MR-001 |
| **Status**           | Zaključeno |
| **Naslov**           | Aplikacija dovoli vnos negativne teže pri izračunu kalorij |
| **Opis**             | Kalkulator prehranskih potreb sprejema negativne vrednosti teže, kar povzroči napačne izračune kalorij in neveljavna prehranska priporočila. Ko uporabniki vnesejo negativne vrednosti (npr. -70 kg), sistem obdela izračun brez validacije in proizvaja nepomenljive rezultate. |
| **Okolje**           | Chrome Browser v119+, Windows 11, Aplikacija MojiRecepti |
| **Konfiguracija**    | MojiRecepti v1.0, Modul Kalkulator Prehranskih Potreb |
| **Kritičnost (Severity)** | Srednja |
| **Prioriteta (Priority)** | Visoka |
| **Analiza**          | Validacija vnosa v kalkulatorju prehranskih potreb preverja le prazna polja, ne pa veljavnosti obsega ali predznaka številskih vnosov. Polje za težo sprejema katerokoli številsko vrednost vključno z negativnimi. |
| **Rešitev**          | Dodana JS-validacija |
| **Odprl**            | Konstantin Mihajlov (@kmihajlov) |
| **Zaprto**           | Konstantin Mihajlov (@kmihajlov) |
| **Datum opazovanja** | 2025-10-31 |
| **Datum odprtja**    | 2025-10-31 |
| **Datum zaprtja**    | 2025-11-02 |


### Koraki 
1. Pojdite v razdelek "Moje prehranske potrebe"
2. Vnesite negativno vrednost v polje za težo (npr. -70)
3. Izpolnite ostala zahtevana polja z veljavnimi podatki
4. Kliknite gumb "Izračunaj"
5. Opazujte kako sistem izračuna prehranske vrednosti na podlagi negativne teže

### Pričakovano 
- Sistem bi moral prikazati napako validacije za negativen vnos teže
- Izračun bi se moral preprečiti dokler ni vnesena veljavna pozitivna teža
- Uporabnik bi moral prejeti jasno sporočilo o neveljavnem vnosu

### Dejansko 
- Sistem sprejema negativne vrednosti teže
- Izračun se izvede z neveljavnimi podatki
- Rezultati prikažejo napačne prehranske vrednosti

### Posnetek zaslona (pred popravkom)
![Napaka: negativna teža](https://github.com/AnjaTodorov/ReceptiApp/blob/main/frontend/sliki/napaka1.png?raw=true)

### Rešitev
Dodana validacija vnosa za preverjanje pozitivnih vrednosti v polju za težo:
```javascript
if (weight <= 0) {
    alert('Teža mora biti pozitivna vrednost!');
    return;
}
```

---
## Napaka MR-002 – Uporabnik lahko ustvari načrt obrokov za pretekle datume
---

| Atribut              | Vsebina |
|----------------------|---------|
| **ID hrošča**        | MR-002 |
| **Status**           | Zaključeno |
| **Naslov**           | Uporabnik lahko ustvari načrt obrokov za pretekle datume |
| **Opis**             | Sistem za načrtovanje obrokov omogoča uporabnikom izbiro in ustvarjanje načrtov obrokov za datume v preteklosti. Ko uporabnik izbere pretekli datum iz izbirnika datumov, sistem sprejme vnos in ustvari načrt brez validacije. |
| **Okolje**           | Chrome Browser v119+, Windows 11, Aplikacija MojiRecepti |
| **Konfiguracija**    | MojiRecepti v1.0, Modul Načrtovanja Obrokov |
| **Kritičnost (Severity)** | Srednja |
| **Prioriteta (Priority)** | Srednja |
| **Analiza**          | Polje za vnos datuma v obrok.js ne vključuje validacije, ki bi omejila izbiro le na trenutne in prihodnje datume. Privzeti datum je nastavljen na naslednji dan, vendar ročni vnos ali izbirnik datumov omogoča izbiro preteklih datumov. |
| **Odprl**            | Konstantin Mihajlov (@kmihajlov) |
| **Zaprto**           | Konstantin Mihajlov (@kmihajlov)
| **Datum opazovanja** | 2025-10-31 |
| **Datum odprtja**    | 2025-10-31 |
| **Datum zaprtja**    | 2025-11-03 |


### Koraki 
1. Pojdite v razdelek "Načrt obrokov"
2. Kliknite na izbirnik datumov ali ročno vnesite pretekli datum
3. Izberite recepte za vsako vrsto obroka
4. Kliknite gumb "Ustvari načrt obrokov"
5. Opazujte kako sistem ustvari načrt obrokov za pretekli datum

### Pričakovano 
- Izbirnik datumov bi moral omejiti izbiro le na današnji in prihodnje datume
- Sistem bi moral prikazati napako validacije za izbiro preteklega datuma
- Ustvarjanje načrta obrokov bi se moralo preprečiti za pretekle datume

### Dejansko 
- Izbirnik datumov omogoča izbiro poljubnih datumov vključno s preteklimi
- Sistem ustvarja načrte obrokov za zgodovinske datume brez opozoril
- Načrti obrokov za pretekle datume se prikažejo na seznamu načrtov

### Posnetek zaslona (pred popravkom)
![Napaka: negativen](https://github.com/AnjaTodorov/ReceptiApp/blob/main/frontend/sliki/napaka2.png)

## Rešitev
Dodana validacija datuma za omejitev načrtov obrokov na trenutne in prihodnje datume:
```javascript
const selectedDate = new Date(mealDate);
const today = new Date();
today.setHours(0, 0, 0, 0);

if (selectedDate < today) {
    alert('Načrt obrokov je mogoče ustvariti le za današnji in prihodnje datume!');
    return;
}
```
