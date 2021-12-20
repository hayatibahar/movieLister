package com.example.movielister.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.*;

public class LoginController extends BaseController {

    @FXML
    private Button btn_signIn;

    @FXML
    private Button btn_signUp;

    @FXML
    private PasswordField pf_loginPassword;

    @FXML
    private TextField tf_loginNickname;


    @FXML
    void openSignUpScene(MouseEvent event) {
        openStage(event,"signUp-view.fxml");

    }

    @FXML
    void loginControl(MouseEvent event) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "fghj852");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select usertypeID from user_tbl where nickname ='"+tf_loginNickname.getText()+"' and pass ='"+pf_loginPassword.getText()+"'");
            resultSet.next();

            ResultSet resultSet2 = statement.executeQuery("select info from userType_tbl where userTypeId="+resultSet.getInt(1));
            resultSet2.next();

            if(resultSet2.getString(1).equals("user")){
                openStage(event,"homePage-view.fxml");
            }
            else if(resultSet2.getString(1).equals("admin")){
                openStage(event,"admin-view.fxml");

            }else{
                System.out.println("Login ekranında ekran değişimi sırasında hata!");
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }



}