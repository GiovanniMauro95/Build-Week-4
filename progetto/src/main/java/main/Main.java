package main;

import main.DAO.UtenteDAOImpl;
// import main.Entities.Tessera;
// import main.Entities.Utente;

// import java.time.LocalDate;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        // Crea una tessera
        // Tessera tessera = new Tessera(true, LocalDate.now());

        // Crea un utente associato a una tessera
        // Utente utente = new Utente("Mario", "Rossi", 30, tessera);

        // Istanza del DAO per Utente
        UtenteDAOImpl utenteDAO = new UtenteDAOImpl();

        // Aggiungi l'utente con la tessera
        // System.out.println("Aggiungendo l'utente...");
        // utenteDAO.aggiungiUtente(utente);

        utenteDAO.rimuoviUtente(UUID.fromString("0a492437-3227-47b0-bc22-7a806aad3512"));
    }
}
