package com.example.movielister.Controller;

import com.example.movielister.Data.Manager.MovieManager;
import com.example.movielister.Data.Repository.MovieRepository;
import com.example.movielister.Model.Movie;
import com.example.movielister.util.FXAlert;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMovieController extends BaseController implements Initializable {
    private MovieManager movieManager = new MovieManager(new MovieRepository());

    @FXML
    private TableColumn<Movie, String> awards;

    @FXML
    private TableColumn<Movie, Integer> commentCount;

    @FXML
    private TableColumn<Movie, Integer> countryID;

    @FXML
    private TableColumn<Movie, Integer> directorID;

    @FXML
    private TableColumn<Movie, String> genre;

    @FXML
    private TableColumn<Movie, Integer> id;

    @FXML
    private TableColumn<Movie, String> plot;

    @FXML
    private TableColumn<Movie, String> poster;

    @FXML
    private TableColumn<Movie, Double> rate;

    @FXML
    private TableColumn<Movie, Integer> rateCount;

    @FXML
    private TableColumn<Movie, String> released;

    @FXML
    private TableColumn<Movie, String> runtime;

    @FXML
    private TableView<Movie> table_movie;

    @FXML
    private TableColumn<Movie, String> title;

    @FXML
    private TableColumn<Movie, Integer> year;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_adminPage;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_homePage;

    @FXML
    private Button btn_update;

    @FXML
    private TextField tf_awards;

    @FXML
    private TextField tf_commentCount;

    @FXML
    private TextField tf_countryID;

    @FXML
    private TextField tf_directorID;

    @FXML
    private TextField tf_genre;

    @FXML
    private TextField tf_id;

    @FXML
    private TextField tf_plot;

    @FXML
    private TextField tf_poster;

    @FXML
    private TextField tf_rate;

    @FXML
    private TextField tf_rateCount;

    @FXML
    private TextField tf_released;

    @FXML
    private TextField tf_runtime;

    @FXML
    private TextField tf_title;

    @FXML
    private TextField tf_year;

    private ObservableList<Movie> movies;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        movies = movieManager.getAllMovie();

        poster.setCellValueFactory(new PropertyValueFactory<Movie, String>("poster"));
        title.setCellValueFactory(new PropertyValueFactory<Movie, String>("title"));
        directorID.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("directorID"));
        released.setCellValueFactory(new PropertyValueFactory<Movie, String>("released"));
        runtime.setCellValueFactory(new PropertyValueFactory<Movie, String>("runtime"));
        plot.setCellValueFactory(new PropertyValueFactory<Movie, String>("plot"));
        awards.setCellValueFactory(new PropertyValueFactory<Movie, String>("awards"));
        genre.setCellValueFactory(new PropertyValueFactory<Movie, String>("genre"));
        year.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("movieYear"));
        rate.setCellValueFactory(new PropertyValueFactory<Movie, Double>("rate"));
        rateCount.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("rateCount"));
        commentCount.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("commentCount"));
        id.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("movieID"));
        countryID.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("countryID"));
        table_movie.setItems(movies);
        tf_id.setText("0");
        setTextFieldFromTable();

    }

    private void setTextFieldFromTable() {
        table_movie.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (table_movie.getSelectionModel().getSelectedIndex() > -1) {
                    Movie movie = table_movie.getItems().get(table_movie.getSelectionModel().getSelectedIndex());
                    tf_id.setText(String.valueOf(movie.getMovieID()));
                    tf_title.setText(movie.getTitle());
                    tf_awards.setText(movie.getAwards());
                    tf_commentCount.setText(String.valueOf(movie.getCommentCount()));
                    tf_countryID.setText(String.valueOf(movie.getCountryID()));
                    tf_directorID.setText(String.valueOf(movie.getDirectorID()));
                    tf_genre.setText(movie.getGenre());
                    tf_plot.setText(movie.getPlot());
                    tf_poster.setText(movie.getPoster());
                    tf_rate.setText(String.valueOf(movie.getRate()));
                    tf_rateCount.setText(String.valueOf(movie.getRateCount()));
                    tf_commentCount.setText(String.valueOf(movie.getCommentCount()));
                    tf_runtime.setText(movie.getRuntime());
                    tf_year.setText(String.valueOf(movie.getMovieYear()));
                    tf_released.setText(String.valueOf(movie.getReleased()));
                }
            }
        });
    }

    @FXML
    void addMovie(MouseEvent event) {
        Movie movie = textFieldToMovie();
        if (movie != null) {
            movieManager.addMovie(movie);
            movies = movieManager.getAllMovie();
            table_movie.setItems(movies);
        }
    }


    @FXML
    void deleteMovie(MouseEvent event) {
        Movie movie = textFieldToMovie();
        if (movie != null) {
            movieManager.deleteMovie(movie);
            movies = movieManager.getAllMovie();
            table_movie.setItems(movies);
        }
    }

    @FXML
    void updateMovie(MouseEvent event) {
        Movie movie = textFieldToMovie();
        if (movie != null) {
            movieManager.updateMovie(movie);
            movies = movieManager.getAllMovie();
            table_movie.setItems(movies);
        }
    }

    @FXML
    void openAdminCountryScene(MouseEvent event) {
        openStage(event, "adminCountry-view.fxml");
    }

    @FXML
    void openAdminDirectorScene(MouseEvent event) {
        openStage(event, "adminDirector-view.fxml");
    }

    @FXML
    void openAdminPageScene(MouseEvent event) {
        openStage(event, "admin-view.fxml");
    }

    private Movie textFieldToMovie() {
        Movie movie = null;
        if (!tf_year.getText().isEmpty() && !tf_directorID.getText().isEmpty()
                && !tf_countryID.getText().isEmpty() && !tf_rate.getText().isEmpty()
                && !rateCount.getText().isEmpty() && !commentCount.getText().isEmpty()) {
            try {
                movie = new Movie(
                        Integer.parseInt(tf_id.getText()),
                        tf_title.getText().replace("'", "^"),
                        Integer.parseInt(tf_year.getText()),
                        tf_released.getText(),
                        tf_runtime.getText().replace("'", "^"),
                        tf_genre.getText().replace("'", "^"),
                        Integer.parseInt(tf_directorID.getText()),
                        tf_plot.getText().replace("'", "^"),
                        Integer.parseInt(tf_countryID.getText()),
                        tf_awards.getText().replace("'", "^"),
                        tf_poster.getText().replace("'", "^"),
                        Double.parseDouble(tf_rate.getText()),
                        Integer.parseInt(tf_rateCount.getText()),
                        Integer.parseInt(tf_commentCount.getText())
                );
            } catch (Exception e) {
                FXAlert.showException(e, "Alanların doğru girildiğinden emin olun! Released örneği : 14-OCT-94");
            }
        } else {
            FXAlert.showWarning("Lütfen boş alan bırakmayınız!");
        }
        return movie;
    }

}
