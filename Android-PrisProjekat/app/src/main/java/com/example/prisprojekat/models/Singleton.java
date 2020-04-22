package com.example.prisprojekat.models;

public class Singleton {

    private User user;

    private static Singleton instance;


    private Singleton(){

    }
    public static Singleton getInstance(){
        if(instance==null){
            instance=new Singleton();
        }
        return instance;
    }
    public void setUser(User user){
        this.user=user;
    }
    public User getUser(){
        return user;
    }
}
