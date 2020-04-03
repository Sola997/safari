package com.example.prisprojekat;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


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
                if(username.getText().toString().equalsIgnoreCase("")){
                    username.setError("cannot be empty");
                    return ;
                }
                if(password.getText().toString().equalsIgnoreCase("")){
                    password.setError("cannot be empty");
                    return;
                }
                Toast.makeText(getContext(),"Uspesno logovanje!", Toast.LENGTH_LONG);
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

}
