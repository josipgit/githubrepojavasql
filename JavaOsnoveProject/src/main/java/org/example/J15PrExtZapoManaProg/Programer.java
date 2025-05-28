package J15PrExtZapoManaProg;

import static java.lang.System.out;

public class Programer extends Zaposlenik {
    String ime;
    int placa;

    public Programer(String ime, int placa) {
        super(ime, placa);
    }

    public void Radi()
    {
        out.println(super.ime + " pise programski kod.");
    }
}
