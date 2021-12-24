package com.example.movielister.Data.Manager;

import com.example.movielister.Data.Repository.CountryRepository;
import com.example.movielister.Data.Repository.DirectorRepository;
import com.example.movielister.Data.Repository.MovieRepository;
import com.example.movielister.Model.Movie;
import com.example.movielister.Model.User;
import com.example.movielister.util.FXAlert;
import javafx.collections.ObservableList;

import java.time.Year;
import java.util.Optional;

public class MovieManager {
    private final MovieRepository movieRepository;
    private final CountryManager countryManager = new CountryManager(new CountryRepository());
    private final DirectorManager directorManager = new DirectorManager(new DirectorRepository());

    public MovieManager(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public ObservableList<Movie> getAllMovie() {
        return movieRepository.getAll();
    }

    public void addMovie(Movie movie) {
        if (movieControl(movie)) {
            movieRepository.insert(movie);
            FXAlert.showInfo("Kayıt başarılı!");
        } else {
            FXAlert.showWarning("Film kayıt edilemedi!");
        }
    }

    public void deleteMovie(Movie movie) {
        boolean okay = FXAlert.showConfirmed(movie.getTitle() + " silenecek onaylıyor musunuz?");
        if (okay) {
            movieRepository.delete(movie);
            FXAlert.showInfo("Film silindi!");
        }
    }

    public void updateMovie(Movie movie) {
        if (movieControl(movie)) {
            movieRepository.update(movie);
            FXAlert.showInfo("Film güncellendi!");

        } else {
            FXAlert.showWarning("Film güncellenemedi!");
        }
    }

    public void deleteAllMovie(User user) {
        Optional<String> answer = FXAlert.input().withText("Tüm filmler silenecek emin misiniz?", "Şifrenizi girin:").showAndWaitString();
        if (answer.isPresent() && answer.get().equals(user.getPass())) {
            movieRepository.deleteAll();
        }
    }

    public Movie getMovieById(int id) {
        Movie movie = movieRepository.getById(id);
        if (movie != null) {
            return movie;
        }
        FXAlert.showWarning("Verilen id'ye sahip film bulunamadı!");
        return null;
    }

    public ObservableList<Movie> getByTitle(String title) {
        ObservableList<Movie> movie = movieRepository.getByTitle(title);
        return movie;
    }

    public ObservableList<Movie> get(String genre, int year, Double rate) {
        ObservableList<Movie> movie = movieRepository.get(genre, year, rate);
        return movie;
    }

    public boolean movieControl(Movie movie) {
        return countryManager.getCountryById(movie.getCountryID()) != null &&
                directorManager.getDirectorById(movie.getDirectorID()) != null &&
                movie.getMovieYear() <= Year.now().getValue() && movie.getTitle() != null && movie.getReleased() != null
                && movie.getRuntime() != null && movie.getGenre() != null && movie.getPlot() != null && movie.getAwards() != null
                && movie.getPoster() != null && movie.getRate() >= 0 && movie.getRateCount() >= 0 && movie.getCommentCount() >= 0
                && !movie.getTitle().isEmpty() && !movie.getReleased().isEmpty() && !movie.getRuntime().isEmpty() && !movie.getGenre().isEmpty()
                && !movie.getPoster().isEmpty() && !movie.getAwards().isEmpty() && !movie.getPlot().isEmpty();
    }


}
