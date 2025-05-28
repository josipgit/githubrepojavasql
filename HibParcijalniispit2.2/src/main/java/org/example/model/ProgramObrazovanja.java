package org.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProgramObrazovanja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IDProgramObrazovanja;

    @Column(nullable = false, length = 100)
    private String Naziv;

    @Column(nullable = false)
    private int CSVET;

    public ProgramObrazovanja() {
    }

    public ProgramObrazovanja(String naziv, int CSVET) {
        Naziv = naziv;
        this.CSVET = CSVET;
    }

    // Getteri i setteri
    public int getIDProgramObrazovanja() {
        return IDProgramObrazovanja;
    }

    public void setIDProgramObrazovanja(int IDProgramObrazovanja) {
        this.IDProgramObrazovanja = IDProgramObrazovanja;
    }

    public String getNaziv() {
        return Naziv;
    }

    public void setNaziv(String naziv) {
        this.Naziv = naziv;
    }

    public int getCSVET() {
        return CSVET;
    }

    public void setCSVET(int CSVET) {
        this.CSVET = CSVET;
    }
}
