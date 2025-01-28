package main.Entities;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Tram")
public class Tram {

    @Id
    @Column(name = "codiceUnivoco", nullable = false, unique = true)
    private UUID codiceUnivoco;

    @Column(name = "capienza", nullable = false)
    private int capienza;

    @ManyToOne
    @JoinColumn(name = "statoId", nullable = false) // Collega statoId alla tabella Stato_Mezzo
    private StatoMezzo stato;

    // Costruttore vuoto richiesto da Hibernate
    public Tram() {
    }

    // Costruttore con parametri
    public Tram(int capienza, StatoMezzo stato) {
        this.capienza = capienza;
        this.stato = stato;
    }

    // Getter e Setter
    public UUID getCodiceUnivoco() {
        return codiceUnivoco;
    }

    public void setCodiceUnivoco(UUID codiceUnivoco) {
        this.codiceUnivoco = codiceUnivoco;
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public StatoMezzo getStato() {
        return stato;
    }

    public void setStato(StatoMezzo stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "Tram{" +
                "codiceUnivoco=" + codiceUnivoco +
                ", capienza=" + capienza +
                ", stato=" + stato +
                '}';
    }
}
