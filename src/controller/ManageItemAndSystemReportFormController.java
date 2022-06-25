package controller;

import dao.CrudDAO;
import dao.SQLUtil;
import entity.Order;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class ManageItemAndSystemReportFormController {
    public AnchorPane ReportAndItemContext;
    public Button btnSystemReport;
    public Button btnManageItems;
    public Button btnMostMoveableItem;
    public Button btnleastMoveableItem;
    public Label lblDate;
    public Label lblTime;
    public Button btnArrowBack;
    public BarChart ChartDaillyIncome;
    public BarChart ChartMonthlyIncome;
    public BarChart ChartAnnuallyIncome;
    public CategoryAxis Day_X;
    public NumberAxis DayIncome_Y;
    public CategoryAxis Month_X;
    public NumberAxis MonthlyIncome_Y;
    public CategoryAxis Year_X;
    public NumberAxis AnnualIncome_y;
    public AnchorPane ReportAndItemContext2;

    public void initialize() throws SQLException, ClassNotFoundException {
        loadDateAndTime();
       // setMonthlyChart();
    }

  /*public void setMonthlyChart() throws SQLException, ClassNotFoundException {
     ResultSet result = SQLUtil.executeQuery("SELECT `Orders`.date,`OrderDetails`.qty,`Item`.unitPrice FROM `Order` INNER JOIN `OrderDetails` ON `Orders`.orderid=OrderDetails`.oid INNER JOIN `Item` ON `OrderDetails`.itemCode=`Item`.code ");


        double janCost=0;
        double febCost=0;
        double marCost=0;
        double aprCost=0;
        double mayCost=0;
        double junCost=0;
        double julCost=0;
        double aguCost=0;
        double sepCost=0;
        double octCost=0;
        double novCost=0;
        double decCost=0;

        while(result.next()){
            java.sql.Date dt=result.getDate(1);
            LocalDate localDate = dt.toLocalDate();

            if(localDate.getMonthValue()==1){
                janCost+= result.getDouble(2);
            }else if(localDate.getMonthValue()==2){
                febCost+= result.getDouble(2);
            }else if(localDate.getMonthValue()==3){
                marCost+=result.getDouble(2);
            }else if (localDate.getMonthValue()==4){
                aprCost+=result.getDouble(2);
            }else if (localDate.getMonthValue()==5){
                mayCost+=result.getDouble(2);
            }else if (localDate.getMonthValue()==6){
                junCost+=result.getDouble(2);
            }else if (localDate.getMonthValue()==7){
                julCost+=result.getDouble(2);
            }else if (localDate.getMonthValue()==8){
                aguCost+=result.getDouble(2);
            }else if (localDate.getMonthValue()==9){
                sepCost+=result.getDouble(2);
            }else if (localDate.getMonthValue()==10){
                octCost+=result.getDouble(2);
            }else if (localDate.getMonthValue()==11){
                novCost+=result.getDouble(2);
            }else if (localDate.getMonthValue()==12){
                decCost+=result.getDouble(2);
            }


        }

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Month");

        final LineChart<String, Number> lineChart =
                new LineChart<String, Number>(xAxis, yAxis);



        XYChart.Series series = new XYChart.Series();
        series.setName("Month");

        series.getData().add(new XYChart.Data("Jan", janCost));
        series.getData().add(new XYChart.Data("Feb", febCost));
        series.getData().add(new XYChart.Data("Mar", marCost));
        series.getData().add(new XYChart.Data("Apr", aprCost));
        series.getData().add(new XYChart.Data("May", mayCost));
        series.getData().add(new XYChart.Data("Jun", junCost));
        series.getData().add(new XYChart.Data("Jul", julCost));
        series.getData().add(new XYChart.Data("Aug", aguCost));
        series.getData().add(new XYChart.Data("Sep", sepCost));
        series.getData().add(new XYChart.Data("Oct", octCost));
        series.getData().add(new XYChart.Data("Nov", novCost));
        series.getData().add(new XYChart.Data("Dec", decCost));



        ChartMonthlyIncome.getData().add(series);

    }
*/
    public void SystemReportOnAction(ActionEvent actionEvent) throws IOException {
        ReportAndItemContext2.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ReportChartForm.fxml"));
        ReportAndItemContext2.getChildren().add(parent);
    }

    public void ManageItemsOnAction(ActionEvent actionEvent) throws IOException {
        ReportAndItemContext2.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/ItemForm.fxml"));
        ReportAndItemContext2.getChildren().add(parent);
    }

    public void MostMoveableItemOnAction(ActionEvent actionEvent) throws IOException {
        ReportAndItemContext2.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/MostMovableItemForm.fxml"));
        ReportAndItemContext2.getChildren().add(parent);
    }

    public void leastMoveableItemOnAction(ActionEvent actionEvent) throws IOException {
        ReportAndItemContext2.getChildren().clear();
        Parent parent = FXMLLoader.load(getClass().getResource("../view/LeastMovableItemForm.fxml"));
        ReportAndItemContext2.getChildren().add(parent);
    }

    public void ArrowBackOnAction(ActionEvent actionEvent) throws IOException {
        setUI("AdminAndUserLoginForm");
    }
    private void loadDateAndTime() {
        /* set Date*/
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        /* set Date*/
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateFormat dateFormat = new SimpleDateFormat("hh : mm : ss aa");
            String dateString = dateFormat.format(new Date()).toString();
            lblTime.setText(dateString);
        }),
                new KeyFrame(Duration.seconds(1))

        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }
    private void setUI(String location) throws IOException {
        Stage stage=(Stage) ReportAndItemContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
    }

    public void ArrowBackMouseClick(MouseEvent mouseEvent) throws IOException {
        setUI("AdminAndUserLoginForm");
    }
}
