package com.hnd.freemovie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.hnd.freemovie.fragment.MainFragment;
import com.hnd.freemovie.fragment.MovieFragment;
import com.hnd.freemovie.model.MoviePage;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private MainFragment mainFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainFragment = new MainFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.executePendingTransactions();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_fragment_container,mainFragment).commit();
    }
    @Override
    public void onBackPressed() {
        if(mainFragment.isVisible()){
            if(!mainFragment.onBackPressed())
                super.onBackPressed();
        } else {//Movie fragment is visible
            fragmentManager.popBackStack();
        }
    }

    public void showMovie(String movieId){
        MoviePage moviePage = new MoviePage();
        moviePage.setId(movieId);
        MovieFragment movieFragment = MovieFragment.newInstance(moviePage);
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_fragment_container,movieFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
    public static void say(Object s){
        Log.d("HND_LOG", String.valueOf(s));
    }
}