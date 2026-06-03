package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.model.Ansatt;
import org.example.model.Avdeling;

import java.util.List;

public class AvdelingDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ansattPU");

    //Metode for å finne avdeling på ID
    public Avdeling finnAvdelingMedId(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.find(Avdeling.class, id);
        } finally {
            em.close();
        }
    }

    //Hent alle aveldinger

    public List<Avdeling> hentAlleAvdelinger() {
        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery("SELECT a FROM Avdeling a", Avdeling.class).getResultList();

        } finally {
            em.close();
        }
    }

    //Lag en ny avdeling
    public void lagreAvdeling(Avdeling avdeling) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(avdeling);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    //Opprette avdeling
    public void opprettAvdeling(String navn, int sjefId) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // Finn ansatt som skal være sjef
            Ansatt sjef = em.find(Ansatt.class, sjefId);

            if (sjef == null) {
                System.out.println("Ansatt finnes ikke!");
                return;
            }

            // Opprett ny avdeling
            Avdeling avdeling = new Avdeling();
            avdeling.setNavn(navn);

            em.persist(avdeling); // må lagres først for å få ID

            // Sett sjef
            avdeling.setSjefId(sjefId);

            // Flytt ansatt til ny avdeling
            sjef.setAvdeling(avdeling);

            em.getTransaction().commit();

            System.out.println("Avdeling opprettet!");

        } finally {
            em.close();
        }
    }

    //Oppdater avdeling
    public void oppdaterAvdeling(Avdeling avdeling) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(avdeling);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    //Slette avdeling
    public void slettAvdeling(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Avdeling avdeling = em.find(Avdeling.class, id);
            if (avdeling != null) {
                em.remove(avdeling);
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
