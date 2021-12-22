package com.example.movielister.Model;

public class Rate implements IEntity {
    int rateID;
    int userID;
    int movieID;
    int rate;

    public Rate(int rateID, int userID, int movieID, int rate) {
        this.rateID = rateID;
        this.userID = userID;
        this.movieID = movieID;
        this.rate = rate;
    }

    public Rate(int userID, int movieID, int rate) {
        this.userID = userID;
        this.movieID = movieID;
        this.rate = rate;
    }

    public int getRateID() {
        return rateID;
    }

    public void setRateID(int rateID) {
        this.rateID = rateID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
