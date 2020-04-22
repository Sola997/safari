package com.example.prisprojekat.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.prisprojekat.R;
import com.example.prisprojekat.fragments.Dialog_fragment_bidding;
import com.example.prisprojekat.fragments.NewArticleFragment;
import com.example.prisprojekat.fragments.ProfileFragment;
import com.example.prisprojekat.fragments.SearchingArticlesFragment;
import com.example.prisprojekat.models.Article;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fm;
    private FragmentTransaction ft;

    private ProfileFragment profileFragment;
    private SearchingArticlesFragment searchingArticlesFragment;
    private NewArticleFragment newArticleFragment;

    private ProfileFragment otherUserProfileFragment;

    private TabLayout tabLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        tabLayout=findViewById(R.id.main_activity_tablayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){
                    changeFragmentsToProfile();
                }else if(tab.getPosition()==1){
                    changeFragmentToSearchingArticles();
                }
                else{
                    changeFragmentsToNewArticle();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        fm=getSupportFragmentManager();
        ft=fm.beginTransaction();
        ft.add(R.id.main_activiry_frame_layout,new ProfileFragment(false,null ));
        ft.commit();
    }
    public void changeFragmentsToProfile(){
        ft=fm.beginTransaction();
        if(profileFragment==null) {
            profileFragment = new ProfileFragment(false, null);
        }
        ft.replace(R.id.main_activiry_frame_layout,profileFragment);
        ft.commit();
    }
    public void changeFragmentsToNewArticle(){
        ft=fm.beginTransaction();
        if(newArticleFragment==null){
            newArticleFragment=new NewArticleFragment();
        }
        ft.replace(R.id.main_activiry_frame_layout,newArticleFragment);
        ft.commit();
    }
    public void changeFragmentToSearchingArticles(){
        ft=fm.beginTransaction();
        if(searchingArticlesFragment==null){
            searchingArticlesFragment=new SearchingArticlesFragment();
        }
        ft.replace(R.id.main_activiry_frame_layout,searchingArticlesFragment);
        ft.commit();
    }
    public void openFragmentToBidForArticle(Article article){
        Dialog_fragment_bidding fragmentBidding=new Dialog_fragment_bidding(article);
        fragmentBidding.show(getSupportFragmentManager(),"biding");
    }

    public void ChangeFragmentToOtherUser(String username){
         Log.d("username je ",username);

        ft=fm.beginTransaction();
        otherUserProfileFragment=new ProfileFragment(true,username);
        ft.replace(R.id.main_activiry_frame_layout,otherUserProfileFragment);
        ft.commit();
    }


}
