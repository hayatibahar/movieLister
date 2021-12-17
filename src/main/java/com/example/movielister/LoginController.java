package com.example.movielister;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private Button btn_signIn;

    @FXML
    private Button btn_signUp;

    @FXML
    private PasswordField pf_loginPassword;

    @FXML
    private TextField tf_loginNickname;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    @FXML
    void openSignUpScene(MouseEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("signUp-view.fxml"));
            stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    void loginControl(MouseEvent event) {
        System.out.println(tf_loginNickname.getText());
        System.out.println(pf_loginPassword.getText());
        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "fghj852");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select usertypeID from user_tbl where nickname ='"+tf_loginNickname.getText()+"' and pass ='"+pf_loginPassword.getText()+"'");
            resultSet.next();
            System.out.println(resultSet.getInt(1));



            if(resultSet.getInt(1) == 1){

                try {
                    root = FXMLLoader.load(getClass().getResource("homePage-view.fxml"));
                    stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if(resultSet.getInt(1)==2){
                try {
                    root = FXMLLoader.load(getClass().getResource("admin-view.fxml"));
                    stage = (Stage)((javafx.scene.Node)event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setScene(scene);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else{

            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }



}