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
- ✏️ [DPU](#-dpu)
- ⭐ [Značilnosti](#-značilnosti)
- ⚙️ [Tehnološki sklad](#-tehnološki-sklad)
- 📁 [Struktura projekta](#-struktura-projekta)
- 📏 [Standardi kodiranja](#-standardi-kodiranja)
- 🌐 [Končne točke REST API](#-končne-točke-REST-API)
- 📌 [Prvi koraki](#-prvi-koraki)
- ▶️ [Zagon aplikacije](#-zagon-aplikacije)
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

<h3>Scenariji:</h3>
<ol>
  <li><strong>Pozitiven scenarij:</strong>
    <ul>
      <li>Uporabnik se uspešno prijavi v aplikacijo.</li>
      <li>Uporabnik izbere eno izmed možnosti (npr. ustvarjanje, posodabljanje ali iskanje recepta).</li>
      <li>Sistem izvede izbrano dejanje in potrdi operacijo (npr. recept je ustvarjen, posodobljen, izbrisan itd.).</li>
    </ul>
  </li>
  <li><strong>Alternativni scenariji:</strong>
    <ul>
      <li><strong>1.a</strong>: Med ustvarjanjem recepta uporabnik izbere možnost nalaganja fotografije, kar sproži razširitev "Naloži Fotografija".</li>
      <li><strong>2.a</strong>: Med iskanjem recepta uporabnik izbere uporabo določenih filtrov za omejitev rezultatov, kar sproži razširitev "Filtriraj Rezultate".</li>
    </ul>
  </li>
</ol>


![DPU](https://github.com/user-attachments/assets/94b2d8a3-edcb-4f4d-935f-b3e2f520537a)


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
DROP TABLE IF EXISTS Recepti;

CREATE TABLE Recepti (
    idRecepti INT AUTO_INCREMENT PRIMARY KEY,
    naziv VARCHAR(40) NOT NULL,
    slika VARCHAR(100) NOT NULL,
    sestavine TEXT NOT NULL,
    opis TEXT NOT NULL
);
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
