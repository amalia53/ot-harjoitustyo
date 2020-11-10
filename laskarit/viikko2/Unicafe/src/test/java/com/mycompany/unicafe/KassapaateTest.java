package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(500);
    }
    
    @Test
    public void kassassaAlussaOikeinSaldoa() {
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void kassassaAlussaEiMyytyjaMaukkaita() {
        assertEquals(0,kassa.maukkaitaLounaitaMyyty());
    }    
    
    @Test
    public void kassassaAlussaEiMyytyjaEdullisia() {
        assertEquals(0,kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullisenOstoTasarahallaLisaaSaldoaOikein() {
        kassa.syoEdullisesti(240);
        assertEquals(100240,kassa.kassassaRahaa());
    }
    
    @Test
    public void edullisenOstoTasarahallaLisaaEdullistenMyyntia() {
        kassa.syoEdullisesti(240);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanOstoTasarahallaLisaaSaldoaOikein() {
        kassa.syoMaukkaasti(400);
        assertEquals(100400,kassa.kassassaRahaa());
    }

    @Test
    public void maukkaanOstoTasarahallaLisaaMaukkaidenMyyntia() {
        kassa.syoMaukkaasti(400);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisenOstoVaihtorahallaLisaaSaldoaOikein() {
        kassa.syoEdullisesti(1000);
        assertEquals(100240, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukkaanOstoVaihtorahallaLisaaSaldoaOikein() {
        kassa.syoMaukkaasti(1000);
        assertEquals(100400, kassa.kassassaRahaa());
    }
    
    @Test
    public void edullisenOstoVaihtorahallaLisaaEdullisiaLounaita() {
        kassa.syoEdullisesti(1000);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanOstoVaihtorahallaLisaaMaukkaitaLounaita() {
        kassa.syoMaukkaasti(1000);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisenOstoVaihtoRahallaAnnetaanRahaaTakaisinOikein() {
        assertEquals(260, kassa.syoEdullisesti(500));
    }

    @Test
    public void maukkaanOstoVaihtoRahallaAnnetaanRahaaTakaisinOikein() {
        assertEquals(600, kassa.syoMaukkaasti(1000));
    }
    
    @Test
    public void edullisenOstoLiianVahallaRahallaEiLisaaSaldoa() {
        kassa.syoEdullisesti(200);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void edullisenOstoLiianVahallaRahallaEiLisaaMyytyjaEdullisia() {
        kassa.syoEdullisesti(200);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void edullisenOstoLiianVahallaRahallaPalauttaaRahat() {
        assertEquals(200, kassa.syoEdullisesti(200));
    }
    
    @Test
    public void edullisenOstoTrueKunTarpeeksiSaldoa() {
        assertEquals(true, kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void edullisenOstoFalseKunEiSaldoa() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(false, kassa.syoEdullisesti(kortti));
    }
    
    @Test
    public void maukkaanOstoLiianVahallaRahallaEiLisaaSaldoa() {
        kassa.syoMaukkaasti(300);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukaanOstoLiianVahallaRahallaEiLisaaMyytyjaMaukkaita() {
        kassa.syoMaukkaasti(300);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanOstoLiianVahallaRahallaPalauttaaRahat() {
        assertEquals(200, kassa.syoMaukkaasti(200));
    }
    
    @Test
    public void maukkaanOstoTrueKunTarpeeksiSaldoa() {
        assertEquals(true, kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void maukkaanOstoFalseKunEiSaldoa() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(false, kassa.syoMaukkaasti(kortti));
    }
    
    @Test
    public void edullisenOstoKortillaLisaaMyytyjaEdullisia() {
        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaanOstoKortillaLisaaMyytyjaMaukkaita() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void edullisenOstoKortillaEiLisaaKassanSaldoa() {
        kassa.syoEdullisesti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void maukkaanOstoKortillaEiLisaaKassanSaldoa() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());
    }
    
    @Test
    public void edullisenOstoKortillaVahentaaKortinSaldoa() {
        kassa.syoEdullisesti(kortti);
        assertEquals(260, kortti.saldo());
    }
    
    @Test
    public void maukkaanOstoKortillaVahentaaKortinSaldoa() {
        kassa.syoMaukkaasti(kortti);
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void kortilleLatausLisaaKortinSaldoa() {
        kassa.lataaRahaaKortille(kortti, 1000);
        assertEquals(1500, kortti.saldo());
    }
    
    @Test
    public void kortilleLatausLisaaKassanSaldoa() {
        kassa.lataaRahaaKortille(kortti, 1000);
        assertEquals(101000, kassa.kassassaRahaa());
    }
    
    @Test
    public void korttilleEiVoiLadataNegatiivista() {
        kassa.lataaRahaaKortille(kortti, -30);
        assertEquals(500, kortti.saldo());
    }
}
