package com.example.movielister.Data.Manager;

import com.example.movielister.Data.Repository.MovieRepository;
import com.example.movielister.Data.Repository.RateRepository;
import com.example.movielister.Data.Repository.UserRepository;
import com.example.movielister.Model.Rate;
import com.example.movielister.util.FXAlert;
import javafx.collections.ObservableList;

public class RateManager {
    private final RateRepository rateRepository;
    private final UserManager userManager = new UserManager(new UserRepository());
    private final MovieManager movieManager = new MovieManager(new MovieRepository());

    public RateManager(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    public ObservableList<Rate> getAllRate() {
        return rateRepository.getAll();
    }

    public void addRate(Rate rate) {
        if (rateControl(rate)) {
            rateRepository.insert(rate);
        } else {
            FXAlert.showWarning("Verilen puan kayıt edilemedi! Girilen değeri kontrol edin.");
        }
    }

    public void deleteRate(Rate rate) {
        boolean okay = FXAlert.showConfirmed("Puan silenecek onaylıyor musunuz?");
        if (okay) {
            rateRepository.delete(rate);
        }
    }

    public void updateRate(Rate rate) {
        if (rateControl(rate)) {
            rateRepository.update(rate);
        } else {
            FXAlert.showWarning("Puan güncellenemedi! Girilen değeri kontrol edin.");
        }
    }

    public Rate getRateById(int id) {
        Rate rate = rateRepository.getById(id);
        if (rate != null) {
            return rate;
        }
        FXAlert.showWarning("Verilen id'ye sahip rate bulunamadı!");
        return null;
    }

    public Rate getByUserIDAndMovieId(int movieID, int userID) {
        Rate rate = rateRepository.getByUserIDAndMovieId(movieID, userID);
        return rate;
    }

    private boolean rateControl(Rate rate) {
        return movieManager.getMovieById(rate.getMovieID()) != null
                && userManager.getUserById(rate.getUserID()) != null && rate.getRate() >= 0 && rate.getRate() <= 10;
    }

}
