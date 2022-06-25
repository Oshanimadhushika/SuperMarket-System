package dao.custom;

import dao.CrudDAO;
import entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderDAO  extends CrudDAO<Order,String> {

    public ArrayList<Order> searchOrder(String enteredText) throws SQLException, ClassNotFoundException;


}
