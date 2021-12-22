package com.example.movielister.Controller;

import com.example.movielister.Data.Manager.UserManager;
import com.example.movielister.Data.Manager.UserTypeManager;
import com.example.movielister.Data.Repository.UserRepository;
import com.example.movielister.Data.Repository.UserTypeRepository;
import com.example.movielister.Model.User;
import com.example.movielister.Model.UserType;
import com.example.movielister.util.FXAlert;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.*;

public class LoginController extends BaseController {

    @FXML
    private Button btn_signIn;

    @FXML
    private Button btn_signUp;

    @FXML
    private PasswordField pf_loginPassword;

    @FXML
    private TextField tf_loginNickname;


    @FXML
    void openSignUpScene(MouseEvent event) {
        openStage(event, "signUp-view.fxml");

    }

    @FXML
    void loginControl(MouseEvent event) {
        UserManager userManager = new UserManager(new UserRepository());
        UserTypeManager userTypeManager = new UserTypeManager(new UserTypeRepository());
        User user = userManager.auth(tf_loginNickname.getText(), pf_loginPassword.getText());
        if (user!=null){
            UserType userType = userTypeManager.getUserTypeById(user.getUserTypeID());
            if (userType.getInfo().equals("user")) {
                openStage(event, "homePage-view.fxml");
            } else if (userType.getInfo().equals("admin")) {
                openStage(event, "admin-view.fxml");
            }else{
                FXAlert.showWarning("Yeni kullanıcı tipi için açılacak ekran koda eklenmemiş!");
            }
        }

    }

}