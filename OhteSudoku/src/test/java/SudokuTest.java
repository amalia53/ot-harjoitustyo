

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class SudokuTest {
    
    @Test
    public void newSudokuGameIsCreated() {
        sudoku.domain.SudokuGame game = new sudoku.domain.SudokuGame();
        assertEquals(game.getCurrentGame().length, 0);
    }
    

}
