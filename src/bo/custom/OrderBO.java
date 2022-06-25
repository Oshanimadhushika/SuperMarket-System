package bo.custom;

import bo.SuperBO;
import dto.OrderDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO extends SuperBO {

    ArrayList<OrderDTO> getAllOrders() throws SQLException, ClassNotFoundException;

    boolean deleteOrders(String id) throws SQLException, ClassNotFoundException;

    ArrayList<OrderDTO> getAllSearchOrder(String enteredText) throws SQLException, ClassNotFoundException;
}
