package controller;

import Model.DTO.SupplierDTO;
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

public class SupplierManagementController implements Initializable {

    ObservableList<SupplierDTO> supplierDTOS = FXCollections.observableArrayList(
            new SupplierDTO("S001","Fernando","Agro Foods Pvt Ltd","No.45 Main Street","Matara","Southern","81000","0712345678","agroofods@gmail.com"),
            new SupplierDTO("S002","Kamal","Fresh Farm Supplies","No.12 Lake Road","Kandy","Central","20000","0723456789","FishFarm@gamil.com"),
            new SupplierDTO("S003","Nimal","Tropical Exports","No.78 Hill Street","Galle","Southern","80000","0734567890","TropicalExports@gmail.com"),
            new SupplierDTO("S004","Sunil","Oceanic Traders","No.34 Beach Road","Negombo","Western","11500","0745678901","oceanicTraders@gmail.com"),
            new SupplierDTO("S005","Ravi","Mountain Fresh Produce","No.56 Mountain View","Nuwara Eliya","Central","22200","0756789012","MountainFresh@gmail.com"),
            new SupplierDTO("S006","Ajith","City Supplies Co.","No.23 City Center","Colombo","Western","00100","0767890123","citySuppliers@gmail.com"),
            new SupplierDTO("S007","Mahesh","Green Valley Farms","No.67 Green Lane","Kurunegala","North Western","60000","0778901234","greenValley@gmail.com"),
            new SupplierDTO("S008","Chathura","Sunrise Agro Ltd","No.89 Sunrise Road","Jaffna","Northern","40000","0789012345","sunrisAagro@gmail.com"),
            new SupplierDTO("S009","Dinesh","Riverbank Supplies","No.11 River Road","Trincomalee","Eastern","31000","0790123456","riverbankSupplies@gmail.com"),
            new SupplierDTO("S010","Pradeep","Island Traders","No.90 Island Way","Batticaloa","Eastern","30000","0701234567","islandTraders@gmail.com")
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

    @FXML
    void actionReload(ActionEvent event) {
        colSupplierid.setCellValueFactory(new PropertyValueFactory<>("colSupplierId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("colName"));
        colCompany.setCellValueFactory(new PropertyValueFactory<>("colCompany"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("colAddress"));
        colCity.setCellValueFactory(new PropertyValueFactory<>("colCity"));
        colProvince.setCellValueFactory(new PropertyValueFactory<>("colProvince"));
        colPCode.setCellValueFactory(new PropertyValueFactory<>("colPCode"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("colPhone"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("colEmail"));
        tblSupplier.setItems(supplierDTOS);
    }

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
        tblSupplier.setItems(supplierDTOS);

        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observableValue, supplierDTO, t1) ->{
            if (t1 != null) {
                txtSupplierId.setText(t1.getColSupplierId());
                txtName.setText(t1.getColName());
                txtCompany.setText(t1.getColCompany());
                txtAddress.setText(t1.getColAddress());
                txtCity.setText(t1.getColCity());
                txtProvince.setText(t1.getColProvince());
                txtPostalCode.setText(t1.getColPCode());
                txtPhone.setText(t1.getColPhone());
                txtEmail.setText(t1.getColEmail());
            }
        });
    }
}
