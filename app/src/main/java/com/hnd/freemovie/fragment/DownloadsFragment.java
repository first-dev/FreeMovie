
package com.hnd.freemovie.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.hnd.freemovie.R;
import com.hnd.freemovie.model.HomePage;

public class DownloadsFragment extends Fragment {

    public DownloadsFragment() {}
    public static DownloadsFragment newInstance() {
        Bundle args = new Bundle();
        DownloadsFragment f = new DownloadsFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_downloads, container, false);

        return layout;
    }
}