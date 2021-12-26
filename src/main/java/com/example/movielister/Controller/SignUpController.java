package com.example.movielister.Controller;

import com.example.movielister.Data.Manager.UserManager;
import com.example.movielister.Data.Repository.UserRepository;
import com.example.movielister.Model.User;
import com.example.movielister.util.FXAlert;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class SignUpController extends BaseController {

    UserManager userManager = new UserManager(new UserRepository());
    @FXML
    private Button btn_signUp;

    @FXML
    private ImageView iv_back;

    @FXML
    private TextField tf_nickname;

    @FXML
    private TextField tf_pass;

    @FXML
    private TextField tf_passConfirm;


    @FXML
    void openLoginScene(MouseEvent event) {
        openStage(event, "login-view.fxml");
    }


    @FXML
    void signUp(MouseEvent event) {
        if (tf_pass.getText().equals(tf_passConfirm.getText())) {
            // TODO USER TYPE DEĞİŞTİR
            User user = new User(tf_nickname.getText(), tf_pass.getText(), 1);
            userManager.addUser(user);
        } else {
            FXAlert.showWarning("Şifreler uyuşmuyor!");
        }

    }

}
