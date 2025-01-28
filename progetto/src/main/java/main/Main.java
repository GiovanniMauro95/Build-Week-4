package main;

import main.DAO.UtenteDAOImpl;
import main.Entities.Biglietto;
import main.Entities.Tessera;
import main.Entities.Utente;

import java.time.LocalDate;
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
                gestisciAdmin(scanner);
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
            case 1: // Distributore Automatico
                gestisciDistributore(scanner);
                break;

            case 2: // Rivenditore Autorizzato
                System.out.println("Funzionalità Rivenditore non ancora implementata.");
                break;

            default:
                System.out.println("Scelta non valida. Riprova.");
        }
    }

    private static void gestisciDistributore(Scanner scanner) {
        System.out.println("Se sei registrato premi '1', altrimenti premi '2':");
        int scelta = scanner.nextInt();

        if (scelta == 1) {
            System.out.println("Utente registrato. Funzionalità in sviluppo.");
        } else {
            System.out.println("Scegli tra:\n1. Creare un abbonamento\n2. Acquistare un biglietto");
            scelta = scanner.nextInt();

            if (scelta == 1) {
                creaAbbonamento(scanner);
            } else {
                creaBiglietto();
            }
        }
    }

    private static void creaAbbonamento(Scanner scanner) {
        scanner.nextLine(); // Pulizia buffer
        System.out.println("Registrazione nuovo utente. Inserisci i tuoi dati:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Cognome: ");
        String cognome = scanner.nextLine();
        System.out.print("Età: ");
        int eta = scanner.nextInt();

        // Creazione tessera e utente
        Tessera tessera = new Tessera(true, LocalDate.now());
        Utente utente = new Utente(nome, cognome, eta, tessera);

        // Simulazione salvataggio
        UtenteDAOImpl utenteDAO = new UtenteDAOImpl();
        utenteDAO.aggiungiUtente(utente);

        System.out.println("Abbonamento creato con successo per: " + utente);
    }

    private static void creaBiglietto() {
        Biglietto biglietto = new Biglietto(true, LocalDate.now());
        System.out.println("Biglietto emesso con successo:");
        System.out.println(biglietto);
    }

    private static void gestisciAdmin(Scanner scanner) {
        System.out.println("Accesso Admin. Seleziona un'opzione:");
        System.out.println("1. Calcola statistiche\n2. Gestione parco mezzi");
        int scelta = scanner.nextInt();

        switch (scelta) {
            case 1:
                System.out.println("Funzionalità di calcolo statistiche in sviluppo.");
                break;

            case 2:
                System.out.println("Funzionalità di gestione parco mezzi in sviluppo.");
                break;

            default:
                System.out.println("Scelta non valida. Riprova.");
        }
    }

}
