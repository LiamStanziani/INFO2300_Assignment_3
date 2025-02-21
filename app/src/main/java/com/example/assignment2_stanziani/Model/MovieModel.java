package com.example.assignment2_stanziani.Model;

import android.graphics.drawable.Drawable;

public class MovieModel {
    private String movieTitle;
    private String director;
    private String rating;
    private String releaseYear;
    private String movieDescription;
    private Drawable movieImage;
    private String movieDrawableString;

    public MovieModel(String movieTitle, String director, String rating, String releaseYear, String movieDescription, Drawable movieImageDrawable, String movieDrawableString) {
        this.movieTitle = movieTitle;
        this.director = director;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.movieDescription = movieDescription;
        this.movieImage = movieImageDrawable;
        this.movieDrawableString = movieDrawableString;
    }

    public MovieModel() {
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String name) {
        this.movieTitle = name;
    }

    public String getStudio() { return director; }

    public void setStudio(String director) {
        this.director = director;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getMovieDescription() { return movieDescription; }

    public void setMovieDescription(String movieDescription) { this.movieDescription = movieDescription; }

    public Drawable getMovieImage() { return movieImage; }

    public void setMovieImage(Drawable movieImageDrawable) { this.movieImage = movieImageDrawable; }

    public String getMovieDrawableString() { return movieDrawableString; }
}
