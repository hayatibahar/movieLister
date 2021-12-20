package com.example.movielister.Data;


import com.example.movielister.Model.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;


public class MovieDatabase {
    private static Connection single_instance = null;

    public static ObservableList<Movie> getAllMovie() {
        ObservableList<Movie> movie = FXCollections.observableArrayList();
        try {

            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "fghj852");

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM movie_tbl");

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
            connection.close();
            return movie;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Connection getInstance() {
        if (single_instance == null) {
            try {

                single_instance = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "fghj852");

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return single_instance;
    }
}

