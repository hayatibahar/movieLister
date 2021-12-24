package com.example.movielister.Controller;

import com.example.movielister.Data.Manager.DirectorManager;
import com.example.movielister.Data.Repository.DirectorRepository;
import com.example.movielister.Model.Director;
import com.example.movielister.util.FXAlert;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminDirectorController extends BaseController implements Initializable {

    private final DirectorManager directorManager = new DirectorManager(new DirectorRepository());
    @FXML
    private TableColumn<Director, Integer> directorID;

    @FXML
    private TableColumn<Director, String> directorName;

    @FXML
    private TableView<Director> table_director;

    @FXML
    private TextField tf_directorID;

    @FXML
    private TextField tf_directorName;

    private ObservableList<Director> directors;

    @FXML
    void openAdminMovieScene(MouseEvent event) {
        openStage(event, "adminMovie-view.fxml");
    }

    @FXML
    void openAdminPageScene(MouseEvent event) {
        openStage(event, "admin-view.fxml");
    }

    @FXML
    void openHomePageScene(MouseEvent event) {
        openStage(event, "homePage-view.fxml");
    }

    @FXML
    void addDirector(MouseEvent event) {
        Director director = textFieldToDirector();
        if (director != null) {
            directorManager.addDirector(director);
            directors = directorManager.getAllDirector();
            table_director.setItems(directors);
        }
    }

    @FXML
    void deleteDirector(MouseEvent event) {
        Director director = textFieldToDirector();
        if (director != null) {
            directorManager.deleteDirector(director);
            directors = directorManager.getAllDirector();
            table_director.setItems(directors);
        }
    }

    @FXML
    void updateDirector(MouseEvent event) {
        Director director = textFieldToDirector();
        if (director != null) {
            directorManager.updateDirector(director);
            directors = directorManager.getAllDirector();
            table_director.setItems(directors);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        directors = directorManager.getAllDirector();
        directorID.setCellValueFactory(new PropertyValueFactory<Director, Integer>("directorID"));
        directorName.setCellValueFactory(new PropertyValueFactory<Director, String>("director"));
        table_director.setItems(directors);
        tf_directorID.setText("0");
        setTextFieldFromTable();
    }

    private void setTextFieldFromTable() {
        table_director.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (table_director.getSelectionModel().getSelectedIndex() > -1) {
                    Director director = table_director.getItems().get(table_director.getSelectionModel().getSelectedIndex());
                    tf_directorID.setText(String.valueOf(director.getDirectorID()));
                    tf_directorName.setText(director.getDirector());
                }
            }
        });
    }

    private Director textFieldToDirector() {
        Director director = null;
        if (!tf_directorName.getText().isEmpty()) {
            try {
                director = new Director(Integer.parseInt(tf_directorID.getText()),
                        tf_directorName.getText()
                );
            } catch (Exception e) {
                FXAlert.showException(e, "Alanların doğru girildiğinden emin olun!");
            }
        } else {
            FXAlert.showWarning("Lütfen boş alan bırakmayınız!");
        }
        return director;
    }
}
