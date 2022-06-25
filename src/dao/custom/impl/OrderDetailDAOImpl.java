package dao.custom.impl;

import dao.SQLUtil;
import dao.custom.OrderDetailDAO;
import entity.OrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailDAOImpl implements OrderDetailDAO {
    @Override
    public ArrayList<OrderDetails> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(OrderDetails entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO `OrderDetails` (oid, itemCode, qty,discount) VALUES (?,?,?,?)", entity.getOid(), entity.getItemCode(),  entity.getQty(),entity.getDiscount());
    }

    @Override
    public boolean update(OrderDetails entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public OrderDetails search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }

   /* @Override
    public ArrayList<OrderDetails> searchOrderDetail(String enteredText) throws SQLException, ClassNotFoundException {
        ResultSet result = SQLUtil.executeQuery("SELECT  FROM `OrderDetails` where oid LIKE ? OR itemCode LIKE ? OR qty LIKE ? OR discount LIKE ?", enteredText, enteredText, enteredText, enteredText);
        ArrayList<OrderDetails> orList = new ArrayList<>();


        while (result.next()) {
            double total=(result.getInt(3)*result.getDouble(4)-result.getDouble(5));
            orList.add(new OrderDetails(result.getString(1),
                    result.getString(2),
                    result.getInt(3),
                    result.getDouble(4),
                    result.getDouble(5),
                    total

            ));
        }*/



}
