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
        if (countryManager.getCountryById(movie.getCOUNTRYID()) != null &&
                directorManager.getDirectorById(movie.getDIRECTORID()) != null &&
                movie.getYEAR() <= Year.now().getValue() && movie.getTITLE() != null && movie.getRELEASED() != null
                && movie.getRUNTIME() != null && movie.getGENRE() != null && movie.getPLOT() != null && movie.getAWARDS() != null
                && movie.getPOSTER() != null && movie.getRATE() >= 0 && movie.getRATECOUNT() >= 0 && movie.getCOMMENTCOUNT() >= 0) {
            movieRepository.insert(movie);
        } else {
            FXAlert.showWarning("Film kayıt edilemedi! Tüm alanların doğru girildiğinden emin olun. " +
                    "Ayrıca yönetmen ya da ülkeler tablosunda bulunmayan veri girişi yapılamaz. " +
                    "Öncelikle ülke ve yönetmen kaydı yapmalısınız.");
        }
    }

    public void deleteMovie(Movie movie) {
        boolean okay = FXAlert.showConfirmed(movie.getTITLE()+" silenecek onaylıyor musunuz?");
        if (okay) {
            movieRepository.delete(movie);
        }
    }

    public void updateMovie(Movie movie) {
        if (countryManager.getCountryById(movie.getCOUNTRYID()) != null &&
                directorManager.getDirectorById(movie.getDIRECTORID()) != null &&
                movie.getYEAR() <= Year.now().getValue() && movie.getTITLE() != null && movie.getRELEASED() != null
                && movie.getRUNTIME() != null && movie.getGENRE() != null && movie.getPLOT() != null && movie.getAWARDS() != null
                && movie.getPOSTER() != null && movie.getRATE() >= 0 && movie.getRATECOUNT() >= 0 && movie.getCOMMENTCOUNT() >= 0) {
            movieRepository.update(movie);
        } else {
            FXAlert.showWarning("Film güncellenemedi! Tüm alanların doğru girildiğinden emin olun. " +
                    "Ayrıca yönetmen ya da ülkeler tablosunda bulunmayan veri girişi yapılamaz. " +
                    "Öncelikle ülke ve yönetmen kaydı yapmalısınız.");
        }
    }

    public void deleteAllMovie(User user) {
        // TODO REFACTOR; KULLANICI ŞİFRESİ Mİ SORALIM, DATABASE ŞİFRESİ Mİ
        Optional<String> answer = FXAlert.input().withText("Tüm filmler silenecek emin misiniz?","Şifrenizi girin:").showAndWaitString();
        if (answer.isPresent() && answer.get().equals(user.getPass())){
            movieRepository.deleteAll();
        }
    }

    public Movie getMovieById(int id) {
        if(movieRepository.getById(id) != null){
            return movieRepository.getById(id);
        }
        FXAlert.showWarning("Verilen id'ye sahip film bulunamadı!");
        return null;
    }

}
