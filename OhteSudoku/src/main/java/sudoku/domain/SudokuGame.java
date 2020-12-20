
package sudoku.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


public class SudokuGame {
    
    private SudokuCreator creator;
    
    private int selectedField;
    private int selectedRow;
    private int selectedColumn;
   
    private int[][] solution;
    private int[][] game;
    private int[][] start;
    private HashMap<Integer, List<Integer>> notes;
    
    public SudokuGame() {
        this.creator = new SudokuCreator();
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
        creator.createGame();
        this.solution = creator.getSolution();
        this.start = creator.getStart();
        this.game = creator.getGame();
    }
    

    
    /**
     * Kertoo tämän hetken pelitilanteen
     * @return tämän hetken peli talukkona
     */
    
    public int[][] getCurrentGame() {
        return this.game;
    }
    
    /**
     * Kertoo tämän hetken muistiinpanot
     * @return tämän hetken muistiinpanot hashmapina
     */

    public HashMap<Integer, List<Integer>> getNotes() {
        return notes;
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
            List<Integer> noteList = notes.get(id);
            if (!noteList.contains(number)) {
                noteList.add(number);
                Collections.sort(notes.get(id));
            } else {
                for (int i = 0; i < noteList.size(); i++) {
                    if (noteList.get(i) == number) {
                        noteList.remove(i);
                    }
                }
            }
        } else {
            List<Integer> notesOnField = new ArrayList<>();
            notesOnField.add(number);
            notes.put(id, notesOnField);
        }
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
    
}