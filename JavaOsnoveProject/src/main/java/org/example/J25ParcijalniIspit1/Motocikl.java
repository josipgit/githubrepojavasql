package J25ParcijalniIspit1;

public class Motocikl extends Vozilo {
    String tipMotora;

    public Motocikl(String regBroj, String marka, int godinaProizvodnje, String tipMotora) throws MojaIznimka {
        super(regBroj, marka, godinaProizvodnje);
        this.tipMotora = tipMotora;
    }

    public String getTipMotora() {
        return tipMotora;
    }

    public void setTipMotora(String tipMotora) {
        this.tipMotora = tipMotora;
    }

    @Override
    public String prikaziPodatke() {
        return super.prikaziPodatke() + " Tip motora: " + tipMotora;
    }

} // end class
