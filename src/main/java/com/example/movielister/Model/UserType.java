package com.example.movielister.Model;

public class UserType implements IEntity{
    int userTypeID;
    String info;

    public UserType(int userTypeID, String info) {
        this.userTypeID = userTypeID;
        this.info = info;
    }

    public UserType(String info) {
        this.info = info;
    }

    public int getUserTypeID() {
        return userTypeID;
    }

    public void setUserTypeID(int userTypeID) {
        this.userTypeID = userTypeID;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
