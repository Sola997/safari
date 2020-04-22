package com.example.prisprojekat.fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.prisprojekat.Helpers.RetrofitClient;
import com.example.prisprojekat.R;
import com.example.prisprojekat.models.Article;
import com.example.prisprojekat.models.Category;

import java.util.List;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewArticleFragment extends Fragment {

    private EditText  nazivPredmeta;

    private EditText opisPredmeta;

    private EditText pocetnaCena;

    private Spinner spinner;

    private Button addArticle;

    private Button imageChooser;

    private List<Category> kategorije;
    private String[] kategorijeNiz;

    public NewArticleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_new_article, container, false);
        inicializeComponent(v);
        String username=RetrofitClient.getInstance().getUsername();
        String password=RetrofitClient.getInstance().getPassword();
        Call<List<Category>> call= RetrofitClient.getInstance().getSafariService().getAllKategorije(Credentials.basic(username,password));
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if(response.isSuccessful()){
                    Log.d("stiglo smo ovde",Integer.toString(response.body().size()));
                    kategorije=response.body();
                    kategorijeNiz=new String[kategorije.size()];
                    for(int i =0;i<kategorije.size();i++){
                        kategorijeNiz[i]=kategorije.get(i).getNazivKategorije();
                    }
                    ArrayAdapter<String> a=new ArrayAdapter(getContext(),R.layout.support_simple_spinner_dropdown_item,kategorijeNiz);
                    spinner.setAdapter(a);
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {

            }
        });
        setUpComponent();
        return v;
    }

    private void setUpComponent() {
        imageChooser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                startActivityForResult(gallery,100);
            }
        });
        addArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nazivPredmeta.getText().length()==0){
                    nazivPredmeta.setError("cannot be empty");
                    return;
                }
                if(opisPredmeta.getText().length()==0){
                    opisPredmeta.setError("cannot be empty");
                }
                if(pocetnaCena.getText().length()==0){
                    pocetnaCena.setError("cannot be empty");
                }
               // if(!spinner.isSelected()){
                  //  return;
              //  }
                Article article=new Article();
                article.setNazivPredmeta(nazivPredmeta.getText().toString());
                article.setOpis(opisPredmeta.getText().toString());
                Log.d("cena",pocetnaCena.getText().toString());
               // article.setPocetnaCena(Integer.parseInt(pocetnaCena.getText().toString()));
                int index=spinner.getSelectedItemPosition();
                article.setKategorija(kategorije.get(index));
                String username=RetrofitClient.getInstance().getUsername();
                String password=RetrofitClient.getInstance().getPassword();
                Log.d("artikli","artikli");
                Call<Article> call=RetrofitClient.getInstance().getSafariService().savePredmet(Credentials.basic(username,password),article,kategorije.get(index).getIdKategorija());
                call.enqueue(new Callback<Article>() {
                    @Override
                    public void onResponse(Call<Article> call, Response<Article> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getContext(),"Uspesno ubacen predmet!",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Article> call, Throwable t) {
                        Toast.makeText(getContext(),"neuspesno!",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK&& requestCode ==100){
            if(data!=null) {
                Bitmap photo;
                Uri selectedImage = data.getData();
                try {
                    photo = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImage);
                    imageChooser.setVisibility(View.GONE);
                } catch (Exception e) {
                }
            }

        }

    }

    private void inicializeComponent(View v) {
        nazivPredmeta=v.findViewById(R.id.new_article_name);
        opisPredmeta=v.findViewById(R.id.new_article_description);
        pocetnaCena=v.findViewById(R.id.new_article_description);
        spinner=v.findViewById(R.id.new_article_spinner);
        addArticle=v.findViewById(R.id.new_article_add_new_article);
        imageChooser=v.findViewById(R.id.new_article_image_chooser);

    }

}
