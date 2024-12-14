package com.RIS.Mojirecepti.entity;



import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Hranilne_Vrednosti")
public class HranilneVrednosti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHranilne_vrednosti")
    private int idHranilneVrednosti;

    @Column(name = "naziv", nullable = false, length = 50)
    private String naziv;

    @Column(name = "kolicina", nullable = false, precision = 10, scale = 2)
    private BigDecimal kolicina;

    public enum Enota {
        G, MG, KJ
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "enota", nullable = false, length = 10)
    private Enota enota;

    @ManyToOne
    @JoinColumn(name = "TK_Recepti", nullable = false)
    private Recepti recepti;

    // Default constructor
    public HranilneVrednosti() {}

    // Parameterized constructor
    public HranilneVrednosti(String naziv, BigDecimal kolicina, Enota enota, Recepti recepti) {
        this.naziv = naziv;
        this.kolicina = kolicina;
        this.enota = enota;
        this.recepti = recepti;
    }

    // Getters and setters
    public int getIdHranilneVrednosti() {
        return idHranilneVrednosti;
    }

    public void setIdHranilneVrednosti(int idHranilneVrednosti) {
        this.idHranilneVrednosti = idHranilneVrednosti;
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
        return "HranilneVrednosti{" +
                "idHranilneVrednosti=" + idHranilneVrednosti +
                ", naziv='" + naziv + '\'' +
                ", kolicina=" + kolicina +
                ", enota=" + enota +
                ", recepti=" + recepti +
                '}';
    }
}
