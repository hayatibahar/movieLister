package com.example.movielister.Controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;


public class AdminController extends BaseController {

    @FXML
    void openHomePageScene(MouseEvent event) {
        openStage(event,"homePage-view.fxml");
    }

    @FXML
    void openLoginScene(MouseEvent event) {
        openStage(event,"login-view.fxml");
    }

    @FXML
    void openAdminAccountScene(MouseEvent event) {
        openStage(event,"adminAccount-view.fxml");
    }

    @FXML
    void openAdminCommentScene(MouseEvent event) {
        openStage(event,"adminComment-view.fxml");
    }

    @FXML
    void openAdminMovieScene(MouseEvent event) {
        openStage(event,"adminMovie-view.fxml");
    }
}
