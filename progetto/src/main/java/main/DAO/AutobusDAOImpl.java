package main.DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import main.Entities.Autobus;
import main.EntityManagerUtil;
import main.DAO.Interfaces.AutobusDAO;

import java.util.List;
import java.util.UUID;

public class AutobusDAOImpl implements AutobusDAO {

    @Override
    public void aggiungiAutobus(Autobus autobus) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(autobus); 
            em.getTransaction().commit();
            System.out.println("Autobus aggiunto con successo: " + autobus);
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
    public void rimuoviAutobus(UUID codiceUnivoco) {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            em.getTransaction().begin();

          
            Autobus autobusDaEliminare = em.find(Autobus.class, codiceUnivoco);

            if (autobusDaEliminare != null) {
                em.remove(autobusDaEliminare); 
                System.out.println("Autobus rimosso con successo: " + autobusDaEliminare);
            } else {
                System.out.println("Autobus non trovato con codice univoco: " + codiceUnivoco);
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
    public List<Autobus> getAllAutobus() {
        EntityManager em = EntityManagerUtil.getEntityManager();
        try {
            
            TypedQuery<Autobus> query = em.createQuery("SELECT a FROM Autobus a", Autobus.class);
            return query.getResultList(); 
        } catch (Exception e) {
            e.printStackTrace();
            return null; 
        } finally {
            em.close();
        }
    }
}
