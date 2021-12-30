package com.example.movielister.Model.Dto;

public class CommentDetail {
    int commentID;
    String nickname;
    String title;
    String comment;
    String date;

    public CommentDetail(int commentID, String nickname, String title, String comment, String date) {
        this.commentID = commentID;
        this.nickname = nickname;
        this.title = title;
        this.comment = comment;
        this.date = date;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
