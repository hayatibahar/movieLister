package com.example.movielister.Model.Dto;

import com.example.movielister.Model.IEntity;

public class WatchListDetail implements IEntity {
    int listID;
    String title;
    double rate;
    int status;

    public WatchListDetail(int listID, String title, double rate, int status) {
        this.listID = listID;
        this.title = title;
        this.rate = rate;
        this.status = status;
    }

    public int getListID() {
        return listID;
    }

    public void setListID(int listID) {
        this.listID = listID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
