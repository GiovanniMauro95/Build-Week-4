package main.Entities;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "distributori") // Nome della tabella nel database
public class Distributori extends EmettitoreBiglietti {

    @Id
    @Column(name = "idDistributore", nullable = false, unique = true)
    private UUID idDistributore;

    @Column(nullable = false)
    private boolean stato;

    @Column(name = "bigliettiVenduti", nullable = false)
    private int bigliettiVenduti;

    @Column(name = "abbonamentiVenduti", nullable = false)
    private int abbonamentiVenduti;

    @Column(name = "idDistributoreComposto", nullable = true, length = 255)
    private String idDistributoreComposto;

    // Costruttore vuoto richiesto da Hibernate
    public Distributori() {
    }

    // Costruttore con parametri (opzionale, per comodità)
    public Distributori(Abbonamento abbonamento, Biglietto biglietto, boolean stato, int bigliettiVenduti, int abbonamentiVenduti, String idDistributoreComposto) {
        super(abbonamento, biglietto);
        this.stato = stato;
        this.bigliettiVenduti = bigliettiVenduti;
        this.abbonamentiVenduti = abbonamentiVenduti;
        this.idDistributoreComposto = idDistributoreComposto;
    }

    // Getter e Setter
    public UUID getIdDistributore() {
        return idDistributore;
    }

    public void setIdDistributore(UUID idDistributore) {
        this.idDistributore = idDistributore;
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

    public String getIdDistributoreComposto() {
        return idDistributoreComposto;
    }

    public void setIdDistributoreComposto(String idDistributoreComposto) {
        this.idDistributoreComposto = idDistributoreComposto;
    }
}
