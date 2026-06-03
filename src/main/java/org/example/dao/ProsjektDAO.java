package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.model.Prosjekt;

import java.util.List;

public class ProsjektDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ansattPU");

    // Finn prosjekt på ID
    public Prosjekt finnProsjektMedId(int id) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.find(Prosjekt.class, id);
        } finally {
            em.close();
        }
    }

    // Hent alle prosjekter
    public List<Prosjekt> hentAlleProsjekter() {
        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery("SELECT p FROM Prosjekt p", Prosjekt.class).getResultList();
        } finally {
            em.close();
        }
    }

    // Lagre nytt prosjekt
    public void lagreProsjekt(Prosjekt prosjekt) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(prosjekt);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}