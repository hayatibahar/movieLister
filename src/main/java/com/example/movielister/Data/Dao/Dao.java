package com.example.movielister.Data.Dao;

import com.example.movielister.Model.IEntity;
import javafx.collections.ObservableList;

public interface Dao<T extends IEntity> {
    ObservableList<T> getAll();
    void insert(T t);
    void update(T t);
    void delete(T t);
    void deleteAll();
    T getById(int id);
}
