package com.example.example_11_1;

public class PosterModel {

    int imageID;
    String movieName;

    public PosterModel(int imageID, String movieName) {
        this.imageID = imageID;
        this.movieName = movieName;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public PosterModel(String movieName) {
        this.movieName = movieName;
    }

    public PosterModel(int imageID) {
        this.imageID = imageID;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
