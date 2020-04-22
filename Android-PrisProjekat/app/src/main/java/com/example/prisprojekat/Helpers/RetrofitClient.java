package com.example.prisprojekat.Helpers;

import com.example.prisprojekat.models.User;

import java.security.cert.CertificateException;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private Retrofit retrofit;
    private SafariService safariService;



    private OkHttpClient client;

    private String username;
    private String password;

    final static String url="http://192.168.0.13:27182";

    private static RetrofitClient retrofitClient=null;

    private RetrofitClient(){
        this.retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build();
        this.safariService=retrofit.create(SafariService.class);
    }

    public void setUsernamePassword(String username,String password){
        this.username=username;
        this.password=password;
    }
    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }


    public static RetrofitClient getInstance(){
        if(retrofitClient==null){
            retrofitClient=new RetrofitClient();
        }
        return retrofitClient;
    }

    public SafariService getSafariService(){
        return safariService;
    }





}
