package com.example.prisprojekat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;


public class RegistryLoginActivity extends AppCompatActivity {

    private FragmentManager fm;
    private FragmentTransaction ft;
    private LoginFragment loginFragment=new LoginFragment();
    private RegistryFragment registryFragment=new RegistryFragment();

    private int trenutniFragment=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.registry_login_toolbar);
        setSupportActionBar(toolbar);
        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();
        ft.add(R.id.registry_login_frame_layout,loginFragment);
        ft.commit();
    }

    public void changeToLogin(){
        ft=fm.beginTransaction();
        ft.replace(R.id.registry_login_frame_layout,loginFragment);
        ft.commit();
    }
    public void changeToRegistry(){
        ft=fm.beginTransaction();
        ft.replace(R.id.registry_login_frame_layout,registryFragment);
        ft.commit();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(fm==null){
            fm=getSupportFragmentManager();
        }
    }

}
