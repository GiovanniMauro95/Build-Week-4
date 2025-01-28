package main.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import main.Entities.Utente;
import main.EntityManagerUtil;
import main.DAO.Interfaces.UtenteDAO;

import java.util.List;
import java.util.UUID;

public class UtenteDAOImpl implements UtenteDAO {

    @Override
    public void aggiungiUtente(Utente utente) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(utente.getTessera()); // Persiste prima la tessera
            em.persist(utente); // Poi persiste l'utente
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
    public void rimuoviUtente(UUID idUtente) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            // Recupera l'utente dal DB tramite idUtente
            Utente utenteDaEliminare = em.find(Utente.class, idUtente);

            if (utenteDaEliminare != null) {
                em.remove(utenteDaEliminare); // Rimuove l'utente trovato
                System.out.println("Utente rimosso con successo: " + utenteDaEliminare);
            } else {
                System.out.println("Utente non trovato con id: " + idUtente);
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
    public List<Utente> getAllUtenti() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            // Creiamo una query JPQL per ottenere tutti gli utenti
            TypedQuery<Utente> query = em.createQuery("SELECT u FROM Utente u", Utente.class);
            return query.getResultList(); // Restituisce la lista di utenti
        } catch (Exception e) {
            e.printStackTrace();
            return null; // In caso di errore, restituisce null
        } finally {
            em.close();
        }
    }
}
