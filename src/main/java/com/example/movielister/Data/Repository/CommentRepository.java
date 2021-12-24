package com.example.movielister.Data.Repository;

import com.example.movielister.Data.DBUtil;
import com.example.movielister.Data.Dao.Dao;
import com.example.movielister.Model.Comment;
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
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8)
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
                        "(userID,movieID,title,userComment,commentDate,likeCount,dislikeCount) " +
                        "values (%d,%d,'%s','%s','%s',%d,%d)", comment.getUserID(), comment.getMovieID(), comment.getTitle(),
                comment.getUserComment(), comment.getCommentDate(), comment.getLikeCount(), comment.getDislikeCount());

        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public void update(Comment comment) {
        String query = String.format("UPDATE comment_tbl SET " +
                        "userID = %d,movieID = %d,title = '%s',userComment = '%s'," +
                        "commentDate = '%s',likeCount = %d,dislikeCount = %d WHERE commentID = %d"
                , comment.getUserID(), comment.getMovieID(), comment.getTitle(),
                comment.getUserComment(), comment.getCommentDate(),
                comment.getLikeCount(), comment.getDislikeCount(), comment.getCommentID());

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
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getInt(8)
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
                    resultSet.getString(6),
                    resultSet.getInt(7),
                    resultSet.getInt(8)
            ));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }


}
