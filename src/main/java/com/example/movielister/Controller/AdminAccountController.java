package com.example.movielister.Controller;

import com.example.movielister.Data.Manager.UserManager;
import com.example.movielister.Data.Repository.UserRepository;
import com.example.movielister.Model.User;
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

public class AdminAccountController extends BaseController implements Initializable {
    UserManager userManager = new UserManager(new UserRepository());

    @FXML
    private TableColumn<User, String> nickname;

    @FXML
    private TableColumn<User, String> password;

    @FXML
    private TableColumn<User, Integer> userID;

    @FXML
    private TableColumn<User, Integer> userTypeID;

    @FXML
    private TableView<User> table_user;

    @FXML
    private TextField tf_nickname;

    @FXML
    private TextField tf_password;

    @FXML
    private TextField tf_userID;

    @FXML
    private TextField tf_userTypeID;

    private ObservableList<User> users;


    @FXML
    void openAdminPageScene(MouseEvent event) {
        openStage(event, "admin-view.fxml");
    }

    @FXML
    void openHomePageScene(MouseEvent event) {
        openStage(event, "homePage-view.fxml");
    }

    @FXML
    void openUserTypeScene(MouseEvent event) {

    }

    @FXML
    void addUser(MouseEvent event) {
        User user = textFieldToUser();
        if (user != null) {
            userManager.addUser(user);
            users = userManager.getAllUser();
            table_user.setItems(users);
        }
    }

    @FXML
    void deleteUser(MouseEvent event) {
        User user = textFieldToUser();
        if (user != null) {
            userManager.deleteUser(user);
            users = userManager.getAllUser();
            table_user.setItems(users);
        }
    }


    @FXML
    void updateUser(MouseEvent event) {
        User user = textFieldToUser();
        if (user != null) {
            userManager.updateUser(user);
            users = userManager.getAllUser();
            table_user.setItems(users);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        users = userManager.getAllUser();
        userID.setCellValueFactory(new PropertyValueFactory<User, Integer>("userID"));
        nickname.setCellValueFactory(new PropertyValueFactory<User, String>("nickname"));
        password.setCellValueFactory(new PropertyValueFactory<User, String>("pass"));
        userTypeID.setCellValueFactory(new PropertyValueFactory<User, Integer>("userTypeID"));
        table_user.setItems(users);
        tf_userID.setText("0");
        setTextFieldFromTable();
    }

    private void setTextFieldFromTable() {
        table_user.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (table_user.getSelectionModel().getSelectedIndex() > -1) {
                    User user = table_user.getItems().get(table_user.getSelectionModel().getSelectedIndex());
                    tf_userID.setText(String.valueOf(user.getUserID()));
                    tf_nickname.setText(user.getNickname());
                    tf_password.setText(user.getPass());
                    tf_userTypeID.setText(String.valueOf(user.getUserTypeID()));
                }
            }
        });
    }


    private User textFieldToUser() {
        User user = null;
        if (!tf_nickname.getText().isEmpty() && !tf_password.getText().isEmpty()) {
            try {
                user = new User(Integer.parseInt(tf_userID.getText()),
                        tf_nickname.getText(),
                        tf_password.getText(),
                        Integer.parseInt(tf_userTypeID.getText())
                );
            } catch (Exception e) {
                FXAlert.showException(e, "Alanların doğru girildiğinden emin olun!");
            }
        } else {
            FXAlert.showWarning("Lütfen boş alan bırakmayınız!");
        }
        return user;
    }

}
