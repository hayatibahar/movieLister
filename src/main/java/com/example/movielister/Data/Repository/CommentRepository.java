package com.example.movielister.Data.Repository;

import com.example.movielister.Data.Dao.Dao;
import com.example.movielister.Model.Comment;
import javafx.collections.ObservableList;

public class CommentRepository implements Dao<Comment> {
    @Override
    public ObservableList<Comment> getAll() {
        return null;
    }

    @Override
    public void insert(Comment comment) {

    }

    @Override
    public void update(Comment comment) {

    }

    @Override
    public void delete(Comment comment) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Comment getById(int id) {
        return null;
    }
}
