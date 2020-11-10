package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals(1000, kortti.saldo());
    }
    
    @Test
    public void lataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(1000);
        assertEquals("saldo: 20.0", kortti.toString());
    }
    
    @Test
    public void otaRahaaVahentaaSaldoa() {
        kortti.otaRahaa(400);
        assertEquals("saldo: 6.0", kortti.toString());
    }
    
    @Test
    public void rahaaEiOtetaJosEiTarpeeksiSaldoa() {
        kortti.otaRahaa(1200);
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void trueJosSaldoRiittaa() {
        assertEquals(true, kortti.otaRahaa(400));
    }
    
    @Test
    public void falseJosSaldoEiRiita() {
        assertEquals(false, kortti.otaRahaa(2000));
    }
}
