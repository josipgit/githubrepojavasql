package J15PrExtZapoManaProg;

import static java.lang.System.out;

public class Zaposlenik { // Zaposlenik je bazna klasa
    String ime;
    int placa;

    public Zaposlenik(String ime, int placa) {
        this.ime = ime;
        this.placa = placa;
    }

    public void oZaposleniku ()
    {
        out.println("Ime zaposlenika: " + ime + "  Placa zaposlenika: " + placa);
    }
}
