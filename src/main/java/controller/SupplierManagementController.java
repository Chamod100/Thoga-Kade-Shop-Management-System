package controller;

import Model.DTO.SupplierDTO;
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

public class SupplierManagementController implements Initializable {

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private TableColumn<?, ?> colAddress;
    @FXML
    private TableColumn<?, ?> colCity;
    @FXML
    private TableColumn<?, ?> colCompany;
    @FXML
    private TableColumn<?, ?> colEmail;
    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableColumn<?, ?> colPCode;
    @FXML
    private TableColumn<?, ?> colPhone;
    @FXML
    private TableColumn<?, ?> colProvince;
    @FXML
    private TableColumn<?, ?> colSupplierid;
    @FXML
    private TableView<SupplierDTO> tblSupplier;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField txtCompany;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPhone;
    @FXML
    private TextField txtPostalCode;
    @FXML
    private TextField txtProvince;
    @FXML
    private TextField txtSupplierId;


    ObservableList<SupplierDTO> supplierList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colSupplierid.setCellValueFactory(new PropertyValueFactory<>("colSupplierId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("colName"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("colCompany"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("colAddress"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("colCity"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("colProvince"));
        colPCode.setCellValueFactory(new PropertyValueFactory<>("colPCode"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("colPhone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("colEmail"));

        tblSupplier.setItems(supplierList);
        loadSuppliers();

        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtSupplierId.setText(newValue.getColSupplierId());
                txtName.setText(newValue.getColName());
                txtCompany.setText(newValue.getColCompany());
                txtAddress.setText(newValue.getColAddress());
                txtCity.setText(newValue.getColCity());
                txtProvince.setText(newValue.getColProvince());
                txtPostalCode.setText(newValue.getColPCode());
                txtPhone.setText(newValue.getColPhone());
                txtEmail.setText(newValue.getColEmail());
            }
        });
    }

    private void loadSuppliers() {
        ObservableList<SupplierDTO> suppliers = FXCollections.observableArrayList();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM Supplier");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                SupplierDTO supplier = new SupplierDTO(
                        rs.getString("SupplierID"),
                        rs.getString("Name"),
                        rs.getString("CompanyName"),
                        rs.getString("Address"),
                        rs.getString("City"),
                        rs.getString("Province"),
                        rs.getString("PostalCode"),
                        rs.getString("Phone"),
                        rs.getString("Email")
                );
                suppliers.add(supplier);
            }
            tblSupplier.setItems(suppliers);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void actionAdd(ActionEvent event) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
             PreparedStatement ps = connection.prepareStatement("INSERT INTO Supplier VALUES (?,?,?,?,?,?,?,?,?)")) {

            ps.setString(1, txtSupplierId.getText());
            ps.setString(2, txtName.getText());
            ps.setString(3, txtCompany.getText());
            ps.setString(4, txtAddress.getText());
            ps.setString(5, txtCity.getText());
            ps.setString(6, txtProvince.getText());
            ps.setString(7, txtPostalCode.getText());
            ps.setString(8, txtPhone.getText());
            ps.setString(9, txtEmail.getText());

            ps.executeUpdate();
            loadSuppliers();
            clearFields();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void actionUpdate(ActionEvent event) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
             PreparedStatement ps = connection.prepareStatement("UPDATE Supplier SET Name=?, CompanyName=?, Address=?, City=?, Province=?, PostalCode=?, Phone=?, Email=? WHERE SupplierID=?")) {

            ps.setString(1, txtName.getText());
            ps.setString(2, txtCompany.getText());
            ps.setString(3, txtAddress.getText());
            ps.setString(4, txtCity.getText());
            ps.setString(5, txtProvince.getText());
            ps.setString(6, txtPostalCode.getText());
            ps.setString(7, txtPhone.getText());
            ps.setString(8, txtEmail.getText());
            ps.setString(9, txtSupplierId.getText());

            ps.executeUpdate();
            loadSuppliers();
            clearFields();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void actionDelete(ActionEvent event) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
             PreparedStatement ps = connection.prepareStatement("DELETE FROM Supplier WHERE SupplierID=?")) {

            ps.setString(1, txtSupplierId.getText());
            ps.executeUpdate();
            loadSuppliers();
            clearFields();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void actionClear(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtSupplierId.clear();
        txtName.clear();
        txtCompany.clear();
        txtAddress.clear();
        txtCity.clear();
        txtProvince.clear();
        txtPostalCode.clear();
        txtPhone.clear();
        txtEmail.clear();
    }
}
