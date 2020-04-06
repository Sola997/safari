package com.example.prisprojekat;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AukcijskaKucaServices {


    @GET("auth/getAllKorisnik")
    Call<List<User>> getAllKorisnik();

}
