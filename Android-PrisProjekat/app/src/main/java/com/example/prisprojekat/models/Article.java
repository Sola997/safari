package com.example.prisprojekat.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Article {
    public Article(){

    }

    @Expose
    @SerializedName("idPredmet")
    private int idPredmet;

    @Expose
    @SerializedName("nazivPredmeta")
    private String nazivPredmeta;

    @Expose
    @SerializedName("opis")
    private String opis;


    @Expose
    @SerializedName("pocetnaCena")
    private int pocetnaCena;

    @Expose
    @SerializedName("status")
    private byte status;

    @Expose
    @SerializedName("kategorija")
    private Category kategorija;

    @Expose
    @SerializedName("korisnik")
    private User korisnik;


    private List<Picture> pictures;

    public int getIdPredmet() {
        return idPredmet;
    }

    public void setIdPredmet(int idPredmet) {
        this.idPredmet = idPredmet;
    }


    public String getNazivPredmeta() {
        return nazivPredmeta;
    }

    public void setNazivPredmeta(String nazivPredmeta) {
        this.nazivPredmeta = nazivPredmeta;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getPocetnaCena() {
        return pocetnaCena;
    }

    public void setPocetnaCena(int pocetnaCena) {
        this.pocetnaCena = pocetnaCena;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Category getKategorija() {
        return kategorija;
    }

    public void setKategorija(Category kategorija) {
        this.kategorija = kategorija;
    }

    public User getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(User korisnik) {
        this.korisnik = korisnik;
    }

    public List<Picture> getPicture() {
        return pictures;
    }

    public void setPicture(List<Picture> pictures) {
        this.pictures = pictures;
    }
}
