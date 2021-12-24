package com.example.movielister.Data.Repository;

import com.example.movielister.Data.DBUtil;
import com.example.movielister.Model.User;

import java.sql.ResultSet;

public class CommonRepository {

    public static boolean exists(String existsQuery) {
        boolean bool = false;
        ResultSet resultSet = DBUtil.dbExecuteQuery(existsQuery);
        try {
            if (resultSet.next()) {
                bool = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bool;
    }
}
