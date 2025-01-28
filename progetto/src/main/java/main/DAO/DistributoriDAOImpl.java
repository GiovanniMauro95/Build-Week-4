package main.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import main.Entities.Distributori;
import main.EntityManagerUtil;
import main.DAO.Interfaces.DistributoriDAO;

import java.util.List;

public class DistributoriDAOImpl implements DistributoriDAO {

    @Override
    public void aggiungiDistributori(Distributori distributori) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(distributori); // Persiste il distributore
            em.getTransaction().commit();
            System.out.println("Distributore aggiunto con successo: " + distributori);
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void rimuoviDistributori(Long idDistributore) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            // Recupera il distributore dal DB tramite idDistributori
            Distributori distributoreDaEliminare = em.find(Distributori.class, idDistributore);

            if (distributoreDaEliminare != null) {
                em.remove(distributoreDaEliminare); // Rimuove il distributore trovato
                System.out.println("Distributore rimosso con successo: " + distributoreDaEliminare);
            } else {
                System.out.println("Distributore non trovato con ID: " + idDistributore);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Distributori> getAllDistributori() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            // Creiamo una query JPQL per ottenere tutti i distributori
            TypedQuery<Distributori> query = em.createQuery("SELECT d FROM Distributori d", Distributori.class);
            return query.getResultList(); // Restituisce la lista di distributori
        } catch (Exception e) {
            e.printStackTrace();
            return null; // In caso di errore, restituisce null
        } finally {
            em.close();
        }
    }
}
