package com.example.prisprojekat.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class Picture {

    @Expose
    @SerializedName("idSlika")
    private int idSlika;

    @Expose
    @SerializedName("ime")
    private String url;

    @Expose
    @SerializedName("putanja")
    private String ime;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getIdSlika() {
        return idSlika;
    }

    public void setIdSlika(int idSlika) {
        this.idSlika = idSlika;
    }

    public Picture() {
    }
}
