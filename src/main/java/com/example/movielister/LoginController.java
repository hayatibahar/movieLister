package com.example.movielister;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button btn_signIn;

    @FXML
    private Button btn_signUp;

    @FXML
    private PasswordField pf_loginPassword;

    @FXML
    private TextField tf_loginNickname;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void openSignUpScene(MouseEvent event) {

    }


}