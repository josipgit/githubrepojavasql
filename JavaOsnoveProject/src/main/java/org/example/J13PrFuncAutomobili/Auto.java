package J13PrFuncAutomobili;

import static java.lang.System.*;

public class Auto {
    String brzina;
    String marka;

    public Auto(String brzina, String marka) {
        this.brzina = brzina;
        this.marka = marka;
    }

    public String DohvatiAutomobil() {
        return ("Marka auta: " + marka + "  Brzina: " + brzina);
    }

    public void PaliAuto() {
        out.println("Auto upaljen !");
    }
}
