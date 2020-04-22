package com.example.prisprojekat.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.prisprojekat.Helpers.RetrofitClient;
import com.example.prisprojekat.R;
import com.example.prisprojekat.models.Singleton;
import com.example.prisprojekat.models.User;
import com.hsalf.smilerating.SmileRating;
import com.hsalf.smileyrating.SmileyRating;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {


    private boolean b;
    private TextView  name_lastname;
    private TextView username;
    private TextView email;
    private TextView ratingtext;
    private SmileyRating smileRating;
    private TextView leave_comment_text;
    private EditText newcomment;
    private Button leave_comment_button;
    private ListView comments;

    private String otherUser;

    public ProfileFragment(boolean b,String user) {
        this.b=b;
        this.otherUser=user;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_profile, container, false);
        final User[] user = {null};
        if(!b) {

            user[0] = Singleton.getInstance().getUser();
        }
        else{


            String username=RetrofitClient.getInstance().getUsername();
            String password=RetrofitClient.getInstance().getPassword();
            Call<User> call=RetrofitClient.getInstance().getSafariService().getUserByUsername(Credentials.basic(username,password),otherUser);
            Log.d("stigli smo ","stigli smo");

            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if(response.isSuccessful()){
                        Log.d("uspesno je","uspesno je");
                    }
                    else{
                        Log.d("nije uspesno","Nije uspesno");
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Log.d("greska",t.toString());
                }
            });

            Log.d("executed",Boolean.toString(call.isExecuted()));
            if(user[0] ==null){

            }
        }
        inicializeComponent(v);
        setValueToComponents(user[0]);

        return  v;
    }

    public void inicializeComponent(View v){
        name_lastname=v.findViewById(R.id.profile_name_lastname);
        username=v.findViewById(R.id.profile_username);
        email=v.findViewById(R.id.profile_email);
        ratingtext=v.findViewById(R.id.profile_rating);
        smileRating=v.findViewById(R.id.profile_smile_rating);
        leave_comment_text=v.findViewById(R.id.profile_comment);
        newcomment=v.findViewById(R.id.profile_leave_comment);
        leave_comment_button=v.findViewById(R.id.profile_submit_comment);
        comments=v.findViewById(R.id.profile_coments);
    }
    public void setValueToComponents(User u){
        name_lastname.setText(u.getName()+" "+u.getLastName());
        username.setText(username.getText().toString()+u.getUsername());
        email.setText(email.getText().toString()+u.getEmail());
        if(b){
            ratingtext.setText("Rate this user!");
        }
        if(!b){
            leave_comment_text.setText("Comments of your profile:");
            newcomment.setVisibility(View.GONE);
            leave_comment_button.setVisibility(View.GONE);
            smileRating.setRating(5);

        }
        else{
            leave_comment_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(newcomment.getText().length()==0){
                        newcomment.setError("cannot be blank");
                        return;
                    }
                }
            });
        }

    }



}
