package com.example.movielister.Data.Manager;

import com.example.movielister.Data.Dao.Dao;
import com.example.movielister.Data.Repository.MovieRepository;
import com.example.movielister.Model.Movie;
import javafx.collections.ObservableList;

public class MovieManager{
    private MovieRepository movieRepository;

    public MovieManager(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    // TODO KONTROL YAPILARI EKLENECEK
    public ObservableList<Movie> getAllMovie(){
        return movieRepository.getAll();
    }

    public void addMovie(Movie movie){
        movieRepository.insert(movie);
    }

    public void deleteMovie(Movie movie){
        movieRepository.delete(movie);
    }

    public void updateMovie(Movie movie){
        movieRepository.update(movie);
    }

    public void deleteAllMovie(){
        movieRepository.deleteAll();
    }

    public void getMovieById(Movie movie){
        movieRepository.getById(movie);
    }

}
