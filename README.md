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

- 📄 [Pregled](#pregled)
- 🌟 [Vizija](#vizija)
- 📚 [Besednjak](#besednjak)
- ✏️ [DPU](#dpu)
- ⭐ [Značilnosti](#značilnosti)
- ⚙️ [Tehnološki sklad](#tehnološki-sklad)
- 📁 [Struktura projekta](#struktura-projekta)
- 📌 [Prvi koraki](#prvi-koraki)
- ▶️ [Zagon aplikacije](#zagon-aplikacije)
- 👤 [Avtorji](#avtorji)
- 📜 [Licenca](#licenca)
 
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
        <tr>
            <td>Funkcionalnosti</td>
            <td>In/ali vloge uporabnikov kot stranski produkt.</td>
            <td>Funkcionalnosti morajo biti jasno opredeljene in razdeljene po vlogah uporabnikov.</td>
            <td>Možnost iskanja receptov po naziv.</td>
        </tr>
    </tbody>
</table>

## ✏️ DPU
Ta diagram zagotavlja jasno predstavitev funkcionalnosti, ki jih ponuja naša aplikacija, in prikazuje, kako uporabniki lahko dodajajo, brišejo in urejajo recepte. Z opredelitvijo različnih primerov uporabe lahko bolje razumemo zahteve sistema in zagotovimo, da učinkovito zadostuje potrebam uporabnikov. Ta dokument si prizadeva ilustrirati diagram primerov uporabe ter podrobno opisati udeležence, njihove cilje in ustrezne primere uporabe, ki prispevajo k celotni funkcionalnosti "Moji recepti."



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

## 📌 Prvi koraki

Za lokalno kopijo in zagon sledite tem preprostim korakom.

### Predpogoji

Poskrbite, da imate nameščeno naslednje:

- Java 17 ali novejša
- Maven za gradnjo backend-a
- Node.js (priporočena različica 14 ali novejša) za frontend
- Delujoča baza podatkov

### Namestitev

#### Kloniranje repozitorija

```bash
git clone https://github.com/AnjaTodorov/ReceptiApp.git
cd ReceptiApp
```
### Namestitev MySQL Workbench

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
