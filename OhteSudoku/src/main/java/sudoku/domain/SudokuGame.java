
package sudoku.domain;


public class SudokuGame {
    
    private int selectedNumber;
    private int selectedFieldX;
    private int selectedFieldY;
    
    private int[][] createSolution() {
        //Creates a possible solution
        return null;
    }
    
    private int[][] createGame() {
        //Creates a game out of the possible solution
        return null;
    }
    
    public void setSelectedNumber(int number) {
        this.selectedNumber = number;
    }
    
    public int getSelectedNumber() {
        return this.selectedNumber;
    }
    
    public void setSelectedField(int x, int y) {
        this.selectedFieldX = x;
        this.selectedFieldY = y;
    }
    
    public int getSelectedFieldX() {
        return this.selectedFieldX; 
    }
    
    public int getSelectedFieldY() {
        return this.selectedFieldX; 
    }
    
        
    public void checkUserInput() {
        //check if input matches solution
    }
}
