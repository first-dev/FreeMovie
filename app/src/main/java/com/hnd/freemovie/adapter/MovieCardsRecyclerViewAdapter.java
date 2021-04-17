package com.hnd.freemovie.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.hnd.freemovie.MainActivity;
import com.hnd.freemovie.R;
import com.hnd.freemovie.util.ImageHelper;
import com.hnd.freemovie.model.HomePage.MovieList;
import com.hnd.freemovie.model.HomePage.MovieCard;

public class MovieCardsRecyclerViewAdapter extends RecyclerView.Adapter<MovieCardsRecyclerViewAdapter.MovieCardsRecyclerViewHolder> {

    private Context context;
    private MovieList movieList;

    public MovieCardsRecyclerViewAdapter(Context context, MovieList movieList) {
        this.movieList = movieList;
        this.context = context;
    }
    @NonNull
    @Override
    public MovieCardsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_movie, parent, false);
        return new MovieCardsRecyclerViewHolder(view);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MovieCardsRecyclerViewHolder holder, int position) {
        MovieCard movieCard = movieList.getMovieCard(position);
        holder.titleView.setText(movieCard.getName());
        holder.yearView.setText(movieCard.getYear());
        holder.ratingView.setText(movieCard.getRating()+" ★");
        if(Double.parseDouble(movieCard.getRating())<7)
            holder.ratingView.setTextColor(context.getResources().getColor(R.color.lightGrayFont));
        else
            holder.ratingView.setTextColor(context.getResources().getColor(R.color.lightGoldenFont));
        holder.movieId = movieCard.getMovieId();
        Glide.with(context)
                //.load("https://m.media-amazon.com/images/M/MV5BMTc5MDE2ODcwNV5BMl5BanBnXkFtZTgwMzI2NzQ2NzM@._V"+(new Random().nextInt(9)+1)+"_.jpg")
                .load(context.getResources()
                .getIdentifier(ImageHelper.getDrawableName(movieCard.getMovieId()), "drawable", context.getPackageName()))
                .centerCrop()
                .placeholder(R.drawable.loading_image)
                .error(R.drawable.broken_image)
                .into(holder.imageView);

    }
    @Override
    public int getItemCount() {
        return movieList.getMovieCardsNumber();
    }


    class MovieCardsRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        String movieId;
        FrameLayout cardLayout;
        TextView titleView, yearView, ratingView;
        ImageView imageView;

        public MovieCardsRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            movieId = null;
            cardLayout = itemView.findViewById(R.id.movieCardLayout);
            titleView = itemView.findViewById(R.id.movieCardTitle);
            yearView = itemView.findViewById(R.id.movieCardYear);
            ratingView = itemView.findViewById(R.id.movieCardRating);
            imageView = itemView.findViewById(R.id.movieImage);
        }

        @Override
        public void onClick(View v) {
            ((MainActivity)context).showMovie(this.movieId);
        }
    }
}
