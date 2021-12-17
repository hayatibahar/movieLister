package com.example.movielister;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {
    @FXML
    private Button btn_signUp;
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void openLoginScene(MouseEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("login-view.fxml"));
            stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
