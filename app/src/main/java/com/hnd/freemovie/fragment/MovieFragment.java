package com.hnd.freemovie.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.hnd.freemovie.R;
import com.hnd.freemovie.model.MoviePage;

public class MovieFragment extends Fragment {

    private MoviePage moviePage;
    public MovieFragment() {}
    public static MovieFragment newInstance(MoviePage moviePage) {
        Bundle args = new Bundle();
        args.putParcelable("moviePage", moviePage);
        MovieFragment f = new MovieFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        moviePage = getArguments().getParcelable("moviePage");
        final View layout = inflater.inflate(R.layout.fragment_movie, container, false);
        return layout;
    }
}