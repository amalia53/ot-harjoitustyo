/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sudoku.dao;
        
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TimeDao {
    
    public List<Integer> times;
    private Scanner scanner;
    private String file;
    
    public TimeDao() throws Exception {
        times = new ArrayList<>();
        file = "sudokuDao";
        scanner = new Scanner(file);
    }
    
    //Nyt ei tallenna mitään
    
    public void saveTime(int timeInt) throws Exception {
        String time = timeInt + "\n";
        try (FileWriter writer = new FileWriter(new File(file))) {
            writer.write(time);
            writer.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        
    }
    
    public void readTimes() throws Exception {
        try {
            while (scanner.hasNextLine()) {
                times.add(Integer.valueOf(scanner.nextLine()));
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }
    
    public int getBestTime() throws Exception {
        readTimes();
        Collections.sort(times);
        return times.get(0);
    }
    
    public List<Integer> getTop(int amount) throws Exception {
        readTimes();
        Collections.sort(times);
        List<Integer> top = new ArrayList();
        for (int i = 0; i < amount; i++) {
            top.add(times.get(i));
        }
        Collections.sort(top);
        return top;
    }
    
    
}
