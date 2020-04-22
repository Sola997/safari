package com.example.prisprojekat.Helpers;

import com.example.prisprojekat.models.Article;
import com.example.prisprojekat.models.Bid;
import com.example.prisprojekat.models.Category;
import com.example.prisprojekat.models.Evaluation;
import com.example.prisprojekat.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SafariService {
    @POST("login")
    Call<Boolean> login(@Query("username") String username, @Query("password")String password);

    @GET("security/getCurrentUser")
    Call<User> getCurrentUser(@Header("Authorization")String credentials);

    @POST("unauth/register")
    Call<User> createUser(@Body User user);

    @GET("auth/getPredmetsByKategorija")
    Call<List<Article>> getPredmetsByKategorija(@Header("Authorization")String credentials,@Query("idKategorija")int idKategorija);

    @GET("auth/getAllKategorije")
    Call<List<Category>> getAllKategorije(@Header("Authorization")String credentials);

    @POST("auth/savePredmet")
    Call<Article> savePredmet(@Header("Authorization")String credentials,@Body Article article, @Query("idKategorija")int idKategorija);

    @POST("auth/saveLicitacija")
    Call<Bid> saveLicitacija(@Header("Authorization")String credentials,@Query("ponuda")int ponuda,@Query("idPredmet") int idPredmet);

    @POST("auth/saveKomnetarIOcenu")
    Call<Evaluation> saveKomnetarIOcenu(@Header("Authorization")String credentials,@Query("ocena") int ocena, @Query("komentar")String komentar);

    @GET("auth/getUserByUserName")
    Call<User> getUserByUsername(@Header("Authorization")String credentials, @Query("userName")String username);
}
