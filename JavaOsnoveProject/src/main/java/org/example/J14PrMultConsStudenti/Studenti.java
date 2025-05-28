package J14PrMultConsStudenti;

import java.util.ArrayList;
import java.util.Arrays;

public class Studenti {
    private String ime;
    private String prezime;
    public int brojIndeksa;
    private ArrayList<String> listaIspita = new ArrayList<>();

    // first constructor with a fixed number of exams
    public Studenti(String ime, String prezime, int brojIndeksa, String... ispiti) {
        this.ime = ime; // this means this class (Studenti)
        this.prezime = prezime;
        this.brojIndeksa = brojIndeksa;
        this.listaIspita.addAll(Arrays.asList(ispiti));
    }

    // Constructor that defaults `brojIndeksa` to 5
    public Studenti(String ime, String prezime, String... ispiti) {
        this(ime, prezime, 5, ispiti); // this calls the first constructor and passes 5 as brojIndeksa
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public int getBrojIndeksa() {
        return brojIndeksa;
    }

    public void setBrojIndeksa(int brojIndeksa) {
        this.brojIndeksa = brojIndeksa;
    }

    public ArrayList<String> getListaIspita() {
        return listaIspita;
    }

    public void setListaIspita(ArrayList<String> listaIspita) {
        this.listaIspita = listaIspita;
    }

    @Override
    public String toString() {
        return "Studenti {" +
                "ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", brojIndeksa=" + brojIndeksa +
                ", listaIspita=" + listaIspita +
                '}';
    }

}