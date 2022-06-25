package bo.custom;

import bo.SuperBO;
import dto.OrderDetailDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDetailBO extends SuperBO {
    ArrayList<OrderDetailDTO> getAllOrderDetails() throws SQLException, ClassNotFoundException;

    public ArrayList<OrderDetailDTO> searchOrderDetails(String enteredText) throws SQLException, ClassNotFoundException;
}
