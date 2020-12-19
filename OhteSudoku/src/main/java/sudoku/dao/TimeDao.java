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
    
    private List<Integer> times;
    private String fileName;
    
    public TimeDao() throws Exception {
        times = new ArrayList<>();
        fileName = "sudokuDao.txt";
        readTimes();
    }
    
    
    public void saveTime(int timeInt) throws Exception {
        times.add(timeInt);
        String timelist = "";
        for (int i = 1; i <= times.size(); i++) {
            timelist += times.get(i);
            if (i != times.size()) {
                timelist += "\n";
            }
        }
        try (FileWriter writer = new FileWriter(new File(fileName))) {
            writer.write(timelist);
            writer.close();
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
        
    }
    
    public void readTimes() throws Exception {
        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                times.add(Integer.valueOf(scanner.nextLine()));
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex);
        }
    }
    
    public int getBestTime() throws Exception {
        Collections.sort(times);
        return times.get(0);
    }
    
    public List<Integer> getTop(int topSize) throws Exception {
        Collections.sort(times);
        List<Integer> top = new ArrayList();
        int max = topSize;
        if (topSize > times.size()) {
            max = times.size();
        }
        for (int i = 0; i < max; i++) {
            top.add(times.get(i));
        }
        Collections.sort(top);
        return top;
    }
    
    
}
