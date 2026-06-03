package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.model.Ansatt;
import org.example.model.Avdeling;

import java.util.List;

public class AnsattDAO {


    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ansattPU");

    // Metode for å finne ansatt på ID
    public Ansatt finnAnsattMedId(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.find(Ansatt.class, id);
        } finally {
            em.close();
        }
    }

    //Finn ansatt med brukernavn
    public Ansatt finnAnsattMedBrukernavn(String brukernavn){
        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery(
                            "SELECT a FROM Ansatt a WHERE a.brukernavn = :bnavn",
                            Ansatt.class
                    )
                    .setParameter("bnavn", brukernavn)
                    .getSingleResult();

        } catch (Exception e) {
            return null; // hvis ikke funnet
        } finally {
            em.close();
        }
    }

    //Hent alle ansatte
    public List<Ansatt> hentAlleAnsatte() {
        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery("SELECT a FrOM Ansatt a", Ansatt.class).getResultList();
        } finally {
            em.close();
        }
    }

    //Lagre ny til ny ansatt
    public void lagreAnsatt(Ansatt ansatt) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(ansatt);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    //Oppdatere ansatt
    public void oppdaterAnsatt(Ansatt ansatt) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.merge(ansatt);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    //Slett ansatt
    public void slettAnsatt(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Ansatt ansatt = em.find(Ansatt.class, id);
            if (ansatt != null) {
                em.remove(ansatt);
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void settAnsattTilAvdeling(int ansattId, int avdelingId) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Ansatt ansatt = em.find(Ansatt.class, ansattId);
            Avdeling avdeling = em.find(Avdeling.class, avdelingId);

            if (ansatt != null && avdeling != null) {
                ansatt.setAvdeling(avdeling);
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
