package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;

import jdk.jfr.Timestamp;
import ohtu.verkkokauppa.Kauppa;
import ohtu.verkkokauppa.Ostoskori;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    Pankki pankki;
    Viitegeneraattori viite;
    Varasto varasto;
    Kauppa k;

    @Before
    public void setUp() {
        pankki = mock(Pankki.class);
        viite = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        k = new Kauppa(varasto, pankki, viite);
    }


    @Test
    public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }

    @Test
    public void ostoksessaKutsutaanTilisiirtoaOikeillaTiedoilla() {

        //Viitenumeroksi 42
        when(viite.uusi()).thenReturn(42);
        
        //Tuote 1 on olut, hinta 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "olut", 5));

        //Tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("Pekka", "1111-4321");

        verify(pankki).tilisiirto("Pekka", 42, "1111-4321",  "33333-44455", 5);
    }

    @Test
    public void ostetaanKaksiEriTuotettaJaTilisiirrossaOikeatTiedot() {

        //Viitenumeroksi 17
        when(viite.uusi()).thenReturn(17);
        
        //Tuote 1 on olut, hinta 5 ja saldo 10
        //Tuote 2 on limu, hinta 2 ja saldo 5
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "olut", 5));
        when(varasto.saldo(2)).thenReturn(5);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "limu", 2));

        //Tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.tilimaksu("Pekka", "1111-4321");

        verify(pankki).tilisiirto("Pekka", 17, "1111-4321",  "33333-44455", 7); 
    }

    @Test
    public void ostetaanKaksiSamaaTuotettaJaTilisiirrossaOikeatTiedot() {

        //Viitenumeroksi 21
        when(viite.uusi()).thenReturn(21);
        
        //Tuote 1 on olut, hinta 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "olut", 5));

        //Tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(1);
        k.tilimaksu("Pekka", "1111-4321");

        verify(pankki).tilisiirto("Pekka", 21, "1111-4321",  "33333-44455", 10); 
    }

    @Test
    public void ostetaanVarastostaLoppunutTuote() {

        //Viitenumeroksi 69
        when(viite.uusi()).thenReturn(69);
        
        //Tuote 1 on olut, hinta 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(0);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "olut", 5));

        //Tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("Pekka", "1111-4321");

        verify(pankki).tilisiirto("Pekka", 69, "1111-4321",  "33333-44455", 0);
    }


    @Test
    public void aloitaAsiointiNollaaOstoskorin() {

        //Viitenumeroksi 123
        when(viite.uusi()).thenReturn(123);

        //Tuote 1 on olut, hinta 5 ja saldo 10
        //Tuote 2 on limu, hinta 2 ja saldo 2
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "olut", 5));
        when(varasto.saldo(2)).thenReturn(2);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "limu", 2));

        //Tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.tilimaksu("Pekka", "1111-4321");

        verify(pankki).tilisiirto("Pekka", 123, "1111-4321",  "33333-44455", 2);
    }

    @Test
    public void jokaiselleMaksutapahtumalleViitenumero() {

        //Viitenumeroksi 666
        when(viite.uusi()).thenReturn(666);

        //Tuote 1 on olut, hinta 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "olut", 5));
        
        //Tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.tilimaksu("Pekka", "1111-4321");
        
        verify(pankki).tilisiirto("Pekka", 666, "1111-4321",  "33333-44455", 5);

       //Viitenumeroksi 667
       when(viite.uusi()).thenReturn(667);

       //Tuote 1 on olut, hinta 5 ja saldo 10
       when(varasto.saldo(1)).thenReturn(10);
       when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "olut", 5));
       
       //Tehdään ostokset
       k.aloitaAsiointi();
       k.lisaaKoriin(1);
       k.tilimaksu("Pekka", "1111-4321");
       
       verify(pankki).tilisiirto("Pekka", 667, "1111-4321",  "33333-44455", 5);
    }

    @Test
    public void lisataanJaPoistetaanTuotteita() {

        //Viitenumeroksi 999
        when(viite.uusi()).thenReturn(999);
        
        //Tuote 1 on olut, hinta 5 ja saldo 10
        //Tuote 2 on limu, hinta 2 ja saldo 5
        when(varasto.saldo(1)).thenReturn(10);
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "olut", 5));
        when(varasto.saldo(2)).thenReturn(5);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "limu", 2));

        //Tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);
        k.lisaaKoriin(2);
        k.poistaKorista(2);
        k.tilimaksu("Pekka", "1111-4321");

        verify(pankki).tilisiirto("Pekka", 999, "1111-4321",  "33333-44455", 5); 
    }



}

