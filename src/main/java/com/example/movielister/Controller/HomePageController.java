package com.example.movielister.Controller;

import com.example.movielister.Data.Manager.MovieManager;
import com.example.movielister.Data.Repository.MovieRepository;
import com.example.movielister.Model.Movie;
import com.example.movielister.MovieListerApplication;
import com.example.movielister.util.RecyclerView;
import com.example.movielister.util.RippleViewRow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    private AnchorPane ap_homePage;

    @FXML
    private RecyclerView<Movie> items;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        MovieManager movieManager = new MovieManager(new MovieRepository());
        Adapter adapter = new Adapter();
        items.setAdapter(adapter);
        items.getItems().addAll(movieManager.getAllMovie());
    }


    public static class Adapter extends RecyclerView.Adapter<Adapter.Holder>{
        @Override
        public RecyclerView.ViewRow call(ListView param) {
            return new RippleViewRow(this);
        }

        @Override
        public Holder onCreateViewHolder(FXMLLoader loader) {
            loader.setLocation(MovieListerApplication.class.getResource("movie-item.fxml"));
            Holder holder = new Holder(loader);
            return holder;
        }

        @Override
        public void onBindViewHolder(Adapter.Holder holder, Object item) {
            Movie movie = (Movie) item;
            System.out.println("holder : "+ movie.getTITLE());
            holder.lbl_movieTitle.setText(movie.getTITLE());
            holder.lbl_director.setText(String.valueOf(movie.getDIRECTORID()));
            holder.lbl_rate.setText(String.valueOf(movie.getRATE()));
            holder.lbl_year.setText(String.valueOf(movie.getYEAR()));
            Image img = new Image(movie.getPOSTER());
            holder.iv_poster.setImage(img);
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

            public Holder(FXMLLoader loader){
                super(loader);
            }
        }
    }

}
