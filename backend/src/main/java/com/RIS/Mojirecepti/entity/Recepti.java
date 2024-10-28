package com.RIS.Mojirecepti.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "Recepti")
public class Recepti {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idRecepti")
    private int idRecepti;

    @Column(name = "slika", nullable = false, length = 100)
    private String slika;

    @Column(name = "naziv", nullable = false, length = 100)
    private String naziv;

    @Column(name = "sestavine", nullable = false, columnDefinition = "TEXT")
    private String sestavine;

    @Column(name = "opis", nullable = false, columnDefinition = "TEXT")
    private String opis;

    // Default constructor
    public Recepti() {}

    // Parameterized constructor
    public Recepti(String slika, String naziv, String sestavine, String opis) {
        this.slika = slika;
        this.naziv = naziv;
        this.sestavine = sestavine;
        this.opis = opis;
    }

    // Getters and setters
    public int getIdRecepti() {
        return idRecepti;
    }

    public void setIdRecepti(int idRecepti) {
        this.idRecepti = idRecepti;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }
    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSestavine() {
        return sestavine;
    }

    public void setSestavine(String sestavine) {
        this.sestavine = sestavine;
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
                ", slika='" + slika + '\'' +
                ", naziv='" + naziv + '\'' +
                ", sestavine='" + sestavine + '\'' +
                ", opis='" + opis + '\'' +
                '}';
    }
}