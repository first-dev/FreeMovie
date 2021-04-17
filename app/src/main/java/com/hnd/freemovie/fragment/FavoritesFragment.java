package com.hnd.freemovie.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.hnd.freemovie.R;
import com.hnd.freemovie.model.HomePage;

public class FavoritesFragment extends Fragment {

    public FavoritesFragment() {}
    public static FavoritesFragment newInstance() {
        Bundle args = new Bundle();
        FavoritesFragment f = new FavoritesFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_favorites, container, false);

        return layout;
    }
}