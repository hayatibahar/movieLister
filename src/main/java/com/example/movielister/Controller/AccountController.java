package com.example.movielister.Controller;

import com.example.movielister.Data.Manager.UserManager;
import com.example.movielister.Data.Manager.WatchListManager;
import com.example.movielister.Data.Repository.UserRepository;
import com.example.movielister.Data.Repository.WatchListRepository;
import com.example.movielister.Model.Dto.WatchListDetail;
import com.example.movielister.Model.User;
import com.example.movielister.util.FXAlert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class AccountController extends BaseController implements Initializable {

    WatchListManager watchListManager = new WatchListManager(new WatchListRepository());
    UserManager userManager = new UserManager(new UserRepository());

    @FXML
    private TableColumn<WatchListDetail, Double> myRating;

    @FXML
    private TableColumn<WatchListDetail, String> title;

    @FXML
    private TableColumn<WatchListDetail, Integer> listID;

    @FXML
    private TableView<WatchListDetail> table_watchList;

    @FXML
    private TextField tf_nickname;

    @FXML
    private TextField tf_password;

    @FXML
    private TextField tf_passwordConfirm;

    @FXML
    private Text txt_nickname;

    @FXML
    private VBox vb_accountDetail;

    private ObservableList<WatchListDetail> watchListDetails;

    @FXML
    void openHomePageScene(MouseEvent event) {
        openStage(event, "homePage-view.fxml");
    }

    @FXML
    void getAccountDetail(MouseEvent event) {
        vb_accountDetail.setVisible(true);
        table_watchList.setVisible(false);
        tf_nickname.setText(DataPassController.user.getNickname());
        tf_password.setText(DataPassController.user.getPass());
        tf_passwordConfirm.setText(DataPassController.user.getPass());

    }

    @FXML
    void getWatchList(MouseEvent event) {
        watchListDetails = FXCollections.observableArrayList(watchListManager.getAllDetailByUserID(DataPassController.user.getUserID()).stream().filter(watchListDetail -> watchListDetail.getStatus() == 0).collect(Collectors.toList()));
        setTable(watchListDetails);
    }

    @FXML
    void getWatchedList(MouseEvent event) {
        watchListDetails = FXCollections.observableArrayList(watchListManager.getAllDetailByUserID(DataPassController.user.getUserID()).stream().filter(watchListDetail -> watchListDetail.getStatus() == 1).collect(Collectors.toList()));
        setTable(watchListDetails);
    }

    private void setTable(ObservableList<WatchListDetail> details) {
        vb_accountDetail.setVisible(false);
        table_watchList.setVisible(true);
        title.setCellValueFactory(new PropertyValueFactory<WatchListDetail, String>("title"));
        myRating.setCellValueFactory(new PropertyValueFactory<WatchListDetail, Double>("rate"));
        listID.setCellValueFactory(new PropertyValueFactory<WatchListDetail, Integer>("listID"));
        title.setPrefWidth(250.0);
        table_watchList.setItems(details);
    }

    @FXML
    void updateUser(MouseEvent event) {
        User user = null;
        if (tf_password.getText().equals(tf_passwordConfirm.getText())) {
            if (!tf_nickname.getText().isEmpty() && !tf_password.getText().isEmpty() && !tf_passwordConfirm.getText().isEmpty()) {
                try {
                    user = new User(DataPassController.user.getUserID(),
                            tf_nickname.getText(),
                            tf_password.getText(),
                            1
                    );
                } catch (Exception e) {
                    FXAlert.showException(e, "Alanların doğru girildiğinden emin olun!");
                }
            } else {
                FXAlert.showWarning("Lütfen boş alan bırakmayınız!");
            }
            if (user != null) {
                userManager.updateUser(user);
                DataPassController.user = userManager.getUserById(DataPassController.user.getUserID());
                txt_nickname.setText(DataPassController.user.getNickname());
            }
        } else {
            FXAlert.showWarning("Şifreler uyuşmuyor!");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txt_nickname.setText(DataPassController.user.getNickname());
    }


}
