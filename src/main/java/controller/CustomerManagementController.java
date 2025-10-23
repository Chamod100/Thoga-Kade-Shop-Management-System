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
    private Button btnAdd, btnClear, btnDelete, btnReload, btnUpdate;
    @FXML
    private TableColumn<?, ?> colAddress, colCid, colCity, colDob, colName, colProvince, colSalary, colTitle;
    @FXML
    private TableView<CustomerDTO> tblCustomer;
    @FXML
    private TextField txtAddress, txtCity, txtCustID, txtDob, txtName, txtPostalCode, txtProvince, txtSalary, txtTitle;

    private ObservableList<CustomerDTO> customerList = FXCollections.observableArrayList();

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
        ObservableList<CustomerDTO> CustomerDTOS = FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Customer");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                CustomerDTO customerinfoDTO = new CustomerDTO(
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
                CustomerDTOS.add(customerinfoDTO);
            }
            tblCustomer.setItems(CustomerDTOS);
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actionReload(ActionEvent event) {
        loadCustomers();
        clearFields();
    }

    public void actionAdd(ActionEvent event) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");

            PreparedStatement ps = connection.prepareStatement("INSERT INTO Customer VALUES (?,?,?,?,?,?,?,?,?)");

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
            connection.close();
            loadCustomers();
            clearFields();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actionUpdate(ActionEvent event) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");

            PreparedStatement ps = connection.prepareStatement("UPDATE Customer SET Title=?, Name=?, DateOfBirth=?, Salary=?, Address=?, City=?, Province=?, PostalCode=? WHERE CustomerID=?");

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
            connection.close();
            loadCustomers();
            clearFields();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actionDelete(ActionEvent event) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");

            PreparedStatement ps = connection.prepareStatement("DELETE FROM Customer WHERE CustomerID=?");
            ps.setString(1, txtCustID.getText());

            ps.executeUpdate();
            connection.close();
            loadCustomers();
            clearFields();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actionClear(ActionEvent event) {
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
}
