package main;

import main.DAO.DistributoriDAOImpl;
import main.DAO.UtenteDAOImpl;
import main.DAO.Interfaces.DistributoriDAO;
import main.DAO.Interfaces.UtenteDAO;
import main.Entities.Abbonamento;
import main.Entities.Biglietto;
import main.Entities.Distributori;
import main.Entities.Tessera;
import main.Entities.Utente;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int scelta;

        // Menu principale
        System.out.println("Benvenuto nel sistema!");
        System.out.println("Vuoi entrare come:\n1. Utente\n2. Admin");
        scelta = scanner.nextInt();

        switch (scelta) {
            case 1:
                gestisciUtente(scanner);
                break;
            case 2:
                // gestisciAdmin(scanner);
                break;
            default:
                System.out.println("Scelta non valida. Riprova.");
        }
        scanner.close();
    }

    private static void gestisciUtente(Scanner scanner) {
        System.out.println("Scegli tra:\n1. Distributore Automatico\n2. Rivenditore Autorizzato");
        int scelta = scanner.nextInt();

        switch (scelta) {
            case 1:
                gestisciDistributore(scanner);
                break;
            case 2:
                System.out.println("Funzionalità Rivenditore non ancora implementata.");
                break;
            default:
                System.out.println("Scelta non valida. Riprova.");
        }
    }

    private static void gestisciDistributore(Scanner scanner) {
        
        DistributoriDAO distributoriDAO = new DistributoriDAOImpl();
        Distributori newDistributore = new Distributori(true); 
        distributoriDAO.aggiungiDistributori(newDistributore);
        List<Distributori> listDistributori = distributoriDAO.getAllDistributori();

        if (listDistributori.isEmpty()) {
            System.out.println("Ci dispiace, ma puoi andare a piedi, tutta salute!");
            return;
        }

        System.out.println("Se sei registrato premi '1', altrimenti premi '2':");
        int scelta = scanner.nextInt();

        if (scelta == 1) {
            gestisciUtenteRegistrato(scanner);
        } else {
            gestisciNuovoUtente(scanner);
        }
    }

    private static void gestisciUtenteRegistrato(Scanner scanner) {
        System.out.println("Utente registrato. \n");
        System.out.println("Scegli tra:\n1. Creare un abbonamento\n2. Acquistare un biglietto");
        int scelta = scanner.nextInt();
        // Logica per l'utente registrato da implementare
    }

    private static void gestisciNuovoUtente(Scanner scanner) {
        System.out.println("Se ti vuoi registrare premi '1'\n Se vuoi solo comprare un biglietto premi '2'");
        int scelta = scanner.nextInt();

        if (scelta == 1) {
            registraNuovoUtente(scanner);
        } else {
            acquistaBiglietto();
        }
    }

    private static void registraNuovoUtente(Scanner scanner) {
        scanner.nextLine(); // Per evitare problemi con il buffer
        Tessera nuovaTessera = new Tessera(true, LocalDate.now());

        System.out.println("Inserisci Nome: ");
        String nome = scanner.nextLine();
        System.out.println("Inserisci Cognome: ");
        String cognome = scanner.nextLine();
        System.out.println("Inserisci Eta': ");
        int eta = scanner.nextInt();

        Utente nuovoUtente = new Utente(nome, cognome, eta, nuovaTessera);
        System.out.println(nuovoUtente + "\n");

        System.out.println("Premi '1' per un settimanale, '2' per un mensile:");
        int scelta = scanner.nextInt();

        Abbonamento newAbbonamento = new Abbonamento("Settimanale", true, LocalDate.now(), LocalDate.now().plusDays(7));
        if (scelta == 2) {
            newAbbonamento.setTipologia("Mensile");
            newAbbonamento.setScadenza(LocalDate.now().plusDays(30));
        }
        nuovoUtente.setAbbonamento(newAbbonamento);

        UtenteDAO utenteDAO = new UtenteDAOImpl();
        utenteDAO.aggiungiUtente(nuovoUtente);
    }

    private static void acquistaBiglietto() {
        Biglietto newBiglietto = new Biglietto(true, LocalDate.now());
        Utente nuovoUtente = new Utente(newBiglietto);
        UtenteDAO utenteDAO = new UtenteDAOImpl();
        utenteDAO.aggiungiUtente(nuovoUtente);
    }
}
