package J25ParcijalniIspit1;

public abstract class Vozilo {
    String regBroj;
    String marka;
    int godinaProizvodnje;

    public Vozilo(String regBroj, String marka, int godinaProizvodnje) throws MojaIznimka {
        if (godinaProizvodnje < 0) {
            throw new MojaIznimka("Godina proizvodnje ne moze bit negativna !!!!!!!!!!!!", "Korisnik: JOSIP");
        }
        this.regBroj = regBroj;
        this.marka = marka;
        this.godinaProizvodnje = godinaProizvodnje;
    }

    public String getRegBroj() {
        return regBroj;
    }

    public void setRegBroj(String regBroj) {
        this.regBroj = regBroj;
    }

    public String getMarka() {
        return marka;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public int getGodinaProizvodnje() {
        return godinaProizvodnje;
    }

    public void setGodinaProizvodnje(int godinaProizvodnje) throws MojaIznimka {
        if (godinaProizvodnje < 0) {
            throw new MojaIznimka("Godina proizvodnje ne moze bit negativna !!!!!!!!!!!!", "Korisnik: JOSIP");
        }
        this.godinaProizvodnje = godinaProizvodnje;
    }

    public String prikaziPodatke() {
        return "RegBroj='" + regBroj + '\'' +
                ", marka='" + marka + '\'' +
                ", godinaProizvodnje=" + godinaProizvodnje +
                '}';
    }

} // end class
