package com.example.movielister.Model;


public class Movie implements IEntity  {
    int FILM_ID;
    String TITLE;
    int YEAR;
    String RELEASED;
    String RUNTIME;
    String GENRE;
    int DIRECTORID;
    String PLOT;
    int COUNTRYID;
    String AWARDS;
    String POSTER;
    double RATE;
    int RATECOUNT;
    int COMMENTCOUNT;

    public Movie(int FILM_ID, String TITLE, int YEAR, String RELEASED, String RUNTIME, String GENRE, int DIRECTORID, String PLOT, int COUNTRYID, String AWARDS, String POSTER, double RATE, int RATECOUNT, int COMMENTCOUNT) {
        this.FILM_ID = FILM_ID;
        this.TITLE = TITLE;
        this.YEAR = YEAR;
        this.RELEASED = RELEASED;
        this.RUNTIME = RUNTIME;
        this.GENRE = GENRE;
        this.DIRECTORID = DIRECTORID;
        this.PLOT = PLOT;
        this.COUNTRYID = COUNTRYID;
        this.AWARDS = AWARDS;
        this.POSTER = POSTER;
        this.RATE = RATE;
        this.RATECOUNT = RATECOUNT;
        this.COMMENTCOUNT = COMMENTCOUNT;
    }

    public Movie(String TITLE, int YEAR, String RELEASED, String RUNTIME, String GENRE, int DIRECTORID, String PLOT, int COUNTRYID, String AWARDS, String POSTER, double RATE, int RATECOUNT, int COMMENTCOUNT) {
        this.TITLE = TITLE;
        this.YEAR = YEAR;
        this.RELEASED = RELEASED;
        this.RUNTIME = RUNTIME;
        this.GENRE = GENRE;
        this.DIRECTORID = DIRECTORID;
        this.PLOT = PLOT;
        this.COUNTRYID = COUNTRYID;
        this.AWARDS = AWARDS;
        this.POSTER = POSTER;
        this.RATE = RATE;
        this.RATECOUNT = RATECOUNT;
        this.COMMENTCOUNT = COMMENTCOUNT;
    }

    public int getDIRECTORID() {
        return DIRECTORID;
    }

    public void setDIRECTORID(int DIRECTORID) {
        this.DIRECTORID = DIRECTORID;
    }

    public int getCOUNTRYID() {
        return COUNTRYID;
    }

    public void setCOUNTRYID(int COUNTRYID) {
        this.COUNTRYID = COUNTRYID;
    }

    public double getRATE() {
        return RATE;
    }

    public void setRATE(double RATE) {
        this.RATE = RATE;
    }

    public int getRATECOUNT() {
        return RATECOUNT;
    }

    public void setRATECOUNT(int RATECOUNT) {
        this.RATECOUNT = RATECOUNT;
    }

    public int getCOMMENTCOUNT() {
        return COMMENTCOUNT;
    }

    public void setCOMMENTCOUNT(int COMMENTCOUNT) {
        this.COMMENTCOUNT = COMMENTCOUNT;
    }

    public int getFILM_ID() {
        return FILM_ID;
    }

    public void setFILM_ID(int FILM_ID) {
        this.FILM_ID = FILM_ID;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public int getYEAR() {
        return YEAR;
    }

    public void setYEAR(int YEAR) {
        this.YEAR = YEAR;
    }

    public String getRELEASED() {
        return RELEASED;
    }

    public void setRELEASED(String RELEASED) {
        this.RELEASED = RELEASED;
    }

    public String getRUNTIME() {
        return RUNTIME;
    }

    public void setRUNTIME(String RUNTIME) {
        this.RUNTIME = RUNTIME;
    }

    public String getGENRE() {
        return GENRE;
    }

    public void setGENRE(String GENRE) {
        this.GENRE = GENRE;
    }

    public String getPLOT() {
        return PLOT;
    }

    public void setPLOT(String PLOT) {
        this.PLOT = PLOT;
    }

    public String getAWARDS() {
        return AWARDS;
    }

    public void setAWARDS(String AWARDS) {
        this.AWARDS = AWARDS;
    }

    public String getPOSTER() {
        return POSTER;
    }

    public void setPOSTER(String POSTER) {
        this.POSTER = POSTER;
    }


}
