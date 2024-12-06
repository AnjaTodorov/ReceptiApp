package com.RIS.Mojirecepti.dto;

import java.math.BigDecimal;

public class SestavineRequest {

    private String naziv;
    private BigDecimal kolicina;
    private String enota;  // String to represent Enota
    private long idRecepti;

    // Getters and Setters
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

    public String getEnota() {
        return enota;
    }

    public void setEnota(String enota) {
        this.enota = enota;
    }

    public long getIdRecepti() {
        return idRecepti;
    }

    public void setIdRecepti(long idRecepti) {
        this.idRecepti = idRecepti;
    }
}
