package J16DrExtAutoBiciklVozi;

import java.util.ArrayList;

public class TestVozilo {
    public static void main(String[] args) {
        ArrayList<Vozilo> lista = new ArrayList<Vozilo>();
        Automobil auto1 = new Automobil("sedan", 150.0, 5);
        Bicikl bike1 = new Bicikl("trkaci", 80.0, "slabasni");

        auto1.kretanje();
        bike1.kretanje();

        lista.add(auto1);
        lista.add(bike1);

        for (Vozilo voz : lista) {
            voz.kretanje();
        }

    }
}
