package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Erotus extends Komento{

    private int prevOperand;

    public Erotus(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    public void suorita() {
        int arvo = 0;

        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }

        this.prevOperand = arvo;
        this.sovellus.setTulos(this.sovellus.getTulos() - arvo);
    }

    @Override
    public void peru() {
        this.sovellus.setTulos(this.sovellus.getTulos() +  prevOperand);
    }
}