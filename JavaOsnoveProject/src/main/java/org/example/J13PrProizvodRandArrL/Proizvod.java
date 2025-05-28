package J13PrProizvodRandArrL;

public class Proizvod {
    String naziv;
    double cijena;
    double kolicina;

    public Proizvod(String naziv, double cijena, double kolicina) {
        this.naziv = naziv;
        this.cijena = cijena;
        this.kolicina = kolicina;
    }

    public String IspisiProizvod() {
        return ("Proizvod: " + naziv + "  Cijena: " + cijena + "  Kolicina: " + kolicina);
    }

}
