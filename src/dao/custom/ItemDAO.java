package dao.custom;

import dao.CrudDAO;
import entity.Customer;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<Item,String> {
    //public ArrayList<Item> getItemFromPrice(double price)throws ClassNotFoundException, SQLException;

    public ArrayList<Item> searchItems(String enteredText) throws SQLException, ClassNotFoundException;

}