<p align="center">
  <img src="https://i.imgur.com/613jK3H.png" alt="slika" width="300">
</p>

<h1 align="center">Moji recepti</h1>
<p align="center"><strong>Shranjujte in organizirajte svoje priljubljene recepte na enem mestu – samo za vas!</strong></p>

<p align="center"><strong>Spletna aplikacija za ustvarjanje, posodabljanje, brisanje in upravljanje vaših lastnih receptov, ki vsebuje zbirko receptov, ki jih prispevajo uporabniki, z sestavinami, navodili in fotografijami.</strong></p>
<p align="center">
  <img src="https://media3.giphy.com/media/A5ugHVbuFL3uo/200w.gif?cid=8d8c03587i12l4bwuobn5p6whkqxkdaoa2ye4ix8m22pkkrx&ep=v1_gifs_search&rid=200w.gif&ct=g" alt="Cooking GIF" width="250">
</p>

## Kazalo vsebine

- 📄 [Pregled](#-pregled)
- 🌟 [Vizija](#-vizija)
- 📚 [Besednjak](#-besednjak)
- 🆕 [Dodana funkcionalnost](#-dodana-funkcionalnost)
- ✏️ [DPU](#%EF%B8%8F-dpu)
- ⭐ [Značilnosti](#--značilnosti)
- ⚙️ [Tehnološki sklad](#%EF%B8%8F-tehnološki-sklad)
- 📁 [Struktura projekta](#-struktura-projekta)
- 🖇️ [Diagram razredov](#%EF%B8%8F-diagram-razredov)
- 📏 [Standardi kodiranja](#-standardi-kodiranja)
- 🌐 [Končne točke REST API](#-končne-točke-rest-api)
- 📌 [Prvi koraki](#-prvi-koraki)
- ▶️ [Zagon aplikacije](#%EF%B8%8F-zagon-aplikacije)
- 👤 [Avtorji](#-avtorji)
- 📜 [Licenca](#-licenca)
 
## 📄 Pregled

Ta projekt ponuja preprost in intuitiven vmesnik za uporabnike, da dodajo, uredijo, posodobijo in izbrišejo svoje najljubše recepte. Ustvarite svoj osebni kuharski arhiv in ga prilagodite po svojih željah. Vaši recepti, vaša pravila – vse na enem mestu, varno shranjeno in dostopno kadarkoli. Zgrajena z uporabo SpringBoot backend-a in sodobnega frontend okvira, je aplikacija zasnovana za enostavno upravljanje receptov in prilagodljive posodobitve.

## 🌟 Vizija
Vizija projekta "Moji recepti" je ustvariti intuitivno in dostopno spletno aplikacijo, ki uporabnikom omogoča enostavno shranjevanje in organiziranje njihovih najljubših receptov. Namen aplikacije je olajšati uporabnikom iskanje, ustvarjanje in upravljanje lastnih receptov, kar jim omogoča, da razvijajo svoje kuharske veščine in se povezujejo s skupnostjo ljubiteljev hrane. Aplikacija je namenjena vsem, ki želijo imeti svoje recepte na enem mestu, ne glede na raven kuharskih izkušenj.

S "Moji recepti" želimo izboljšati uporabniško izkušnjo s funkcionalnostmi, kot so dodajanje fotografij, izbira sestavin in enostavno upravljanje receptov. Poleg tega želimo da aplikacija omogoča razvrščanje obrokov v kategorije, kot so zajtrk, kosilo, večerja in pijače, kar uporabnikom pomaga hitreje in učinkoviteje pripravljati obroke.

## 📚 Besednjak

V tem razdelku bomo predstavili ključne termine, uporabljene v projektu. Namen besednjaka je zagotoviti razumevanje specifičnih izrazov in konceptov, ki so pomembni za pravilno rabo aplikacije. Vsak izraz je opremljen z definicijo, smernicami in primerom, da bi uporabnikom olajšali razumevanje funkcionalnosti in vloge posameznih komponent.

<table>
    <thead>
        <tr>
            <th>Izraz</th>
            <th>Definicija</th>
            <th>Smernice</th>
            <th>Primer</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Recept</td>
            <td>Navodila za pripravo jedi, ki vključuje seznam sestavin in postopke.</td>
            <td>Recepti morajo biti jasni in enostavni za sledenje.</td>
            <td>Piščančji curry z zelenjavnim rižem.</td>
        </tr>
        <tr>
            <td>Sestavine</td>
            <td>Podatki o vseh potrebnih elementih za pripravo recepta.</td>
            <td>Vsak recept mora imeti seznam sestavin, ki so natančno navedene.</td>
            <td>Piščanec, riž, curry pasta, zelenjava.</td>
        </tr>
        <tr>
            <td>Uporabniški vmesnik</td>
            <td>Del aplikacije, s katerim uporabnik komunicira za dodajanje, urejanje in iskanje receptov.</td>
            <td>Vmesnik naj bo intuitiven in enostaven za navigacijo.</td>
            <td>Oblika za dodajanje novega recepta z možnostjo nalaganja slike.</td>
        </tr>
        <tr>
            <td>CRUD operacije</td>
            <td>Osnovne operacije za ustvarjanje, branje, posodabljanje in brisanje podatkov.</td>
            <td>Vsaka aplikacija mora omogočati te operacije za učinkovito upravljanje podatkov.</td>
            <td>Dodajanje novega recepta, urejanje obstoječega recepta.</td>
        </tr>
        <tr>
            <td>Baza podatkov</td>
            <td>Struktura, kjer so shranjeni podatki o receptih in uporabnikih.</td>
            <td>Podatki morajo biti organizirani in enostavno dostopni.</td>
            <td>MySQL baza, ki hrani informacije o vseh receptih.</td>
        </tr>
    </tbody>
</table>

## 🆕 Dodana funkcionalnost
### Načrtovanje obrokov in nakupovalni seznam
#### 1. Implementirana funkcionalnost
Dodatna funkcionalnost omogoča uporabnikom, da iz receptov, ki so jih dodali, ustvarijo načrt obrokov (Meal Plan). Načrt obrokov vsebuje tri kategorije: zajtrk, kosilo in večerja. Poleg tega lahko uporabnik na podlagi sestavin iz načrta obrokov ustvari nakupovalni seznam.

#### 2. Kako deluje nova funkcionalnost
- **Ustvarjanje načrta obrokov**: Uporabnik lahko izbere recepte za zajtrk, kosilo in večerjo, ki jih je že dodal v aplikacijo in datum načrta. Ob ustvarjanju načrta obrokov se ta shrani in je viden v ločenem razdelku aplikacije.
- **Ustvarjanje nakupovalnega seznama**: Ko je načrt obrokov ustvarjen, lahko uporabnik s klikom na ustrezno možnost pridobi seznam sestavin za izbrane obroke. Nakupovalni seznam združi količine enakih sestavin, da je seznam čim bolj pregleden in praktičen.

#### 3. Preizkus nove funkcionalnosti
- **Dostop do načrta obrokov**:
  1. Odprite aplikacijo.
  2. Dodajte recepte za zajtrk, kosilo in večerjo v skladu z običajnim postopkom.
  3. Pojdite na razdelek "Načrt obrokov" in izberite možnost "Ustvari načrt obrokov".
  4. Izberite želene recepte za zajtrk, kosilo in večerjo, nato kliknite "Potrdi".
  5. Načrt obrokov bo shranjen in dostopen v seznamu obstoječih načrtov.

- **Dostop do nakupovalnega seznama**:
  1. Izberite načrt obrokov iz seznama obstoječih načrtov.
  2. Kliknite na gumb "Ustvari nakupovalni seznam".
  3. Prikazan bo seznam vseh sestavin, potrebnih za izbrane recepte, združenih po količinah.

Nova funkcionalnost je namenjena lažjemu načrtovanju obrokov in organizaciji nakupov, kar uporabniku prihrani čas in izboljša uporabniško izkušnjo.

## ✏️ DPU
<h3>Naziv: Moji Recepti </h3>

<h3>Vloge:</h3>
<ul>
  <li><strong>Uporabnik</strong>: Predstavlja glavnega uporabnika, ki uporablja sistem "Moji Recepti" za upravljanje receptov. Uporabnik lahko ustvarja, posodablja, briše in išče recepte.</li>
</ul>

<h3>Splošen opis:</h3>
<p>
Ta diagram zagotavlja jasno predstavitev funkcionalnosti, ki jih ponuja naša aplikacija, in prikazuje, kako uporabniki lahko dodajajo, brišejo in urejajo recepte. Z opredelitvijo različnih primerov uporabe lahko bolje razumemo zahteve sistema in zagotovimo, da učinkovito zadostuje potrebam uporabnikov. Ta dokument si prizadeva ilustrirati diagram primerov uporabe ter podrobno opisati udeležence, njihove cilje in ustrezne primere uporabe, ki prispevajo k celotni funkcionalnosti "Moji recepti."
</p>
<h3>Primeri uporabe:</h3>
<ol>
  <li>
    <strong>Ustvari Recept</strong>
    <ul>
      <li><em>Opis</em>: Uporabniku omogoča ustvarjanje novega recepta.</li>
      <li><em>Razširitve</em>: Vključuje možnost "Naloži Fotografija", kjer uporabnik lahko naloži sliko recepta.</li>
    </ul>
  </li>
  <li>
    <strong>Posodobi Recept</strong>
    <ul>
      <li><em>Opis</em>: Omogoča uporabniku, da spremeni ali posodobi obstoječi recept.</li>
    </ul>
  </li>
  <li>
    <strong>Izbriši Recept</strong>
    <ul>
      <li><em>Opis</em>: Uporabniku omogoča, da izbriše obstoječi recept iz svoje zbirke.</li>
    </ul>
  </li>
  <li>
    <strong>Poglej Recepte</strong>
    <ul>
      <li><em>Opis</em>: Uporabniku omogoča pregled seznama vseh shranjenih receptov.</li>
    </ul>
  </li>
  <li>
    <strong>Išči Recepte</strong>
    <ul>
      <li><em>Opis</em>: Omogoča uporabniku, da poišče določene recepte.</li>
      <li><em>Razširitve</em>: Vključuje možnost "Filtriraj Rezultate", ki uporabniku omogoča, da zoži rezultate iskanja glede na različne kriterije.</li>
    </ul>
  </li>
</ol>


![ReceptiDPU](https://github.com/user-attachments/assets/836c6368-7a19-4837-bc7c-2b924dee1013)



## ⭐  Značilnosti

- **Upravljanje receptov**: Dodajanje, posodabljanje in brisanje receptov
- **Nalaganje fotografij**: Naložite fotografije za vsak recept
- **Izbira sestavin**: Možnost izbire določenih sestavin za predlog ustreznih receptov
- **Zbirka receptov**: Brskajte po širokem spektru preprostih do kompleksnih receptov

## ⚙️ Tehnološki sklad

- **Backend**: SpringBoot (Java)
  <p>
    <a href="https://spring.io/" target="_blank">
      <img src="https://i0.wp.com/e4developer.com/wp-content/uploads/2018/01/spring-boot.png?w=1300&ssl=1" alt="Spring Boot Logo" width="110" style="margin: 15px;">
    </a>
  </p>
- **Frontend**: NodeJS okvir
  <p>
    <a href="https://nodejs.org/" target="_blank">
      <img src="https://nodejs.org/static/images/logo.svg" alt="Node.js Logo" width="110" style="margin: 15px;">
    </a>
  </p>
- **Baza podatkov**: MySQL
  <p>
    <a href="https://www.mysql.com/" target="_blank">
      <img src="https://www.mysql.com/common/logos/logo-mysql-170x115.png" alt="MySQL Logo" width="110" style="margin: 15px;">
    </a>
  </p>
- **Implementacija**: Lokalni strežnik

## 📁 Struktura projekta

```bash
project-root/
│
├── .idea/                                      # IDE nastavitve (npr. za IntelliJ)
│   └── *                                       # Vsebuje nastavitve specifične za projekt, workspace.xml, itd.
│
├── .mvn/                                       # Maven wrapper datoteke za upravljanje odvisnosti
│   ├── wrapper/
│   │   ├── maven-wrapper.jar                   # Maven wrapper za odvisnosti
│   │   └── maven-wrapper.properties            # Lastnosti za Maven wrapper
│
├── backend/                                    # Backend mapa (Spring Boot)
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/RIS/Mojirecepti/        # Osnovni paket
│   │   │   │       ├── controller/             # REST API kontrolerji
│   │   │   │       │   └── ReceptiController.java # Kontroler za obdelavo zahtevkov za recepte
│   │   │   │       ├── entity/                 # Entitetne (DB) razrede
│   │   │   │       │   └── Recepti.java        # Entiteta za recept
│   │   │   │       ├── repository/             # Repozitoriji za dostop do podatkov
│   │   │   │       │   └── ReceptiRepository.java # Repozitorij za entitete receptov
│   │   │   │       ├── config/                 # Konfiguracijski razredi
│   │   │   │       │   └── WebConfig.java      # Web nastavitve (npr. CORS)
│   │   │   │       ├── Hello.java              # Testni razred
│   │   │   │       └── MojiReceptiApplication.java # Glavni razred za Spring Boot aplikacijo
│   │   │   └── resources/
│   │   │       └── application.properties      # Konfiguracija aplikacije (npr. DB povezava)
│   │   └── test/                               # Testni razredi za backend
│   ├── pom.xml                                 # Maven odvisnosti in konfiguracija za gradnjo
│
├── frontend/                                   # Mapa za frontend (odjemalska koda)
│   ├── app.js                                  # Glavni JavaScript datoteka za frontend logiko
│   ├── index.html                              # Glavna HTML stran (domov)
│   ├── recepti.html                            # HTML stran za prikaz in upravljanje receptov
│   ├── style.css                               # CSS za stilizacijo frontend-a
│   ├── sliki/                                  # Mapa za slike v frontend-u
│
├── node_modules/                               # Node.js odvisnosti (samodejno generirane s npm)
│   └── *                                       # Vsebuje vse nameščene pakete za Node.js
│
├── .gitignore                                  # Datoteke, ki jih Git ignorira
├── mvnw                                        # Maven wrapper skripta (Linux/Mac)
├── mvnw.cmd                                    # Maven wrapper skripta (Windows)
├── package-lock.json                           # Zaklenjena datoteka za Node.js odvisnosti (samodejno generirano s npm)
├── package.json                                # Node.js odvisnosti in skripte za frontend
├── README.md                                   # Dokumentacija projekta
└──  server.js                                   # Node.js strežnik za zagon frontend-a


```

## 🖇️ Diagram razredov

Spodaj je vizualni prikaz diagrama razredov backend aplikacije. Ta diagram prikazuje strukturo in povezave med različnimi razredi ter vmesniki, ki sestavljajo backend sistem.


![ReceptiRazredniDiagram](https://github.com/user-attachments/assets/34c4c184-0e7b-417f-91bb-026f4ef779a6)

---

## Opis diagrama razredov

Diagram razredov prikazuje osnovno strukturo aplikacije **Moji Recepti** in njene glavne funkcionalne komponente. Vključuje entitete, kontrolerje in konfiguracije ter prikazuje njihove odnose in odgovornosti.

### 1. Entitete

- **Recepti**
  - **Namen:** Predstavlja recept v aplikaciji.
  - **Atributi:**
    - `idRecepti`: Unikatni identifikator za recept.
    - `naziv`, `sestavine`, `slika`, `opis`: Opisujejo ime recepta, sestavine, sliko in podrobnosti recepta.
    - `tip`: Določa tip recepta, ki je povezan z enumeracijo **Tip** (npr. zajtrk, kosilo, večerja).
  - **Ključne metode:**
    - Getterji in setterji za vse atribute (npr. `getNaziv()`, `setTip()`).
    - `toString()`: Pretvori objekt recepta v berljivo besedilo.

- **Tip (Enumeracija)**
  - **Namen:** Določa tipe obrokov.
  - **Vrednosti:** 
    - `ZAJTRK` (Zajtrk)
    - `KOSILO` (Kosilo)
    - `VEČERJA` (Večerja).
  - **Povezava:** Neposredno povezan z razredom **Recepti**, kjer določa tip posameznega recepta.

- **NacrtObrokov**
  - **Namen:** Predstavlja načrt obrokov.
  - **Atributi:**
    - `idNacrtObrokov`: Unikaten ID za načrt obrokov.
    - `datum`: Datum načrta obrokov.
  - **Ključne metode:**
    - Getterji in setterji (npr. `getDatum()`, `setDatum()`).

- **ReceptiNacrtObrokov**
  - **Namen:** Povezovalna entiteta, ki povezuje recepte z načrti obrokov.
  - **Atributi:**
    - `idReceptNacrtObrokov`: Unikaten ID, ki povezuje recept z načrtom obrokov.
    - `recepti`: Referenca na pripadajoči recept.
    - `nacrtObrokov`: Referenca na pripadajoči načrt obrokov.
  - **Ključne metode:**
    - Getterji in setterji za atribute.
    - `toString()`: Vrne berljivo predstavitev povezave.

---

### 2. Repozitoriji (vmesniki)

Repozitoriji abstraktirajo dostop do podatkov in zagotavljajo funkcionalnosti za iskanje, shranjevanje in posodabljanje podatkov.

- **ReceptiRepository**
  - **Namen:** Upravljanje entitet `Recepti`.
  - **Ključna metoda:** `findByTip(tip: Tip): List<Recepti>` vrne recepte določenega tipa.

- **NacrtObrokovRepository**
  - **Namen:** Upravljanje entitet `NacrtObrokov`.
  - **Ključna metoda:** `findByDate(date: Date)` poišče načrte obrokov glede na datum.

- **ReceptiNacrtObrokovRepository**
  - **Namen:** Upravljanje povezav med recepti in načrti obrokov.

---

### 3. Kontrolerji

Kontrolerji izvajajo poslovno logiko aplikacije, obravnavajo zahteve in komunicirajo z repozitoriji.

- **ReceptiController**
  - **Namen:** Upravljanje CRUD operacij za `Recepti`.
  - **Ključne metode:**
    - `getAllRecepti()`: Pridobi vse recepte.
    - `getReceptById(Long id)`: Pridobi recept glede na ID.
    - `createRecept(...)`: Ustvari nov recept, vključno z nalaganjem slike.
    - `updateRecept(...)`: Posodobi obstoječi recept.
    - `deleteRecept(Long id)`: Izbriše recept glede na ID.

- **NacrtObrokovController**
  - **Namen:** Upravljanje CRUD operacij za `NacrtObrokov`.
  - **Ključne metode:**
    - `getAllNacrtObrokov()`: Pridobi vse načrte obrokov.
    - `createNacrtObrok(...)`: Ustvari nov načrt obrokov.
    - `updateNacrtObrok(...)`: Posodobi obstoječi načrt obrokov.
    - `deleteNacrtObrok(Long id)`: Izbriše načrt obrokov glede na ID.

- **ReceptiNacrtObrokovController**
  - **Namen:** Upravljanje povezave med recepti in načrti obrokov.
  - **Ključne metode:**
    - `getAllMealPlans()`: Pridobi vse povezave med recepti in načrti obrokov.
    - `createMealPlan(...)`: Ustvari novo povezavo.
    - `deleteMealPlan(Long id)`: Odstrani specifično povezavo.

---

### 4. Konfiguracije

- **WebConfig**
  - **Namen:** Konfigurira CORS nastavitve za omogočanje cross-origin zahtev.
  - **Ključna metoda:**
    - `addCorsMappings(...)`: Določi CORS nastavitve za aplikacijo.

---

### 5. Glavni razred

- **MojiReceptiApplication**
  - **Namen:** Vstopna točka aplikacije.
  - **Ključna metoda:** `main(String[] args)` inicializira Spring Boot aplikacijo.

---
## 📏 Standardi kodiranja

Pri razvoju aplikacije "Moji recepti" upoštevamo določene standarde kodiranja, da zagotovimo berljivost, vzdržljivost in kakovost kode. Ti standardi vključujejo:

1. **Imenovanje spremenljivk in funkcij**:
   - Uporabljajte opisna imena, ki jasno označujejo namen spremenljivke ali funkcije.
   - Uporabite konvencije imenovanja (camelCase za spremenljivke in funkcije, PascalCase za razrede).

2. **Komentiranje kode**:
   - Dodajte komentarje, kjer je to potrebno, da pojasnite zapletene dele kode.

3. **Struktura kode**:
   - Organizirajte kodo v logične enote in mape (npr. po funkcionalnostih).

4. **Preverjanje napak**:
   - Implementirajte ustrezno obravnavo napak in izjem.
   - Uporabite enote testiranja za preverjanje funkcionalnosti kode.

5. **Verzijski nadzor**:
   - Redno posodabljajte in dokumentirajte spremembe v Git repozitoriju.
   - Uporabljajte smiselne sporočila za commit.

Z upoštevanjem teh standardov kodiranja želimo olajšati sodelovanje v projektu in zagotavljati visoko kakovost programske opreme.

## 🌐 Končne točke REST API

| **Končna točka**         | **Metoda** | **Opis**                                    | **Odgovor**                                                                                           |
|--------------------------|------------|---------------------------------------------|--------------------------------------------------------------------------------------------------------|
| `/recepti`               | GET        | Pridobi seznam vseh receptov               | Vrne seznam receptov v JSON formatu, kjer vsak vsebuje `id`, `naziv`, `slika`, `sestavine` in `opis`. |
| `/recepti/{id}`          | GET        | Pridobi recept po ID-ju                    | Vrne podrobnosti recepta v JSON formatu (`id`, `naziv`, `slika`, `sestavine`, `opis`) za določen `id`. |
| `/recepti`               | POST       | Ustvari nov recept                         | Vrne ustvarjeni recept z dodeljenim `id`, ali sporočilo o napaki, če ustvarjanje ni uspešno.          |
| `/recepti/{id}`          | PUT        | Posodobi obstoječi recept po ID-ju         | Vrne posodobljeni recept, če je uspešno, ali sporočilo o napaki, če recept z določenim `id` ne obstaja. |
| `/recepti/{id}`          | DELETE     | Izbriše recept po ID-ju                    | Vrne sporočilo o uspehu, če je brisanje uspešno, ali sporočilo o napaki, če recept z določenim `id` ne obstaja. |




## 📌 Prvi koraki

Za lokalno kopijo in zagon sledite tem preprostim korakom.

### Predpogoji

Poskrbite, da imate nameščeno naslednje:

<ul>
  <li><a href="https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html" target="_blank">Java 17 ali novejša</a></li>
  <li><a href="https://maven.apache.org/install.html" target="_blank">Maven</a> za gradnjo backend-a</li>
  <li><a href="https://nodejs.org/" target="_blank">Node.js</a> (priporočena različica 14 ali novejša) za frontend</li>
  <li><a href="./#namestitev-mysql-workbench" target="_blank">Delujoča baza podatkov</a></li>
</ul>


### Namestitev

#### Kloniranje repozitorija

```bash
git clone https://github.com/AnjaTodorov/ReceptiApp.git
cd ReceptiApp
```
<h3 id="namestitev-mysql-workbench">Namestitev MySQL Workbench</h3>

1. Pojdite na [MySQL Workbench Download Page](https://dev.mysql.com/downloads/workbench/).
2. Izberite ustrezno različico za vaš operacijski sistem in sledite navodilom za namestitev.

### Nastavitev baze podatkov

1. Odprite MySQL Workbench in se povežite na vašo lokalno bazo podatkov.
2. Za ustvarjanje baze podatkov za aplikacijo zaženite naslednje ukaze:

```sql
DROP DATABASE IF EXISTS ReceptiDB;
CREATE DATABASE ReceptiDB;
USE ReceptiDB;

SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS Recepti_Nacrt_obrokov;
DROP TABLE IF EXISTS Sestavine;
DROP TABLE IF EXISTS Hranilne_vrednosti;
DROP TABLE IF EXISTS Nacrt_obrokov;
DROP TABLE IF EXISTS Recepti;


CREATE TABLE Recepti (
    idRecepti INT AUTO_INCREMENT PRIMARY KEY,
    naziv VARCHAR(40) NOT NULL,
    slika VARCHAR(100) NOT NULL,
    tip ENUM('zajtrk', 'kosilo', 'večerja') NOT NULL,
    opis TEXT NOT NULL,
    osebe INT NOT NULL 
);

CREATE TABLE Nacrt_obrokov (
    idNacrt_obrokov INT AUTO_INCREMENT PRIMARY KEY,
    datum DATE DEFAULT (CURRENT_DATE + INTERVAL 1 DAY)
);

CREATE TABLE Hranilne_vrednosti (
    idHranilne_vrednosti INT AUTO_INCREMENT PRIMARY KEY,
    naziv VARCHAR(50) NOT NULL,
    kolicina DECIMAL(10, 2) NOT NULL,
    enota ENUM('g', 'mg', 'kJ') NOT NULL,
    TK_Recepti INT NOT NULL,
    CONSTRAINT FK_Recepti_Hranilne_vrednosti FOREIGN KEY (TK_Recepti)
        REFERENCES Recepti(idRecepti) ON DELETE CASCADE ON UPDATE CASCADE
);


CREATE TABLE Sestavine (
    idSestavina INT AUTO_INCREMENT PRIMARY KEY,
    naziv VARCHAR(100) NOT NULL,
    kolicina DECIMAL(10, 2) NOT NULL,
    enota ENUM('g', 'kg', 'ml', 'l', 'kos', 'žlica', 'čajna žlička', 'skodelica') NOT NULL,
    TK_Recepti INT,
    CONSTRAINT FK_Recepti_Sestavine FOREIGN KEY (TK_Recepti)
        REFERENCES Recepti(idRecepti) ON DELETE CASCADE ON UPDATE CASCADE
);




CREATE TABLE Recepti_Nacrt_obrokov (
    idRecepti_Nacrt_obrokov INT AUTO_INCREMENT PRIMARY KEY,
    TK_Recepti INT NOT NULL,
    TK_Nacrt_obrokov INT NOT NULL,
    meal_type ENUM('ZAJTRK', 'KOSILO', 'VEČERJA') NOT NULL,
    CONSTRAINT FK_Recepti FOREIGN KEY (TK_Recepti)
        REFERENCES Recepti(idRecepti) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_Nacrt_obrokov FOREIGN KEY (TK_Nacrt_obrokov)
        REFERENCES Nacrt_obrokov(idNacrt_obrokov) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT UNIQUE (TK_Nacrt_obrokov, meal_type)
);

SET FOREIGN_KEY_CHECKS=1;



```
### Povezava Spring Boot z MySQL
V vaši aplikaciji dodajte naslednje nastavitve v datoteko application.properties:
```bash
spring.application.name=Moji-recepti
spring.datasource.url=jdbc:mysql://localhost:3306/ReceptiDB
spring.datasource.username=root
spring.datasource.password= # Spremenite na vaš dejanski geslo
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
```
## ▶️ Zagon aplikacije

### Zagon backend-a

Pojdite v mapo backend:
```bash
cd backend
```
Začnite backend
```bash
mvn spring-boot:run
```
Zagon frontend-a

Začnite razvojni strežnik frontend-a:
```bash
node server.js
```

## 👤 Avtorji

- Matej Filipov
- Anastasija Todorov
- Konstantin Mihajlov

## 📜 Licenca

Ta projekt trenutno ni licenciran. Za prihodnje spremembe licenciranja spremljajte ta repozitorij.  
Prispevki so dobrodošli, vendar upoštevajte, da se lahko pravice uporabe spremenijo s prihodnjimi licencami.
