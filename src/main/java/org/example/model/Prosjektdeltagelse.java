package org.example.model;

import jakarta.persistence.*;

@Entity
public class Prosjektdeltagelse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "ansatt_id")
    private Ansatt ansatt;

    @ManyToOne
    @JoinColumn(name = "prosjekt_id")
    private Prosjekt prosjekt;

    private String rolle;
    private int timer;

    public Prosjektdeltagelse() {}

    public Prosjektdeltagelse(Ansatt ansatt, Prosjekt prosjekt, String rolle) {
        this.ansatt = ansatt;
        this.prosjekt = prosjekt;
        this.rolle = rolle;
        this.timer = 0;
    }

    // Gettere og settere
    public Ansatt getAnsatt() {

        return ansatt;
    }

    public Prosjekt getProsjekt() {

        return prosjekt;
    }

    public String getRolle() {

        return rolle;
    }

    public void setRolle(String rolle) {

        this.rolle = rolle;
    }

    public int getTimer() {

        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public void setAnsatt(Ansatt ansatt) {
        this.ansatt = ansatt;
    }

    public void setProsjekt(Prosjekt prosjekt) {
        this.prosjekt = prosjekt;
    }
}