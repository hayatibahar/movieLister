package com.example.movielister.Model;

public class Country implements IEntity {
    int countryID;
    String country;

    public Country(int countryID, String country) {
        this.countryID = countryID;
        this.country = country;
    }

    public Country(String country) {
        this.country = country;
    }

    public int getCountryID() {
        return countryID;
    }

    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
