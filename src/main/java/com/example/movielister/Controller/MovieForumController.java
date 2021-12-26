package com.example.movielister.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MovieForumController extends BaseController implements Initializable {

    @FXML
    private ImageView iv_poster;

    @FXML
    private Label lbl_awards;

    @FXML
    private Label lbl_commentCount;

    @FXML
    private Label lbl_country;

    @FXML
    private Label lbl_director;

    @FXML
    private Label lbl_genre;

    @FXML
    private Label lbl_movieTitle;

    @FXML
    private Label lbl_plot;

    @FXML
    private Label lbl_rate;

    @FXML
    private Label lbl_rateCount;

    @FXML
    private Label lbl_runtime;

    @FXML
    private Label lbl_year;

    @FXML
    private Label lbl_yourRate;

    @FXML
    private Slider slider_yourRate;


    @FXML
    void openHomePageScene(MouseEvent event) {
        openStage(event, "homePage-view.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lbl_movieTitle.setText(DataPassController.movie.getTitle());
//        lbl_director.setText(DataPassController.movie.getDirectorID());
        lbl_awards.setText("Awards: " + DataPassController.movie.getAwards());
        lbl_year.setText("Released: " + DataPassController.movie.getReleased());
        lbl_runtime.setText("Run time: " + DataPassController.movie.getRuntime());
        lbl_rate.setText("Movielister rate: " + DataPassController.movie.getRate());
        lbl_rateCount.setText("Rate count: " + DataPassController.movie.getRateCount());
        lbl_commentCount.setText("Comment count: " + DataPassController.movie.getCommentCount());
        lbl_plot.setText("Plot: " + DataPassController.movie.getPlot());
        lbl_genre.setText("Genre: " + DataPassController.movie.getGenre());
//        lbl_country.setText(DataPassController.movie.getTitle());
        Image img = new Image(DataPassController.movie.getPoster());
        iv_poster.setImage(img);

        //

        // TODO KULLANICI YORUM EKLEYECEK, COMMENT İTEM YAPILACAK RECYCLER VİEW İLE ALTTAKİ ANCHOR PANE YORUMLAR EKLENECEK
    }
}
