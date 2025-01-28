package main.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import main.Entities.Tram;
import main.EntityManagerUtil;
import main.DAO.Interfaces.TramDAO;

import java.util.List;
import java.util.UUID;

public class TramDAOImpl implements TramDAO {

    @Override
    public void aggiungiTram(Tram tram) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(tram); 
            em.getTransaction().commit();
            System.out.println("Tram aggiunto con successo: " + tram);
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
    public void rimuoviTram(UUID codiceUnivoco) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();

           
            Tram tramDaEliminare = em.find(Tram.class, codiceUnivoco);

            if (tramDaEliminare != null) {
                em.remove(tramDaEliminare); 
                System.out.println("Tram rimosso con successo: " + tramDaEliminare);
            } else {
                System.out.println("Tram non trovato con codice: " + codiceUnivoco);
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
    public List<Tram> getAllTram() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
          
            TypedQuery<Tram> query = em.createQuery("SELECT t FROM Tram t", Tram.class);
            return query.getResultList(); 
        } catch (Exception e) {
            e.printStackTrace();
            return null; 
        } finally {
            em.close();
        }
    }
}
