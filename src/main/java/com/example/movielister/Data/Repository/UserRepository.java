package com.example.movielister.Data.Repository;

import com.example.movielister.Data.DBUtil;
import com.example.movielister.Data.Dao.Dao;
import com.example.movielister.Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class UserRepository implements Dao<User> {
    @Override
    public ObservableList<User> getAll() {
        ObservableList<User> users = FXCollections.observableArrayList();
        String query = "SELECT * FROM user_tbl";
        ResultSet resultSet = DBUtil.dbExecuteQuery(query);
        try {
            while (resultSet.next()) {
                users.add(new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void insert(User user) {
        String query = String.format("INSERT INTO user_tbl (nickname,pass,userTypeID) values ('%s','%s',%d)",
                user.getNickname(), user.getPass(), user.getUserTypeID());

        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public void update(User user) {
        String query = String.format("UPDATE user_tbl SET nickname = '%s',pass = '%s', userTypeID = %d WHERE userID = %d",
                user.getNickname(), user.getPass(), user.getUserTypeID(), user.getUserID());

        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public void delete(User user) {
        String query = String.format("DELETE FROM user_tbl WHERE userID = %d",
                user.getUserID());

        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public void deleteAll() {
        String query = "DELETE FROM user_tbl";

        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public User getById(int id) {
        String query = String.format("SELECT * FROM user_tbl WHERE userID = %d",id);
        ResultSet resultSet = DBUtil.dbExecuteQuery(query);
        try {
            if (resultSet.next()) {
                return (new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public User auth(String nickname,String pass) {
        String query = String.format("SELECT * FROM user_tbl WHERE nickname = '%s' and pass = '%s'",nickname,pass);
        ResultSet resultSet = DBUtil.dbExecuteQuery(query);
        User newUser = null;
        try {
            while (resultSet.next()) {
                newUser = (new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newUser;
    }

    public boolean exists(User user) {
        return CommonRepository.exists(String.format("SELECT * FROM user_tbl WHERE nickname = '%s'", user.getNickname()));
    }

}
