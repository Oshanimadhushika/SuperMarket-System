package dao.custom;

import dao.SuperDAO;
import dto.OrderDetailDTO;
import entity.CustomEntity;
import entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    public ArrayList<OrderDetails> searchOrderDetail(String enteredText) throws SQLException, ClassNotFoundException;

    ArrayList<CustomEntity> mostMovableItem() throws SQLException, ClassNotFoundException;
    ArrayList<CustomEntity> leastMovableItem() throws SQLException, ClassNotFoundException;
   // public ArrayList<CustomEntity> searchOrderByOrderID(String id) throws SQLException, ClassNotFoundException;
}
