package com.example.movielister.Data.Repository;

import com.example.movielister.Data.DBUtil;
import com.example.movielister.Data.Dao.Dao;
import com.example.movielister.Model.User;
import com.example.movielister.Model.UserType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class UserTypeRepository implements Dao<UserType> {
    @Override
    public ObservableList<UserType> getAll() {
        ObservableList<UserType> userTypes = FXCollections.observableArrayList();
        String query = "SELECT * FROM usertype_tbl";
        ResultSet resultSet = DBUtil.dbExecuteQuery(query);
        try {
            while (resultSet.next()) {
                userTypes.add(new UserType(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userTypes;
    }

    @Override
    public void insert(UserType userType) {
        String query = String.format("INSERT INTO usertype_tbl (info) values ('%s')",
                userType.getInfo());

        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public void update(UserType userType) {
        String query = String.format("UPDATE usertype_tbl SET info = '%s' WHERE userTypeID = %d",
                userType.getInfo(),userType.getUserTypeID());

        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public void delete(UserType userType) {
        String query = String.format("DELETE FORM usertype_tbl WHERE userTypeID = %d", userType.getUserTypeID());

        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public void deleteAll() {
        String query = "DELETE FORM usertype_tbl";

        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public UserType getById(int id) {
        String query = String.format("SELECT * FROM usertype_tbl WHERE userTypeID = %d",id);
        ResultSet resultSet = DBUtil.dbExecuteQuery(query);
        try {
            if (resultSet.next()) {
                return (new UserType(
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
