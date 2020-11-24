
package sudoku.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SudokuGame {
    
    private int selectedNumber;
    private int selectedField;
    private int selectedRow;
    private int selectedColumn;
    
    private int[][] solution;
    private int[][] game;
    
    public SudokuGame() {
        this.solution = new int[9][9];
        this.game = new int[9][9];
    }
    
    public int[][] createSolution() {
        //Creates a possible solution
        /*List<Integer> numbers = new ArrayList<Integer>();
        for (int i = 1; i <=9; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);*/
        //Test solution:
        int[][] sol = { {5,4,3,9,2,1,8,7,6}, {2,1,9,6,8,7,5,4,3}, {8,7,6,3,5,4,2,1,9}, {9,8,7,4,6,5,3,2,1}, {3,2,1,7,9,8,6,5,4}, {6,5,4,1,3,2,9,8,7}, {7,6,5,2,4,3,1,9,8}, {4,3,2,8,1,9,7,6,5}, {1,9,8,5,7,6,4,3,2} };
        this.solution = sol;
        return this.solution;
    }
    
    public int[][] createGame() {
        //Creates a game out of the possible solution
        createSolution();
        this.game = this.solution;
        return this.game;
    }
    
    public int[][] getCurrentGame() {
        return this.game;
    }
    
    
    
    public void setSelectedNumber(int number) {
        this.selectedNumber = number;
    }
    
    public int getSelectedNumber() {
        return this.selectedNumber;
    }
    
    public void setSelectedField(int id) {
        this.selectedField = id;
        this.selectedColumn = (id - 1) % 9;
        this.selectedRow = (id - selectedColumn) / 9;
        System.out.println("ID: " + selectedField + " Column: " + selectedColumn + " Row: " + selectedRow);
    }
    
    public int getSelectedField() {
        return this.selectedField; 
    }
    
    public int getSelectedRow() {
        return this.selectedRow; 
    }
    
    public int getSelectedColumn() {
        return this.selectedColumn; 
    }
    
        
    public boolean checkInputNumber(int number, int row, int column) {
        //check if input matches solution
        if (number == solution[row][column]) {
            return true;
        } else {
            return false;
        }
    }
    
    public int getNumberOnField(int x, int y) {
        return this.game[y][x];
    }
}
