package controller;

import Model.DTO.ItemDTO;
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

public class ItemManagementController implements Initializable {

    ObservableList<ItemDTO> ItemDTO = FXCollections.observableArrayList(
            new ItemDTO("I001","Red Rice 5kg","Grocery","40","1200.00"),
            new ItemDTO("I002","Wheat Flour 2kg","Grocery","30","800.00"),
            new ItemDTO("I003","Olive Oil 1L","Grocery","20","1500.00"),
            new ItemDTO("I004","Chicken Breast 1kg","Meat","25","900.00"),
            new ItemDTO("I005","Salmon Fillet 500g","Seafood","15","2000.00"),
            new ItemDTO("I006","Broccoli 1 bunch","Vegetables","50","300.00"),
            new ItemDTO("I007","Carrots 1kg","Vegetables","40","250.00"),
            new ItemDTO("I008","Apples 1kg","Fruits","60","400.00"),
            new ItemDTO("I009","Bananas 1kg","Fruits","70","350.00"),
            new ItemDTO("I010","Almonds 500g","Nuts","30","1200.00")
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

    @FXML
    void actionReload(ActionEvent event) {
        colItem.setCellValueFactory(new PropertyValueFactory<>("colItem"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("colDescription"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("colCategory"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("colQty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("colPrice"));
        tblItem.setItems(ItemDTO);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colItem.setCellValueFactory(new PropertyValueFactory<>("colItem"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("colDescription"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("colCategory"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("colQty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("colPrice"));
        tblItem.setItems(ItemDTO);

        tblItem.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
                    if (newValue != null) {
                        txtICode.setText(newValue.getColItem());
                        txtDescription.setText(newValue.getColDescription());
                        txtCategory.setText(newValue.getColCategory());
                        txtQty.setText(newValue.getColQty());
                        txtPrice.setText(newValue.getColPrice());
                    }
                }
        );
    }
}
