package org.example.model;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "ansatt")
public class Ansatt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ansattId;

    @ManyToOne
    @JoinColumn(name = "avdeling_id")
    private Avdeling avdeling;

    @Column(unique = true, nullable = false)
    private String brukernavn;

    @Column(nullable = false)
    private String fornavn;

    @Column(nullable = false)
    private String etternavn;

    @Column(name = "ansettelsesdato", nullable = false)
    private LocalDate ansettelsesdato;

    private String stilling;

    @Column(name = "manedslonn")
    private double manedslonn;

    // Tom konstruktør (påkrevd av JPA)

    public Ansatt() {}

    // Getters og setters

    public int getAnsattId() {
        return ansattId;
    }

    public Avdeling getAvdeling(){
        return avdeling;
    }

    public void setAvdeling(Avdeling avdeling) {
        this.avdeling = avdeling;
    }

    public void setAnsattId(int ansattId) {
        this.ansattId = ansattId;
    }

    public String getBrukernavn() {
        return brukernavn;
    }

    public void setBrukernavn(String brukernavn) {
        this.brukernavn = brukernavn;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public LocalDate getAnsettelsesdato() {
        return ansettelsesdato;
    }

    public void setAnsettelsesdato(LocalDate ansettelsesdato) {
        this.ansettelsesdato = ansettelsesdato;
    }

    public String getStilling() {
        return stilling;
    }

    public void setStilling(String stilling) {
        this.stilling = stilling;
    }

    public double getManedslonn() {
        return manedslonn;
    }

    public void setManedslonn(double manedslonn) {
        this.manedslonn = manedslonn;
    }

    @Override
    public String toString() {
        return "Ansatt { " +
                "ansattId = " + ansattId +
                ", brukernavn = '" + brukernavn + '\'' +
                ", fornavn = '" + fornavn + '\'' +
                ", etternavn = '" + etternavn + '\'' +
                ", ansettelsesdato = " + ansettelsesdato +
                ", stilling = '" + stilling + '\'' +
                ", manedslonn = " + manedslonn +
                ", avdeling = " + avdeling +
                '}';

    }
}
