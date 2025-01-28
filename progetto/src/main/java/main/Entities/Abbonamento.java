package main.Entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
public class Abbonamento {

    @Id
    private UUID idAbbonamento;

    @Column(nullable = false)
    private String tipologia;

    @Column(nullable = false)
    private boolean stato;

    @Column(nullable = false)
    private LocalDate dataEmissione;

    @Column(nullable = false)
    private LocalDate scadenza;

    // Costruttore vuoto richiesto da Hibernate
    public Abbonamento() {}

    // Costruttore con parametri
    public Abbonamento(String tipologia, boolean stato, LocalDate dataEmissione, LocalDate scadenza) {
        this.tipologia = tipologia;
        this.stato = stato;
        this.dataEmissione = dataEmissione;
        this.scadenza = scadenza;
    }

    // Getter e Setter
    public UUID getIdBiglietto() {
        return idAbbonamento;
    }

    public void setIdBiglietto(UUID idAbbonamento) {
        this.idAbbonamento = idAbbonamento;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public boolean isStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    public LocalDate getScadenza() {
        return scadenza;
    }

    public void setScadenza(LocalDate scadenza) {
        this.scadenza = scadenza;
    }
}
