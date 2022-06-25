package controller;

import bo.BOFactory;
import bo.custom.MostMoveableItemBO;
import dto.CustomDTO;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import view.tdm.MovableTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class MostMovableItemFormController {
    public AnchorPane MostMovableItemContext;
    public TableView<MovableTM> tblMostMovabletem;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnHand;
    public TableColumn colOrderQty;

    MostMoveableItemBO mostMovableBO = (MostMoveableItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MOST_MOVABLE);

    public void initialize(){
        tblMostMovabletem.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblMostMovabletem.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblMostMovabletem.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblMostMovabletem.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        tblMostMovabletem.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("orderQty"));

        loadAllMovableItems();
    }

    private void loadAllMovableItems() {
        tblMostMovabletem.getItems().clear();
        try {
            /*Get all items*/
            ArrayList<CustomDTO> mostMovableItems = mostMovableBO.getAllMostMovableItem();
            for (CustomDTO dto : mostMovableItems) {
                tblMostMovabletem.getItems().add(new MovableTM(dto.getCode(), dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand(), dto.getQty()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

