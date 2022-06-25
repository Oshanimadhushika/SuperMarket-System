package dao.custom.impl;

import dao.SQLUtil;
import dao.custom.ItemDAO;
import entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM `Item`");
        ArrayList<Item> allItems = new ArrayList<>();
        while (rst.next()) {
            allItems.add(new Item(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getBigDecimal(4),
                    rst.getInt(5),
                    rst.getBigDecimal(6)

            ));
        }
        return allItems;
    }

    @Override
    public boolean delete(String code) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("DELETE FROM `Item` WHERE code=?", code);
    }

    @Override
    public boolean save(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO `Item` (code, description,packSize, unitPrice, qtyOnHand,discount) VALUES (?,?,?,?,?,?)", entity.getCode(), entity.getDescription(),entity.getPackSize(), entity.getUnitPrice(), entity.getQtyOnHand(),entity.getDiscount());
    }

    @Override
    public boolean update(Item entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("UPDATE `Item` SET description=?,packSize=?, unitPrice=?, qtyOnHand=?,discount=? WHERE code=?", entity.getDescription(),entity.getPackSize(),entity.getUnitPrice(), entity.getQtyOnHand(),entity.getDiscount(), entity.getCode());
    }

    @Override
    public Item search(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM `Item` WHERE code=?", code);
        if (rst.next()) {
            return new Item(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getBigDecimal(4),
                    rst.getInt(5),
                    rst.getBigDecimal(6));
        }
        return null;
    }

    @Override
    public boolean exist(String code) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("SELECT code FROM `Item` WHERE code=?", code).next();
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT code FROM `Item` ORDER BY code DESC LIMIT 1;");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }


    @Override
    public ArrayList<Item> searchItems(String enteredText) throws SQLException, ClassNotFoundException {
        ResultSet result = SQLUtil.executeQuery("SELECT * FROM `Item` where code LIKE ? OR description LIKE ? OR packSize LIKE ? OR unitPrice LIKE ? OR qtyOnHand LIKE ? OR discount LIKE ? ", enteredText, enteredText, enteredText, enteredText, enteredText, enteredText);
        ArrayList<Item> list = new ArrayList<>();

        while (result.next()) {
            list.add(new Item(result.getString(1),
                    result.getString(2),
                    result.getString(3),
                    result.getBigDecimal(4),
                    result.getInt(5),
                    result.getBigDecimal(6)

            ));
        }
        return list;
    }
}
