package org.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Upis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IDUpis;

    @ManyToOne
    @JoinColumn(name = "PolaznikID", nullable = false)
    private Polaznik polaznik;

    @ManyToOne
    @JoinColumn(name = "ProgramObrazovanjaID", nullable = false)
    private ProgramObrazovanja programObrazovanja;

    public Upis() {
    }

    public Upis(Polaznik polaznik, ProgramObrazovanja programObrazovanja) {
        this.polaznik = polaznik;
        this.programObrazovanja = programObrazovanja;
    }

    // Getteri i setteri
    public int getIDUpis() {
        return IDUpis;
    }

    public void setIDUpis(int IDUpis) {
        this.IDUpis = IDUpis;
    }

    public Polaznik getPolaznik() {
        return polaznik;
    }

    public void setPolaznik(Polaznik polaznik) {
        this.polaznik = polaznik;
    }

    public ProgramObrazovanja getProgramObrazovanja() {
        return programObrazovanja;
    }

    public void setProgramObrazovanja(ProgramObrazovanja programObrazovanja) {
        this.programObrazovanja = programObrazovanja;
    }
}


