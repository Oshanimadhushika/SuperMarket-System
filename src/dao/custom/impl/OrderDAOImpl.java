package dao.custom.impl;

import dao.SQLUtil;
import dao.custom.OrderDAO;
import entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class OrderDAOImpl implements OrderDAO {
    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        ResultSet resultSet=SQLUtil.executeQuery("SELECT orderid,customerID FROM `Orders` ORDER BY orderid DESC");

        ArrayList<Order> allOrders=new ArrayList<>();

        while (resultSet.next()) {
            allOrders.add(new Order(resultSet.getString(1),
                    resultSet.getString(2))
            );
        }

        return allOrders;
    }

    @Override
    public boolean save(Order entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO `Orders` (orderid, date, customerID) VALUES (?,?,?)", entity.getOrderId(), entity.getDate(), entity.getCustomerID());
    }

    @Override
    public boolean update(Order entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String oid) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("SELECT orderid FROM `Orders` WHERE orderid=?", oid).next();
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return  SQLUtil.executeUpdate("DELETE FROM `Orders` WHERE orderid=?",s);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT orderid FROM `Orders` ORDER BY orderid DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("orderid").replace("OID-", "")) + 1)) : "OID-001";
    }


    @Override
    public ArrayList<Order> searchOrder(String enteredText) throws SQLException, ClassNotFoundException {
        ResultSet result = SQLUtil.executeQuery("SELECT orderid,customerID FROM `Orders` where orderid LIKE ?  OR customerID LIKE ? ORDER BY orderid DESC", enteredText, enteredText);
        ArrayList<Order> orList = new ArrayList<>();


        while (result.next()) {
            orList.add(new Order(
                    result.getString(1),
                    result.getString(2)

            ));
        }
        return orList;
    }
}
