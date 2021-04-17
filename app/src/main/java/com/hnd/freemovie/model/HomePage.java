package com.hnd.freemovie.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class HomePage implements Parcelable {

    protected HomePage(Parcel in) {
        movieListsNumber = in.readInt();
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(movieListsNumber);
    }
    @Override
    public int describeContents() {
        return 0;
    }
    public static final Creator<HomePage> CREATOR = new Creator<HomePage>() {
        @Override
        public HomePage createFromParcel(Parcel in) {
            return new HomePage(in);
        }

        @Override
        public HomePage[] newArray(int size) {
            return new HomePage[size];
        }
    };

    private int movieListsNumber;
    private ArrayList<MovieList> movieLists;
    public static final int NOT_FULL_STATE = 0;
    public static final int FULL_STATE = 1;
    private int state;

    public HomePage(int movieListsNumber, ArrayList<MovieList> movieLists, int state) {
        this.movieListsNumber = movieListsNumber;
        this.movieLists = movieLists;
        this.state = state;
    }
    public HomePage(HomePage homePage){
        this.movieListsNumber = homePage.getMovieListsNumber();
        this.movieLists = homePage.getMovieLists();
    }
    public HomePage(HomePage homePage,int movieListsNumber){
        this.movieLists = new ArrayList<MovieList>();
        this.movieListsNumber = movieListsNumber;
        for (int i=0;i<movieListsNumber;i++){
            this.movieLists.add(homePage.getMovieList(i));
        }
    }
    public int getMovieListsNumber() {
        return movieListsNumber;
    }
    public void setMovieListsNumber(int movieListsNumber) {
        this.movieListsNumber = movieListsNumber;
    }
    public ArrayList<MovieList> getMovieLists() {
        return movieLists;
    }
    public void setMovieLists(ArrayList<MovieList> movieLists) {
        this.movieLists = movieLists;
    }
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }

    public MovieList getMovieList(int index){
        return this.getMovieLists().get(index);
    }
    public void addMoveList(MovieList movieList){
        this.movieLists.add(movieList);
        this.movieListsNumber++;
    }
    public boolean isFull(){
        return this.state == FULL_STATE;
    }

    public static class MovieList {
        private int movieCardsNumber;
        private String id, name;
        private ArrayList<MovieCard> movieCards;

        public MovieList(int movieCardsNumber, String id, String name, ArrayList<MovieCard> movieCards) {
            this.movieCardsNumber = movieCardsNumber;
            this.name = name;
            this.movieCards = movieCards;
        }
        public int getMovieCardsNumber() {
            return movieCardsNumber;
        }
        public void setMovieCardsNumber(int movieCardsNumber) {
            this.movieCardsNumber = movieCardsNumber;
        }
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public ArrayList<MovieCard> getMovieCards() {
            return movieCards;
        }
        public void setMovieCards(ArrayList<MovieCard> movieCards) {
            this.movieCards = movieCards;
        }

        public MovieCard getMovieCard(int index){
            return this.getMovieCards().get(index);
        }
        public void addMovieCard(MovieCard movieCard){
            this.movieCards.add(movieCard);
            this.movieCardsNumber++;
        }
    }
    public static class MovieCard {
        private String movieId;
        private String name;
        private String year;
        private String quality;
        private String rating;
        private String imageUrl;
        public MovieCard(String movieId, String name, String year, String quality, String rating, String imageUrl) {
            this.movieId = movieId;
            this.name = name;
            this.year = year;
            this.quality = quality;
            this.rating = rating;
            this.imageUrl = imageUrl;
        }
        public String getMovieId() {
            return movieId;
        }
        public void setMovieId(String movieId) {
            this.movieId = movieId;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getYear() {
            return year;
        }
        public void setYear(String year) {
            this.year = year;
        }
        public String getQuality() {
            return quality;
        }
        public void setQuality(String quality) {
            this.quality = quality;
        }
        public String getRating() {
            return rating;
        }
        public void setRating(String rating) {
            this.rating = rating;
        }
        public String getImageUrl() {
            return imageUrl;
        }
        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
