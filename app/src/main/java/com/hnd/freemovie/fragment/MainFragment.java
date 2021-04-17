
package com.hnd.freemovie.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.hnd.freemovie.MainActivity;
import com.hnd.freemovie.R;
import com.hnd.freemovie.model.HomePage;
import com.hnd.freemovie.util.TabFragmentHelper;

import java.util.ArrayList;

public class MainFragment extends Fragment implements View.OnClickListener {

    private HomePage fullHomePage;
    private HomePage.MovieList movieList;
    private HomePage.MovieCard movieCard;
    private ArrayList<HomePage.MovieCard> movieCards = new ArrayList<>();
    private ArrayList<HomePage.MovieList> movieLists = new ArrayList<>();
    private TabFragmentHelper tabFragmentHelper;
    private BottomNavigationView bottomNavigationView;
    private AppBarLayout appBarLayout;
    private HomeFragment homeFragment;
    private DownloadsFragment downloadsFragment;
    private FavoritesFragment favoritesFragment;
    private int containerId;

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_main, container, false);
        layout.findViewById(R.id.appBarDrawer).setOnClickListener(this);
        layout.findViewById(R.id.appBarSearch).setOnClickListener(this);
        layout.findViewById(R.id.appBarVoiceSearch).setOnClickListener(this);
        layout.findViewById(R.id.appBarMore).setOnClickListener(this);
        fullHomePage = initializeHomePage();
        containerId = R.id.tab_fragment_container;
        homeFragment = HomeFragment.newInstance(fullHomePage);
        downloadsFragment = DownloadsFragment.newInstance();
        favoritesFragment = FavoritesFragment.newInstance();
        appBarLayout = layout.findViewById(R.id.appBarLayout);
        bottomNavigationView = layout.findViewById(R.id.bottom_navigation);
        tabFragmentHelper = new TabFragmentHelper(containerId, getChildFragmentManager());

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (bottomNavigationView.getSelectedItemId() == item.getItemId())
                    return false;
                appBarLayout.setExpanded(true);
                switch (item.getItemId()) {
                    case R.id.navigation_downloads:
                        tabFragmentHelper.switchTo(downloadsFragment);
                        return true;
                    case R.id.navigation_favorites:
                        tabFragmentHelper.switchTo(favoritesFragment);
                        return true;
                    case R.id.navigation_home:
                        tabFragmentHelper.switchTo(homeFragment);
                        return true;
                }
                return false;
            }
        });
        tabFragmentHelper.initializeFragment(homeFragment);
        return layout;
    }

    public boolean onBackPressed() {
        if (tabFragmentHelper.getVisibleFragment() instanceof HomeFragment)
            return false;
        tabFragmentHelper.switchTo(homeFragment);
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
        return true;
    }


    private HomePage initializeHomePage() {
        for (int i = 1; i <= 20; i++) {
            //new Movie List
            movieCards = new ArrayList<>();
            movieList = new HomePage.MovieList(0, null, null, null);
            movieList.setName("Movie list " + i);
            movieList.setId(String.valueOf(i));
            movieList.setMovieCardsNumber(9);
            for (int j = 1; j <= 9; j++) {
                //new Movie Card
                movieCard = new HomePage.MovieCard("Id: " + i + "/" + j, "Name " + i + "/" + j, "2002", "", "7.3", "");
                movieCards.add(movieCard);
            }
            movieList.setMovieCards(movieCards);
            movieLists.add(movieList);
        }
        HomePage homePage = new HomePage(movieLists.size(), movieLists, HomePage.FULL_STATE);
        return new HomePage(movieLists.size(), movieLists, HomePage.FULL_STATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appBarDrawer:
                MainActivity.say("appBarDrawer");
                break;
            case R.id.appBarSearch:
                MainActivity.say("appBarSearch");
                break;
            case R.id.appBarVoiceSearch:
                MainActivity.say("appBarVoiceSearch");
                break;
            case R.id.appBarMore:
                MainActivity.say("appBarMore");
                break;
        }
    }
}