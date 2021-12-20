package com.example.movielister.Controller;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;


public class SignUpController extends BaseController {
    @FXML
    private Button btn_signUp;

    @FXML
    void openLoginScene(MouseEvent event) {
        openStage(event,"login-view.fxml");
    }

}
