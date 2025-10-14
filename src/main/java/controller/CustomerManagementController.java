package controller;

import Model.DTO.CustomerDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class CustomerManagementController implements Initializable {

    ObservableList<CustomerDTO> CustomerDTO = FXCollections.observableArrayList(
            new CustomerDTO("C001", "Mr.", "Danapala", "1981-02-06", "40000", "No.20 Walana", "Panadura", "Western", "12500"),
            new CustomerDTO("C002", "Ms.", "Samanthi", "1990-05-12", "55000", "No.15 Galle", "Galle", "Southern", "8000"),
            new CustomerDTO("C003", "Mrs.", "Kumari", "1985-11-23", "72000", "No.5 Kandy", "Kandy"," Central", "20000"),
            new CustomerDTO("C004", "Miss.", "Niluka", "1978-07-30", "95000", "No.8 Jaffna", "Jaffna", "Northern", "15000"),
            new CustomerDTO("C005", "Mr.", "Perera", "1992-03-15", "48000", "No.12 Matara", "Matara", "Southern", "9000"),
            new CustomerDTO("C006", "Ms.", "Lakshmi", "1988-09-09", "67000", "No.3 Negombo", "Negombo", "Western", "11000"),
            new CustomerDTO("C007", "Mrs.", "Fernando", "1975-12-01", "83000", "No.18 Trincomalee", "Trincomalee", "Eastern", "13000"),
            new CustomerDTO("C008", "Miss.", "Jayathilaka", "1983-06-21", "76000", "No.7 Anuradhapura", "Anuradhapura", "North Central", "14000"),
            new CustomerDTO("C009", "Mr.", "Silva", "1995-04-10", "52000", "No.22 Kurunegala", "Kurunegala", "North Western", "10000"),
            new CustomerDTO("C010", "Ms.", "Wijesinghe", "1980-08-18", "88000", "No.9 Badulla", "Badulla", "Uva", "16000")
    );

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


    public void actionReload(ActionEvent actionEvent) {
        txtCustID.setText("");
        txtTitle.setText("");
        txtName.setText("");
        txtDob.setText("");
        txtSalary.setText("");
        txtAddress.setText("");
        txtCity.setText("");
        txtProvince.setText("");
        txtPostalCode.setText("");
    }

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
        tblCustomer.setItems(CustomerDTO);

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
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

    public void actionAdd(ActionEvent actionEvent) {
        String cid = txtCustID.getText();
        String Title = txtTitle.getText();
        String Name = txtName.getText();
        String Dob = txtDob.getText();
        String Salary = txtSalary.getText();
        String Address = txtAddress.getText();
        String City = txtCity.getText();
        String Province = txtProvince.getText();
        String PCode = txtPostalCode.getText();

        CustomerDTO customerDTO = new CustomerDTO(cid,Title,Name,Dob,Salary,Address,City,Province,PCode);
        CustomerDTO.add(customerDTO);

        tblCustomer.refresh();

        txtCustID.setText("");
        txtTitle.setText("");
        txtName.setText("");
        txtDob.setText("");
        txtSalary.setText("");
        txtAddress.setText("");
        txtCity.setText("");
        txtProvince.setText("");
        txtPostalCode.setText("");
    }

    public void actionUpdate(ActionEvent actionEvent) {
        CustomerDTO selectedItem = tblCustomer.getSelectionModel().getSelectedItem();

        selectedItem.setColCid(txtCustID.getText());
        selectedItem.setColTitle(txtTitle.getText());
        selectedItem.setColName(txtName.getText());
        selectedItem.setColDob(txtDob.getText());
        selectedItem.setColSalary(txtSalary.getText());
        selectedItem.setColAddress(txtAddress.getText());
        selectedItem.setColCity(txtCity.getText());
        selectedItem.setColProvince(txtProvince.getText());
        selectedItem.setColPCode(txtPostalCode.getText());

        tblCustomer.refresh();

        txtCustID.setText("");
        txtTitle.setText("");
        txtName.setText("");
        txtDob.setText("");
        txtSalary.setText("");
        txtAddress.setText("");
        txtCity.setText("");
        txtProvince.setText("");
        txtPostalCode.setText("");
    }
    public void actionDelete(ActionEvent actionEvent) {
        CustomerDTO selectedItem = tblCustomer.getSelectionModel().getSelectedItem();
        CustomerDTO.remove(selectedItem);
        tblCustomer.refresh();

        txtCustID.setText("");
        txtTitle.setText("");
        txtName.setText("");
        txtDob.setText("");
        txtSalary.setText("");
        txtAddress.setText("");
        txtCity.setText("");
        txtProvince.setText("");
        txtPostalCode.setText("");
    }

    public void actionClear(ActionEvent actionEvent) {
        txtCustID.setText("");
        txtTitle.setText("");
        txtName.setText("");
        txtDob.setText("");
        txtSalary.setText("");
        txtAddress.setText("");
        txtCity.setText("");
        txtProvince.setText("");
        txtPostalCode.setText("");
    }
}
