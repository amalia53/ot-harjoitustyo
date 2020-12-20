

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sudoku.domain.*;
import sudoku.dao.*;


public class SudokuTest {
    
    SudokuGame game = new SudokuGame();
    SudokuCreator creator = new SudokuCreator();
    
    @Before
    public void setUp() throws Exception {
        TimeDao times = new TimeDao();
    }
    
    @Test
    public void newSudokuGameIsCreated() {
        assertEquals(game.getCurrentGame().length, 9);
    }
    
    @Test
    public void setSelectedFieldSetsField() {
        game.setSelectedField(30);
        assertEquals(game.getSelectedField(), 30);
    }
    
    @Test
    public void setSelectedFieldSetsColumn() {
        game.setSelectedField(80);
        assertEquals(game.getSelectedColumn(), 7);
    }
    
    @Test
    public void setSelectedFieldSetsRow() {
        game.setSelectedField(80);
        assertEquals(game.getSelectedRow(), 8);
    }
    
    @Test
    public void getNumberOnFieldGetsCorrectNumber() {
        game.createGame();
        int number = game.getNumberOnField(5, 3);
        assertEquals(number, game.getCurrentGame()[3][5]);
    }
    
    @Test
    public void checkEarlierRowsTrueWhenNumberNotFound() {
        game.createGame();
        assertEquals(creator.checkEarlierRows(0, 0, 0, 10), true);
    }
    
    @Test
    public void checkStartPointReturns0() {
        assertEquals(creator.checkStartPoint(2), 0);
    }
    
    @Test
    public void checkStartPointReturns3() {
        assertEquals(creator.checkStartPoint(3), 3);
    }
    
    @Test
    public void checkStartPointReturns6() {
        assertEquals(creator.checkStartPoint(7), 6);
    }
    
    @Test
    public void checkColTrueWhenRow0() {
        assertEquals(creator.checkCol(0, 3, 7), true);
    }
    
    @Test
    public void getPossibleNumberReturnsNumberFromList() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        assertEquals(creator.getPossibleNumber(0, 0, numbers), 1);
    }
    
    @Test
    public void getPossibleNumberRemovesNumberFromList() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        creator.getPossibleNumber(0, 0, numbers);
        assertEquals(numbers.size(), 0);
    }
    
    @Test
    public void randomReturnsNumberBetween0And8() {
        int random = creator.random();
        boolean between = false;
        if (random >= 0 && random < 9) {
            between = true;
        }
        assertEquals(between, true);
    }
    
    @Test
    public void copyTableCopiesTable() {
        int[][] original = new int[9][9];
        original[0][0] = 1;
        int[][] copy = new int[9][9];
        creator.copyTable(original, copy);
        assertEquals(original[0][0], copy[0][0]);
    }
    
    @Test
    public void checkIfDoneReturnsFalse() {
        game.createGame();
        assertEquals(game.checkIfDone(), false);
    }
    
    @Test
    public void checkIfDoneReturnsTrue() {
        game.createGame();
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                int id = (x-1)*9 + y;
                if (game.getNumberOnField(x, y) == 0) {
                    game.addToGame(id, y, x, game.getNumberOnSolution(y, x));
                } 
            } 
        }
        assertEquals(game.checkIfDone(), true);
    }
    
    @Test
    public void addToGameAddsToGame() {
        game.createGame();
        int col = 0;
        int row = 0;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (game.getCurrentGame()[y][x] != 0) {
                    col = y;
                    row = x;
                    break;
                }
            }
        }
        int id = (col)*9+row+1;
        game.addToGame(id, col, row, 2);
        assertEquals(game.getNumberOnField(row, col), 2);
    }
    
    @Test
    public void addToGameClearsNotes() {
        game.createGame();
        int col = 0;
        int row = 0;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (game.getCurrentGame()[y][x] != 0) {
                    col = y;
                    row = x;
                    break;
                }
            }
        }
        int id = (col)*9+row+1;
        game.addNote(id, 1);
        game.addToGame(id, col, row, 2);
        assertEquals(game.getNotes().containsKey(id), false);
    }
    
    @Test
    public void addNoteCreatesListAndAddsToIt() {
        game.addNote(1, 1);
        assertEquals(game.getNotes().get(1).size(), 1);
    }
    
    @Test
    public void addNoteRemovesNumberIfAlreadyOnList() {
        game.addNote(1, 2);
        game.addNote(1, 2);
        assertEquals(game.getNotes().get(1).size(), 0);

    }
    
    @Test
    public void getNotesOnFieldReturnNotesWithLineBreaks() {
        for (int i = 1; i <= 9; i++) {
            game.addNote(1, i);
        }
        String notes = game.getNotesOnField(1);
        assertEquals(notes, "123\n456\n789");
    }
    
    @Test
    public void checkIfOriginalNumberReturnsTrueWhenSetByGame() {
        game.createGame();
        int col = 0;
        int row = 0;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (game.getCurrentGame()[y][x] != 0) {
                    col = y;
                    row = x;
                    break;
                }
            }
        }
        assertEquals(game.checkIfOriginalNumber(col, row), true);
    }
    
    @Test
    public void checkIfOriginalNumberReturnsFalseWhenNotSetByGame() {
        game.createGame();
        int col = 0;
        int row = 0;
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (game.getCurrentGame()[y][x] == 0) {
                    col = y;
                    row = x;
                    break;
                }
            }
        }
        assertEquals(game.checkIfOriginalNumber(col, row), false);
    }
    
    
}
