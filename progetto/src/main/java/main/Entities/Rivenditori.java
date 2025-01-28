package main.Entities;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Rivenditori") // Nome della tabella nel database
public class Rivenditori extends EmettitoreBiglietti {

    @Id
    @Column(name = "idRivenditore", nullable = false, unique = true)
    private UUID idRivenditore;

    @Column(nullable = false)
    private boolean stato;

    @Column(name = "bigliettiVenduti", nullable = false)
    private int bigliettiVenduti;

    @Column(name = "abbonamentiVenduti", nullable = false)
    private int abbonamentiVenduti;

    @Column(name = "idRivenditoreComposto", nullable = true, length = 255)
    private String idRivenditoreComposto;

    // Costruttore vuoto richiesto da Hibernate
    public Rivenditori() {
    }

    // Costruttore con parametri (opzionale, per comodità)
    public Rivenditori(Abbonamento abbonamento, Biglietto biglietto, boolean stato, int bigliettiVenduti, int abbonamentiVenduti, String idRivenditoreComposto) {
        super(abbonamento, biglietto);
        this.stato = stato;
        this.bigliettiVenduti = bigliettiVenduti;
        this.abbonamentiVenduti = abbonamentiVenduti;
        this.idRivenditoreComposto = idRivenditoreComposto;
    }

    // Getter e Setter
    public UUID getIdRivenditore() {
        return idRivenditore;
    }

    public void setIdRivenditore(UUID idRivenditore) {
        this.idRivenditore = idRivenditore;
    }

    public boolean isStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }

    public int getBigliettiVenduti() {
        return bigliettiVenduti;
    }

    public void setBigliettiVenduti(int bigliettiVenduti) {
        this.bigliettiVenduti = bigliettiVenduti;
    }

    public int getAbbonamentiVenduti() {
        return abbonamentiVenduti;
    }

    public void setAbbonamentiVenduti(int abbonamentiVenduti) {
        this.abbonamentiVenduti = abbonamentiVenduti;
    }

    public String getIdRivenditoreComposto() {
        return idRivenditoreComposto;
    }

    public void setIdRivenditoreComposto(String idRivenditoreComposto) {
        this.idRivenditoreComposto = idRivenditoreComposto;
    }
}
