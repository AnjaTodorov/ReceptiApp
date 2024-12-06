package com.RIS.Mojirecepti.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Sestavine")
public class Sestavine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idSestavina")
    private int idSestavina;

    @Column(name = "naziv", nullable = false, length = 50)
    private String naziv;

    @Column(name = "kolicina", nullable = false, precision = 10, scale = 2)
    private BigDecimal kolicina;
    public enum Enota {
        CAJNA_ZLICKA, SKODELICA, ZLICA, G, KG, KOS, L, ML
    }


    @Enumerated(EnumType.STRING)
    @Column(name = "enota", nullable = false, length = 20)
    private Enota enota;

    @ManyToOne
    @JoinColumn(name = "TK_Recepti", nullable = false)
    private Recepti recepti;

    // Default constructor
    public Sestavine() {}

    // Parameterized constructor
    public Sestavine(String naziv, BigDecimal kolicina, Enota enota, Recepti recepti) {
        this.naziv = naziv;
        this.kolicina = kolicina;
        this.enota = enota;
        this.recepti = recepti;
    }

    // Getters and setters
    public int getIdSestavina() {
        return idSestavina;
    }

    public void setIdSestavina(int idSestavina) {
        this.idSestavina = idSestavina;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public BigDecimal getKolicina() {
        return kolicina;
    }

    public void setKolicina(BigDecimal kolicina) {
        this.kolicina = kolicina;
    }

    public Enota getEnota() {
        return enota;
    }

    public void setEnota(Enota enota) {
        this.enota = enota;
    }

    public Recepti getRecepti() {
        return recepti;
    }

    public void setRecepti(Recepti recepti) {
        this.recepti = recepti;
    }

    @Override
    public String toString() {
        return "Sestavine{" +
                "idSestavina=" + idSestavina +
                ", naziv='" + naziv + '\'' +
                ", kolicina=" + kolicina +
                ", enota=" + enota +
                ", recepti=" + recepti +
                '}';
    }
}
