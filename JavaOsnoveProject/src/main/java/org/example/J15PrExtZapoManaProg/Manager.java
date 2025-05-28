package J15PrExtZapoManaProg;

import static java.lang.System.out;

public class Manager extends Zaposlenik {  // ovdje je Zaposlenik bazna(parent, base) klasa
    String ime;
    int placa;

    public Manager(String ime, int placa) {
        super(ime, placa);
    }

    public void Radi()
    {
        out.println(super.ime + " upravlja timom.");
    }
}
