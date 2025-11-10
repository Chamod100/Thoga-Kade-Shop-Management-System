package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashbordFormController {

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnEmployees;

    @FXML
    private Button btnItems;

    @FXML
    private Button btnSuppliers;

    @FXML
    private AnchorPane mainContent;

    Stage stage = new Stage();
    Stage stage1 = new Stage();
    Stage stage2 = new Stage();
    Stage stage3 = new Stage();

    @FXML
    void actionCustomers(ActionEvent event) {
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Customer_management.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    @FXML
    void actionEmployees(ActionEvent event) {
        try {
            stage1.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Employee_management.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage1.show();
    }

    @FXML
    void actionItems(ActionEvent event) {
        try {
            stage2.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Item_management.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage2.show();
    }

    @FXML
    void actionSuppliers(ActionEvent event) {
        try {
            stage3.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Supplier_management.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage3.show();
    }

}
