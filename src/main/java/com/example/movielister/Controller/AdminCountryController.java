package com.example.movielister.Controller;

import com.example.movielister.Data.Manager.CountryManager;
import com.example.movielister.Data.Repository.CountryRepository;
import com.example.movielister.Model.Country;
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

public class AdminCountryController extends BaseController implements Initializable {
    private final CountryManager countryManager = new CountryManager(new CountryRepository());
    @FXML
    private TableColumn<Country, Integer> countryID;

    @FXML
    private TableColumn<Country, String> countryName;

    @FXML
    private TableView<Country> table_country;

    @FXML
    private TextField tf_countryID;

    @FXML
    private TextField tf_countryName;

    private ObservableList<Country> countries;


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
    void addCountry(MouseEvent event) {
        Country country = textFieldToCountry();
        if (country != null) {
            countryManager.addCountry(country);
            countries = countryManager.getAllCountry();
            table_country.setItems(countries);
        }
    }

    @FXML
    void deleteCountry(MouseEvent event) {
        Country country = textFieldToCountry();
        if (country != null) {
            countryManager.deleteCountry(country);
            countries = countryManager.getAllCountry();
            table_country.setItems(countries);
        }
    }

    @FXML
    void updateCountry(MouseEvent event) {
        Country country = textFieldToCountry();
        if (country != null) {
            countryManager.updateCountry(country);
            countries = countryManager.getAllCountry();
            table_country.setItems(countries);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        countries = countryManager.getAllCountry();
        countryID.setCellValueFactory(new PropertyValueFactory<Country, Integer>("countryID"));
        countryName.setCellValueFactory(new PropertyValueFactory<Country, String>("country"));
        table_country.setItems(countries);
        tf_countryID.setText("0");
        setTextFieldFromTable();
    }

    private void setTextFieldFromTable() {
        table_country.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (table_country.getSelectionModel().getSelectedIndex() > -1) {
                    Country country = table_country.getItems().get(table_country.getSelectionModel().getSelectedIndex());
                    tf_countryID.setText(String.valueOf(country.getCountryID()));
                    tf_countryName.setText(country.getCountry());
                }
            }
        });
    }


    private Country textFieldToCountry() {
        Country country = null;
        if (!tf_countryName.getText().isEmpty()) {
            try {
                country = new Country(Integer.parseInt(tf_countryID.getText()),
                        tf_countryName.getText()
                );
            } catch (Exception e) {
                FXAlert.showException(e, "Alanların doğru girildiğinden emin olun!");
            }
        } else {
            FXAlert.showWarning("Lütfen boş alan bırakmayınız!");
        }
        return country;
    }
}
