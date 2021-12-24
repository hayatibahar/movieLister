package com.example.movielister.Data.Manager;

import com.example.movielister.Data.Repository.CountryRepository;
import com.example.movielister.Model.Country;
import com.example.movielister.Model.User;
import com.example.movielister.util.FXAlert;
import javafx.collections.ObservableList;

import java.util.Optional;

public class CountryManager {
    private final CountryRepository countryRepository;

    public CountryManager(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public ObservableList<Country> getAllCountry() {
        return countryRepository.getAll();
    }

    public void addCountry(Country country) {
        if (country.getCountry() !=null && !countryRepository.getAll().contains(country)){
            countryRepository.insert(country);
        }else{
            FXAlert.showWarning("Ülke kayıt edilemedi! Girilen değeri kontrol edin.");
        }
    }

    public void deleteCountry(Country country) {
        boolean okay = FXAlert.showConfirmed(country.getCountry()+" silenecek onaylıyor musunuz?");
        if (okay) {
            countryRepository.delete(country);
        }
    }

    public void updateCountry(Country country) {
        if (country.getCountry() !=null && !countryRepository.getAll().contains(country)){
            countryRepository.update(country);
        }else{
            FXAlert.showWarning("Ülke güncellenemedi! Girilen değeri kontrol edin.");
        }
    }

    public void deleteAllCountry(User user) {
        Optional<String> answer = FXAlert.input().withText("Tüm ülkeler silenecek emin misiniz?","Şifrenizi girin:").showAndWaitString();
        if (answer.isPresent() && answer.get().equals(user.getPass())){
            countryRepository.deleteAll();
        }
    }

    public Country getCountryById(int id) {
        Country country = countryRepository.getById(id);
        if (country != null) {
            return country;
        }
        FXAlert.showWarning("Verilen id'ye sahip ülke bulunamadı!");
        return null;
    }
}
