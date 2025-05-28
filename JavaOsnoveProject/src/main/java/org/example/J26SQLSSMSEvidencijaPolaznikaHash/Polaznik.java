package J26EvidencijaPolaznikaHash;

// Klasa Polaznik koja implementira Comparable sučelje za sortiranje
public class Polaznik implements Comparable<Polaznik> { //
    // Privatni atributi klase
    private String ime;
    private String prezime;
    private String email;

    // Konstruktor - metoda za inicijalizaciju objekta
    public Polaznik(String ime, String prezime, String email) {
        this.ime = ime;     // Postavljanje imena
        this.prezime = prezime; // Postavljanje prezimena
        this.email = email;  // Postavljanje emaila
    }

    // Getter metode - omogućuju pristup privatnim atributima

    public String getIme() {
        return ime; // Vraća ime polaznika
    }

    public String getPrezime() {
        return prezime; // Vraća prezime polaznika
    }

    public String getEmail() {
        return email; // Vraća email polaznika
    }

    // Metoda compareTo za usporedbu polaznika, koristi se za sortiranje polaznika
    @Override
    public int compareTo(Polaznik other) {
        //sortiramo po prezimenu
        int prezimeCmp = this.prezime.compareToIgnoreCase(other.prezime);
        if (prezimeCmp != 0) return prezimeCmp; //ako je nula onda je isto prezime obojice

        // ako je prezime isto, sortiramo po imenu
        int imeCmp = this.ime.compareToIgnoreCase(other.ime);
        if (imeCmp != 0) return imeCmp;

        //ako su i ime i prezime isti, sortiramo po e‑mailu
        return this.email.compareToIgnoreCase(other.email);
    }

    // Metoda za provjeru jednakosti polaznika
    // Ova metoda treba nadjačati (override‑ati) istoimenu metodu iz nadklase ili sučelja.
    // Ako to nije slučaj, prijavi pogrešku
    // metoda će raditi i bez anotacije, ali se snažno preporučuje koristiti ju
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Ako je isti objekt, vraća true
        if (o == null || getClass() != o.getClass()) return false; // Provjera tipa
        Polaznik polaznik = (Polaznik) o; // Pretvorba u Polaznik tip
        // Uspoređuje emailove, ignorira velika/mala slova
        return email.equalsIgnoreCase(polaznik.email);
    }

    // Metoda za izračun hash vrijednosti, važna je za HashSet/TreeSet
    @Override
    public int hashCode() {
        // Koristi email u lowercase za hash izračun
        return email.toLowerCase().hashCode();
    }

    // Metoda za tekstualni prikaz polaznika
    @Override
    public String toString() {
        // Formatirani string s podacima o polazniku
        return "Ime: " + ime + ", Prezime: " + prezime + ", E-mail: " + email;
    }
}