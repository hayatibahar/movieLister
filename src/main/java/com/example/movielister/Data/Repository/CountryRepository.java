package com.example.movielister.Data.Repository;

import com.example.movielister.Data.DBUtil;
import com.example.movielister.Data.Dao.Dao;
import com.example.movielister.Model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;

public class CountryRepository implements Dao<Country> {
    @Override
    public ObservableList<Country> getAll() {
        ObservableList<Country> countries = FXCollections.observableArrayList();
        String query = "SELECT * FROM country_tbl";
        ResultSet resultSet = DBUtil.dbExecuteQuery(query);
        try {
            while (resultSet.next()) {
                countries.add(new Country(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countries;
    }

    @Override
    public void insert(Country country) {
        String query = String.format("INSERT INTO country_tbl (country) values ('%s')", country.getCountry());

        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public void update(Country country) {
        String query = String.format("UPDATE country_tbl SET country = '%s' WHERE countryID = %d",
                country.getCountry(), country.getCountryID());

        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public void delete(Country country) {
        String query = String.format("DELETE FROM country_tbl WHERE countryID = %d", country.getCountryID());

        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public void deleteAll() {
        String query = "DELETE FROM country_tbl";

        DBUtil.dbExecuteUpdate(query);
    }

    @Override
    public Country getById(int id) {
        String query = String.format("SELECT * FROM country_tbl WHERE countryID = %d", id);
        ResultSet resultSet = DBUtil.dbExecuteQuery(query);
        try {
            if (resultSet.next()) {
                return (new Country(
                        resultSet.getInt(1),
                        resultSet.getString(2)
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean exists(Country country) {
        return CommonRepository.exists(String.format("SELECT * FROM country_tbl WHERE country = '%s'", country.getCountry()));
    }

}
