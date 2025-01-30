package main;

import main.DAO.AutobusDAOImpl;
import main.DAO.CorseDAOImpl;
import main.DAO.DistributoriDAOImpl;
import main.DAO.RivenditoriDAOImpl;
import main.DAO.TramDAOImpl;
import main.DAO.TrattaDAOImpl;
import main.DAO.UtenteDAOImpl;
import main.DAO.Interfaces.AutobusDAO;
import main.DAO.Interfaces.CorseDAO;
import main.DAO.Interfaces.DistributoriDAO;
import main.DAO.Interfaces.RivenditoriDAO;
import main.DAO.Interfaces.TramDAO;
import main.DAO.Interfaces.TrattaDAO;
import main.DAO.Interfaces.UtenteDAO;
import main.Entities.Abbonamento;
import main.Entities.Autobus;
import main.Entities.Biglietto;
import main.Entities.Corsa;
import main.Entities.Distributori;
import main.Entities.Rivenditori;
import main.Entities.StatoMezzo;
import main.Entities.Tessera;
import main.Entities.Tram;
import main.Entities.Tratta;
import main.Entities.Utente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.UUID;

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
                gestisciAdmin(scanner);
                break;
            default:
                System.out.println("Scelta non valida. Riprova.");
        }
        scanner.close();
    }

    private static void gestisciAdmin(Scanner scanner) {
        System.out.println(
                "\nScegli tra: \n1. Se vuoi aggiungere un distributore\n2. Se vuoi aggiungere un rivenditore");
        System.out.print("Scelta : ");
        int scelta = scanner.nextInt();

        switch (scelta) {
            case 1:
                aggiungiDitributore();
                break;
            case 2:
                aggiungiRivenditore();
                break;
            default:
                System.out.println("Scelta non valida. Riprova.");
        }

        System.out.println(
                "\nScegli di creare tratte su Milano '1' \nScegli di creare tratte su Torino '2' \nScegli di creare tratte su Firenze '3' \nScegli di creare tratte su Roma '4'");
        System.out.print("Scelta : ");
        scelta = scanner.nextInt();
        String sceltaCitta;
        UUID idtratta;

        switch (scelta) {
            case 1:
                sceltaCitta = "Milano";
                idtratta = creatoreTratta(scanner, sceltaCitta);
                break;
            case 2:
                sceltaCitta = "Torino";
                idtratta = creatoreTratta(scanner, sceltaCitta);
                break;

            case 3:
                sceltaCitta = "Firenze";
                idtratta = creatoreTratta(scanner, sceltaCitta);
                break;

            case 4:
                sceltaCitta = "Roma";
                idtratta = creatoreTratta(scanner, sceltaCitta);
                break;

            default:
                System.out.println("Errore nella digitazione");
                return;
        }

        System.out.println("\nCrea un mezzo, scegli '1' per l'Autobus o scegli '2' per il Tram");
        System.out.print("Scelta : ");
        scelta = scanner.nextInt();

        switch (scelta) {
            case 1:
                creaAutobus(idtratta);
                break;
            case 2:
                creaTram(idtratta);
                break;
            default:
                System.out.println("Errore nella digitazione");
                return;
        }

    }

    private static UUID creatoreTratta(Scanner scanner, String sceltaCitta) {
        List<String> cittaPartenza = Arrays.asList("Bologna", "Napoli", "Venezia", "Palermo", "Ancona", "Bergamo",
                "Taranto", "Cosenza", "Perugia", "Messina");
        List<String> distanze = Arrays.asList("40", "60", "50", "90", "190", "260", "340", "600",
                "390", "280");

        Collections.shuffle(cittaPartenza);
        Collections.shuffle(distanze);

        List<Tratta> tratte = new ArrayList<>();
        Set<String> cittaUsate = new HashSet<>();

        int numeroRandom = (int) (Math.random() * cittaPartenza.size());
        UUID idTratta = UUID.randomUUID();
        for (int i = 0; i < cittaPartenza.size() - numeroRandom; i++) {
            String citta = cittaPartenza.get(i);
            if (!cittaUsate.contains(citta)) {
                Tratta tratta = new Tratta(idTratta, citta, sceltaCitta, distanze.get(i));
                tratte.add(tratta);
                cittaUsate.add(citta);
            }
        }

        TrattaDAO trattaDAO = new TrattaDAOImpl();

        for (Tratta tratta : tratte) {
            trattaDAO.aggiugiTratta(tratta);
        }

        return idTratta;

    }

    private static StatoMezzo creaStatoMezzo() {
        StatoMezzo newStatoMezzo = new StatoMezzo(true);
        return newStatoMezzo;
    }

    private static Corsa creaCorsa(UUID idTratta) {
        TrattaDAO TrattaDAO = new TrattaDAOImpl();
        List<Tratta> newTratta = TrattaDAO.getAllTratteWithID(idTratta);

        Corsa newCorsa = new Corsa(newTratta.get(0).getIdTratta());
        int tempoTotale = 0;

        for (Tratta tratta : newTratta) {
            tempoTotale += Integer.parseInt(tratta.getTempoDiPercorrenza());
        }

        newCorsa.setTempoDiPercorrenza(Integer.toString(tempoTotale));

        CorseDAO corsaSave = new CorseDAOImpl();
        corsaSave.aggiungiCorsa(newCorsa);

        return newCorsa;
    }

    private static void creaAutobus(UUID idTratta) {
        Autobus newAutobus = new Autobus(100, creaStatoMezzo(), creaCorsa(idTratta));
        AutobusDAO autobusDAO = new AutobusDAOImpl();
        autobusDAO.aggiungiAutobus(newAutobus);

        System.out.println(newAutobus);

    }

    private static void creaTram(UUID idTratta) {
        
        Tram newTram = new Tram(30, creaStatoMezzo(), creaCorsa(idTratta));
        TramDAO tramDAO = new TramDAOImpl();
        tramDAO.aggiungiTram(newTram);
        System.out.println(newTram);

    }

    private static void gestisciUtente(Scanner scanner) {
        System.out.println("\nScegli tra:\n1. Distributore Automatico\n2. Rivenditore Autorizzato");
        System.out.print("Scelta : ");
        int scelta = scanner.nextInt();

        switch (scelta) {
            case 1:
                gestisciSistema(scanner, scelta);
                break;
            case 2:
                gestisciSistema(scanner, scelta);
                break;
            default:
                System.out.println("Scelta non valida. Riprova.");
        }
    }

    private static void opzioneDistributore(Scanner scanner) {
        DistributoriDAO distributoriDAO = new DistributoriDAOImpl();
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

    }

    private static void opzioneRivenditori(Scanner scanner) {

        RivenditoriDAO rivenditoriDAO = new RivenditoriDAOImpl();
        List<Rivenditori> listRivenditori = rivenditoriDAO.getAllRivenditori();

        if (listRivenditori.isEmpty()) {
            System.out.println("Ci dispiace, ma puoi andare a piedi, tutta salute!");
            return;
        }

        System.out.println('\n' + "Lista Distributori : " + '\n' + listRivenditori);

        System.out.println("\nScegli il distributore :");
        System.out.print("Scelta : ");
        int scelta = scanner.nextInt();

        Rivenditori rivenditorescelto = rivenditoriDAO.getRivenditore(scelta);

        System.out.println("\nDistributore scelto dall'utente : " + rivenditorescelto);

    }

    private static void gestisciSistema(Scanner scanner, int opzioneUtente) {

        if (opzioneUtente == 1) {
            opzioneDistributore(scanner);
        } else {
            opzioneRivenditori(scanner);
        }

        System.out.println("\nSe sei registrato premi '1', altrimenti premi '2':");
        System.out.print("Scelta : ");
        int scelta = scanner.nextInt();

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

    public static void aggiungiDitributore() {
        Distributori newDistributore = new Distributori(true);
        DistributoriDAO distributoriDAO = new DistributoriDAOImpl();
        distributoriDAO.aggiungiDistributori(newDistributore);
    }

    public static void aggiungiRivenditore() {
        Rivenditori newRivenditore = new Rivenditori();
        RivenditoriDAO rivenditoriDAO = new RivenditoriDAOImpl();
        rivenditoriDAO.aggiungiRivenditori(newRivenditore);
    }

}
