package com.hnd.freemovie.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hnd.freemovie.R;
import com.hnd.freemovie.adapter.MovieListsRecyclerViewAdapter;
import com.hnd.freemovie.model.HomePage;

public class HomeFragment extends Fragment {

    private HomePage fullHomePage;
    private MovieListsRecyclerViewAdapter movieListsRecyclerViewAdapter;

    /*
    private boolean isLoading = false;
    private int newListsToLoad = 3;
     */
    public HomeFragment() {
    }

    public static HomeFragment newInstance(HomePage homePage) {
        Bundle args = new Bundle();
        args.putParcelable("homePage", homePage);
        HomeFragment f = new HomeFragment();
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View layout = inflater.inflate(R.layout.fragment_home, container, false);
        fullHomePage = getArguments().getParcelable("homePage");
        RecyclerView recyclerView = layout.findViewById(R.id.homeFragmentRecyclerView);
        movieListsRecyclerViewAdapter = new MovieListsRecyclerViewAdapter(getContext(), fullHomePage);
        recyclerView.setAdapter(movieListsRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        // >>Smooth cross RecyclingViews Swipe
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                if (e.getAction() == MotionEvent.ACTION_DOWN && rv.getScrollState() == RecyclerView.SCROLL_STATE_SETTLING)
                    rv.stopScroll();
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });
        // >>Load more on scroll (canceled)
        /*
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if(homePage.isFull())
                    return;
                if(!isLoading){
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == (homePage.getMovieListsNumber()-1)-1) {
                        if(homePage.getMovieListsNumber() == fullHomePage.getMovieListsNumber()){
                            homePage.setState(HomePage.FULL_STATE);
                            movieListsRecyclerViewAdapter.notifyItemRemoved(homePage.getMovieListsNumber());
                        } else {
                            if(fullHomePage.getMovieListsNumber()-homePage.getMovieListsNumber() < newListsToLoad){
                                newListsToLoad = fullHomePage.getMovieListsNumber()-homePage.getMovieListsNumber();
                            }
                            loadMoreLists(newListsToLoad);
                            isLoading = true;
                        }
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if(homePage.isFull())
                    return;
                if(!isLoading){
                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == (homePage.getMovieListsNumber()-1)-1) {
                        if(homePage.getMovieListsNumber() == fullHomePage.getMovieListsNumber()){
                            homePage.setState(HomePage.FULL_STATE);
                            movieListsRecyclerViewAdapter.notifyItemRemoved(homePage.getMovieListsNumber());
                        } else {
                            if(fullHomePage.getMovieListsNumber()-homePage.getMovieListsNumber() < newListsToLoad){
                                newListsToLoad = fullHomePage.getMovieListsNumber()-homePage.getMovieListsNumber();
                            }
                            loadMoreLists(newListsToLoad);
                            isLoading = true;
                        }
                    }
                }
            }
        });
         */


        return layout;
    }
/*
    private void loadMoreLists(final int newItemCount){
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                for (int i=0;i<newItemCount;i++)
                    homePage.addMoveList(fullHomePage.getMovieList(homePage.getMovieListsNumber()));
                movieListsRecyclerViewAdapter.notifyItemInserted(homePage.getMovieLists().size() - 1);
                isLoading = false;
            }
        });
    }
 */
}