package com.example.prisprojekat.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.prisprojekat.Helpers.RetrofitClient;
import com.example.prisprojekat.R;
import com.example.prisprojekat.Activities.RegistryLoginActivity;
import com.example.prisprojekat.models.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistryFragment extends Fragment {
    private EditText name;
    private EditText lastName;
    private EditText username;
    private EditText password;
    private EditText password2;
    private EditText email;

    private Button registry;
    private Button goToLogin;


    public RegistryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_registry, container, false);
        name=v.findViewById(R.id.registry_name);
        lastName=v.findViewById(R.id.registry_last_name);
        username=v.findViewById(R.id.registry_username);
        password=v.findViewById(R.id.registry_password);
        password2=v.findViewById(R.id.registry_password2);
        email=v.findViewById(R.id.registry_email);
        registry=v.findViewById(R.id.registry_button);
        goToLogin=v.findViewById(R.id.registry_go_to_login);

        registry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().length()==0){
                    name.setError("can not be empty");
                }
                if(lastName.getText().length()==0){
                    lastName.setError("can not be empty");
                }
                if(username.getText().length()==0){
                    username.setError("can not be empty");
                }
                if(password.getText().length()==0){
                    password.setError("can not be empty");
                }
                if(password2.getText().length()==0){
                   password2.setError("can not be empty");
                }
                if(email.getText().length()==0){
                    email.setError("can not be empty");
                }

                if(!password.getText().toString().equalsIgnoreCase(password2.getText().toString())){
                    password2.setError("does not match ");
                    return;
                }
                if(!email.getText().toString().contains("@")){
                    email.setError("email must contain @");
                }
                if(!(email.getText().toString().contains("gmail")||email.getText().toString().contains("hotmail"))){
                    email.setError("email must contain gmail or hotmail");
                    return;
                }
                User u=new User();
                u.setName(name.getText().toString());
                u.setLastName(lastName.getText().toString());
                u.setUsername(username.getText().toString());
                u.setPassword(password.getText().toString());
                u.setEmail(email.getText().toString());
                Call<User> call=RetrofitClient.getInstance().getSafariService().createUser(u);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getActivity(),"Uspesno ste se registrovali!",Toast.LENGTH_LONG);
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            ((RegistryLoginActivity)getActivity()).changeToLogin();

                        }
                        else{
                            Toast.makeText(getContext(),"Nesto je poslo po zlu, probajte opet",Toast.LENGTH_LONG);
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("greskaaaaaaaa",t.toString());
                    }
                });
            }
        });

        goToLogin.setOnClickListener(view -> {
            ((RegistryLoginActivity)getActivity()).changeToLogin();
        });
        return v;
    }

}
