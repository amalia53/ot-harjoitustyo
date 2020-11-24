

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
    public void createSolutionReturnsTable() {
        int[][] solution = game.createSolution();
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
    public void checkInputNumberReturnsTrueWhenCorrect() {
        game.createGame();
        assertEquals(game.checkInputNumber(5, 0, 0), true);
    }
    
    @Test
    public void checkInputNumberReturnsFalseWhenIncorrect() {
        game.createGame();
        assertEquals(game.checkInputNumber(6, 0, 0), false);
    }

}
