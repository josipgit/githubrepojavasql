package J16DrExtAutoBiciklVozi;

import static java.lang.System.out;

public class Automobil extends Vozilo
{
    int brojVrata;

    public Automobil(String tip, Double brzina, int brojVrata) {
        super(tip, brzina);
        this.brojVrata = brojVrata;
    }

    @Override
    public void kretanje() {
        out.println("Automobil: " + tip + "  Brzina: " + brzina + "  Broj vrata: " + brojVrata);
    }
}
