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
        if (countryManager.getCountryById(movie.getCountryID()) != null &&
                directorManager.getDirectorById(movie.getDirectorID()) != null &&
                movie.getMovieYear() <= Year.now().getValue() && movie.getTitle() != null && movie.getReleased() != null
                && movie.getRuntime() != null && movie.getGenre() != null && movie.getPlot() != null && movie.getAwards() != null
                && movie.getPoster() != null && movie.getRate() >= 0 && movie.getRateCount() >= 0 && movie.getCommentCount() >= 0) {
            movieRepository.insert(movie);
            FXAlert.showInfo("Kayıt başarılı!");
        } else {
            FXAlert.showWarning("Film kayıt edilemedi! Tüm alanların doğru girildiğinden emin olun. " +
                    "Ayrıca yönetmen ya da ülkeler tablosunda bulunmayan veri girişi yapılamaz. " +
                    "Öncelikle ülke ve yönetmen kaydı yapmalısınız.");
        }
    }

    public void deleteMovie(Movie movie) {
        boolean okay = FXAlert.showConfirmed(movie.getTitle() + " silenecek onaylıyor musunuz?");
        if (okay) {
            movieRepository.delete(movie);
        }
    }

    public void updateMovie(Movie movie) {
        if (countryManager.getCountryById(movie.getCountryID()) != null &&
                directorManager.getDirectorById(movie.getDirectorID()) != null &&
                movie.getMovieYear() <= Year.now().getValue() && movie.getTitle() != null && movie.getReleased() != null
                && movie.getRuntime() != null && movie.getGenre() != null && movie.getPlot() != null && movie.getAwards() != null
                && movie.getPoster() != null && movie.getRate() >= 0 && movie.getRateCount() >= 0 && movie.getCommentCount() >= 0) {
            movieRepository.update(movie);
        } else {
            FXAlert.showWarning("Film güncellenemedi! Tüm alanların doğru girildiğinden emin olun. " +
                    "Ayrıca yönetmen ya da ülkeler tablosunda bulunmayan veri girişi yapılamaz. " +
                    "Öncelikle ülke ve yönetmen kaydı yapmalısınız.");
        }
    }

    public void deleteAllMovie(User user) {
        Optional<String> answer = FXAlert.input().withText("Tüm filmler silenecek emin misiniz?","Şifrenizi girin:").showAndWaitString();
        if (answer.isPresent() && answer.get().equals(user.getPass())){
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
        if (movie != null) {
            return movie;
        }
        return null;
    }

    public ObservableList<Movie> get(String genre, int year, Double rate) {
        ObservableList<Movie> movie = movieRepository.get(genre, year, rate);
        if (movie != null) {
            return movie;
        }
        return null;
    }


}
