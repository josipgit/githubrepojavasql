package J24PrJAVARedakStupacPolaznik;

class Polaznik implements Comparable<Polaznik> {
    private int dob;
    private String ime;
    private String prezime;

    // Konstruktor
    public Polaznik(String ime, String prezime, int dob) {
        this.ime = ime;
        this.prezime = prezime;
        this.dob = dob;
    }

    public int compareTo(Polaznik s) {
        int gradeComparison = Integer.compare(this.dob, s.dob);
        return gradeComparison;
    }

    // Getter metode
    public int getDob() {
        return dob;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    @Override
    public String toString() {
        return ime + " " + prezime + " (" + dob + " godina)";
    }
}
