package com.example.movielister.Controller;

import com.example.movielister.Data.Manager.*;
import com.example.movielister.Data.Repository.*;
import com.example.movielister.Model.*;
import com.example.movielister.Model.Dto.CommentDetail;
import com.example.movielister.MovieListerApplication;
import com.example.movielister.util.FXAlert;
import com.example.movielister.util.RecyclerView;
import com.example.movielister.util.RippleViewRow;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class MovieForumController extends BaseController implements Initializable {
    CommentManager commentManager = new CommentManager(new CommentRepository());
    DirectorManager directorManager = new DirectorManager(new DirectorRepository());
    CountryManager countryManager = new CountryManager(new CountryRepository());
    WatchListManager watchListManager = new WatchListManager(new WatchListRepository());
    RateManager rateManager = new RateManager(new RateRepository());


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
    private TextArea ta_comment;

    @FXML
    private TextField tf_title;

    @FXML
    private RadioButton rb_watch;
    @FXML
    private RadioButton rb_watched;

    @FXML
    private RecyclerView<CommentDetail> items;

    private ObservableList<CommentDetail> commentDetails;
    private Country country;
    private Director director;
    private WatchList watchList;
    private Rate rate;
    private int yourRate;

    @FXML
    void addWatchList(MouseEvent event) {
        if (rb_watch.isSelected() && watchList == null) {
            watchListManager.addWatchList(new WatchList(
                    DataPassController.user.getUserID(),
                    DataPassController.movie.getMovieID(),
                    0
            ));
        } else if (rb_watch.isSelected() && watchList != null) {
            watchList.setStatus(0);
            watchListManager.updateWatchList(watchList);
        }
        clearRbWatched();
    }

    @FXML
    void addWatchedList(MouseEvent event) {
        if (rb_watched.isSelected() && watchList == null) {
            watchListManager.addWatchList(new WatchList(
                    DataPassController.user.getUserID(),
                    DataPassController.movie.getMovieID(),
                    1
            ));
        } else if (rb_watched.isSelected() && watchList != null) {
            watchList.setStatus(1);
            watchListManager.updateWatchList(watchList);
        }
        clearRbWatch();

    }

    @FXML
    void openHomePageScene(MouseEvent event) {
        if (!rb_watch.isSelected() && !rb_watched.isSelected() && watchList != null) {
            watchListManager.deleteWatchList(watchList);
        }
        if (rate != null) {
            rate.setRate(yourRate);
            rateManager.updateRate(rate);
        } else {
            if (yourRate != 0) {
                rateManager.addRate(new Rate(
                        DataPassController.user.getUserID(),
                        DataPassController.movie.getMovieID(),
                        yourRate
                ));
            }
        }
        openStage(event, "homePage-view.fxml");
    }

    @FXML
    void addComment(MouseEvent event) {
        if (!ta_comment.getText().trim().isEmpty() && !tf_title.getText().trim().isEmpty()) {
            commentManager.addComment(new Comment(
                    DataPassController.user.getUserID(),
                    DataPassController.movie.getMovieID(),
                    tf_title.getText(),
                    ta_comment.getText(),
                    new Timestamp(System.currentTimeMillis()).toLocalDateTime().toLocalDate().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"))
            ));
        } else {
            FXAlert.showInfo("Başlık ve yorum alanlarını eksiksiz giriniz.");
        }
    }

    private void clearRbWatched() {
        if (rb_watched.isSelected()) {
            rb_watched.setSelected(false);
        }
        watchList = watchListManager.getByUserIDAndMovieId(DataPassController.movie.getMovieID(), DataPassController.user.getUserID());
    }

    private void clearRbWatch() {
        if (rb_watch.isSelected()) {
            rb_watch.setSelected(false);
        }
        watchList = watchListManager.getByUserIDAndMovieId(DataPassController.movie.getMovieID(), DataPassController.user.getUserID());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        watchList = watchListManager.getByUserIDAndMovieId(DataPassController.movie.getMovieID(), DataPassController.user.getUserID());
        rate = rateManager.getByUserIDAndMovieId(DataPassController.movie.getMovieID(), DataPassController.user.getUserID());
        country = countryManager.getCountryById(DataPassController.movie.getCountryID());
        director = directorManager.getDirectorById(DataPassController.movie.getDirectorID());
        commentDetails = commentManager.getDetailsByMovieID(DataPassController.movie.getMovieID());
        lbl_movieTitle.setText(DataPassController.movie.getTitle());
        lbl_director.setText(director.getDirector());
        lbl_awards.setText("Awards: " + DataPassController.movie.getAwards());
        lbl_year.setText("Released: " + Timestamp.valueOf(DataPassController.movie.getReleased()).toLocalDateTime().toLocalDate());
        lbl_runtime.setText("Run time: " + DataPassController.movie.getRuntime());
        lbl_rate.setText("Movielister rate: " + DataPassController.movie.getRate());
        lbl_rateCount.setText("Rate count: " + DataPassController.movie.getRateCount());
        lbl_commentCount.setText("Comment count: " + DataPassController.movie.getCommentCount());
        lbl_plot.setText("Plot: " + DataPassController.movie.getPlot());
        lbl_genre.setText("Genre: " + DataPassController.movie.getGenre());
        lbl_country.setText(country.getCountry());
        Image img = new Image(DataPassController.movie.getPoster());
        iv_poster.setImage(img);
        if (watchList != null && watchList.getStatus() == 1) {
            rb_watched.setSelected(true);
        } else if (watchList != null && watchList.getStatus() == 0) {
            rb_watch.setSelected(true);
        }
        if (rate != null) {
            yourRate = rate.getRate();
            lbl_yourRate.setText("Your rate: " + yourRate);
            slider_yourRate.setValue(yourRate);
        } else {
            lbl_yourRate.setText("Your rate: 0");
        }
        Adapter adapter = new Adapter();
        items.setAdapter(adapter);
        items.getItems().addAll(commentDetails);


        slider_yourRate.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                yourRate = t1.intValue();
                lbl_yourRate.setText("Your rate: " + yourRate);
            }
        });

    }

    public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
        @Override
        public RecyclerView.ViewRow call(ListView param) {
            return new RippleViewRow(this);
        }

        @Override
        public Adapter.Holder onCreateViewHolder(FXMLLoader loader) {
            loader.setLocation(MovieListerApplication.class.getResource("comment-item.fxml"));
            return new Adapter.Holder(loader);
        }

        @Override
        public void onBindViewHolder(Adapter.Holder holder, Object item) {
            CommentDetail commentDetail = (CommentDetail) item;

            Platform.runLater(() -> {
                holder.lbl_user.setText("commented by " + commentDetail.getNickname());
                holder.lbl_date.setText(String.valueOf(Timestamp.valueOf(commentDetail.getDate()).toLocalDateTime().toLocalDate()));
                holder.lbl_title.setText(commentDetail.getTitle());
                holder.ta_comment.setText(commentDetail.getComment());
            });


        }

        public static class Holder extends RecyclerView.ViewHolder {

            @FXML
            Label lbl_user;
            @FXML
            Label lbl_date;
            @FXML
            Label lbl_title;
            @FXML
            TextArea ta_comment;

            public Holder(FXMLLoader loader) {
                super(loader);
            }
        }
    }

}
