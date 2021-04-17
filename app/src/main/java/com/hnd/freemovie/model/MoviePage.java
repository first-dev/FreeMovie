package com.hnd.freemovie.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class MoviePage implements Parcelable {

    protected MoviePage(Parcel in) {
        id = in.readString();
        name = in.readString();
        year = in.readString();
        language = in.readString();
        country = in.readString();
        MPARating = in.readString();
        duration = in.readString();
        quality = in.readString();
        resolution = in.readString();
        translation = in.readString();
        story = in.readString();
        trailerURL = in.readString();
    }
    public static final Creator<MoviePage> CREATOR = new Creator<MoviePage>() {
        @Override
        public MoviePage createFromParcel(Parcel in) {
            return new MoviePage(in);
        }

        @Override
        public MoviePage[] newArray(int size) {
            return new MoviePage[size];
        }
    };
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(year);
        dest.writeString(language);
        dest.writeString(country);
        dest.writeString(MPARating);
        dest.writeString(duration);
        dest.writeString(quality);
        dest.writeString(resolution);
        dest.writeString(translation);
        dest.writeString(story);
        dest.writeString(trailerURL);
    }

    private String id, name, year, language, country,
            MPARating,duration,quality,resolution,
            translation,story,trailerURL;
    private ArrayList<Hero> heroes;
    public MoviePage(){}
    public MoviePage(String id, String name, String year, String language, String country,
                     String MPARating, String duration, String quality, String resolution,
                     String translation, String story, String trailerURL, ArrayList<Hero> heroes) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.language = language;
        this.country = country;
        this.MPARating = MPARating;
        this.duration = duration;
        this.quality = quality;
        this.resolution = resolution;
        this.translation = translation;
        this.story = story;
        this.trailerURL = trailerURL;
        this.heroes = heroes;
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
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getMPARating() {
        return MPARating;
    }
    public void setMPARating(String MPARating) {
        this.MPARating = MPARating;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    public String getQuality() {
        return quality;
    }
    public void setQuality(String quality) {
        this.quality = quality;
    }
    public String getResolution() {
        return resolution;
    }
    public void setResolution(String resolution) {
        this.resolution = resolution;
    }
    public String getTranslation() {
        return translation;
    }
    public void setTranslation(String translation) {
        this.translation = translation;
    }
    public String getStory() {
        return story;
    }
    public void setStory(String story) {
        this.story = story;
    }
    public String getTrailerURL() {
        return trailerURL;
    }
    public void setTrailerURL(String trailerURL) {
        this.trailerURL = trailerURL;
    }
    public ArrayList<Hero> getHeroes() {
        return heroes;
    }
    public void setHeroes(ArrayList<Hero> heroes) {
        this.heroes = heroes;
    }


    public class Hero {
        private String realName,inMovieName,imageUrl;
        public Hero(String realName, String inMovieName, String imageUrl) {
            this.realName = realName;
            this.inMovieName = inMovieName;
            this.imageUrl = imageUrl;
        }
        public String getRealName() {
            return realName;
        }
        public void setRealName(String realName) {
            this.realName = realName;
        }
        public String getInMovieName() {
            return inMovieName;
        }
        public void setInMovieName(String inMovieName) {
            this.inMovieName = inMovieName;
        }
        public String getPicture() {
            return imageUrl;
        }
        public void setPicture(String imageUrl) {
            this.imageUrl = imageUrl;
        }

    }

}
