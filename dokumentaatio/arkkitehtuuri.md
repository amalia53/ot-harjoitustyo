# Arkkitehtuurikuvaus

## Rakenne

<img src="kaavio.jpg" alt="drawing" width="600"/>

sudoku-pakkaukseni sisäälä on kolme pakkausta: sudoku.ui, sudoku.domain ja sudoku.dao. sudoku-ui sisältää käyttöliittymän toteuttavat Launch ja SudokuUi, sudoku.domain sisältää sovelluslogiikan toteuttavat SudokuGame ja SudokuSolver. sudoku.dao on tällä hetkellä tyhjä, mutta tulee sisältämään pelaajan ennätysaikojen tallennuksen toteutuksen.

## Käyttöliittymä

Main sudoku.ui.Launch:issa käynnistetään käyttöliittymä, joka on toteutettu luokassa sudoku.ui.SudokuUi.

Käyttöliittymässä on neljä erillaista näkymää, jotka ovat toteutettu Scene-olioina.
- alkuvalikko
- vaihtuva pelinäkymä
- pelivalikko
- voitto

Käyttöliittymässä luodaan olio sudoku.domain.SudokuGame, jonka avulla kutsutaan tämän metodeja.

Käyttöliittymän kautta sovelluslogiikkaa kutsumalla pelaaja voi aloittaa uuden pelin, lisätä ja poistaa numeroita sudokupelistä. Lisäksi käyttöliittymässä voidaan liikkua valikosta pelinäkymään, pelistä pelivalikkoon, pelivalikosta käynnissä olevaan peliin tai uuteen peliin ja voittonäkymästä joko uuteen peliin tai valikkoon.

## Sovelluslogiikka

Sovelluslogiikka on toteutettu luokissa sudoku.domain.SudokuGame ja sudoku.domain.SudokuSolver. sudoku.domain.SudokuGame sisältää pelin luomisesta ja hallinnoimisesta vastaavan koodin. sudoku.domain.SudokuSolver tulee tulevaisuudessa toteuttamaan ratkottavan ja uniikin pelin luomista varten sudokun ratkojan. 

Pelin luomisesta vastaavista metodeista käyttöliittymä kutsuu...
- createGame(), kun valitaan uusi peli, joka luo ratkaisun ja pelin sovelluslogiikassa
- getNumberOnField(int row, int col), jonka avulla käyttöliittymä luo pelistä GridPane-ruudukon

Pelin hallinnoimisesta vastaavista metodeista käyttöliittymä kutsuu...
- setSelectedField(int id), kun valitaan ruudukosta ruutu
- checkIfOriginalNumber(int row, int col), kun ruutuun yritetään lisätä tai siitä yritetään poistaa numeroa
- addToGame() ja checkIfDone(), kun ruutuun lisätään numero. addToGame()-metodia kutsutaan myös poistaessa numero pelistä
- eri gettereitä

## Toiminnallisuuksia

![Sekvenssikaavio](OhteSudoku_addingNumbers.png)
