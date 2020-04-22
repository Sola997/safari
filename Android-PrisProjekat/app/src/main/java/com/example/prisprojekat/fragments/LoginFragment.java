package com.example.prisprojekat.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prisprojekat.Activities.MainActivity;
import com.example.prisprojekat.Helpers.RetrofitClient;
import com.example.prisprojekat.R;
import com.example.prisprojekat.Activities.RegistryLoginActivity;
import com.example.prisprojekat.models.Singleton;
import com.example.prisprojekat.models.User;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private EditText username;
    private EditText password;
    private Button login;
    private Button registry;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_login, container, false);
        username=v.findViewById(R.id.login_username);
        password=v.findViewById(R.id.login_password);
        login=v.findViewById(R.id.login_button);
        registry=v.findViewById(R.id.login_go_to_registry);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(username.getText().toString().equalsIgnoreCase(" ")){
                    username.setError("cannot be empty");
                }
                if(password.getText().toString().equalsIgnoreCase(" ")){
                    password.setError("cannot be empty");
                    return;
                }

                String usernamtext=username.getText().toString();
                String passwordtext=password.getText().toString();

                Call<Boolean> call=RetrofitClient.getInstance().getSafariService().login(usernamtext,passwordtext);
                call.enqueue(new Callback<Boolean>() {
                    @Override
                    public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                        Log.d("evo me",response.toString());
                        if(response.isSuccessful()) {
                            if (response.body()==true) {
                                Toast.makeText(getContext(),"Logovanje uspelo",Toast.LENGTH_LONG).show();
                                loadUser(usernamtext, passwordtext);
                            }
                            else{
                                Toast.makeText(getContext(),"Logovanje nije uspelo, probajte opet",Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Boolean> call, Throwable t) {
                        Toast.makeText(getContext(),"nije uspelo nesto",Toast.LENGTH_LONG).show();
                        Log.d("greskica",t.toString());

                    }
                });

            }
        });
        registry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((RegistryLoginActivity)getActivity()).changeToRegistry();
            }
        });
        return v;
    }
    public void loadUser(String username,String password){
        User u=null;
        Call<User> call=RetrofitClient.getInstance().getSafariService().getCurrentUser(Credentials.basic(username,password));
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    RetrofitClient.getInstance().setUsernamePassword(username,password);
                    Log.d("Ovde smo","Ovde smo");
                    Singleton.getInstance().setUser(response.body());
                    Intent intent=new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });


    }

}
