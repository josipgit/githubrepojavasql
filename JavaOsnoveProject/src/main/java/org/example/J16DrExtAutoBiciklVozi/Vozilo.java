package J16DrExtAutoBiciklVozi;

public abstract class Vozilo { // mora bit abstract klasa ako ima ijednu abstract metodu
    String tip;
    Double brzina;

    public Vozilo(String tip, Double brzina) {
        this.tip = tip;
        this.brzina = brzina;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Double getBrzina() {
        return brzina;
    }

    public void setBrzina(Double brzina) {
        this.brzina = brzina;
    }

    public abstract void kretanje(); // ova funkcija nema tijelo jer je apstraktna

} // end class
