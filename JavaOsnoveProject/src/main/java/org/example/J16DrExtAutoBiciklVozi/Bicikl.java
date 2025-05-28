package J16DrExtAutoBiciklVozi;

import static java.lang.System.out;

public class Bicikl extends  Vozilo{
    String vrsta;

    public Bicikl(String tip, Double brzina, String vrsta) {
        super(tip, brzina);
        this.vrsta = vrsta;
    }

    @Override
    public void kretanje() {
        out.println("Bicikl: " + tip + "  Vrsta: " + vrsta + " se krece brzinom " + brzina);
    }

} // end class
