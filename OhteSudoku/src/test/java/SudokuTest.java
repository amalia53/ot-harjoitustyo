

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class SudokuTest {
    
    sudoku.domain.SudokuGame game = new sudoku.domain.SudokuGame();
    
    
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
        assertEquals(game.checkEarlierRows(0, 0, 0, 10), true);
    }
    
    @Test
    public void checkStartPointReturns0() {
        assertEquals(game.checkStartPoint(2), 0);
    }
    
    @Test
    public void checkStartPointReturns3() {
        assertEquals(game.checkStartPoint(3), 3);
    }
    
    @Test
    public void checkStartPointReturns6() {
        assertEquals(game.checkStartPoint(7), 6);
    }
    
    @Test
    public void checkColTrueWhenRow0() {
        assertEquals(game.checkCol(0, 3, 7), true);
    }
    
    @Test
    public void getPossibleNumberReturnsNumberFromList() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        assertEquals(game.getPossibleNumber(0, 0, numbers), 1);
    }
    
    @Test
    public void getPossibleNumberRemovesNumberFromList() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        game.getPossibleNumber(0, 0, numbers);
        assertEquals(numbers.size(), 0);
    }
    
    @Test
    public void randomReturnsNumberBetween0And8() {
        int random = game.random();
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
        game.copyTable(original, copy);
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
                if (game.getNumberOnField(x, y) == 0) {
                    game.addToGame(y, x, game.getNumberOnSolution(y, x));
                } 
            } 
        }
        assertEquals(game.checkIfDone(), true);
    }
}
