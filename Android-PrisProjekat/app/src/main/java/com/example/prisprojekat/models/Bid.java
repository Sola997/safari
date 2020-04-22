package com.example.prisprojekat.models;

public class Bid {

    private byte pobedio;

    private int ponuda;

    private User user;

    private Article article;

    public byte getPobedio() {
        return pobedio;
    }

    public void setPobedio(byte pobedio) {
        this.pobedio = pobedio;
    }

    public int getPonuda() {
        return ponuda;
    }

    public void setPonuda(int ponuda) {
        this.ponuda = ponuda;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
