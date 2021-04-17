package com.hnd.freemovie.adapter;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.hnd.freemovie.R;
import com.hnd.freemovie.util.RecyclerViewHelper;
import com.hnd.freemovie.model.HomePage;
import com.hnd.freemovie.model.HomePage.MovieList;

public class MovieListsRecyclerViewAdapter extends RecyclerView.Adapter<MovieListsRecyclerViewAdapter.MovieListsRecyclerViewHolder> {

    /*
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
     */
    private final Context context;
    private HomePage homePage;
    private final RecyclerViewHelper recyclerViewHelper;
    private SparseIntArray positionList = new SparseIntArray();
    private RecyclerView.RecycledViewPool recycledViewPool;
    public MovieListsRecyclerViewAdapter(Context context, HomePage homePage) {
        this.context = context;
        this.homePage = homePage;
        recyclerViewHelper = new RecyclerViewHelper(context);
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @NonNull
    @Override
    public MovieListsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_movie, parent, false);
        return new MovieListsRecyclerViewHolder(view);
        /*
        if(viewType == VIEW_TYPE_ITEM){
            View view = inflater.inflate(R.layout.list_movie, parent, false);
            return new MovieListsRecyclerViewHolder(view);
        } else if(viewType == VIEW_TYPE_LOADING) {
            View view = inflater.inflate(R.layout.item_loading_list_movie, parent, false);
            return new LoadingViewHolder(view);
        } else {
            return null;
        }
         */
    }

    @Override
    public void onBindViewHolder(@NonNull final MovieListsRecyclerViewHolder holder, int position) {
        MovieList movieList = homePage.getMovieList(position);
        RecyclerView recyclerView = holder.recyclerView;
        MovieCardsRecyclerViewAdapter movieCardsRecyclerViewAdapter = new MovieCardsRecyclerViewAdapter(context, movieList);
        holder.textViewTitle.setText(movieList.getName());
        recyclerView.setAdapter(movieCardsRecyclerViewAdapter);
        // Retrieve and set the saved position
        int lastSeenFirstPosition = positionList.get(position, 0);
        if (lastSeenFirstPosition >= 0) {
            holder.linearLayoutManager.scrollToPosition(lastSeenFirstPosition);
        }

    }
    @Override
    public int getItemCount() {
        if(homePage.getState()==HomePage.FULL_STATE)
            return homePage.getMovieListsNumber();
        else
            return homePage.getMovieListsNumber()+1;
    }
    /*
    @Override
    public int getItemViewType(int position) {
        if (position == homePage.getMovieListsNumber())
            return VIEW_TYPE_LOADING;
        else
            return VIEW_TYPE_ITEM;
    }
     */
    @Override
    public void onViewRecycled(@NonNull MovieListsRecyclerViewHolder holder) {
            final int position = holder.getAdapterPosition();
            int firstVisiblePosition = holder.linearLayoutManager.findFirstCompletelyVisibleItemPosition();
            positionList.put(position, firstVisiblePosition);
        super.onViewRecycled(holder);
    }

    class MovieListsRecyclerViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout headerLayout;
        TextView textViewTitle;
        RecyclerView recyclerView;
        LinearLayoutManager linearLayoutManager;
        public MovieListsRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            headerLayout = itemView.findViewById(R.id.movieListHeader);
            textViewTitle = itemView.findViewById(R.id.movieListTitle);
            recyclerView = itemView.findViewById(R.id.movieListRecyclerView);
            headerLayout.setPadding(recyclerViewHelper.getSideMargin(),headerLayout.getPaddingTop(),
                    recyclerViewHelper.getSideMargin(),headerLayout.getPaddingBottom());
            recyclerView.setPadding(recyclerViewHelper.getRVSidePadding(),recyclerView.getPaddingTop(),
                    recyclerViewHelper.getRVSidePadding(),recyclerView.getPaddingBottom());
            linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            linearLayoutManager.setRecycleChildrenOnDetach(true);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setRecycledViewPool(recycledViewPool);
            recyclerView.setHasFixedSize(true);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setOnFlingListener(null);
            LinearSnapHelper snapHelper = new LinearSnapHelper(){
                @Override
                public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
                    final int itemCount = layoutManager.getItemCount();
                    final View currentView = findSnapView(layoutManager);
                    final int currentPosition = layoutManager.getPosition(currentView);
                    int oldTargetPosition = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
                    if(oldTargetPosition == RecyclerView.NO_POSITION) return RecyclerView.NO_POSITION;
                    int deltaJump =  oldTargetPosition - currentPosition;
                    final int maxJump = 4;

                    if(deltaJump > maxJump)
                        deltaJump = maxJump;
                    if(deltaJump < -maxJump)
                        deltaJump = -maxJump;

                    int targetPos = currentPosition + deltaJump;
                    if (targetPos < 0)
                        targetPos = 0;

                    if (targetPos >= itemCount)
                        targetPos = itemCount - 1;

                    return targetPos;
                }

            };
            snapHelper.attachToRecyclerView(recyclerView);
        }

    }
    /*
    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
     */

}
