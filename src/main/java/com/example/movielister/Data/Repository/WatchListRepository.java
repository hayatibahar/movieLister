package com.example.movielister.Data.Repository;

import com.example.movielister.Data.DBUtil;
import com.example.movielister.Data.Dao.Dao;
import com.example.movielister.Model.Dto.WatchListDetail;
import com.example.movielister.Model.WatchList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class WatchListRepository implements Dao<WatchList> {
    @Override
    public ObservableList<WatchList> getAll() {
        ObservableList<WatchList> watchLists = FXCollections.observableArrayList();
        String query = "SELECT * FROM country_tbl";
        ResultSet resultSet = DBUtil.dbExecuteQuery(query);
        try {
            while (resultSet.next()) {
                watchLists.add(new WatchList(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return watchLists;
    }

    @Override
    public void insert(WatchList watchList) {
        String query = String.format("INSERT INTO watchlist_tbl (userID,movieID,status) values (%d,%d,%d)",
                watchList.getUserID(),watchList.getMovieID(),watchList.getStatus());

        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public void update(WatchList watchList) {
        String query = String.format("UPDATE watchlist_tbl SET userID = %d,movieID = %d, status = %d WHERE listID = %d",
                watchList.getUserID(),watchList.getMovieID(),watchList.getStatus(),watchList.getListID());

        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public void delete(WatchList watchList) {
        String query = String.format("DELETE FROM watchlist_tbl WHERE listID = %d", watchList.getListID());

        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public void deleteAll() {
        String query = "DELETE FROM watchlist_tbl";

        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public WatchList getById(int id) {
        String query = String.format("SELECT * FROM watchlist_tbl WHERE listID = %d", id);
        ResultSet resultSet = DBUtil.dbExecuteQuery(query);
        try {
            if (resultSet.next()) {
                return (new WatchList(
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

    public ObservableList<WatchList> getAllByUserID(int id) {
        ObservableList<WatchList> watchLists = FXCollections.observableArrayList();
        String query = String.format("SELECT * FROM watchlist_tbl WHERE userID = %d", id);
        ResultSet resultSet = DBUtil.dbExecuteQuery(query);
        try {
            while (resultSet.next()) {
                watchLists.add(new WatchList(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3),
                        resultSet.getInt(4)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return watchLists;
    }

    public ObservableList<WatchListDetail> getAllDetailByUserID(int id) {
        ObservableList<WatchListDetail> watchListDetails = FXCollections.observableArrayList();
        String query = String.format("SELECT w.listID,m.title, case when r.rate is null then 0 else r.rate end as rate,w.status from watchlist_tbl w LEFT JOIN rate_tbl r on w.userID = r.userID LEFT JOIN movie_tbl m on w.movieID = m.movieID WHERE w.userID = %d", id);
        ResultSet resultSet = DBUtil.dbExecuteQuery(query);
        try {
            while (resultSet.next()) {
                watchListDetails.add(new WatchListDetail(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getInt(4)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return watchListDetails;
    }
}
