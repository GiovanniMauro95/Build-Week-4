package main.Entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Stato_Mezzo")
public class StatoMezzo {

    @Id
    @Column(name = "idStato", nullable = false, unique = true)
    private Long idStato;

    @Column(name = "stato", nullable = false)
    private boolean stato;

    @Column(name = "data_inizio", nullable = false)
    private LocalDate dataInizio;

    @Column(name = "data_fine", nullable = false)
    private LocalDate dataFine;

    // Costruttore vuoto richiesto da Hibernate
    public StatoMezzo() {
    }

    // Costruttore con parametri
    public StatoMezzo(boolean stato, LocalDate dataInizio, LocalDate dataFine) {
        this.stato = stato;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    // Getter e Setter
    public Long getIdStato() {
        return idStato;
    }

    public void setIdStato(Long idStato) {
        this.idStato = idStato;
    }

    public boolean isStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    @Override
    public String toString() {
        return "StatoMezzo{" +
                "idStato=" + idStato +
                ", stato=" + stato +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                '}';
    }
}
