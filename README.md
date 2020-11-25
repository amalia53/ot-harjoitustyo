# Ohte-projekti

Sudoku-sovellus

## Dokumentaatio

**[Työaikakirjanpito](https://github.com/amalia53/ot-harjoitustyo/blob/master/dokumentaatio/tyoaikakirjanpito.md)**

**[Määrittelydokumentti](https://github.com/amalia53/ot-harjoitustyo/blob/master/dokumentaatio/maarittelydokumentti.md)**

**[Arkkitehtuuri]**(https://github.com/amalia53/ot-harjoitustyo/blob/master/dokumentaatio/arkkitehtuuri.md)**

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
