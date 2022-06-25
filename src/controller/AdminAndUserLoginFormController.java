package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminAndUserLoginFormController {
    public AnchorPane AdminLoginContext;
    public Button btnAdminLog;
    public Button btnUserLog;
    public JFXTextField txtAdminUserName;
    public JFXTextField txtuserUserName;
    public JFXPasswordField TxtAdminPassword;
    public JFXPasswordField txtuserPassword;

    int attempts=0;

    public void AdminLogOnAction(ActionEvent actionEvent) throws IOException {
        //Stage.
        attempts++;
        if(attempts<=3){
            String tempUserName=txtAdminUserName.getText();
            String tempPassword=TxtAdminPassword.getText();
            if(tempUserName.equals("Admin") && tempPassword.equals("1234")){

                setUI("ManageItemAndSystemReportForm");

            }else{
                new Alert(Alert.AlertType.WARNING,"Try Again!!").show();
            }

        }else{
            txtAdminUserName.setEditable(false);
            TxtAdminPassword.setEditable(false);
        }

    }

    public void UserLogOnAction(ActionEvent actionEvent) throws IOException {
        attempts++;
        if(attempts<=3){
            String tempUserName=txtuserUserName.getText();
            String tempPassword=txtuserPassword.getText();
            if(tempUserName.equals("User") && tempPassword.equals("0000")){
                setUI("CashierForm");

            }else{
                new Alert(Alert.AlertType.WARNING,"Try Again!!").show();
            }

        }else{
            txtuserUserName.setEditable(false);
            txtuserPassword.setEditable(false);
        }

    }
    private void setUI(String location) throws IOException {
        Stage stage=(Stage) AdminLoginContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }
}
