package com.example.movielister.Data.Manager;

import com.example.movielister.Data.Repository.MovieRepository;
import com.example.movielister.Data.Repository.UserRepository;
import com.example.movielister.Data.Repository.WatchListRepository;
import com.example.movielister.Model.User;
import com.example.movielister.Model.WatchList;
import com.example.movielister.util.FXAlert;
import javafx.collections.ObservableList;

import java.util.Optional;

public class WatchListManager {
    private final WatchListRepository watchListRepository;
    private final UserManager userManager = new UserManager(new UserRepository());
    private final MovieManager movieManager = new MovieManager(new MovieRepository());

    public WatchListManager(WatchListRepository watchListRepository) {
        this.watchListRepository = watchListRepository;
    }

    public ObservableList<WatchList> getAllWatchList() {
        return watchListRepository.getAll();
    }

    public void addWatchList(WatchList watchList) {
        if (movieManager.getMovieById(watchList.getMovieID()) != null
                && userManager.getUserById(watchList.getUserID()) !=null){
            watchListRepository.insert(watchList);
        }else{
            FXAlert.showWarning("İzleme listesi kayıt edilemedi! Girilen değeri kontrol edin.");
        }
    }

    public void deleteWatchList(WatchList watchList) {
        boolean okay = FXAlert.showConfirmed(watchList.getListID()+" silenecek onaylıyor musunuz?");
        if (okay) {
            watchListRepository.delete(watchList);
        }
    }

    public void updateWatchList(WatchList watchList) {
        if (movieManager.getMovieById(watchList.getMovieID()) != null
                && userManager.getUserById(watchList.getUserID()) !=null){
            watchListRepository.update(watchList);
        }else{
            FXAlert.showWarning("İzleme listesi güncellenemedi! Girilen değeri kontrol edin.");
        }
    }

    public void deleteAllWatchList(User user) {
        Optional<String> answer = FXAlert.input().withText("Tüm izleme listesi silenecek emin misiniz?","Şifrenizi girin:").showAndWaitString();
        if (answer.isPresent() && answer.get().equals(user.getPass())){
            watchListRepository.deleteAll();
        }
    }

    public WatchList getWatchListById(int id) {
        if(watchListRepository.getById(id) != null){
            return watchListRepository.getById(id);
        }
        FXAlert.showWarning("Verilen id'ye sahip izleme listesi bulunamadı!");
        return null;
    }

    public ObservableList<WatchList> getAllByUserID(int id) {
        if(watchListRepository.getById(id) != null){
            return watchListRepository.getAllByUserID(id);
        }
        FXAlert.showWarning("İzleme listeniz boş durumda.");
        return null;
    }
}