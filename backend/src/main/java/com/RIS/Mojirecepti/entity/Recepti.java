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

    @Column(name = "tip", nullable = false)
    @Enumerated(EnumType.STRING)
    private Tip tip;

    @Column(name = "opis", nullable = false, columnDefinition = "TEXT")
    private String opis;

    @Column(name = "osebe", nullable = false)
    private int osebe; // New column for number of people

    public enum Tip {
        zajtrk, kosilo, večerja
    }

    // Default constructor
    public Recepti() {}

    // Parameterized constructor
    public Recepti(String naziv, String slika, Tip tip, String opis, int osebe) {
        this.naziv = naziv;
        this.slika = slika;
        this.tip = tip;
        this.opis = opis;
        this.osebe = osebe;
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

    public int getOsebe() {
        return osebe;
    }

    public void setOsebe(int osebe) {
        this.osebe = osebe;
    }

    @Override
    public String toString() {
        return "Recepti{" +
                "idRecepti=" + idRecepti +
                ", naziv='" + naziv + '\'' +
                ", slika='" + slika + '\'' +
                ", tip=" + tip +
                ", opis='" + opis + '\'' +
                ", osebe=" + osebe +
                '}';
    }
}
