package controller;

import bo.BOFactory;
import bo.custom.OrderBO;
import bo.custom.OrderDetailBO;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dao.DAOFactory;
import dao.custom.QueryDAO;
import dto.OrderDTO;
import dto.OrderDetailDTO;
import javafx.beans.property.ReadOnlyObjectWrapper;
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
import view.tdm.OrderDetailTM;
import view.tdm.OrderTM;
import view.tdm.SeeOrderDetailTM;
import view.tdm.SeeOrdersTM;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

public class SearchOrderDetailFormController {
    public Button btnRemoveOrder;
    OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER);
    OrderDetailBO orderDetailBO = (OrderDetailBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDER_Detail);
     //QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);

    public AnchorPane SearchOrderContext;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colQTY;
    public TableColumn colUnitPrice;
    public TableColumn colDiscount;
    public TableColumn colTotal;
    public TableColumn colDelete;
    public TableView<SeeOrdersTM> tblOrderId;
    public TableView<SeeOrderDetailTM>  tblOrderDetails;
    public TableColumn colOrderID;
    public TableColumn ColCusID;
    public TextField txtSearchOrderDetail;
    public TextField txtSearchOrder;
    public Label lblTotal;

    public void initialize() throws SQLException, ClassNotFoundException {
        tblOrderId.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        tblOrderId.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("CustomerId"));


        tblOrderDetails.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblOrderDetails.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblOrderDetails.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("qty"));
        tblOrderDetails.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblOrderDetails.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("discount"));
        tblOrderDetails.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("total"));
        TableColumn<SeeOrderDetailTM, Button> lastCol = (TableColumn<SeeOrderDetailTM, Button>) tblOrderDetails.getColumns().get(6);
        lastCol.setCellValueFactory(param -> {
            Button btnDelete = new Button("Delete");
            btnDelete.setOnAction(event -> {
                tblOrderDetails.getItems().remove(param.getValue());
                tblOrderDetails.getSelectionModel().clearSelection();


               /* SeeOrderDetailTM selectedItem = tblOrderDetails.getSelectionModel().getSelectedItem();

                boolean b = false;
                try {
                    b = orderBO.deleteOrders(selectedItem.getItemCode());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                if (b) {
                    new Alert(Alert.AlertType.INFORMATION, "Deleted SussesFull").show();
                    tblOrderDetails.getItems().clear();
                    tblOrderId.getItems().clear();
                    try {
                        initialize();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Something Went Wrong..").show();
                }*/
            });

            return new ReadOnlyObjectWrapper<>(btnDelete);
        });

        //add Orders to The Table
        ArrayList<OrderDTO> allOrders = orderBO.getAllOrders();

        for (OrderDTO allOrder : allOrders) {
            tblOrderId.getItems().add(new SeeOrdersTM(allOrder.getOrderId(), allOrder.getCustomerID()));
        }
        tblOrderId.refresh();

        tblOrderId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue != null) {

                searchOrderDetails(newValue.getOrderId());
            }

        });
    }

    

    public void ArrowBackMouseClick(MouseEvent mouseEvent) throws IOException {
        setUI("CashierForm");
    }
    
    private void setUI(String location) throws IOException {
        Stage stage=(Stage) SearchOrderContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
    }

    private void searchOrderDetails(String newValue) {
        //Search by ID
        String value = "%" + newValue + "%";

        ArrayList<OrderDetailDTO> oDetailDto = null;

        try {

            oDetailDto = orderDetailBO.searchOrderDetails(value);


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ObservableList<SeeOrderDetailTM> orderDetailsTMS = FXCollections.observableArrayList();

        double allTotal = 0;

        for (OrderDetailDTO od : oDetailDto) {
            orderDetailsTMS.add(new SeeOrderDetailTM(od.getOid(),
                    od.getItemCode(),
                    od.getQty(),
                    od.getUnitPrice(),
                    od.getDiscount(),
                    BigDecimal.valueOf(od.getTotal())));

            allTotal += od.getTotal();
        }

        tblOrderDetails.getItems().clear();
        tblOrderDetails.getItems().addAll(orderDetailsTMS);
        tblOrderDetails.refresh();
        lblTotal.setText(String.valueOf(allTotal));


    }

   

    public void RemoveOrderOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        SeeOrdersTM selectedItem = tblOrderId.getSelectionModel().getSelectedItem();
        boolean b = orderBO.deleteOrders(selectedItem.getOrderId());

        if (b) {
            new Alert(Alert.AlertType.INFORMATION, "Deleted SussesFull").show();
            tblOrderDetails.getItems().clear();
            tblOrderId.getItems().clear();
            initialize();
        } else {
            new Alert(Alert.AlertType.INFORMATION, "Something Went Wrong..").show();
        }
    }

    public void SearchOrderKeyRelesed(KeyEvent keyEvent) throws SQLException, ClassNotFoundException {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            String value = "%" + txtSearchOrder.getText() + "%";

            ArrayList<OrderDTO> orderDto = orderBO.getAllSearchOrder(value);


            ObservableList<SeeOrdersTM> orderTMS = FXCollections.observableArrayList();


            for (OrderDTO od : orderDto) {
                orderTMS.add(new SeeOrdersTM(
                        od.getOrderId(),
                        od.getCustomerID()
                ));

            }

            tblOrderId.getItems().clear();
            tblOrderId.getItems().addAll(orderTMS);
            tblOrderId.refresh();
        }
    }

    public void SearchOrderDetailKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            tblOrderDetails.getItems().clear();
            searchOrderDetails(txtSearchOrderDetail.getText());
        }
    }
}
