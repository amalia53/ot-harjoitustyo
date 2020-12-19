
package sudoku.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class SudokuGame {
    
    private int selectedField;
    private int selectedRow;
    private int selectedColumn;
   
    private int[][] solution;
    private int[][] game;
    private int[][] start;
    private HashMap<Integer, List<Integer>> notes;
    
    public SudokuGame() {
        this.solution = new int[9][9];
        this.start = new int[9][9];
        this.game = new int[9][9];
        this.notes = new HashMap<>();
    }
    
    /**
     * Luo ratkaisusta pelin poistamalla ratkaisusta numeroita. 
     * Tallettaa syntyvän pelin oliomuuttujiin start ja game
     */
    
    public void createGame() {
        while (createSolution() == null) {
            this.createSolution();
        }
        start = copyTable(solution, start);
        for (int i = 0; i < 40; i++) {
            removeNumberFromSolution();
        }
        game = copyTable(start, game);
    }
    

    
    /**
     * Kertoo tämän hetken pelitilanteen
     * @return game-muuttuja, jossa tämän hetken peli talukossa
     */
    
    public int[][] getCurrentGame() {
        return this.game;
    }
    
    /**
     * Lisää annetun numeron annetulle paikalle game-taulukkoon, kun pelaaja täyttää uuden numeron peliin
     * @param col      kolumni, johon numero laitettiin
     * @param row      rivi, johon numero laitettiin
     * @param number   numero, joka ruutuun lisättiin
     */
    
    public void addToGame(int id, int col, int row, int number) {
        game[col][row] = number;
        if (notes.containsKey(id) && number < 10) {
            notes.remove(id);
        }
    }
    
    /**
     * Lisää muistiinpanon notes-mappiin
     * @param id        ruudun järjestysnumero
     * @param number    lisättävä muistiinpano
     */
    
    public void addNote(int id, int number) {
        if (notes.containsKey(id)) {
            if (!notes.get(id).contains(number)) {
                notes.get(id).add(number);
                Collections.sort(notes.get(id));
            } else {
                for (int i = 0; i < notes.get(id).size(); i++) {
                    if (notes.get(id).get(i) == number) {
                        notes.get(id).remove(i);
                    }
                }
            }
        } else {
            List<Integer> notesOnField = new ArrayList<>();
            notesOnField.add(number);
            notes.put(id, notesOnField);
        }
        /*for (int i = 0; i < notes.get(id).size(); i++) {
            System.out.println(notes.get(id).get(i));
        }*/
    }
    
    /**
     * Palauttaa muistiinpanot kysytyssä ruudussa
     * @param id    ruudun järjestysnumero
     * @return muistiinpanot String-muuttujana
     */
    
    public String getNotesOnField(int id) {
        if (notes.containsKey(id)) {
            String notesOnField = "";
            List<Integer> noteList = notes.get(id);
            for (int i = 0; i < noteList.size(); i++) {
                notesOnField += noteList.get(i);
                if (i == 2 || i == 5) {
                    notesOnField += "\n";
                }
            }
            return notesOnField;
        } else {
            return null;
        }
    }
    
    /**
     * Asettaa valitun ruudun, rivin ja kolumnin oliomuuttujiin
     * @param id   ruudun id eli järjestysnumero
     */
    
    public void setSelectedField(int id) {
        this.selectedField = id;
        this.selectedColumn = (id - 1) % 9;
        this.selectedRow = (id - selectedColumn) / 9;
    }
    
    /**
     * Kertoo valitun ruudun id:n eli järjestysnumeron
     * @return valitun ruudun id
     */
    
    public int getSelectedField() {
        return this.selectedField; 
    }
    
    /**
     * Kertoo valitun ruudun rivin
     * @return valitun ruudun rivi
     */
    
    
    public int getSelectedRow() {
        return this.selectedRow; 
    }
    
    /**
     * Kertoo valitun ruudun kolumnin
     * @return valitun ruudun kolumni
     */
    
    public int getSelectedColumn() {
        return this.selectedColumn; 
    }
    
    /**
     * Tarkistaa, onko kyseisen ruudun numero pelin vai pelaajan asettama
     * @param col  valitun ruudun kolumni
     * @param row  valitun ruudun rivi
     * @return true, jos on pelin asettama; false, jos pelaajan asettama
     * 
     */
    
    public boolean checkIfOriginalNumber(int col, int row) {
        return start[col][row] != 0;
    }
    
    /**
     * Kertoo oikean numeron eli ratkaisun kyseisessä ruudussa
     * @param row     valittu rivi
     * @param column  valittu kolumni
     * @return ratkaisussa oleva numero kyseisessä ruudussa
     */
    
    public int getNumberOnSolution(int row, int column) {
        return solution[row][column];
    }
    
    /**
     * Tarkistaa, onko sudoku ratkaistu kokonaan ja oikein eli onko peli voitettu
     * @return true, jos oikein ja valmis; false jos kesken tai väärin
     */
    
    public boolean checkIfDone() {
        int correct = 0;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (game[x][y] == solution[x][y]) {
                    correct++;
                }
            }
        }
        boolean done = false;
        if (correct == 81) {
            done = true;
        }
        return done;
    }
    
    /**
     * Kertoo, mikä numero on tämän hetkisessä pelissä kysytyssä ruudussa
     * @param row   kysytyn ruudun rivi
     * @param col   kysytyn ruudun kolumni
     * @return valittuun ruutuun asetettu numero
     */
    
    public int getNumberOnField(int row, int col) {
        return this.game[col][row];
    }
    
    /**
     * Poistaa ratkaisusta sarunnaisen numeron, mikäli siinä on vielä numero
     * Tulevaisuudessa tarkoitus, että tarkistaa sudokuratkojan avulla, onko ratkaisu vielä uniikki ja ratkottavissa poiston jälkeen
     * Jos ei ratkaistavissa, palauttaa numeron ratkaisuun ja kokeilee toista satunnaista numeroa
     */
    
    public void removeNumberFromSolution() {
        int[][] tempGame = new int[9][9];
        tempGame = copyTable(start, tempGame);
        while (true) {
            int row = random();
            int col = random();
            if (tempGame[col][row] != 0) {
                if (isSolvable(tempGame)) {
                    start[col][row] = 0;
                    break;
                }
            }
        }
    }
 
    
    public boolean isSolvable(int[][] tempGame) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (tempGame[col][row] == 0) {
                    for (int number = 1; number <= 9; number++) {
                        if (checkIfOk(row, col, number)) {
                            tempGame[col][row] = number;
                            if (isSolvable(tempGame)) {
                                return true;
                            } else {
                            }
                        }
                    }
                    
                    return false;
                }
            }
        }
        return true;
    }
    
    /**
     * Luo sudokupelin ratkaisun ja tallettaa sen solution-oliomuuttujaan
     * @return sudokupelin ratkaisun 9x9 taulukkona
     */
    
    public int[][] createSolution() {
        //Creates a possible solution
        List<Integer> numbers = new ArrayList<>();
        for (int row = 0; row < 9; row++) {
            for (int i = 1; i <= 9; i++) {
                numbers.add(i);
            } 
            Collections.shuffle(numbers);
            for (int col = 0; col < 9; col++) {
                int number = getPossibleNumber(row, col, numbers);
                if (number == -1) {
                    solution = new int[9][9];
                    return null;
                } else {
                    solution[col][row] = number;
                }
            }
        } 
        return this.solution;
    }
    
    /**
     * Kertoo numeron 1-9 väliltä, jonka voi pelin sääntöjen mukaan lisätä kyseiseen ruutuun
     * @param row       annetun ruudun rivi
     * @param col       annetun ruudun kolumni
     * @param numbers   lista numeroita, joita voi lisätä kyseiselle riville sääntöjen mukaan
     * @return sääntöjenmukaisen numeron väliltä 1-9
     */
    
    public int getPossibleNumber(int row, int col, List<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            int number = numbers.get(i);
            if (checkIfOk(row, col, number) == true) {
                numbers.remove(i);
                return number;
            }
        } 
        return -1;
    }
    
    public boolean checkIfOk(int row, int col, int number) {
        boolean rowIsOk = checkRow(row, col, number);
        boolean columnIsOk = checkCol(row, col, number);
        boolean squareIsOk = checkSquare(row, col, number);
        return columnIsOk && squareIsOk && rowIsOk;
    }
    
    /**
     * Tarkistaa, onko annettua numeroa jo kyseisessä rivissä, jossa kysyttyvruutu sijaitsee
     * @param row     annetun ruudun rivi
     * @param col     annetun ruudun kolumni
     * @param number  tarkistettava numero
     * @return true, jos numeroa ei ole rivissä; false, jos numero löytyy rivistä
     */
    
    public boolean checkRow(int row, int col, int number) {
        if (col == 0) {
            return true;
        }
        for (int i = 0; i < col; i++) {
            int numberOnField =  solution[i][row];
            if (number == numberOnField) {
                return false;
            }
        } 
        return true;
    }
    
    /**
     * Tarkistaa, onko annettua numeroa jo kyseisessä kolumnissa, jossa kyseinen ruutu sijaitsee
     * @param row     annetun ruudun rivi
     * @param col     annetun ruudun kolumni
     * @param number  tarkistettava numero
     * @return true, jos numeroa ei ole kolumnissa; false, jos numero löytyy kolumnista
     */
    
    public boolean checkCol(int row, int col, int number) {
        if (row == 0) {
            return true;
        }
        for (int i = 0; i < row; i++) {
            int numberOnField =  solution[col][i];
            if (number == numberOnField) {
                return false;
            }
        } 
        return true;
        
    }
    
    /**
     * Tarkistaa, löytyykö annettu numero jo 3x3 ruudukossa, jossa kyseinen ruutu sijaitsee
     * @param row     annetun ruudun rivi
     * @param col     annetun ruudun kolumni
     * @param number  tarkistettava numero
     * @return true, jos numeroa ei löydy ruudukossa; false, jos numero löytyy ruudukosta;
     */
    
    public boolean checkSquare(int row, int col, int number) {
        boolean check = true;
        int a = 0;
        int b = 0;
        a = checkStartPoint(row);
        b = checkStartPoint(col);
        if (row % 3 != 0) {
            check = checkEarlierRows(a, b, row, number);
        } 
        if (col > 0) {
            for (int y = b; y < col; y++) {
                int numberOnField = this.solution[y][row];
                if (numberOnField == number) {
                    return false;
                }
            }
        } 
        return check;
    }
    
    /**
     * Kertoo kyseisen ruudun 3x3 ruudukon aloituspisteen
     * @param rowcol   rivi tai kolumni, jota tarkistetaan
     * @return aloituspiste ruudukossa
     */
    
    public int checkStartPoint(int rowcol) {
        int a = 0;
        if ((rowcol + 1) % 3 == 0) {
            a = rowcol - 2;  
        } 
        if ((rowcol + 2) % 3 == 0) {
            a = rowcol - 1;
        } 
        if (rowcol % 3 == 0) {
            a = rowcol;
        }
        return a;
    }
    
    /**
     * Tarkistaa löytyykö kyseistä numeroa edellisiltä riveiltä 3x3 ruudukon alueelta
     * @param a       rivin aloituspiste
     * @param b       kolumnin aloituspiste
     * @param row     annetun ruudun rivi
     * @param number  tarkistettava ruutu
     * @return true, jos numeroa ei löydy; false, jos numero löytyy
     */
    
    public boolean checkEarlierRows(int a, int b, int row, int number) {
        for (int x = a; x < row; x++) {
            for (int y = b; y < b + 3; y++) {
                int numberOnField = this.solution[y][x];
                if (number == numberOnField) {
                    return false;
                }
            }
        }
        return true;
    }
    
        /**
     * Kopioi toisen taulukon tiedot toiseen
     * 
     * @param original   taulukko, joka halutaan kopiopida
     * @param copy       taulukko, johon kopiopidaan
     * @return  taulukko, johon on kopioitu toinen taulukko
     */
    
    public int[][] copyTable(int[][] original, int[][] copy) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                copy[y][x] = original[y][x];
            }
        }
        return copy;
    }
       
    /**
     * Arpoo satunnaisen numeron väliltä 0-8
     * @return satunnainen numero väliltä 0-8
     */
    
    public int random() {
        Random random = new Random();
        int randomNumber = random.nextInt(9);
        return randomNumber;

    }
}
