package main.DAO.Interfaces;

import java.util.List;
import java.util.UUID;
import main.Entities.Utente;

public interface UtenteDAO {
    void aggiungiUtente(Utente utente);

    void rimuoviUtente(UUID idUtente);

    List<Utente> getAllUtenti();
}
