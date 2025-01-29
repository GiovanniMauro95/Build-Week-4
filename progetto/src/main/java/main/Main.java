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
        System.out.print("Scelta : ");
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
        System.out.println("\nScegli tra:\n1. Distributore Automatico\n2. Rivenditore Autorizzato");
        System.out.print("Scelta : ");
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

        System.out.println('\n' + "Lista Distributori : " + '\n' + listDistributori);

        System.out.println("\nScegli il distributore :");
        System.out.print("Scelta : ");
        int scelta = scanner.nextInt();

        Distributori distributoreScelto = distributoriDAO.getDistributore(scelta);

        System.out.println("\nDistributore scelto dall'utente : " + distributoreScelto);

        System.out.println("\nSe sei registrato premi '1', altrimenti premi '2':");
        System.out.print("Scelta : ");
        scelta = scanner.nextInt();

        if (scelta >= 0) {

            if (scelta == 1) {
                gestisciUtenteRegistrato(scanner);
            } else {
                gestisciNuovoUtente(scanner);
            }

        } else {
            System.out.println("Opzione non valida !");
            return;
        }

    }

    private static void gestisciUtenteRegistrato(Scanner scanner) {
        System.out.println("\nRicerca i tuoi dati : ");

        UtenteDAO utenteDAO = new UtenteDAOImpl();

        scanner.nextLine();

        System.out.print("Inserisci Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Inserisci Cognome: ");
        String cognome = scanner.nextLine();
        System.out.print("Inserisci Eta': ");
        int eta = scanner.nextInt();

        Utente utenteRegistrato = utenteDAO.trovaUtente(nome, cognome, eta);

        System.out.println("\nI tuoi dati : " + '\n' + utenteRegistrato);

        System.out.println('\n' + "Modifica abbonamento: ");

        if (utenteRegistrato.getAbbonamento().getTipologia().equals("Mensile")) {
            System.out.println("Il tuo abbonamento e' già il massimo estendibile !");
            utenteRegistrato.getAbbonamento().setDataEmissioneMensile(LocalDate.now());
            System.out.println("Scadra' giorno : " + utenteRegistrato.getAbbonamento().getScadenza());
            utenteDAO.aggiornaUtente(utenteRegistrato);
        } else {
            System.out.println("Se vuoi estendere a Mensile il tuo abbonamento premi '1'");
            System.out.print("Scelta : ");
            int scelta = scanner.nextInt();

            if (scelta == 1) {
                System.out.println("Abbonamento Esteso !");
                utenteRegistrato.getAbbonamento().setTipologia("Mensile");
                utenteRegistrato.getAbbonamento().setDataEmissioneMensile(LocalDate.now());
                utenteDAO.aggiornaUtente(utenteRegistrato);

            } else {
                System.out.println("Errore di inserimento");
                return;
            }
        }

    }

    private static void gestisciNuovoUtente(Scanner scanner) {
        System.out.println("\nSe vuoi un abbonamento premi '1', Se vuoi solo comprare un biglietto premi '2'");
        System.out.print("Scelta : ");
        int scelta = scanner.nextInt();

        if (scelta == 1) {
            registraNuovoUtente(scanner);
        } else {
            registraNuovoUtenteSenzaTessera(scanner);
        }
    }

    private static void creaAbbonamento(Scanner scanner, Utente utente) {

        System.out.println("Premi '1' per un settimanale, '2' per un mensile:");
        System.out.print("Scelta : ");
        int scelta = scanner.nextInt();

        Abbonamento newAbbonamento = new Abbonamento("Settimanale", true,
                LocalDate.now(), LocalDate.now().plusDays(7));
        if (scelta == 2) {
            newAbbonamento.setTipologia("Mensile");
            newAbbonamento.setScadenza(LocalDate.now().plusDays(30));
        }

        utente.setAbbonamento(newAbbonamento);

    }

    private static void registraNuovoUtente(Scanner scanner) {
        scanner.nextLine();
        System.out.println("\nInserisci i tuoi dati : ");
        Tessera nuovaTessera = new Tessera(true, LocalDate.now());

        System.out.print("Inserisci Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Inserisci Cognome: ");
        String cognome = scanner.nextLine();
        System.out.print("Inserisci Eta': ");
        int eta = scanner.nextInt();

        Utente nuovoUtente = new Utente(nome, cognome, eta, nuovaTessera);

        creaAbbonamento(scanner, nuovoUtente);

        UtenteDAO utenteDAO = new UtenteDAOImpl();
        utenteDAO.aggiungiUtente(nuovoUtente);
    }

    private static void registraNuovoUtenteSenzaTessera(Scanner scanner) {
        scanner.nextLine();
        System.out.println("\nInserisci i tuoi dati : ");

        System.out.print("Inserisci Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Inserisci Cognome: ");
        String cognome = scanner.nextLine();
        System.out.print("Inserisci Eta': ");
        int eta = scanner.nextInt();

        Biglietto newBiglietto = acquistaBiglietto();

        Utente nuovoUtente = new Utente(nome, cognome, eta, newBiglietto);

        UtenteDAO utenteDAO = new UtenteDAOImpl();
        utenteDAO.aggiungiUtente(nuovoUtente);
    }

    private static Biglietto acquistaBiglietto() {
        Biglietto newBiglietto = new Biglietto(true, LocalDate.now());
        return newBiglietto;
    }
}
