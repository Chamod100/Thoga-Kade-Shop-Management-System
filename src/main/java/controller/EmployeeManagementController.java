package controller;

import Model.DTO.EmployeeDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EmployeeManagementController implements Initializable {

    ObservableList<EmployeeDTO> EmployeeDTO = FXCollections.observableArrayList(
            new EmployeeDTO("E001", "Sunil Perera", "832451230V", "1983-07-12", "Manager", "75000.00", "0712456789", "No.15 Temple Road, Kalutara", "2018-05-10", "Active"),
            new EmployeeDTO("E002", "Nimal Silva", "912345678V", "1990-03-22", "Receptionist", "45000.00", "0723456789", "No.22 Lake Street, Colombo", "2020-11-15", "Active"),
            new EmployeeDTO("E003", "Kamal Fernando", "823456789V", "1985-11-30", "Chef", "60000.00", "0734567890", "No.5 Ocean Avenue, Galle", "2019-07-20", "Inactive"),
            new EmployeeDTO("E004", "Ravi Jayasinghe", "734567890V", "1992-06-18", "Housekeeper", "40000.00", "0745678901", "No.10 Hill Street, Kandy", "2021-03-05", "Active"),
            new EmployeeDTO("E005", "Ajith Perera", "645678901V", "1988-09-25", "Waiter", "35000.00", "0756789012", "No.8 Garden Road, Negombo", "2017-12-12", "Active"),
            new EmployeeDTO("E006", "Mahesh Kumara", "556789012V", "1995-01-14", "Bartender", "38000.00", "0767890123", "No.30 River Road, Matara", "2022-06-22", "Inactive"),
            new EmployeeDTO("E007", "Chathura Silva", "467890123V", "1987-04-09", "Security", "30000.00", "0778901234", "No.12 Forest Lane, Nuwara Eliya", "2016-08-30", "Active"),
            new EmployeeDTO("E008", "Dinesh Perera", "378901234V", "1991-12-03", "Driver", "32000.00", "0789012345", "No.3 Mountain Road, Jaffna", "2019-10-18", "Active"),
            new EmployeeDTO("E009", "Pradeep Jayasuriya", "289012345V", "1984-05-27", "Cleaner", "28000.00", "0790123456", "No.7 Beach Avenue, Trincomalee", "2018-02-14", "Inactive"),
            new EmployeeDTO("E010", "Saman Fernando", "190123456V", "1993-08-15", "Technician", "50000.00", "0701234567", "No.20 City Road, Batticaloa", "2021-09-09", "Active")

    );

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
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colDob;

    @FXML
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colJoinedDate;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNic;

    @FXML
    private TableColumn<?, ?> colPosition;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private DatePicker dpDob;

    @FXML
    private DatePicker dpJoinedDate;

    @FXML
    private TableView<EmployeeDTO> tblEmployee;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

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
    private TextField txtStatus;

    @FXML
    void actionReload(ActionEvent event) {
        txtEmployeeId.setText("");
        txtName.setText("");
        txtNic.setText("");
        dpDob.setValue(null);
        txtPosition.setText("");
        txtSalary.setText("");
        txtContact.setText("");
        txtAddress.setText("");
        dpJoinedDate.setValue(null);
        txtStatus.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colID.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("colID"));
        colName.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("colName"));
        colNic.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("colNic"));
        colDob.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("colDob"));
        colPosition.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("colPosition"));
        colSalary.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("colSalary"));
        colContact.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("colContract"));
        colAddress.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("colAddress"));
        colJoinedDate.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("colJoinedDate"));
        colStatus.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("colStatus"));
        tblEmployee.setItems(EmployeeDTO);

        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observableValue, supplierDTO, NewValue) ->{
            if (NewValue != null) {
                txtEmployeeId.setText(NewValue.getColID());
                txtName.setText(NewValue.getColName());
                txtNic.setText(NewValue.getColNic());
                dpDob.setValue(java.time.LocalDate.parse(NewValue.getColDob()));
                txtPosition.setText(NewValue.getColPosition());
                txtSalary.setText(String.valueOf(NewValue.getColSalary()));
                txtContact.setText(NewValue.getColContract());
                txtAddress.setText(NewValue.getColAddress());
                dpJoinedDate.setValue(java.time.LocalDate.parse(NewValue.getColJoinedDate()));
                txtStatus.setText(NewValue.getColStatus());
            }
        });
    }

    public void actionAdd(ActionEvent actionEvent) {
        String ID = txtEmployeeId.getText();
        String name = txtName.getText();
        String nic = txtNic.getText();
        String dob = String.valueOf(dpDob.getValue());
        String position = txtPosition.getText();
        String salary = txtSalary.getText();
        String contact = txtContact.getText();
        String address = txtAddress.getText();
        String joinedDate = String.valueOf(dpJoinedDate.getValue());
        String status = txtStatus.getText();

        EmployeeDTO.add(new EmployeeDTO(ID, name, nic, dob, position, salary, contact, address, joinedDate, status));
        tblEmployee.refresh();

        txtEmployeeId.setText("");
        txtName.setText("");
        txtNic.setText("");
        dpDob.setValue(null);
        txtPosition.setText("");
        txtSalary.setText("");
        txtContact.setText("");
        txtAddress.setText("");
        dpJoinedDate.setValue(null);
        txtStatus.setText("");
    }

    public void actionUpdate(ActionEvent actionEvent) {
        EmployeeDTO selectedItem = tblEmployee.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {

            selectedItem.setColID(txtEmployeeId.getText());
            selectedItem.setColName(txtName.getText());
            selectedItem.setColNic(txtNic.getText());
            selectedItem.setColDob(String.valueOf(dpDob.getValue()));
            selectedItem.setColPosition(txtPosition.getText());
            selectedItem.setColSalary(txtSalary.getText());
            selectedItem.setColContract(txtContact.getText());
            selectedItem.setColAddress(txtAddress.getText());
            selectedItem.setColJoinedDate(String.valueOf(dpJoinedDate.getValue()));
            selectedItem.setColStatus(txtStatus.getText());
            tblEmployee.refresh();

            txtEmployeeId.setText("");
            txtName.setText("");
            txtNic.setText("");
            dpDob.setValue(null);
            txtPosition.setText("");
            txtSalary.setText("");
            txtContact.setText("");
            txtAddress.setText("");
            dpJoinedDate.setValue(null);
            txtStatus.setText("");
        }
    }

    public void actionDelete(ActionEvent actionEvent) {
        EmployeeDTO selectedItem = tblEmployee.getSelectionModel().getSelectedItem();
        EmployeeDTO.remove(selectedItem);
        tblEmployee.refresh();

        txtEmployeeId.setText("");
        txtName.setText("");
        txtNic.setText("");
        dpDob.setValue(null);
        txtPosition.setText("");
        txtSalary.setText("");
        txtContact.setText("");
        txtAddress.setText("");
        dpJoinedDate.setValue(null);
        txtStatus.setText("");
    }

    public void actionClear(ActionEvent actionEvent) {
        txtEmployeeId.setText("");
        txtName.setText("");
        txtNic.setText("");
        dpDob.setValue(null);
        txtPosition.setText("");
        txtSalary.setText("");
        txtContact.setText("");
        txtAddress.setText("");
        dpJoinedDate.setValue(null);
        txtStatus.setText("");
    }
}
