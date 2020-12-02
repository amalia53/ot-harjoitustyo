
package sudoku.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class SudokuGame {
    
    private int selectedNumber;
    private int selectedField;
    private int selectedRow;
    private int selectedColumn;
    
    private int[][] solution;
    private int[][] game;
    private int[][] start;
    
    public SudokuGame() {
        this.solution = new int[9][9];
        this.start = new int[9][9];
        this.game = new int[9][9];
    }
    
    public int[][] createGame() {
        while (createSolution() == null) {
            createSolution();
        }
        start = copyTable(solution, start);
        for (int i = 0; i < 30; i++) {
            removeNumberFromSolution();
        }
        game = copyTable(start, game);
        return this.start;
    }
    
    public int[][] copyTable(int[][] original, int[][] copy) {
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                copy[y][x] = original[y][x];
            }
        }
        return copy;
    }
    
    public int[][] getCurrentGame() {
        return this.game;
    }
    
    public void addToGame(int col, int row, int number) {
        game[col][row] = number;
        System.out.println("Current game:");
        toString(game);
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
    
    public boolean checkIfOriginalNumber(int col, int row) {
        return start[col][row] != 0;
    }
        
    public boolean checkInputNumber(int number, int row, int col) {
        //check if input matches solution
        return number == solution[col][row];
    }
    
    public int getNumberOnField(int x, int y) {
        return this.game[y][x];
    }
   
    public void toString(int[][] game) {
        for (int x = 0; x < 9; x++) {
            String rowToString = "";
            for (int y = 0; y < 9; y++) {
                rowToString = rowToString + game[x][y];
            } System.out.println(rowToString);
        }
    }
    
    public void removeNumberFromSolution() {
        int row = random();
        int col = random();
        if (start[col][row] != 0) {
            start[col][row] = 0;
        }
        //Test if still solvable after removing number
    }
    
    public int random() {
        Random random = new Random();
        int randomNumber = random.nextInt(9);
        return randomNumber;

    }
    
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
    
    public int getPossibleNumber(int row, int col, List<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            int number = numbers.get(i);
            boolean column = checkCol(row, col, number);
            boolean square = checkSquare(row, col, number);
            if (column && square) {
                numbers.remove(i);
                return number;
            }
        } 
        return -1;
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
        } 
        return true;
        
    }
    
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
}
