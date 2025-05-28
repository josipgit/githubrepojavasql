package J25ParcijalniIspit1;

public class MojaIznimka extends Throwable { // Use Exception instead of Throwable
    private String korisnik;

    public MojaIznimka(String poruka, String korisnik) {
        super(poruka); // Set the exception message
        this.korisnik = korisnik;
    }

    public String getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(String korisnik) {
        this.korisnik = korisnik;
    }

    @Override
    public String toString() {
        return super.getMessage() + " Korisnik: " + korisnik;
    }
}