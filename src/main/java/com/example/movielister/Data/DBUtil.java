package com.example.movielister.Data;

import com.example.movielister.util.FXAlert;

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
            FXAlert.showException(e, "JDBC Driver bağlantı sırasında hata!");

        }

        try {
            conn = DriverManager.getConnection(connStr, user, password);
        } catch (SQLException e) {
            FXAlert.showException(e, "Database connection sırasında hata!");
        }
    }

    public static void dbDisconnect() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (Exception e) {
            FXAlert.showException(e, "Database disconnect sırasında hata!");
        }

    }

    public static ResultSet dbExecuteQuery(String queryStmt) {
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
            FXAlert.showException(e, "Execute query sırasında hata!");

        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    FXAlert.showException(e, "Resultset kapatılırken hata!");
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    FXAlert.showException(e, "Statement kapatılırken hata!");
                }
            }
            dbDisconnect();
        }
        return crs;
    }

    //DB Execute Update (For Update/Insert/Delete) Operation
    public static void dbExecuteUpdate(String sqlStmt) {
        Statement stmt = null;
        try {
            dbConnect();
            System.out.println("Execute statement: " + sqlStmt + "\n");
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            FXAlert.showException(e, "Execute update sırasında hata!");
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    FXAlert.showException(e, "Statement kapatılırken hata!");
                }
            }
            dbDisconnect();
        }
    }
}

