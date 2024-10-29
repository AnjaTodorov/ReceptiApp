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
- ⭐ [Značilnosti](#značilnosti)
- ⚙️ [Tehnološki sklad](#tehnološki-sklad)
- 📌 [Prvi koraki](#prvi-koraki)
- ▶️ [Zagon aplikacije](#zagon-aplikacije)
- 👤 [Avtorji](#avtorji)
  
## 📄 Pregled

Ta projekt ponuja preprost in intuitiven vmesnik za uporabnike, da dodajo, uredijo, posodobijo in izbrišejo svoje najljubše recepte. Ustvarite svoj osebni kuharski arhiv in ga prilagodite po svojih željah. Vaši recepti, vaša pravila – vse na enem mestu, varno shranjeno in dostopno kadarkoli. Zgrajena z uporabo SpringBoot backend-a in sodobnega frontend okvira, je aplikacija zasnovana za enostavno upravljanje receptov in prilagodljive posodobitve. 

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
