package controller;

import bo.BOFactory;
import bo.custom.CustomerBO;
import com.jfoenix.controls.JFXTextField;
import dto.CustomerDTO;
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

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManageCustomerFormController {
    public AnchorPane CustomerFormContext;
    public Button btnSaveCustomer;
    public JFXTextField txtCusId;
    public JFXTextField txtCusName;
    public JFXTextField txtCusAddress;
    public JFXTextField txtCusCity;
    public JFXTextField txtCusProvince;
    public JFXTextField txtCusPostalCode;
    public TableView<CustomerTM> tblCustomer;
    public TableColumn colCusId;
    public TableColumn colCusName;
    public TableColumn colCusAddress;
    public TableColumn colCity;
    public TableColumn colProvince;
    public TableColumn colPostalCode;
    public TableColumn colDelete;
    public Button btnAddNewCustomer;
    public TextField txtSearch;
    public Button btnDeleteCustomer;

    private final CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    public void initialize() {
        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("customerID"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblCustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("city"));
        tblCustomer.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("province"));
        tblCustomer.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        initUI();

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDeleteCustomer.setDisable(newValue == null);
            btnSaveCustomer.setText(newValue != null ? "Update" : "Save");
            btnSaveCustomer.setDisable(newValue == null);

            if (newValue != null) {
                txtCusId.setText(newValue.getCustomerID());
                txtCusName.setText(newValue.getCustomerName());
                txtCusAddress.setText(newValue.getAddress());
                txtCusCity.setText(newValue.getCity());
                txtCusProvince.setText(newValue.getProvince());
                txtCusPostalCode.setText(newValue.getPostalCode());


                txtCusId.setDisable(false);
                txtCusName.setDisable(false);
                txtCusAddress.setDisable(false);
                txtCusCity.setDisable(false);
                txtCusProvince.setDisable(false);
                txtCusPostalCode.setDisable(false);
            }
        });

        txtCusAddress.setOnAction(event -> btnSaveCustomer.fire());
        loadAllCustomers();
    }


    private void loadAllCustomers() {
        tblCustomer.getItems().clear();
        /*Get all customers*/
        try {
            ArrayList<CustomerDTO> allCustomers = customerBO.getAllCustomers();
            for (CustomerDTO customer : allCustomers) {
                tblCustomer.getItems().add(new CustomerTM(customer.getId(), customer.getName(), customer.getAddress(),customer.getCity(),customer.getProvince(),customer.getPostalCode()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }
    boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerBO.customerExist(id);
    }

    public void SaveCustomerOnAction(ActionEvent actionEvent) {
        String id = txtCusId.getText();
        String name = txtCusName.getText();
        String address = txtCusAddress.getText();
        String city=txtCusCity.getText();
        String province=txtCusProvince.getText();
        String postalCode=txtCusPostalCode.getText();

        if (!name.matches("[A-Za-z ]+")) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            txtCusName.requestFocus();
            return;
        } else if (!address.matches(".{3,}")) {
            new Alert(Alert.AlertType.ERROR, "Address should be at least 3 characters long").show();
            txtCusAddress.requestFocus();
            return;
        }else if (!city.matches("^[A-Z ][a-z]{1,}$")) {
        new Alert(Alert.AlertType.ERROR, "Invalid city").show();
        txtCusCity.requestFocus();
        return;
    }else if (!province.matches("^[A-Z ][a-z]{1,}$")) {
        new Alert(Alert.AlertType.ERROR, "Invalid province").show();
        txtCusProvince.requestFocus();
        return;
    }else if (!postalCode.matches("^[A-z0-9 ,/]{4,20}$")) {
        new Alert(Alert.AlertType.ERROR, "Invalid postal code").show();
        txtCusPostalCode.requestFocus();
        return;
    }

        if (btnSaveCustomer.getText().equalsIgnoreCase("save")) {
            /*Save Customer*/
            try {
                if (existCustomer(id)) {
                    new Alert(Alert.AlertType.ERROR, id + " already exists").show();
                }

                customerBO.saveCustomer(new CustomerDTO(id, name, address,city,province,postalCode));
                tblCustomer.getItems().add(new CustomerTM(id, name, address,city,province,postalCode));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the customer " + e.getMessage()).show();
                e.printStackTrace();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            /*Update customer*/
            try {
                if (!existCustomer(id)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + id).show();
                }
                //Customer update
                customerBO.updateCustomer(new CustomerDTO(id, name, address,city,province,postalCode));

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the customer " + id + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            CustomerTM selectedCustomer = (CustomerTM) tblCustomer.getSelectionModel().getSelectedItem();
            selectedCustomer.setCustomerName(name);
            selectedCustomer.setAddress(address);
            selectedCustomer.setCity(city);
            selectedCustomer.setProvince(province);
            selectedCustomer.setPostalCode(postalCode);
            tblCustomer.refresh();
        }
        btnAddNewCustomer.fire();
    }


    private void initUI() {
        txtCusId.clear();
        txtCusName.clear();
        txtCusAddress.clear();
        txtCusCity.clear();
        txtCusProvince.clear();
        txtCusPostalCode.clear();
        txtCusId.setDisable(true);
        txtCusName.setDisable(true);
        txtCusAddress.setDisable(true);
        txtCusCity.setDisable(true);
        txtCusProvince.setDisable(true);
        txtCusPostalCode.setDisable(true);
        txtCusId.setEditable(false);
        btnSaveCustomer.setDisable(true);
        btnDeleteCustomer.setDisable(true);
    }


    public void AddNewICustomerOnAction(ActionEvent actionEvent) {
        txtCusId.setDisable(false);
        txtCusName.setDisable(false);
        txtCusAddress.setDisable(false);
        txtCusCity.setDisable(false);
        txtCusProvince.setDisable(false);
        txtCusPostalCode.setDisable(false);
        txtCusId.clear();
        txtCusId.setText(generateNewId());
        txtCusName.clear();
        txtCusAddress.clear();
        txtCusCity.clear();
        txtCusProvince.clear();
        txtCusPostalCode.clear();
        txtCusName.requestFocus();
        btnSaveCustomer.setDisable(false);
        btnSaveCustomer.setText("Save");
        tblCustomer.getSelectionModel().clearSelection();
    }

    public void ArrowBackMouseClick(MouseEvent mouseEvent) throws IOException {
        setUI("CashierForm");
    }
    private void setUI(String location) throws IOException {
        Stage stage=(Stage) CustomerFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
    }

    public void SearchOnAction(ActionEvent actionEvent) {
    }

    public void DeleteCustomerOnAction(ActionEvent actionEvent) {
        /*Delete Customer*/
        String id = tblCustomer.getSelectionModel().getSelectedItem().getCustomerID();
        try {
            if (!existCustomer(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + id).show();
            }
            customerBO.deleteCustomer(id);
            tblCustomer.getItems().remove(tblCustomer.getSelectionModel().getSelectedItem());
            tblCustomer.getSelectionModel().clearSelection();
            initUI();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the customer " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private String generateNewId() {
        try {
            return customerBO.generateNewCustomerID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        if (tblCustomer.getItems().isEmpty()) {
            return "C00-001";
        } else {
            String id = getLastCustomerId();
            int newCustomerId = Integer.parseInt(id.replace("C", "")) + 1;
            return String.format("C00-%03d", newCustomerId);
        }

    }

    private String getLastCustomerId() {
        List<CustomerTM> tempCustomersList = new ArrayList<>(tblCustomer.getItems());
        Collections.sort(tempCustomersList);
        return tempCustomersList.get(tempCustomersList.size() - 1).getCustomerID();
    }

    public void SearchOnKeyReleased(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {

        String search = "%" + txtSearch.getText() + "%";

        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            ArrayList<CustomerDTO> customerDTOS = customerBO.searchCustomers(search);
            ObservableList<CustomerTM> oBCustomerTMS = FXCollections.observableArrayList();

            for (CustomerDTO cusDto : customerDTOS) {
                oBCustomerTMS.add(new CustomerTM(cusDto.getId(),
                        cusDto.getName(),
                        cusDto.getAddress(),
                        cusDto.getCity(),
                        cusDto.getProvince(),
                        cusDto.getPostalCode()));
            }
            tblCustomer.getItems().clear();
            tblCustomer.getItems().addAll(oBCustomerTMS);
            tblCustomer.refresh();
        }
    }
}
