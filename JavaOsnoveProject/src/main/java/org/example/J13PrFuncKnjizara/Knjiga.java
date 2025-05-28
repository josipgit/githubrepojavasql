package J13PrFuncKnjizara;

public class Knjiga {
    String naziv;
    String autor;
    String cijena;

    public Knjiga(String naziv, String autor, String cijena) {
        this.naziv = naziv;
        this.autor = autor;
        this.cijena = cijena;
    }

    public String DohvatiKnjigu() {
        return ("Knjiga: " + naziv + "  Autor: " + autor + "  Cijena: " + cijena);
    }
}
