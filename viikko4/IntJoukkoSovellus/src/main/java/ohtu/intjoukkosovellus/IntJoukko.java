
package ohtu.intjoukkosovellus;

import java.util.Arrays;

public class IntJoukko {

    public final static int KAPASITEETTI = 5; // aloitustalukon koko
    public final static int OLETUSKASVATUS = 5;  // luotava uusi taulukko on näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] taulukko;       // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;     // Tyhjässä joukossa alkioiden_määrä on nolla. 


    public IntJoukko() {
        this(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);
    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti <= 0) {
            throw new IndexOutOfBoundsException("Kapasiteetti väärin");
        }
        if (kasvatuskoko <= 0) {
            throw new IndexOutOfBoundsException("Kasvatuskoko väärin");
        }

        taulukko = new int[kapasiteetti];
        Arrays.fill(taulukko, 0);
        this.alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }


    public void lisaa(int luku) {
        if(!kuuluu(luku)) {
            taulukko[alkioidenLkm] = luku;
            alkioidenLkm++;

            // taulukko on täysi, joten kasvatetaan sitä
            if(alkioidenLkm == taulukko.length) {
                int[] temp = new int[taulukko.length + this.kasvatuskoko];
                Arrays.fill(temp, 0);
                kopioiTaulukko(taulukko, temp);

                taulukko = new int[temp.length];
                kopioiTaulukko(temp, taulukko);
            }
        }
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == taulukko[i]) {
                return true;
            }
        }
        return false;
    }

    public void poista(int luku) {
        int kohta = -1;

        //haetaan poistettava luku ja sen indeksi
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == taulukko[i]) {
                kohta = i;         
                break;
            }
        }

        //siirretään luvut indeksistä loppuun yhden pykälän verran eteenpäin
        if (kohta != -1) {
            for (int i = kohta; i < alkioidenLkm-1; i++) {
                taulukko[i] = taulukko[i+1];
            }
            taulukko[alkioidenLkm] = 0;
            alkioidenLkm--;
        }
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }
    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        String s = "{";

        for (int i = 0; i < alkioidenLkm; i++) {
            s += taulukko[i];
            if (i+1 < alkioidenLkm) {
                s += ", ";
            }
        }
        return s + "}";

    }

    public int[] toIntArray() {   
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = taulukko[i];
        }
        return taulu;
    }
   
    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        for (int i = 0; i < a.alkioidenLkm; i++) {
            b.lisaa(a.taulukko[i]);
        }
        return b;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();

        for (int i = 0; i < a.alkioidenLkm; i++) {
            if (b.kuuluu(a.taulukko[i])) {
                y.lisaa(a.taulukko[i]);
            }
        }
        return y;
    }
    
    public static IntJoukko erotus (IntJoukko a, IntJoukko b) {
        for (int i = 0; i < b.alkioidenLkm; i++) {
            if (a.kuuluu(b.taulukko[i])){
               a.poista(b.taulukko[i]);
            }
        }
        return a;
    }
}
