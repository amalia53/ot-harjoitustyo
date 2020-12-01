
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
        List<Integer> numbers = new ArrayList<Integer>();
            for (int row = 0; row < 9; row++) {
                for (int i = 1; i <=9; i++) {
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
    
    public int getPossibleNumber(int row, int col, List<Integer> numbers) {
            for (int i = 0; i < numbers.size(); i++) {
                int number = numbers.get(i);
                boolean column = checkCol(row, col, number);
                boolean square = checkSquare(row, col, number);
                if (column && square) {
                    numbers.remove(i);
                    return number;
                }
        } return -1;
    }
    
    public boolean checkCol(int row, int col, int number) {
        if (row == 0) {
            return true;
        }
        for (int i = 0; i < row; i++) {
            int numberOnField =  solution[col][i];
            if (number == numberOnField) {
                return false;
            }
        } return true;
        
    }
    
    public boolean checkSquare(int row, int col, int number) {
        int a = 0;
        int b = 0;
        if ((row + 1) % 3 == 0) {
            a = row - 2;  
        } if ((row + 2) % 3 == 0) {
            a = row - 1;
        } if (row % 3 == 0) {
            a = row;
        }
        if ((col + 1) % 3 == 0) {
            b = col - 2;  
        } if ((col + 2) % 3 == 0) {
            b = col - 1;
        } if (col % 3 == 0) {
            b = col;
        }
        if (row % 3 != 0) {
            for (int x = a; x < row; x++) {
                for (int y = b; y < b + 3; y++) {
                    int numberOnField = this.solution[y][x];
                    if (number == numberOnField) {
                        return false;
                    }
                }
            }
        }
        if (col > 0) {
            for (int y = b; y < col; y++) {
                int numberOnField = this.solution[y][row];
                if (numberOnField == number) {
                    return false;
                }
            }
        } 
        
        return true;
    }
    
    public int[][] createGame() {
        while (createSolution() == null) {
            createSolution();
        }
        this.game = this.solution;
        toString(this.game);
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
        return number == solution[row][column];
    }
    
    public int getNumberOnField(int x, int y) {
        return this.game[y][x];
    }
   
    public void toString(int[][] game) {
        
        for (int x = 0; x < 9; x++) {
            String rowToString = "";
            for (int y = 0; y < 9; y++) {
                rowToString = rowToString + game[y][x];
            }
            System.out.println(rowToString);
        }
    }
}
