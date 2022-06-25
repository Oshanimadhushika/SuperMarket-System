package controller;

import bo.BOFactory;
import bo.custom.ItemBO;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
import dto.ItemDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import view.tdm.CustomerTM;
import view.tdm.ItemTM;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemFormController {
    public AnchorPane ItemFormContext;
    public Button btnSaveItem;
    public JFXTextField txtDescription;
    public JFXTextField txtItemCode;
    public JFXTextField txtPackSize;
    public JFXTextField txtUnitPrize;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtDiscount;
    public TableView<ItemTM> tblItem;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colPackSize;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnHand;
    public TableColumn colDiscount;
    public Button btnAddNewItem;
    public TextField txtSearch;
    public Button btnDeleteItem;

    private ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);
    public void initialize() {
        tblItem.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("code"));
        tblItem.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItem.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("packSize"));
        tblItem.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblItem.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        tblItem.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("discount"));

        initUI();

        tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDeleteItem.setDisable(newValue == null);
            btnSaveItem.setText(newValue != null ? "Update" : "Save");
            btnSaveItem.setDisable(newValue == null);

            if (newValue != null) {
                txtItemCode.setText(newValue.getCode());
                txtDescription.setText(newValue.getDescription());
                txtPackSize.setText(newValue.getPackSize());
                txtUnitPrize.setText(newValue.getUnitPrice().setScale(2).toString());
                txtQtyOnHand.setText(newValue.getQtyOnHand() + "");
                txtDiscount.setText(newValue.getDiscount().setScale(2).toString());

                txtItemCode.setDisable(false);
                txtDescription.setDisable(false);
                txtPackSize.setDisable(false);
                txtUnitPrize.setDisable(false);
                txtQtyOnHand.setDisable(false);
                txtDiscount.setDisable(false);
            }
        });

        txtQtyOnHand.setOnAction(event -> btnSaveItem.fire());
        loadAllItems();
    }

    private void loadAllItems() {
        tblItem.getItems().clear();
        try {
            /*Get all items*/
            ArrayList<ItemDTO> allItems = itemBO.getAllItems();
            for (ItemDTO item : allItems) {
                tblItem.getItems().add(new ItemTM(item.getCode(),item.getDescription(),item.getPackSize(),item.getUnitPrice(),item.getQtyOnHand(),item.getDiscount()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initUI() {
        txtItemCode.clear();
        txtDescription.clear();
        txtPackSize.clear();
        txtUnitPrize.clear();
        txtQtyOnHand.clear();
        txtDiscount.clear();
        txtItemCode.setDisable(true);
        txtDescription.setDisable(true);
        txtPackSize.setDisable(true);
        txtUnitPrize.setDisable(true);
        txtQtyOnHand.setDisable(true);
        txtDiscount.setDisable(true);
        txtItemCode.setEditable(false);
        btnSaveItem.setDisable(true);
        btnDeleteItem.setDisable(true);
    }

    public void SaveItemOnAction(ActionEvent actionEvent) {
        String code = txtItemCode.getText();
        String description = txtDescription.getText();
        String packSize=txtPackSize.getText();
        BigDecimal unitPrice = new BigDecimal(txtUnitPrize.getText()).setScale(2);
        int qtyOnHand = Integer.parseInt(txtQtyOnHand.getText());
        BigDecimal discount = new BigDecimal(txtDiscount.getText()).setScale(2);


        if (!description.matches("[A-Za-z0-9 ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid description").show();
            txtDescription.requestFocus();
            return;
        } else if (!txtPackSize.getText().matches("^([0-9]{0,4}(g|kg|KG))$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid pack size").show();
            txtPackSize.requestFocus();
            return;
        } else if (!txtUnitPrize.getText().matches("^[0-9]+[.]?[0-9]*$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid unit price").show();
            txtUnitPrize.requestFocus();
            return;
        } else if (!txtQtyOnHand.getText().matches("^\\d+$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid qty on hand").show();
            txtQtyOnHand.requestFocus();
            return;
        }else if (!txtDiscount.getText().matches("^[0-9]+[.]?[0-9]*$")) {
            new Alert(Alert.AlertType.ERROR, "Invalid discount").show();
            txtDiscount.requestFocus();
            return;
        }




        if (btnSaveItem.getText().equalsIgnoreCase("save")) {
            try {
                if (existItem(code)) {
                    new Alert(Alert.AlertType.ERROR, code + " already exists").show();
                }
                //Save Item
                itemBO.saveItem(new ItemDTO(code, description,packSize, unitPrice, qtyOnHand,discount));
                tblItem.getItems().add(new ItemTM(code, description,packSize, unitPrice, qtyOnHand,discount));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            try {

                if (!existItem(code)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + code).show();
                }
                /*Update Item*/
                itemBO.updateItem(new ItemDTO(code, description,packSize, unitPrice, qtyOnHand,discount));
                ItemTM selectedItem = tblItem.getSelectionModel().getSelectedItem();
                selectedItem.setDescription(description);
                selectedItem.setPackSize(packSize);
                selectedItem.setUnitPrice(unitPrice);
                selectedItem.setQtyOnHand(qtyOnHand);
                selectedItem.setDiscount(discount);
                tblItem.refresh();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        btnAddNewItem.fire();
    }


    private boolean existItem(String code) throws SQLException, ClassNotFoundException {
        return itemBO.itemExist(code);
    }


    private String generateNewId() {
        try {
            return itemBO.generateNewItemCode();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return "I00-001";
    }



    public void AddNewItemOnAction(ActionEvent actionEvent) {
        txtItemCode.setDisable(false);
        txtDescription.setDisable(false);
        txtPackSize.setDisable(false);
        txtUnitPrize.setDisable(false);
        txtQtyOnHand.setDisable(false);
        txtDiscount.setDisable(false);
        txtItemCode.clear();
        txtItemCode.setText(generateNewId());
        txtDescription.clear();
        txtPackSize.clear();
        txtUnitPrize.clear();
        txtQtyOnHand.clear();
        txtDiscount.clear();
        txtDescription.requestFocus();
        btnSaveItem.setDisable(false);
        btnSaveItem.setText("Save");
        tblItem.getSelectionModel().clearSelection();
    }


    private void setUI(String location) throws IOException {
        Stage stage=(Stage) ItemFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
    }



    public void SearchOnAction(ActionEvent actionEvent) {

    }


    public void DeleteItemOnAction(ActionEvent actionEvent) {

        /*Delete Item*/
        String code = tblItem.getSelectionModel().getSelectedItem().getCode();
        try {
            if (!existItem(code)) {
                new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + code).show();
            }
            itemBO.deleteItem(code);
            tblItem.getItems().remove(tblItem.getSelectionModel().getSelectedItem());
            tblItem.getSelectionModel().clearSelection();
            initUI();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the item " + code).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void SearchOnKeyReleased(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        String search = "%" + txtSearch.getText() + "%";

        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            ArrayList<ItemDTO> itemDTOS = itemBO.searchItems(search);
            ObservableList<ItemTM> oBItemTMS = FXCollections.observableArrayList();

            for (ItemDTO itDTO : itemDTOS) {
                oBItemTMS.add(new ItemTM(itDTO.getCode(),
                        itDTO.getDescription(),
                        itDTO.getPackSize(),
                        itDTO.getUnitPrice(),
                        itDTO.getQtyOnHand(),
                        itDTO.getDiscount()));
            }
            tblItem.getItems().clear();
            tblItem.getItems().addAll(oBItemTMS);
            tblItem.refresh();
        }
    }
}
