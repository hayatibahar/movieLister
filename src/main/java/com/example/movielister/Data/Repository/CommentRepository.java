package com.example.movielister.Data.Repository;

import com.example.movielister.Data.DBUtil;
import com.example.movielister.Data.Dao.Dao;
import com.example.movielister.Model.Comment;
import com.example.movielister.Model.Dto.CommentDetail;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class CommentRepository implements Dao<Comment> {
    @Override
    public ObservableList<Comment> getAll() {
        ObservableList<Comment> comments = FXCollections.observableArrayList();
        String query = "SELECT * FROM comment_tbl";
        ResultSet resultSet = DBUtil.dbExecuteQuery(query);
        try {
            while (resultSet.next()) {
                comments.add(new Comment(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    public void insert(Comment comment) {
        String query = String.format("INSERT INTO comment_tbl " +
                        "(userID,movieID,title,userComment,commentDate) " +
                        "values (%d,%d,'%s','%s','%s')", comment.getUserID(), comment.getMovieID(), comment.getTitle(),
                comment.getUserComment(), comment.getCommentDate());

        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public void update(Comment comment) {
        String query = String.format("UPDATE comment_tbl SET " +
                        "userID = %d,movieID = %d,title = '%s',userComment = '%s'," +
                        "commentDate = '%s' WHERE commentID = %d"
                , comment.getUserID(), comment.getMovieID(), comment.getTitle(),
                comment.getUserComment(), comment.getCommentDate(), comment.getCommentID());

        DBUtil.dbExecuteUpdate(query);

    }

    @Override
    public void delete(Comment comment) {
        String query = String.format("DELETE FROM comment_tbl WHERE commentID = %d",
                comment.getCommentID());

        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public void deleteAll() {
        String query = "DELETE FROM user_tbl";

        DBUtil.dbExecuteUpdate(query);

    }

    @Override
    public Comment getById(int id) {
        String query = String.format("SELECT * FROM comment_tbl WHERE commentID = %d", id);
        ResultSet resultSet = DBUtil.dbExecuteQuery(query);
        try {
            if (resultSet.next()) {
                return (new Comment(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<Comment> getByMovieId(int id) {
        String query = String.format("SELECT * FROM comment_tbl WHERE movieID = %d", id);
        ObservableList<Comment> comments = FXCollections.observableArrayList();
        ResultSet resultSet = DBUtil.dbExecuteQuery(query);
        try {
            comments.add(new Comment(
                    resultSet.getInt(1),
                    resultSet.getInt(2),
                    resultSet.getInt(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }

    public ObservableList<CommentDetail> getDetailsByMovieID(int id) {
        String query = String.format("SELECT c.commentID,u.nickname,c.title,c.userComment,c.commentDate FROM comment_tbl c LEFT JOIN user_tbl u on u.userID = c.userID WHERE movieID = %d", id);
        ObservableList<CommentDetail> commentDetails = FXCollections.observableArrayList();
        ResultSet resultSet = DBUtil.dbExecuteQuery(query);
        try {
            while (resultSet.next()) {
                commentDetails.add(new CommentDetail(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return commentDetails;
    }

}
