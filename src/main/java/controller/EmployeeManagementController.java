package controller;

import Model.DTO.EmployeeDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class EmployeeManagementController implements Initializable {

    @FXML
    private Button btnAdd;
    @FXML
    private Button btnClear;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private TableColumn<?, ?> colID;
    @FXML
    private TableColumn<?, ?> colName;
    @FXML
    private TableColumn<?, ?> colNic;
    @FXML
    private TableColumn<?, ?> colDob;
    @FXML
    private TableColumn<?, ?> colPosition;
    @FXML
    private TableColumn<?, ?> colSalary;
    @FXML
    private TableColumn<?, ?> colContact;
    @FXML
    private TableColumn<?, ?> colAddress;
    @FXML
    private TableColumn<?, ?> colJoinedDate;
    @FXML
    private TableColumn<?, ?> colStatus;
    @FXML
    private TableView<EmployeeDTO> tblEmployee;
    @FXML
    private TextField txtEmployeeId;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtNic;
    @FXML
    private TextField txtPosition;
    @FXML
    private TextField txtSalary;
    @FXML
    private TextField txtContact;
    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtStatus;
    @FXML
    private DatePicker dpDob;
    @FXML
    private DatePicker dpJoinedDate;


    ObservableList<EmployeeDTO> employeeList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colID.setCellValueFactory(new PropertyValueFactory<>("colID"));
        colName.setCellValueFactory(new PropertyValueFactory<>("colName"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("colNic"));
        colDob.setCellValueFactory(new PropertyValueFactory<>("colDob"));
        colPosition.setCellValueFactory(new PropertyValueFactory<>("colPosition"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("colSalary"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("colContact"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("colAddress"));
        colJoinedDate.setCellValueFactory(new PropertyValueFactory<>("colJoinedDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("colStatus"));

        tblEmployee.setItems(employeeList);
        loadEmployees();

        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtEmployeeId.setText(newValue.getColID());
                txtName.setText(newValue.getColName());
                txtNic.setText(newValue.getColNic());
                dpDob.setValue(LocalDate.parse(newValue.getColDob()));
                txtPosition.setText(newValue.getColPosition());
                txtSalary.setText(newValue.getColSalary());
                txtContact.setText(newValue.getColContact());
                txtAddress.setText(newValue.getColAddress());
                dpJoinedDate.setValue(LocalDate.parse(newValue.getColJoinedDate()));
                txtStatus.setText(newValue.getColStatus());
            }
        });
    }

    private void loadEmployees() {
        ObservableList<EmployeeDTO> employeesDTOS = FXCollections.observableArrayList();

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234");
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM Employee");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EmployeeDTO employee = new EmployeeDTO(
                        rs.getString("EmployeeID"),
                        rs.getString("Name"),
                        rs.getString("NIC"),
                        rs.getString("DateOfBirth"),
                        rs.getString("Position"),
                        rs.getString("Salary"),
                        rs.getString("ContactNumber"),
                        rs.getString("Address"),
                        rs.getString("JoinedDate"),
                        rs.getString("Status")
                );
                employeesDTOS.add(employee);
            }

            tblEmployee.setItems(employeesDTOS);

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void actionAdd(ActionEvent event) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234")) {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO Employee VALUES (?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, txtEmployeeId.getText());
            ps.setString(2, txtName.getText());
            ps.setString(3, txtNic.getText());
            ps.setString(4, String.valueOf(dpDob.getValue()));
            ps.setString(5, txtPosition.getText());
            ps.setString(6, txtSalary.getText());
            ps.setString(7, txtContact.getText());
            ps.setString(8, txtAddress.getText());
            ps.setString(9, String.valueOf(dpJoinedDate.getValue()));
            ps.setString(10, txtStatus.getText());

            ps.executeUpdate();

            loadEmployees();
            clearFields();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void actionUpdate(ActionEvent event) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234")) {
            PreparedStatement ps = connection.prepareStatement("UPDATE Employee SET Name=?, NIC=?, DateOfBirth=?, Position=?, Salary=?, ContactNumber=?, Address=?, JoinedDate=?, Status=? WHERE EmployeeID=?");
            ps.setString(1, txtName.getText());
            ps.setString(2, txtNic.getText());
            ps.setString(3, String.valueOf(dpDob.getValue()));
            ps.setString(4, txtPosition.getText());
            ps.setString(5, txtSalary.getText());
            ps.setString(6, txtContact.getText());
            ps.setString(7, txtAddress.getText());
            ps.setString(8, String.valueOf(dpJoinedDate.getValue()));
            ps.setString(9, txtStatus.getText());
            ps.setString(10, txtEmployeeId.getText());

            ps.executeUpdate();
            loadEmployees();
            clearFields();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void actionDelete(ActionEvent event) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Togakademanagement", "root", "1234")) {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM Employee WHERE EmployeeID=?");
            ps.setString(1, txtEmployeeId.getText());

            ps.executeUpdate();

            loadEmployees();
            clearFields();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void actionClear(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtEmployeeId.clear();
        txtName.clear();
        txtNic.clear();
        dpDob.setValue(null);
        txtPosition.clear();
        txtSalary.clear();
        txtContact.clear();
        txtAddress.clear();
        dpJoinedDate.setValue(null);
        txtStatus.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
