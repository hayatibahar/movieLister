package com.example.movielister.Data;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class DBUtil {
    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String user = "system";
    private static final String password = "fghj852";
    private static final String connStr = "jdbc:oracle:thin:@localhost:1521:XE";
    private static Connection conn = null;

    public static void dbConnect() {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your Oracle JDBC Driver?");
            e.printStackTrace();
        }

        System.out.println("Oracle JDBC Driver Registered!");

        try {
            conn = DriverManager.getConnection(connStr, user, password);
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console" + e);
            e.printStackTrace();
        }
    }

    public static void dbDisconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static ResultSet dbExecuteQuery(String queryStmt){
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSet crs = null;
        try {
            dbConnect();
            System.out.println("Select statement: " + queryStmt + "\n");

            stmt = conn.createStatement();

            resultSet = stmt.executeQuery(queryStmt);

            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            dbDisconnect();
        }
        return crs;
    }

    //DB Execute Update (For Update/Insert/Delete) Operation
    public static void dbExecuteUpdate(String sqlStmt){
        Statement stmt = null;
        try {
            dbConnect();
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            dbDisconnect();
        }
    }
}

