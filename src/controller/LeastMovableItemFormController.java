package controller;

import bo.BOFactory;
import bo.custom.LeastMovableItemBO;
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

public class LeastMovableItemFormController {
    public AnchorPane LeastMovableItemContext;
    public TableView<MovableTM> tblILeastMovabletem;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colUnitPrice;
    public TableColumn colQtyOnHand;
    public TableColumn colOrderQty;

    LeastMovableItemBO leastMovableItemBO = (LeastMovableItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LEAST_MOVABLE);

    public void initialize(){
        tblILeastMovabletem.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblILeastMovabletem.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblILeastMovabletem.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblILeastMovabletem.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        tblILeastMovabletem.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("orderQty"));

        loadAllMovableItems();
    }

    private void loadAllMovableItems() {
        tblILeastMovabletem.getItems().clear();
        try {
            /*Get all items*/
            ArrayList<CustomDTO> leastMovableItems = leastMovableItemBO.getAllLeastMovableItem();
            for (CustomDTO dto : leastMovableItems) {
                tblILeastMovabletem.getItems().add(new MovableTM(dto.getCode(), dto.getDescription(), dto.getUnitPrice(), dto.getQtyOnHand(), dto.getQty()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
