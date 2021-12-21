package com.example.movielister.Data.Repository;

import com.example.movielister.Data.DBUtil;
import com.example.movielister.Data.Dao.Dao;
import com.example.movielister.Model.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class MovieRepository implements Dao<Movie> {
    @Override
    public ObservableList<Movie> getAll() {
        ObservableList<Movie> movie = FXCollections.observableArrayList();
        String query = "SELECT * FROM movie_tbl";
        ResultSet resultSet = DBUtil.dbExecuteQuery(query);
        try {
            while (resultSet.next()) {
                movie.add(new Movie(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getString(8),
                        resultSet.getInt(9),
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getDouble(12),
                        resultSet.getInt(13),
                        resultSet.getInt(14)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movie;
    }

    @Override
    public void insert(Movie movie) {
        String query = String.format("Insert into movie_tbl " +
                        "(title,movieYear,released,runtime,genre,directorID,plot,countryID,awards,poster,rate,ratecount,commentcount) " +
                        "values (%s,%d,%s,%s,%s,%d,%s,%d,%s,%s,%f,%d,%d)",
                movie.getTITLE(), movie.getYEAR(), movie.getRELEASED(), movie.getRUNTIME(), movie.getGENRE(),
                movie.getDIRECTORID(), movie.getPLOT(), movie.getCOUNTRYID(), movie.getAWARDS(), movie.getPOSTER(), movie.getRATE(),
                movie.getRATECOUNT(), movie.getCOMMENTCOUNT());

        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public void update(Movie movie) {
        String query = String.format("UPDATE movie_tbl SET " +
                        "title = %s, movieYear = %d,released = %s,runtime = %s,genre = %s,directorID = %d,plot = %s,countryID = %d,awards = %s,poster = %s,rate = %f,ratecount = %d,commentcount = %d",
                movie.getTITLE(), movie.getYEAR(), movie.getRELEASED(), movie.getRUNTIME(), movie.getGENRE(),
                movie.getDIRECTORID(), movie.getPLOT(), movie.getCOUNTRYID(), movie.getAWARDS(), movie.getPOSTER(), movie.getRATE(),
                movie.getRATECOUNT(), movie.getCOMMENTCOUNT());
        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public void delete(Movie movie) {
        String query = String.format("DELETE FROM movie_tbl WHERE movieID = %d ",movie.getFILM_ID());
        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public void deleteAll() {
        String query = "DELETE FROM movie_tbl";
        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public Movie getById(Movie movie) {
        String query = String.format("SELECT * FROM movie_tbl WHERE movieID = %d ",movie.getFILM_ID());
        ResultSet resultSet = DBUtil.dbExecuteQuery(query);
        try {
            while (resultSet.next()) {
                return (new Movie(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getInt(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7),
                        resultSet.getString(8),
                        resultSet.getInt(9),
                        resultSet.getString(10),
                        resultSet.getString(11),
                        resultSet.getDouble(12),
                        resultSet.getInt(13),
                        resultSet.getInt(14)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
