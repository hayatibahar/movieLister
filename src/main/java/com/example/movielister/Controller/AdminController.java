package com.example.movielister.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/*TODO
    Button - Director & Country butonlarına işlevleri eklenecek.
*/


public class AdminController extends BaseController implements Initializable {

    @FXML
    private Label lbl_nickname;

    @FXML
    void openHomePageScene(MouseEvent event) {
        openStage(event, "homePage-view.fxml");
    }

    @FXML
    void openLoginScene(MouseEvent event) {
        openStage(event, "login-view.fxml");
    }

    @FXML
    void openAdminAccountScene(MouseEvent event) {
        openStage(event, "adminAccount-view.fxml");
    }

    @FXML
    void openAdminCountryScene(MouseEvent event) {
        openStage(event, "adminCountry-view.fxml");
    }

    @FXML
    void openAdminDirectoryScene(MouseEvent event) {
        openStage(event, "adminDirector-view.fxml");
    }

    @FXML
    void openAdminCommentScene(MouseEvent event) {
        openStage(event, "adminComment-view.fxml");
    }

    @FXML
    void openAdminMovieScene(MouseEvent event) {
        openStage(event, "adminMovie-view.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbl_nickname.setText(DataPassController.user.getNickname());
    }


}
