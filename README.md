# Ohte-projekti

Sovelluksessani voi pelata klassista sudoku-peliä, jossa pelaajan tulee täyttä 9x9 -ruudukko, niin että jokainen vaaka- ja pystyrivi sekä 3x3 -osaruudukko sisältää yhden jokaista numeroa 1-9 välillä.

## Dokumentaatio

**[Käyttöohje](https://github.com/amalia53/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)**

**[Määrittelydokumentti](https://github.com/amalia53/ot-harjoitustyo/blob/master/dokumentaatio/maarittelydokumentti.md)**

**[Arkkitehtuuri](https://github.com/amalia53/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)**

**[Testausdokumentti](https://github.com/amalia53/ot-harjoitustyo/blob/master/dokumentaatio/Testausdokumentti.md)**

**[Työaikakirjanpito](https://github.com/amalia53/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)**


## Releaset

**[Viikko 5](https://github.com/amalia53/ot-harjoitustyo/releases/tag/Viikko5)**

**[Viikko 6](https://github.com/amalia53/ot-harjoitustyo/releases/tag/Viikko6)**

**[Loppupalautus]()**

## Komentorivi

Ohjelman voi suorittaa komennolla:

`mvn compile exec:java -Dexec.mainClass=sudoku.Main`

### Testaus

Testit voi suorittaa komennolla:

`mvn test`

Testikattavuuden voi suorittaa komennolla:

`mvn jacoco:report`

### Checkstyle

Checkstylen voi suorittaa komennolla:

`mvn jxr:jxr checkstyle:checkstyle`

### Jar

Suoritettavan jarin voi luoda komennolla:

`mvn package`

### Javadoc

Javadocin voi luoda komennolla:

`mvn javadoc:javadoc`


