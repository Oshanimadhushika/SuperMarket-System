package dao.custom;

import dao.CrudDAO;
import entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<Customer,String> {
       // public ArrayList<Customer> getAllCustomersByAddress(String address)throws ClassNotFoundException, SQLException;

     public ArrayList<Customer> searchCustomers(String enteredText) throws SQLException, ClassNotFoundException;

    }


