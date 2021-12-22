package com.example.movielister.Data.Manager;

import com.example.movielister.Data.Repository.UserRepository;
import com.example.movielister.Data.Repository.UserTypeRepository;
import com.example.movielister.Model.User;
import com.example.movielister.util.FXAlert;
import javafx.collections.ObservableList;
import java.util.Optional;

public class UserManager {
    private final UserRepository userRepository;
    private final UserTypeManager userTypeManager = new UserTypeManager(new UserTypeRepository());

    public UserManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ObservableList<User> getAllUser() {
        return userRepository.getAll();
    }

    public void addUser(User user) {
        if (user.getNickname() != null && user.getPass() != null && !userRepository.getAll().contains(user)
                && userTypeManager.getUserTypeById(user.getUserTypeID()) != null) {
            userRepository.insert(user);
        } else {
            FXAlert.showWarning("Kullanıcı kayıt edilemedi! Boş alan bırakmayın. Eğer boş alan yok ise farklı bir kullanıcı adı girin.");
        }
    }

    public void deleteUser(User user) {
        boolean okay = FXAlert.showConfirmed(user.getNickname()+" silenecek onaylıyor musunuz?");
        if (okay) {
            userRepository.delete(user);
        }
    }

    public void updateUser(User user) {
        if (user.getNickname() != null && user.getPass() != null && !userRepository.getAll().contains(user)
                && userTypeManager.getUserTypeById(user.getUserTypeID()) != null) {
            userRepository.update(user);
        } else {
            FXAlert.showWarning("Kullanıcı güncellenemedi! Boş alan bırakmayın. Eğer boş alan yok ise farklı bir kullanıcı adı girin.");
        }
    }

    public void deleteAllUser(User user) {
        Optional<String> answer = FXAlert.input().withText("Tüm kullanıcılar silenecek emin misiniz?","Şifrenizi girin:").showAndWaitString();
        if (answer.isPresent() && answer.get().equals(user.getPass())){
            userRepository.deleteAll();
        }
    }

    public User getUserById(int id) {
        if(userRepository.getById(id) != null){
            return userRepository.getById(id);
        }
        FXAlert.showWarning("Verilen id'ye sahip kullanıcı bulunamadı!");
        return null;
    }

    public User auth(String nickname, String pass) {
        if (userRepository.auth(nickname, pass) != null) {
            return userRepository.auth(nickname, pass);
        }
        FXAlert.showWarning("Kullanıcı adı veya şifre hatalı!");
        return null;
    }
}
