package com.example.movielister.Data.Repository;

import com.example.movielister.Data.DBUtil;
import com.example.movielister.Data.Dao.Dao;
import com.example.movielister.Model.Rate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class RateRepository implements Dao<Rate> {
    @Override
    public ObservableList<Rate> getAll() {
        ObservableList<Rate> rates = FXCollections.observableArrayList();
        String query = "SELECT * FROM rate_tbl";
        ResultSet resultSet = DBUtil.dbExecuteQuery(query);
        try {
            while (resultSet.next()) {
                rates.add(new Rate(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rates;
    }

    @Override
    public void insert(Rate rate) {
        String query = String.format("INSERT INTO rate_tbl (userID,movieID,rate) values (%d,%d,%d)",
                rate.getUserID(), rate.getMovieID(), rate.getRate());

        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public void update(Rate rate) {
        String query = String.format("UPDATE rate_tbl SET userID = %d,movieID = %d,rate = %d WHERE rateID = %d",
                rate.getUserID(), rate.getMovieID(), rate.getRate(), rate.getRateID());

        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public void delete(Rate rate) {
        String query = String.format("DELETE FROM rate_tbl WHERE rateID = %d",
                rate.getRate());

        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Rate getById(int id) {
        String query = String.format("SELECT * FROM rate_tbl WHERE rateID = %d", id);
        ResultSet resultSet = DBUtil.dbExecuteQuery(query);
        try {
            if (resultSet.next()) {
                return (new Rate(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Rate getByUserIDAndMovieId(int movieID, int userID) {
        String query = String.format("SELECT * FROM rate_tbl WHERE movieID = %d AND userID = %d", movieID, userID);
        ResultSet resultSet = DBUtil.dbExecuteQuery(query);
        try {
            if (resultSet.next()) {
                return (new Rate(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
