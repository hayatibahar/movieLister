package com.example.movielister.Controller;

import com.example.movielister.Data.Manager.UserTypeManager;
import com.example.movielister.Data.Repository.UserTypeRepository;
import com.example.movielister.Model.UserType;
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

public class AdminUserTypeController extends BaseController implements Initializable {
    UserTypeManager userTypeManager = new UserTypeManager(new UserTypeRepository());

    @FXML
    private TableColumn<UserType, String> info;

    @FXML
    private TableColumn<UserType, Integer> userTypeID;

    @FXML
    private TableView<UserType> table_userType;

    @FXML
    private TextField tf_userTypeID;

    @FXML
    private TextField tf_info;

    private ObservableList<UserType> userTypes;


    @FXML
    void openAdminPageScene(MouseEvent event) {
        openStage(event, "admin-view.fxml");
    }

    @FXML
    void openHomePageScene(MouseEvent event) {
        openStage(event, "homePage-view.fxml");
    }

    @FXML
    void openAdminAccountScene(MouseEvent event) {
        openStage(event, "adminAccount-view.fxml");
    }

    @FXML
    void addUserType(MouseEvent event) {
        UserType userType = textFieldToUser();
        if (userType != null) {
            userTypeManager.addUserType(userType);
            userTypes = userTypeManager.getAllUserType();
            table_userType.setItems(userTypes);
        }
    }

    @FXML
    void deleteUserType(MouseEvent event) {
        UserType userType = textFieldToUser();
        if (userType != null) {
            userTypeManager.deleteUserType(userType);
            userTypes = userTypeManager.getAllUserType();
            table_userType.setItems(userTypes);
        }
    }


    @FXML
    void updateUserType(MouseEvent event) {
        UserType userType = textFieldToUser();
        if (userType != null) {
            userTypeManager.updateUserType(userType);
            userTypes = userTypeManager.getAllUserType();
            table_userType.setItems(userTypes);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userTypes = userTypeManager.getAllUserType();
        info.setCellValueFactory(new PropertyValueFactory<UserType, String>("info"));
        userTypeID.setCellValueFactory(new PropertyValueFactory<UserType, Integer>("userTypeID"));
        table_userType.setItems(userTypes);
        tf_userTypeID.setText("0");
        setTextFieldFromTable();

    }

    private void setTextFieldFromTable() {
        table_userType.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (table_userType.getSelectionModel().getSelectedIndex() > -1) {
                    UserType userType = table_userType.getItems().get(table_userType.getSelectionModel().getSelectedIndex());
                    tf_userTypeID.setText(String.valueOf(userType.getUserTypeID()));
                    tf_info.setText(userType.getInfo());
                }
            }
        });
    }


    private UserType textFieldToUser() {
        UserType userType = null;
        if (!tf_userTypeID.getText().isEmpty() && !tf_info.getText().isEmpty()) {
            try {
                userType = new UserType(Integer.parseInt(tf_userTypeID.getText()),
                        tf_info.getText()
                );
            } catch (Exception e) {
                FXAlert.showException(e, "Alanların doğru girildiğinden emin olun!");
            }
        } else {
            FXAlert.showWarning("Lütfen boş alan bırakmayınız!");
        }
        return userType;
    }

}
