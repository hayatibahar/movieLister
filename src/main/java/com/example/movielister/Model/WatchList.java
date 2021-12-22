package com.example.movielister.Model;

public class WatchList implements IEntity {
    int listID;
    int userID;
    int movieID;
    int status;

    public WatchList(int listID, int userID, int movieID, int status) {
        this.listID = listID;
        this.userID = userID;
        this.movieID = movieID;
        this.status = status;
    }

    public WatchList(int userID, int movieID, int status) {
        this.userID = userID;
        this.movieID = movieID;
        this.status = status;
    }

    public int getListID() {
        return listID;
    }

    public void setListID(int listID) {
        this.listID = listID;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
