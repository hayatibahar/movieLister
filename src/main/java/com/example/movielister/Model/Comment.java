package com.example.movielister.Model;

public class Comment {
    int commentID;
    int userID;
    int movieID;
    String title;
    String userComment;
    String commentDate;
    int likeCount;
    int dislikeCount;

    public Comment(int commentID, int userID, int movieID, String title, String userComment, String commentDate, int likeCount, int dislikeCount) {
        this.commentID = commentID;
        this.userID = userID;
        this.movieID = movieID;
        this.title = title;
        this.userComment = userComment;
        this.commentDate = commentDate;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
    }

    public Comment(int userID, int movieID, String title, String userComment, String commentDate, int likeCount, int dislikeCount) {
        this.userID = userID;
        this.movieID = movieID;
        this.title = title;
        this.userComment = userComment;
        this.commentDate = commentDate;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserComment() {
        return userComment;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }
}
