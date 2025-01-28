package main.Entities;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "Tratta")
public class Tratta {

    @Id
    @Column(name = "idTratta", nullable = false, unique = true)
    private UUID idTratta;

    @Column(name = "capoLinea", nullable = false, length = 100)
    private String capoLinea;

    @Column(name = "zonaPartenza", nullable = false, length = 100)
    private String zonaPartenza;

    @Column(name = "tempoDiPercorrenza", nullable = false)
    private String tempoDiPercorrenza; 

    // Costruttore vuoto richiesto da Hibernate
    public Tratta() {}

    // Costruttore con parametri
    public Tratta(String capoLinea, String zonaPartenza, String tempoDiPercorrenza) {
        this.capoLinea = capoLinea;
        this.zonaPartenza = zonaPartenza;
        this.tempoDiPercorrenza = tempoDiPercorrenza;
    }

    // Getter e Setter
    public UUID getIdTratta() {
        return idTratta;
    }

    public void setIdTratta(UUID idTratta) {
        this.idTratta = idTratta;
    }

    public String getCapoLinea() {
        return capoLinea;
    }

    public void setCapoLinea(String capoLinea) {
        this.capoLinea = capoLinea;
    }

    public String getZonaPartenza() {
        return zonaPartenza;
    }

    public void setZonaPartenza(String zonaPartenza) {
        this.zonaPartenza = zonaPartenza;
    }

    public String getTempoDiPercorrenza() {
        return tempoDiPercorrenza;
    }

    public void setTempoDiPercorrenza(String tempoDiPercorrenza) {
        this.tempoDiPercorrenza = tempoDiPercorrenza;
    }

    @Override
    public String toString() {
        return "Tratta{" +
                "idTratta=" + idTratta +
                ", capoLinea='" + capoLinea + '\'' +
                ", zonaPartenza='" + zonaPartenza + '\'' +
                ", tempoDiPercorrenza='" + tempoDiPercorrenza + '\'' +
                '}';
    }
}
