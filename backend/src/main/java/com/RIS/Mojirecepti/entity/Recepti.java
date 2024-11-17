package com.RIS.Mojirecepti.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Recepti")
public class Recepti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRecepti")
    private int idRecepti;

    @Column(name = "naziv", nullable = false, length = 40)
    private String naziv;

    @Column(name = "slika", nullable = false, length = 100)
    private String slika;

    @Column(name = "sestavine", nullable = false, columnDefinition = "TEXT")
    private String sestavine;

    @Column(name = "tip", nullable = false)
    @Enumerated(EnumType.STRING)
    private Tip tip;

    @Column(name = "opis", nullable = false, columnDefinition = "TEXT")
    private String opis;

    public enum Tip {
        zajtrk, kosilo, večerja
    }

    // Default constructor
    public Recepti() {}

    // Parameterized constructor
    public Recepti(String naziv, String slika, String sestavine, Tip tip, String opis) {
        this.naziv = naziv;
        this.slika = slika;
        this.sestavine = sestavine;
        this.tip = tip;
        this.opis = opis;
    }

    // Getters and setters
    public int getIdRecepti() {
        return idRecepti;
    }

    public void setIdRecepti(int idRecepti) {
        this.idRecepti = idRecepti;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String getSestavine() {
        return sestavine;
    }

    public void setSestavine(String sestavine) {
        this.sestavine = sestavine;
    }

    public Tip getTip() {
        return tip;
    }

    public void setTip(Tip tip) {
        this.tip = tip;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return "Recepti{" +
                "idRecepti=" + idRecepti +
                ", naziv='" + naziv + '\'' +
                ", slika='" + slika + '\'' +
                ", sestavine='" + sestavine + '\'' +
                ", tip=" + tip +
                ", opis='" + opis + '\'' +
                '}';
    }
}