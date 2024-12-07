# Poročilo o napredku - Scrum Postopek

## Uvod

V tem poročilu bomo opisali celoten proces razvoja funkcionalnosti **prilagoditve količin sestavin glede na število porcij** za aplikacijo z recepti. Uporabili smo metodologijo **Scrum**, pri čemer smo sledili principom, kot so razdeljevanje nalog, ocena nalog s pomočjo Planning Pokerja, implementacija nalog, ter sledenje napredku na **GitHub Agile tabli**.

Projekt smo izvedli v kratkem časovnem obdobju, zato smo morali biti zelo učinkoviti in natančni pri razdeljevanju nalog in spremljanju napredka. Vse naloge in napredek smo spremljali preko **GitHub-a**, kjer smo ustvarili agilno tablo z različnimi fazami nalog (ToDo, In Progress, Done).

## Sprint Planning

### Izbira uporabniške zgodbe
Na začetku smo izbrali uporabniško zgodbo, ki je bila:  
**"Kot uporabnik želim možnost prilagoditve količin sestavin glede na število porcij, da lahko skuham točno toliko, kot potrebujem."** 

Ta funkcionalnost je bila del širše naloge v okviru projekta za aplikacijo z recepti.

### Razdelitev nalog
Po izbiri uporabniške zgodbe smo nalogo razdelili na manjše, izvedljive naloge, ki so specifične, merljive in izvedljive v kratkem času. Naloge so bile razdeljene po naslednjih fazah:

| **Task Name** | **Description** | **Story Points** |
|---------------|-----------------|------------------|
| Posodobitev frontenda (JS) za generiranje nakupuvalni seznam | Posodobitev backenda za generiranje nakupovalnega seznama, ki bo omogočil generiranje seznamov sestavin za nakup glede na recept. | 3 |
| Testiranje in validacija | Preverjanje delovanja funkcionalnosti sistema, vključno z backendom in frontendom. | 2 |
| Dokumentacija | Priprava dokumentacije za projekt, ki vključuje opis funkcionalnosti, tehnične zahteve, nastavitve sistema in navodila za uporabo. | 2 |
| Prikaz izračunanih sestavin glede na izbrano število porcij v UI (frontend) | Posodobitev uporabniškega vmesnika za prikaz nakupovalnega seznama, ki bo omogočal pregled sestavin za nakup. | 5 |
| Povezava frontend-backend | Izvedba povezave med frontend aplikacijo in backendom, da bi omogočili pretok podatkov med obema sistemoma. | 5 |
| Dodaj stolpec osebe/porcij v tabelo Recepti | Dodajanje stolpca "osebe" v tabelo receptov, da se omogoči prilagoditev količin sestavin glede na število oseb. | 1 |
| Preoblikovanje sestavin v tabelo | Preoblikovanje shranjevanja sestavin, da bodo te shranjene v tabeli in povezane z recepti. | 3 |
| Posodobitev backenda za tabelo Recepti | Posodobitev backenda za upravljanje s tabelo receptov. | 5 |
| Posodobitev backenda za tabelo Sestavine | Posodobitev backenda za upravljanje s tabelo sestavin. | 5 |
| Posodobitev UI za ustvarjanje receptov - dodajanje število porcij, dodajanje sestavin | Posodobitev uporabniškega vmesnika za ustvarjanje receptov z možnostjo vnosa števila oseb in prilagoditve količin sestavin. | 3 |


### Ocenjevanje nalog s pomočjo Planning Pokerja
Vsako nalogo smo ocenili s pomočjo metode **Planning Poker**, kjer smo določili časovno zahtevnost nalog. Za ocenjevanje smo uporabili **story points**, ki so nam omogočili boljšo predstavo o obsegu nalog. 

## Implementacija in napredek

### Načrtovanje in izvajanje nalog
Po začetnem načrtovanju smo se osredotočili na implementacijo nalog, kot so **posodobitev backenda za generiranje nakupovalnega seznama** in **dodajanje stolpca osebe v tabelo receptov**. Implementirali smo osnovno strukturo za prilagoditev količin sestavin glede na število oseb.

Ko smo poskrbeli za osnovno funkcionalnost, smo se osredotočili na **povezavo frontend-backend**, kar je omogočilo pretok podatkov med obema sistemoma. Ta povezava je bila ključna za pravilno delovanje aplikacije, saj je omogočila, da so uporabniki lahko v realnem času prilagodili količine sestavin glede na število oseb, ki jih recept streže.

Ob istem času smo začeli z nalogo za **prikaz nakupovalnega seznama v uporabniškem vmesniku**, ki je uporabnikom omogočala, da so enostavno pregledali vse potrebne sestavine za nakup.

### Testiranje in validacija
Po implementaciji vseh ključnih funkcionalnosti smo izvedli obsežno **testiranje in validacijo** sistema. Preverjali smo, ali so vsi podatki pravilno obdelani, shranjeni in prikazani uporabnikom, ter izvedli testiranje vseh interakcij z uporabniškim vmesnikom.

### Dokumentacija
Ko je bila funkcionalnost implementirana, smo pripravili celovito **dokumentacijo** projekta. Ta je vključevala podroben opis vseh funkcionalnosti, tehnične zahteve, nastavitve sistema in navodila za uporabo, kar bo omogočilo enostavno vzdrževanje sistema v prihodnosti.

### Premikanje nalog po tabeli
Vse naloge smo spremljali na **GitHub Agile tabli** in jih redno premikali med fazami (ToDo → Doing → Done), da smo imeli jasen pregled nad napredkom. Na koncu sprint-a smo vse naloge zaključili in premaknili v fazo **Done**.

## Zaključek

Projekt je bil izveden uspešno v kratkem časovnem obdobju, kar je zahtevalo natančno načrtovanje, hitrost pri implementaciji in natančnost pri testiranju. Z uporabo Scrum metodologije smo lahko učinkovito upravljali naloge, spremljali napredek in zagotovili, da bo končni izdelek deloval kot pričakovano. Celoten razvojni proces je bil dokumentiran v skladu z najboljšimi praksami Scrum-a in je vključeval:

1. **Sprint Planning** – jasna določitev nalog.
2. **Iterativno izvajanje nalog** – redno spremljanje napredka.
3. **Testiranje in validacija** – zagotavljanje kakovosti.
4. **Dokumentacija** – ustrezna priprava za prihodnje nadgradnje.

Za nadaljevanje lahko enostavno nadgradimo funkcionalnost in vključimo dodatne možnosti za prilagoditve ali nove značilnosti aplikacije.
