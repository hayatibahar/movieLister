package com.example.movielister.Model;


public class Movie implements IEntity {
    int movieID;
    String title;
    int movieYear;
    String released;
    String runtime;
    String genre;
    int directorID;
    String plot;
    int countryID;
    String awards;
    String poster;
    double rate;
    int rateCount;
    int commentCount;

    public Movie(int movieID, String title, int movieYear, String released, String runtime, String genre, int directorID, String plot, int countryID, String awards, String poster, double rate, int rateCount, int commentCount) {
        this.movieID = movieID;
        this.title = title;
        this.movieYear = movieYear;
        this.released = released;
        this.runtime = runtime;
        this.genre = genre;
        this.directorID = directorID;
        this.plot = plot;
        this.countryID = countryID;
        this.awards = awards;
        this.poster = poster;
        this.rate = rate;
        this.rateCount = rateCount;
        this.commentCount = commentCount;
    }

    public Movie(String title, int movieYear, String released, String runtime, String genre, int directorID, String plot, int countryID, String awards, String poster, double rate, int rateCount, int commentCount) {
        this.title = title;
        this.movieYear = movieYear;
        this.released = released;
        this.runtime = runtime;
        this.genre = genre;
        this.directorID = directorID;
        this.plot = plot;
        this.countryID = countryID;
        this.awards = awards;
        this.poster = poster;
        this.rate = rate;
        this.rateCount = rateCount;
        this.commentCount = commentCount;
    }

    public int getDirectorID() {
        return directorID;
    }

    public void setDirectorID(int directorID) {
        this.directorID = directorID;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getRateCount() {
        return rateCount;
    }

    public void setRateCount(int rateCount) {
        this.rateCount = rateCount;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getMovieYear() {
        return movieYear;
    }

    public void setMovieYear(int movieYear) {
        this.movieYear = movieYear;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }


}
