package J13PrFuncUcenik;

public class Ucenik {
    String ime;
    int godine;
    Double[] ocjene;

    public Ucenik(String ime, int godine, Double... ocjene) {
        this.ime = ime;
        this.godine = godine;
        this.ocjene = ocjene;
    }

    public String IspisiUcenika() {
        return ("Ucenik: " + ime + "  Godine: " + godine + "  Ocjene: " + ocjene);
    }

    public double IzracunajProsjekOcjena() {
        if (ocjene.length == 0) {
            return 0; // Avoid division by zero
        }

        int sum = 0;
        for (double ocjena : ocjene) {
            sum += ocjena; // Convert String to int
        }

        return (double) sum / ocjene.length; // Calculate average
    }

}
