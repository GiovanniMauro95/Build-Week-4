package main.Entities;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Utenti")
public class Utente {

    @Id

    private UUID idUtente;

    private String nome;

    private String cognome;

    private int eta;

    @OneToOne
    @JoinColumn(name = "tesseraID", nullable = false)
    private Tessera tessera;

    // Costruttore vuoto richiesto da Hibernate
    public Utente() {
    }

    // Costruttore con parametri
    public Utente(String nome, String cognome, int eta, Tessera tessera) {
        this.nome = nome;
        this.cognome = cognome;
        this.eta = eta;
        this.tessera = tessera;
    }

    // Getter e Setter
    public UUID getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(UUID idUtente) {
        this.idUtente = idUtente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }
}
