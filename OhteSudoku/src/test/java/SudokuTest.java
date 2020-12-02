

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
    public void createGameReturnsTable() {
        int[][] solution = game.createGame();
        assertEquals(solution.length, 9);
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
    public void getSelectedFieldNumberGivesCorrectNumber() {
        game.setSelectedNumber(5);
        assertEquals(game.getSelectedNumber(),5);
    }
    
    @Test
    public void checkInputNumberTrueWhenCorrect() {
        game.createGame();
        assertEquals(game.checkInputNumber(game.getNumberOnField(0, 0), 0, 0), true);
    }
    
    @Test
    public void checkInputNumberFalseWhenIncorrect() {
        game.createGame();
        assertEquals(game.checkInputNumber(game.getNumberOnField(0, 0)-1, 0, 0), false);
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
}
