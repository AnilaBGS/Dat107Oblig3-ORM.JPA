package org.example.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.example.model.Ansatt;
import org.example.model.Prosjekt;
import org.example.model.Prosjektdeltagelse;

import java.util.List;

public class ProsjektdeltagelseDAO {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ansattPU");

    public void leggTilAnsattIProsjekt(int ansattId, int prosjektId, String rolle) {

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Ansatt ansatt = em.find(Ansatt.class, ansattId);
            Prosjekt prosjekt = em.find(Prosjekt.class, prosjektId);

            if (ansatt == null || prosjekt == null) {
                em.getTransaction().rollback();
                System.out.println("Ansatt eller prosjekt finnes ikke!");
                return;
            }

            Prosjektdeltagelse pd = new Prosjektdeltagelse();
            pd.setAnsatt(ansatt);
            pd.setProsjekt(prosjekt);
            pd.setRolle(rolle);
            pd.setTimer(0);

            em.persist(pd);

            em.getTransaction().commit();

            System.out.println("Ansatt lagt til prosjekt!");

        } finally {
            em.close();
        }
    }

    public List<Prosjektdeltagelse> finnProsjekterForAnsatt(int ansattId) {

        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery(
                            "SELECT p FROM Prosjektdeltagelse p WHERE p.ansatt.ansattId = :id",
                            Prosjektdeltagelse.class
                    )
                    .setParameter("id", ansattId)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    public List<Prosjektdeltagelse> finnAnsatteForProsjekt(int prosjektId) {

        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery(
                            "SELECT p FROM Prosjektdeltagelse p WHERE p.prosjekt.prosjektId = :id",
                            Prosjektdeltagelse.class
                    )
                    .setParameter("id", prosjektId)
                    .getResultList();

        } finally {
            em.close();
        }
    }

    public void oppdaterTimer(int ansattId, int prosjektId, int nyeTimer) {

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            List<Prosjektdeltagelse> result = em.createQuery(
                            "SELECT p FROM Prosjektdeltagelse p " +
                                    "WHERE p.ansatt.ansattId = :a AND p.prosjekt.prosjektId = :p",
                            Prosjektdeltagelse.class
                    )
                    .setParameter("a", ansattId)
                    .setParameter("p", prosjektId)
                    .getResultList();

            if (result.isEmpty()) {
                System.out.println("Fant ikke prosjektdeltagelse!");
                em.getTransaction().rollback();
                return;
            }

            Prosjektdeltagelse pd = result.get(0);
            pd.setTimer(nyeTimer);

            em.getTransaction().commit();

            System.out.println("Timer oppdatert!");

        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();

        } finally {
            em.close();
        }
    }
}
