package org.example.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "avdeling")
public class Avdeling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "avdeling_id")
    private int avdelingId;

    @Column(name = "navn")
    private String navn;

    @Column(name = "sjef_id")
    private Integer sjefId; // midlertidig bare som ID

    @OneToMany(mappedBy = "avdeling", fetch = FetchType.EAGER)
    private List<Ansatt> ansatte;

    // Tom konstruktør
    public Avdeling() {}

    // Getters og setters
    public int getAvdelingId() {
        return avdelingId;
    }

    public void setAvdelingId(int avdelingId) {
        this.avdelingId = avdelingId;
    }

    public List<Ansatt> getAnsatte() {
        return ansatte;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Integer getSjefId() {
        return sjefId;
    }

    public void setSjefId(Integer sjefId) {
        this.sjefId = sjefId;
    }

    @Override
    public String toString() {
        return "Avdeling { " +
                "avdelingId = " + avdelingId +
                ", navn = '" + navn + '\'' +
                ", sjefId = " + sjefId +
                '}';
    }
}