package J19PrExcTryCatchThrow;

public class MojaIznimka extends Throwable { // or Exception or Error
    private String korisnik;

    //default konstruktor
    public MojaIznimka() {}

    //    Konstruktor s dodatnim argumentom, ime korisnika koji je izazvao iznimku
    public MojaIznimka(String poruka, String korisnik) {
        super(poruka);
        setKorisnik(korisnik);
    }

    public String getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(String korisnik) {
        this.korisnik = korisnik;
    }
}
