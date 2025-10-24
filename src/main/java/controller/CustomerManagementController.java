package controller;

import Model.DTO.CustomerDTO;
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

public class CustomerManagementController implements Initializable {

    @FXML
    private TableColumn<?, ?> ColPCode;
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
    private TableColumn<?, ?> colAddress;
    @FXML
    private TableColumn<?, ?> colCid;
    @FXML
    private TableColumn<?, ?> colCity;
    @FXML
    private TableColumn<?, ?> colDob;
    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableColumn<?, ?> colProvince;
    @FXML
    private TableColumn<?, ?> colSalary;
    @FXML
    private TableColumn<?, ?> colTitle;
    @FXML
    private TableView<CustomerDTO> tblCustomer;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtCustID;
    @FXML
    private TextField txtDob;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPostalCode;
    @FXML
    private TextField txtProvince;
    @FXML
    private TextField txtSalary;
    @FXML
    private TextField txtTitle;

    private final ObservableList<CustomerDTO> customerList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colCid.setCellValueFactory(new PropertyValueFactory<>("colCid"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("colTitle"));
        colName.setCellValueFactory(new PropertyValueFactory<>("colName"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("colDob"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("colSalary"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("colAddress"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("colCity"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("colProvince"));
        ColPCode.setCellValueFactory(new PropertyValueFactory<>("colPCode"));

        tblCustomer.setItems(customerList);
        loadCustomers();

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtCustID.setText(newValue.getColCid());
                txtTitle.setText(newValue.getColTitle());
                txtName.setText(newValue.getColName());
                txtDob.setText(newValue.getColDob());
                txtSalary.setText(newValue.getColSalary());
                txtAddress.setText(newValue.getColAddress());
                txtCity.setText(newValue.getColCity());
                txtProvince.setText(newValue.getColProvince());
                txtPostalCode.setText(newValue.getColPCode());
            }
        });
    }

    private void loadCustomers() {
        customerList.clear();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Customer");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                CustomerDTO customer = new CustomerDTO(
                        resultSet.getString("CustomerID"),
                        resultSet.getString("Title"),
                        resultSet.getString("Name"),
                        resultSet.getString("DateOfBirth"),
                        resultSet.getString("Salary"),
                        resultSet.getString("Address"),
                        resultSet.getString("City"),
                        resultSet.getString("Province"),
                        resultSet.getString("PostalCode")
                );
                customerList.add(customer);
            }

        } catch (SQLException e) {
            showError("Error loading customers", e.getMessage());
        }
    }

    @FXML
    private void actionReload(ActionEvent event) {
        loadCustomers();
        clearFields();
    }

    @FXML
    private void actionAdd(ActionEvent event) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
             PreparedStatement ps = connection.prepareStatement("INSERT INTO Customer VALUES (?,?,?,?,?,?,?,?,?)")) {

            ps.setString(1, txtCustID.getText());
            ps.setString(2, txtTitle.getText());
            ps.setString(3, txtName.getText());
            ps.setString(4, txtDob.getText());
            ps.setString(5, txtSalary.getText());
            ps.setString(6, txtAddress.getText());
            ps.setString(7, txtCity.getText());
            ps.setString(8, txtProvince.getText());
            ps.setString(9, txtPostalCode.getText());

            ps.executeUpdate();
            loadCustomers();
            clearFields();

        } catch (SQLException e) {
            showError("Error adding customer", e.getMessage());
        }
    }

    @FXML
    private void actionUpdate(ActionEvent event) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
             PreparedStatement ps = connection.prepareStatement("UPDATE Customer SET Title=?, Name=?, DateOfBirth=?, Salary=?, Address=?, City=?, Province=?, PostalCode=? WHERE CustomerID=?")) {

            ps.setString(1, txtTitle.getText());
            ps.setString(2, txtName.getText());
            ps.setString(3, txtDob.getText());
            ps.setString(4, txtSalary.getText());
            ps.setString(5, txtAddress.getText());
            ps.setString(6, txtCity.getText());
            ps.setString(7, txtProvince.getText());
            ps.setString(8, txtPostalCode.getText());
            ps.setString(9, txtCustID.getText());

            ps.executeUpdate();
            loadCustomers();
            clearFields();

        } catch (SQLException e) {
            showError("Error updating customer", e.getMessage());
        }
    }

    @FXML
    private void actionDelete(ActionEvent event) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
             PreparedStatement ps = connection.prepareStatement("DELETE FROM Customer WHERE CustomerID=?")) {

            ps.setString(1, txtCustID.getText());
            ps.executeUpdate();
            loadCustomers();
            clearFields();

        } catch (SQLException e) {
            showError("Error deleting customer", e.getMessage());
        }
    }

    @FXML
    private void actionClear(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtCustID.clear();
        txtTitle.clear();
        txtName.clear();
        txtDob.clear();
        txtSalary.clear();
        txtAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostalCode.clear();
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
