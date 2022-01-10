package com.example.movielister.Controller;

import com.example.movielister.Data.Manager.DirectorManager;
import com.example.movielister.Data.Manager.MovieManager;
import com.example.movielister.Data.Repository.DirectorRepository;
import com.example.movielister.Data.Repository.MovieRepository;
import com.example.movielister.Model.Director;
import com.example.movielister.Model.Movie;
import com.example.movielister.MovieListerApplication;
import com.example.movielister.util.FXAlert;
import com.example.movielister.util.RecyclerView;
import com.example.movielister.util.RippleViewRow;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HomePageController extends BaseController implements Initializable {

    private final MovieService service = new MovieService();
    DirectorManager directorManager = new DirectorManager(new DirectorRepository());
    @FXML
    private AnchorPane ap_homePage;
    @FXML
    private TextField tf_search;
    @FXML
    private AnchorPane ap_recyclerView;
    @FXML
    private ComboBox cb_genre;
    @FXML
    private ComboBox cb_rate;
    @FXML
    private ComboBox cb_year;
    @FXML
    private RecyclerView<Movie> items;
    private ObservableList<Movie> movies;
    private ObservableList<Director> directors;
    private MovieManager movieManager;

    @FXML
    void getMovieComboBox(MouseEvent event) {
        if (!cb_rate.getSelectionModel().isEmpty() &&
                !cb_genre.getSelectionModel().isEmpty() &&
                !cb_year.getSelectionModel().isEmpty()) {
            String genre = cb_genre.getSelectionModel().getSelectedItem().toString();
            int year = Integer.parseInt(cb_year.getSelectionModel().getSelectedItem().toString());
            Double rate = Double.parseDouble(cb_rate.getSelectionModel().getSelectedItem().toString());
            items.getItems().clear();
            items.getItems().addAll(movieManager.get(genre, year, rate));
        }
    }

    @FXML
    void openSceneAccount(MouseEvent event) {
        openStage(event, "account-view.fxml");
    }

    @FXML
    void resetComboBox(MouseEvent event) {
        if (!cb_rate.getSelectionModel().isEmpty() &&
                !cb_genre.getSelectionModel().isEmpty() &&
                !cb_year.getSelectionModel().isEmpty()) {
            cb_genre.getSelectionModel().clearSelection();
            cb_year.getSelectionModel().clearSelection();
            cb_rate.getSelectionModel().clearSelection();
            tf_search.clear();
            service.title = "";
            service.restart();
        }
    }

    @FXML
    void openLoginScene(MouseEvent event) {
        openStage(event, "login-view.fxml");
    }

    @FXML
    void searchMovie(MouseEvent event) {
        if (tf_search.getText() != null && !tf_search.getText().trim().isEmpty()) {
            service.title = tf_search.getText();
            service.restart();
        }
    }

    @FXML
    void showAllMovie(MouseEvent event) {
        if (tf_search.getText() != null && !tf_search.getText().trim().isEmpty()) {
            tf_search.clear();
            service.title = "";
            service.restart();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> genres = Arrays.asList("Drama", "Crime", "Action",
                "Biography", "History", "Adventure", "Western", "Romance",
                "Sci-Fi", "Fantasy", "Thriller", "Animation", "Thriller",
                "Comedy", "Music", "Mystery", "Horror", "War", "Film-Noir");
        movieManager = new MovieManager(new MovieRepository());
        movies = movieManager.getAllMovie();
        directors = directorManager.getAllDirector();
        Adapter adapter = new Adapter();
        items.setAdapter(adapter);
        items.getItems().addAll(movies);
        cb_genre.setItems(FXCollections.observableArrayList(genres));
        cb_year.setItems(FXCollections.observableArrayList(movies.stream().map(movie -> movie.getMovieYear()).distinct().sorted().collect(Collectors.toList())));
        cb_rate.setItems(FXCollections.observableArrayList(IntStream.rangeClosed(0, 10).boxed().collect(Collectors.toList())));

    }


    public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
        @Override
        public RecyclerView.ViewRow call(ListView param) {
            return new RippleViewRow(this);
        }

        @Override
        public Holder onCreateViewHolder(FXMLLoader loader) {
            loader.setLocation(MovieListerApplication.class.getResource("movie-item.fxml"));
            return new Holder(loader);
        }

        @Override
        public void onBindViewHolder(Adapter.Holder holder, Object item) {
            Movie movie = (Movie) item;
            Image img = new Image(movie.getPoster());

            Platform.runLater(() -> {
                holder.lbl_movieTitle.setText(movie.getTitle());
                holder.lbl_director.setText(directors.stream().filter(director -> director.getDirectorID() == movie.getDirectorID()).findFirst().get().getDirector());
                holder.lbl_rate.setText(String.valueOf(movie.getRate()));
                holder.lbl_year.setText(String.valueOf(movie.getMovieYear()));
                holder.iv_poster.setImage(img);
            });

            holder.getView().setOnMouseClicked(mouseEvent -> {
                DataPassController.movie = movie;
                openStage(mouseEvent, "movieForum-view.fxml");
            });
        }

        public static class Holder extends RecyclerView.ViewHolder {

            @FXML
            ImageView iv_poster;
            @FXML
            Label lbl_movieTitle;
            @FXML
            Label lbl_director;
            @FXML
            Label lbl_year;
            @FXML
            Label lbl_rate;

            public Holder(FXMLLoader loader) {
                super(loader);
            }
        }
    }

    private class MovieService extends Service<ObservableList<Movie>> {

        private String title;

        @Override
        protected Task<ObservableList<Movie>> createTask() {
            return new Task<>() {
                @Override
                protected ObservableList<Movie> call() {
                    if (title.equals("") || title.equals(" ") || title.isEmpty()) {
                        return movies;
                    } else {
                        ObservableList<Movie> search = movieManager.getByTitle(title);
                        if (search == null) {
                            return movies;
                        }
                        return search;
                    }
                }

                @Override
                protected void succeeded() {
                    items.getItems().clear();
                    items.getItems().addAll(getValue());

                }

                @Override
                protected void failed() {
                    Throwable error = getException();
                    FXAlert.showException(error, "Film ekranında hata!");

                }
            };
        }
    }

}
