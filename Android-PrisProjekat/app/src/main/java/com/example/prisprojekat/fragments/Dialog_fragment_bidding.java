package com.example.prisprojekat.fragments;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prisprojekat.Helpers.RetrofitClient;
import com.example.prisprojekat.R;
import com.example.prisprojekat.models.Article;
import com.example.prisprojekat.models.Bid;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Dialog_fragment_bidding extends DialogFragment {

    private TextView name;
    private TextView currentPrice;
    private TextView startingPrice;
    private EditText editText;

    private Article article;


    public Dialog_fragment_bidding(Article article) {
        this.article = article;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        LayoutInflater inflater=requireActivity().getLayoutInflater();
        View v=inflater.inflate(R.layout.dialog_biding, null);

        name=v.findViewById(R.id.dialog_biding_name);
        currentPrice=v.findViewById(R.id.dialog_biding_current_highest_price);
        startingPrice=v.findViewById(R.id.dialog_biding_starting_price);
        editText=v.findViewById(R.id.dialog_biding_price);

        name.setText(name.getText().toString()+article.getNazivPredmeta());
        startingPrice.setText(startingPrice.getText().toString()+Integer.toString(article.getPocetnaCena()));
        builder.setView(v)
                .setPositiveButton("Make your bid!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(editText.getText().length()==0){
                            Toast.makeText(getContext(), "You need to enter your bid,try again", Toast.LENGTH_SHORT).show();

                        }
                        else{
                            int bid=Integer.parseInt(editText.getText().toString());
                            String username=RetrofitClient.getInstance().getUsername();
                            String password=RetrofitClient.getInstance().getPassword();
                            Call<Bid> call= RetrofitClient.getInstance().getSafariService().saveLicitacija(Credentials.basic(username,password),bid,article.getIdPredmet());
                            call.enqueue(new Callback<Bid>() {
                                @Override
                                public void onResponse(Call<Bid> call, Response<Bid> response) {
                                    Log.d("response",response.raw().toString());
                                    if(response.isSuccessful()){
                                        Log.d("uspeli smo","uspeli smo");
                                    }
                                }

                                @Override
                                public void onFailure(Call<Bid> call, Throwable t) {

                                }
                            });

                        }

                    }
                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Dialog_fragment_bidding.this.getDialog().cancel();
            }
        });




        return  builder.create();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_dialog_fragment_bidding, container, false);

        return v;
    }

}
