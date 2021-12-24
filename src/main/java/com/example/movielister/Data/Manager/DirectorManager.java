package com.example.movielister.Data.Manager;

import com.example.movielister.Data.Repository.DirectorRepository;
import com.example.movielister.Model.Director;
import com.example.movielister.Model.User;
import com.example.movielister.util.FXAlert;
import javafx.collections.ObservableList;

import java.util.Optional;

public class DirectorManager {
    private final DirectorRepository directorRepository;

    public DirectorManager(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    public ObservableList<Director> getAllDirector() {
        return directorRepository.getAll();
    }

    public void addDirector(Director director) {
        if (directorControl(director)) {
            directorRepository.insert(director);
        } else {
            FXAlert.showWarning("Yönetmen kayıt edilemedi! Girilen değeri kontrol edin.");
        }
    }

    public void deleteDirector(Director director) {
        boolean okay = FXAlert.showConfirmed(director.getDirector()+" silenecek onaylıyor musunuz?");
        if (okay) {
            directorRepository.delete(director);
        }
    }

    public void updateDirector(Director director) {
        if (directorControl(director)) {
            directorRepository.update(director);
        } else {
            FXAlert.showWarning("Yönetmen güncellenemedi! Girilen değeri kontrol edin.");
        }
    }

    public void deleteAllDirector(User user) {
        Optional<String> answer = FXAlert.input().withText("Tüm yönetmenler silenecek emin misiniz?","Şifrenizi girin:").showAndWaitString();
        if (answer.isPresent() && answer.get().equals(user.getPass())){
            directorRepository.deleteAll();
        }
    }

    public Director getDirectorById(int id) {
        Director director = directorRepository.getById(id);
        if (director != null) {
            return director;
        }
        FXAlert.showWarning("Verilen id'ye sahip yönetmen bulunamadı!");
        return null;
    }

    public boolean directorControl(Director director) {
        return director.getDirector() != null && !directorRepository.exists(director);
    }
}
