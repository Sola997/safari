package com.example.prisprojekat.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.prisprojekat.Activities.MainActivity;
import com.example.prisprojekat.Helpers.MyAdapterSearching;
import com.example.prisprojekat.Helpers.RetrofitClient;
import com.example.prisprojekat.R;
import com.example.prisprojekat.models.Article;
import com.example.prisprojekat.models.Category;
import com.example.prisprojekat.models.Singleton;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import okhttp3.Credentials;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchingArticlesFragment extends Fragment {
    private RecyclerView recyclerView;
    private MyAdapterSearching adapter;
    private RecyclerView.LayoutManager layoutManager;
    private Spinner spinner;
    private String[] kategorijeNiz;
    private List<Category> kategorijaLista;

    public SearchingArticlesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_searching_articles, container, false);
        this.recyclerView=v.findViewById(R.id.searching_recycler_view);
        this.spinner=v.findViewById(R.id.searching_spinner);
        String username=RetrofitClient.getInstance().getUsername();
        String password=RetrofitClient.getInstance().getPassword();
        loadSpinnerItems(username,password);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                showArticles(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return v;
    }

    private void loadSpinnerItems(String username, String password) {
        Call<List<Category>> call=RetrofitClient.getInstance().getSafariService().getAllKategorije(Credentials.basic(username,password));
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {

                if(response.isSuccessful()){

                    kategorijaLista=response.body();
                    kategorijeNiz=new String[kategorijaLista.size()];
                    for(int i=0;i<kategorijaLista.size();i++){
                        kategorijeNiz[i]=kategorijaLista.get(i).getNazivKategorije();
                        ArrayAdapter<String> a=new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,kategorijeNiz);
                        spinner.setAdapter(a);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Log.d("filtriranje","filtriranje nije uspelo");
            }
        });

    }

    public void showArticles(int i){
        String username=RetrofitClient.getInstance().getUsername();
        String password=RetrofitClient.getInstance().getPassword();
        Call<List<Article>> call=RetrofitClient.getInstance().getSafariService().getPredmetsByKategorija(Credentials.basic(username,password), kategorijaLista.get(i).getIdKategorija());
        call.enqueue(new Callback<List<Article>>() {
            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                if(response.isSuccessful()){
                    List<Article> lista=response.body();
                    setUpRecyclerView(lista);
                }
            }

            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {

            }
        });



    }
    public void setUpRecyclerView(List<Article>lista){
        layoutManager=new LinearLayoutManager(getContext());
        adapter=new MyAdapterSearching(lista);
        adapter.setOnItemBidClickListener(new MyAdapterSearching.OnItemBidClickListener() {
            @Override
            public void onItemClick(int position) {
                Article article=lista.get(position);

                ((MainActivity)getActivity()).openFragmentToBidForArticle(article);
            }
        });
        adapter.setOnItemOwnerClickListener(new MyAdapterSearching.OnItemOwnerClickListener() {
            @Override
            public void onItemClick(int position) {
                Article article=lista.get(position);
                ((MainActivity)getActivity()).ChangeFragmentToOtherUser(article.getKorisnik().getUsername());

            }
        });

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
    }

}
