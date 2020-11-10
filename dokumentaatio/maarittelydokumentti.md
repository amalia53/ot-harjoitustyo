# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovelluksessani voi pelata klassista sudoku-peliä, jossa pelaajan tulee täyttä 9x9 -ruudukko, niin että jokainen vaaka- ja pystyrivi sekä 3x3 -osaruudukko
sisältää yhden jokaista numeroa 1-9 välillä. 

## Käyttöliittymä

Sovellus koostuu valikosta, pelistä ja voittonäkymästä. Kun sovelluksen käynnistää, aukeaa valikko. Pelinäkymään pääsee valitsemalla valikosta uuden pelin. Mikäli pelistä siirtyy valikkoon kesken pelin, valikosta löytyy myös painike jatka peliä, josta pääsee takaisin pelinäkymään. Voittaessa tulee voittonäkymä, josta pääsee uuteen peliin tai valikkoon.

#### Valikon näkymässä
- (Painike: Jatka peliä) *jos peli on kesken*
- Painike: Uusi peli

#### Pelinäkymässä
- Peliruudukko, josta jokaisen ruudun voi valita
- Numerot 1-9, jotka voi valita täytettäväksi
- Ruudun tyhjennys valinta
- Valikko-painike, josta siirrytään valikkoon

#### Voittonäkymä

- Valmis peliruudukko
- Onnitteluviesti
- Painike: Uusi peli
- Painike: Valikko

![Hahmotelma käyttöliittymästä](kayttisHahmotelma.jpeg)

## Toiminnallisuus

#### Sovelluksen avatessaan

- Voi valita aloittaa uuden pelin

#### Pelatessa

- Näkee peliruudukon
- Voi valita ruudun, johon voi...
  - Lisätä numeron
  - Poistaa numeron
- Siirtyä valikkoon

#### Valikossa

- Voi palata takaisin peliin
- Valita uuden helpon tai vaikean pelin

#### Pelin loputtuessa

- Peli loppuu, kun kaikissa ruuduissa on oikea numero
- Voi katsoa valmista ruudukkoa
- Aloittaa uuden pelin
- Siirtyä valikkoon

## Jatkokehitysideoita

Perusversion jälkeen voidaan lisätä seuraavia toiminnallisuuksia:

- Muistiinpanojen täyttäminen ruutuihin
- Useampi vaikeusaste
- Asetukset, joissa voi...
  - Lisätä näkymään valitun ruudun pysty- ja vaakarivin korostamisen
  - Mahdollisuus vaihtaa yötilaan, jolloin tausta on tumma
