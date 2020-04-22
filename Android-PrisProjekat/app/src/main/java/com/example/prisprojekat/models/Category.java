package com.example.prisprojekat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {
    @Expose
    @SerializedName("idKategorija")
    private int idKategorija;

    @Expose
    @SerializedName("nazivKategorije")
    private String nazivKategorije;

    public int getIdKategorija() {
        return idKategorija;
    }

    public void setIdKategorija(int idKategorija) {
        this.idKategorija = idKategorija;
    }



    public String getNazivKategorije() {
        return nazivKategorije;
    }

    public void setNazivKategorije(String nazivKategorije) {
        this.nazivKategorije = nazivKategorije;
    }

    public Category(String nazivKategorije) {
        this.nazivKategorije = nazivKategorije;
    }
}
