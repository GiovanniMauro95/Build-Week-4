package main.Entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "Tessere")
public class Tessera {

    @Id
    private UUID idTessera;

    private boolean stato;

    private LocalDate dataEmissione;

    private LocalDate scadenza;

    // Costruttore vuoto richiesto da Hibernate
    public Tessera() {
    }

    // Costruttore con parametri
    public Tessera(boolean stato, LocalDate dataEmissione, LocalDate scadenza) {
        this.stato = stato;
        this.dataEmissione = dataEmissione;
        this.scadenza = scadenza;
    }

    // Getter e Setter
    public UUID getIdTessera() {
        return idTessera;
    }

    public void setIdTessera(UUID idTessera) {
        this.idTessera = idTessera;
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
