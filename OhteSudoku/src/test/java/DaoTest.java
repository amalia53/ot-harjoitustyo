/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileWriter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sudoku.dao.TimeDao;
import org.junit.rules.TemporaryFolder;


/**
 *
 * @author amalia
 */
public class DaoTest {
    
    TimeDao dao;
    
    @Before
    public void setUp() throws Exception {
        String name = "testFile.txt";
        dao = new TimeDao(name);
    }

        
    @Test
    public void saveTimeSavesToList() throws Exception {
        dao.clearTimes();
        dao.saveTime(10);
        assertEquals(dao.getBestTime(), 10);
    }
    
    @Test
    public void readTimesReadsFile() throws Exception {
        dao.clearTimes();
        dao.saveTime(10);
        dao.readTimes();
        int lastEntry = dao.getTop(2).get(1);
        assertEquals(lastEntry, 10);
    }
    
    @Test
    public void getTopGivesARoghtSizeList() throws Exception {
        dao.clearTimes();
        dao.clearTimes();
        dao.saveTime(1);
        dao.saveTime(2);
        dao.saveTime(3);
        dao.saveTime(4);
        dao.saveTime(5);
        dao.getTop(5);
        assertEquals(dao.getTop(5).size(), 5);
    }
    
}

