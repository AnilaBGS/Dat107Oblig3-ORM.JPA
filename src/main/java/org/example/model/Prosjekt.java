package org.example.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Prosjekt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prosjekt_id")
    private int prosjektId;

    private String navn;
    private String beskrivelse;

    //Knytt til prosjektdeltagelse
    @OneToMany(mappedBy = "prosjekt")
    private List<Prosjektdeltagelse> deltagelser;

    //Konstruktør
    public Prosjekt() {
    }

    public Prosjekt(String navn, String beskrivelse) {
        this.navn = navn;
        this.beskrivelse = beskrivelse;
    }

    // Gettere og settere
    public int getProsjektId() {
        return prosjektId;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public List<Prosjektdeltagelse> getDeltagelser() {
        return deltagelser;
    }

    @Override
    public String toString() {
        return "Prosjekt { id = " + prosjektId + ", navn = ' " + navn + " ' }";
    }
}
