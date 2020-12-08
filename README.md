# Ohte-projekti

Sovelluksessani voi pelata klassista sudoku-peliä, jossa pelaajan tulee täyttä 9x9 -ruudukko, niin että jokainen vaaka- ja pystyrivi sekä 3x3 -osaruudukko sisältää yhden jokaista numeroa 1-9 välillä.

## Dokumentaatio

**[Työaikakirjanpito](https://github.com/amalia53/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)**

**[Määrittelydokumentti](https://github.com/amalia53/ot-harjoitustyo/blob/master/dokumentaatio/maarittelydokumentti.md)**

**[Arkkitehtuuri](https://github.com/amalia53/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)**

**[Käyttöohje](https://github.com/amalia53/ot-harjoitustyo/blob/master/dokumentaatio/kayttoohje.md)**

## Releaset

**[Viikko 5](https://github.com/amalia53/ot-harjoitustyo/releases/tag/Viikko5)**

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
