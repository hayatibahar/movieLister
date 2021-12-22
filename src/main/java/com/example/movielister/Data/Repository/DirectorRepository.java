package com.example.movielister.Data.Repository;

import com.example.movielister.Data.DBUtil;
import com.example.movielister.Data.Dao.Dao;
import com.example.movielister.Model.Director;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class DirectorRepository implements Dao<Director> {
    @Override
    public ObservableList<Director> getAll() {
        ObservableList<Director> directors = FXCollections.observableArrayList();
        String query = "SELECT * FROM director_tbl";
        ResultSet resultSet = DBUtil.dbExecuteQuery(query);
        try {
            while (resultSet.next()) {
                directors.add(new Director(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return directors;
    }

    @Override
    public void insert(Director director) {
        String query = String.format("INSERT INTO director_tbl (director) values ('%s')", director.getDirector());

        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public void update(Director director) {
        String query = String.format("UPDATE director_tbl SET director = '%s' WHERE directorID = %d",
                director.getDirector(), director.getDirectorID());

        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public void delete(Director director) {
        String query = String.format("DELETE FROM director_tbl WHERE directorID = %d", director.getDirectorID());

        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public void deleteAll() {
        String query = "DELETE FROM director_tbl";

        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public Director getById(int id) {
        String query = String.format("SELECT * FROM director_tbl WHERE directorID = %d", id);
        ResultSet resultSet = DBUtil.dbExecuteQuery(query);
        try {
            if (resultSet.next()) {
                return (new Director(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
