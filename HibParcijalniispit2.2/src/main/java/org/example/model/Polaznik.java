package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Polaznik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IDPolaznik;

    @Column(nullable = false, length = 100)
    private String Ime;

    @Column(nullable = false, length = 100)
    private String Prezime;

    public Polaznik() {
    }

    public Polaznik(String ime, String prezime) {
        Ime = ime;
        Prezime = prezime;
    }

    // Getteri i setteri
    public int getIDPolaznik() {
        return IDPolaznik;
    }

    public void setIDPolaznik(int IDPolaznik) {
        this.IDPolaznik = IDPolaznik;
    }

    public String getIme() {
        return Ime;
    }

    public void setIme(String ime) {
        this.Ime = ime;
    }

    public String getPrezime() {
        return Prezime;
    }

    public void setPrezime(String prezime) {
        this.Prezime = prezime;
    }
}

