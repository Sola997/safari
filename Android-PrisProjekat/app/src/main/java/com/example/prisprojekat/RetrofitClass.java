package com.example.prisprojekat;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClass {
    private Retrofit retrofit;
    private AukcijskaKucaServices aukcijskaKucaServices;

    public RetrofitClass(){
        this.retrofit=new Retrofit.Builder()
                .baseUrl("url")
                .addConverterFactory(GsonConverterFactory.create()).build();
        aukcijskaKucaServices=retrofit.create(AukcijskaKucaServices.class);

    }
    public void test(){



    }

}
