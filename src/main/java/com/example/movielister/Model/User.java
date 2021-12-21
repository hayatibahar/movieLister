package com.example.movielister.Model;

public class User {
    int userID;
    String nickname;
    String pass;
    int userTypeID;

    public User(int userID, String nickname, String pass, int userTypeID) {
        this.userID = userID;
        this.nickname = nickname;
        this.pass = pass;
        this.userTypeID = userTypeID;
    }

    public User(String nickname, String pass, int userTypeID) {
        this.nickname = nickname;
        this.pass = pass;
        this.userTypeID = userTypeID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getUserTypeID() {
        return userTypeID;
    }

    public void setUserTypeID(int userTypeID) {
        this.userTypeID = userTypeID;
    }
}
