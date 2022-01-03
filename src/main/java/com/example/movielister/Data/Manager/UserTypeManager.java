package com.example.movielister.Data.Manager;

import com.example.movielister.Data.Repository.UserTypeRepository;
import com.example.movielister.Model.User;
import com.example.movielister.Model.UserType;
import com.example.movielister.util.FXAlert;
import javafx.collections.ObservableList;

import java.util.Optional;

public class UserTypeManager {
    private final UserTypeRepository userTypeRepository;

    public UserTypeManager(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }

    public ObservableList<UserType> getAllUserType() {
        return userTypeRepository.getAll();
    }

    public void addUserType(UserType userType) {
        if (userType.getInfo() !=null && !userTypeRepository.getAll().contains(userType)){
            userTypeRepository.insert(userType);
        }else{
            FXAlert.showWarning("Kullanıcı tipi kayıt edilemedi! Girilen değerleri kontrol edin.");
        }
    }

    public void deleteUserType(UserType userType) {
        boolean okay = FXAlert.showConfirmed(userType.getInfo()+" silenecek onaylıyor musunuz?");
        if (okay) {
            userTypeRepository.delete(userType);
        }
    }

    public void updateUserType(UserType userType) {
        if (userType.getInfo() !=null && !userTypeRepository.getAll().contains(userType)){
            userTypeRepository.update(userType);
        }else{
            FXAlert.showWarning("Kullanıcı tipi güncellenemedi! Girilen değerleri kontrol edin.");
        }
    }

    public void deleteAllUserType(User user) {
        Optional<String> answer = FXAlert.input().withText("Tüm kullanıcı tipleri silenecek emin misiniz?","Şifrenizi girin:").showAndWaitString();
        if (answer.isPresent() && answer.get().equals(user.getPass())){
            userTypeRepository.deleteAll();
        }
    }

    public UserType getUserTypeById(int id) {
        UserType userType = userTypeRepository.getById(id);
        if (userType != null) {
            return userType;
        }
        FXAlert.showWarning("Kullanıcı tipinde hata var!");
        return null;
    }

}
