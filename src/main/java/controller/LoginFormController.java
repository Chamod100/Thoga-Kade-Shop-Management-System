package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginFormController implements Initializable {

    public TextField txtUser;
    public PasswordField txtPassword;

    Stage stage = new Stage();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtUser.setOnAction(actionEvent -> txtPassword.requestFocus());

        txtPassword.setOnAction(actionEvent -> onLogin(new ActionEvent()));
    }


    public void onLogin(ActionEvent actionEvent) {
        String name = "Chamod";
        String password = "1234";

        if (name.equals(txtUser.getText()) && password.equals(txtPassword.getText())) {
            try {
                txtUser.setText("");
                txtPassword.setText("");
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashbord_form.fxml"))));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            stage.show();
        } else {
            txtUser.setText("");
            txtPassword.setText("");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Error");
            alert.setContentText("UserName or Password is wrong");
            alert.show();
        }
    }
}