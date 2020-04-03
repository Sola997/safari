package com.example.prisprojekat;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


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

        registry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean b=false;
                if(name.getText().toString().equalsIgnoreCase(" ")){
                    name.setError("can not be empty");
                    return;
                }
                if(lastName.getText().toString().equalsIgnoreCase(" ")){
                    name.setError("can not be empty");
                    return;
                }
                if(username.getText().toString().equalsIgnoreCase(" ")){
                    name.setError("can not be empty");
                    return;
                }
                if(password.getText().toString().equalsIgnoreCase(" ")){
                    name.setError("can not be empty");
                    return;
                }
                if(password2.getText().toString().equalsIgnoreCase(" ")){
                    name.setError("can not be empty");
                   return;
                }
                if(email.getText().toString().equalsIgnoreCase(" ")){
                    name.setError("can not be empty");
                    return;
                }
                if(!password.getText().toString().equalsIgnoreCase(password2.getText().toString())){
                    password2.setError("does not match ");
                    return;
                }
                User u=new User();
                u.setName(name.getText().toString());
                u.setLastName(lastName.getText().toString());
                u.setUsername(username.getText().toString());
                u.setPassword(password.getText().toString());
                u.setEmail(email.getText().toString());
            }
        });
        return v;
    }

}
