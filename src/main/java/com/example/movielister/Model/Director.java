package com.example.movielister.Model;

public class Director {
    int directorID;
    String director;

    public Director(int directorID, String director) {
        this.directorID = directorID;
        this.director = director;
    }

    public Director(String director) {
        this.director = director;
    }

    public int getDirectorID() {
        return directorID;
    }

    public void setDirectorID(int directorID) {
        this.directorID = directorID;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
