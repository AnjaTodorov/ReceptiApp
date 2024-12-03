package com.RIS.Mojirecepti.entity;

import jakarta.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "Nacrt_obrokov")

public class NacrtObrokov {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idNacrt_obrokov")
    private int idNacrtObrokov;

    @Column(name = "datum", nullable = false, columnDefinition = "DATE")
    private LocalDate datum;

    // Default constructor
    public NacrtObrokov() {}

    // Parameterized constructor
    public NacrtObrokov(LocalDate datum) {
        this.datum = datum;
    }

    public NacrtObrokov(int idNacrtObrokov, LocalDate datum) {
        this.idNacrtObrokov = idNacrtObrokov;
        this.datum = datum;
    }

    // Getters and setters
    public int getIdNacrtObrokov() {
        return idNacrtObrokov;
    }

    public void setIdNacrtObrokov(int idNacrtObrokov) {
        this.idNacrtObrokov = idNacrtObrokov;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public void setDatum(LocalDate datum) {
        this.datum = datum;
    }

    @Override
    public String toString() {
        return "NacrtObrokov{" +
                "idNacrtObrokov=" + idNacrtObrokov +
                ", datum=" + datum +
                '}';
    }


}