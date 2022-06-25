package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CashierFormController {
    public Button btnManageOrder;
    public Button btnMakecustomerOrder;
    public AnchorPane CashierFormContext;
    public Button btnPlaceOrder;

    public void ManageOrderOnAction(ActionEvent actionEvent) throws IOException {

        setUI("SearchOrderDetailForm");
    }

    public void MakeCustomerOrderOnAction(ActionEvent actionEvent) throws IOException {
        setUI("ManageCustomerForm");
    }
    private void setUI(String location) throws IOException {
        Stage stage=(Stage) CashierFormContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
    }

    public void ArrowBackMouseClick(MouseEvent mouseEvent) throws IOException {
        setUI("AdminAndUserLoginForm");
    }

    public void PlaceOrderOnAction(ActionEvent actionEvent) throws IOException {
        setUI("PlaceOrderForm");
    }
}
