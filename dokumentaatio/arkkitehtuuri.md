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

Käyttöliittymän kautta pelaaja voi aloittaa uuden pelin, lisätä ja poistaa numeroita sudokupelistä ja kulkea valikon ja peli- tai voittonäkymän välillä.

## Sovelluslogiikka

Sovelluslogiikka on toteutettu luokissa sudoku.domain.SudokuGame ja sudoku.domain.SudokuSolver. sudoku.domain.SudokuGame sisältää pelin luomisesta ja hallinnoimisesta vastaavan koodin. sudoku.domain.SudokuSolver tulee tulevaisuudessa toteuttamaan ratkottavan ja uniikin pelin luomista varten sudokun ratkojan. 

Pelin luomisesta vastaavia tärkeimpiä metodeja ovat muunmuaassa...
- createSolution()
- getPossibleNumber()
- removeNumberFromSolution()
- createGame()

Pelin hallinnoimisesta vastaavia tärkeimpiä metodeja ovat muunmuaassa...
- setSelectedField()
- addToGame()
- checkIfDone()


## Toiminnallisuuksia

![Sekvenssikaavio](OhteSudoku_addingNumbers.png)
