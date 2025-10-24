package controller;

import Model.DTO.ItemDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ItemManagementController implements Initializable {

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnReload;
    @FXML
    private Button btnUpdate;
    @FXML
    private TableColumn<?, ?> colDescription;
    @FXML
    private TableColumn<?, ?> colCategory;
    @FXML
    private TableColumn<?, ?> colItem;
    @FXML
    private TableColumn<?, ?> colPrice;
    @FXML
    private TableColumn<?, ?> colQty;
    @FXML
    private TableView<ItemDTO> tblItem;
    @FXML
    private TextField txtCategory;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextField txtICode;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtQty;


    ObservableList<ItemDTO> itemList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colItem.setCellValueFactory(new PropertyValueFactory<>("colItem"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("colDescription"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("colCategory"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("colQty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("colPrice"));

        tblItem.setItems(itemList);
        loadItems();

        tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtICode.setText(newValue.getColItem());
                txtDescription.setText(newValue.getColDescription());
                txtCategory.setText(newValue.getColCategory());
                txtQty.setText(newValue.getColQty());
                txtPrice.setText(newValue.getColPrice());
            }
        });
    }

    private void loadItems() {
        ObservableList<ItemDTO> itemDTOS = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Item");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                ItemDTO item = new ItemDTO(
                        resultSet.getString("ItemCode"),
                        resultSet.getString("Description"),
                        resultSet.getString("Category"),
                        resultSet.getString("QtyOnHand"),
                        resultSet.getString("UnitPrice")
                );
                itemDTOS.add(item);
            }
            tblItem.setItems(itemDTOS);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void actionAdd(ActionEvent event) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
             PreparedStatement ps = connection.prepareStatement("INSERT INTO Item VALUES (?,?,?,?,?)")) {

            ps.setString(1, txtICode.getText());
            ps.setString(2, txtDescription.getText());
            ps.setString(3, txtCategory.getText());
            ps.setString(4, txtQty.getText());
            ps.setString(5, txtPrice.getText());

            ps.executeUpdate();
            loadItems();
            clearFields();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void actionUpdate(ActionEvent event) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
             PreparedStatement ps = connection.prepareStatement(
                     "UPDATE Item SET Description=?, Category=?, QtyOnHand=?, UnitPrice=? WHERE ItemCode=?")) {

            ps.setString(1, txtDescription.getText());
            ps.setString(2, txtCategory.getText());
            ps.setString(3, txtQty.getText());
            ps.setString(4, txtPrice.getText());
            ps.setString(5, txtICode.getText());

            ps.executeUpdate();
            loadItems();
            clearFields();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void actionDelete(ActionEvent event) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
             PreparedStatement ps = connection.prepareStatement("DELETE FROM Item WHERE ItemCode=?")) {

            ps.setString(1, txtICode.getText());
            ps.executeUpdate();
            loadItems();
            clearFields();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void actionReload(ActionEvent event) {
        loadItems();
        clearFields();
    }

    @FXML
    public void actionClear(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtICode.clear();
        txtDescription.clear();
        txtCategory.clear();
        txtQty.clear();
        txtPrice.clear();
    }
}
