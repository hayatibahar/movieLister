package com.example.movielister;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    void openHomePageScene(MouseEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("homePage-view.fxml"));
            stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

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

    @FXML
    void openAdminAccountScene(MouseEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("adminAccount-view.fxml"));
            stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void openAdminCommentScene(MouseEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("adminComment-view.fxml"));
            stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void openAdminMovieScene(MouseEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("adminMovie-view.fxml"));
            stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
