package com.devstack.pos.controller;

import com.devstack.pos.dao.DatabaseAccessCode;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;



public class SignUpFormController {
    public JFXTextField txtEmail;
    public JFXPasswordField txtPassword;
    public JFXButton btnRegisterNowOnAction;
    public AnchorPane context;
    public JFXButton btnAlreadyHaveOnActionS;

    public void AlreadyHaveAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUi( "LoginForm");
    }

    public void btnRegisterNowOnAction(ActionEvent actionEvent) {
        try{
            /*Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/robotikka", "root", "1234");
            String sql = "INSERT INTO user VALUES (?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,txtEmail.getText());
            preparedStatement.setString(2, PasswordManager.encryptPassword(txtPassword.getText()));*/
            /*we commented this because of DatabaseAccessCode class now we didn't break single responsibility and cohesion */

            if(new DatabaseAccessCode().createUser(txtEmail.getText(),txtPassword.getText())){
                new Alert( Alert.AlertType.CONFIRMATION,"User Saved!").show();
                clearField();

            }else {
                new Alert(Alert.AlertType.ERROR,"Try Again").show();
                clearField();
            }
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }

    }

    private void clearField() {
        txtEmail.clear();
        txtPassword.clear();
    }

    private void setUi(String url) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+url+".fxml"))));
        stage.centerOnScreen();


    }


}
