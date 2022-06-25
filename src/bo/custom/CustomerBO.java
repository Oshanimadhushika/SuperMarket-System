package bo.custom;

import bo.SuperBO;
import dto.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

    public interface CustomerBO extends SuperBO {
        public ArrayList<CustomerDTO> searchCustomers(String enteredText) throws SQLException, ClassNotFoundException;

        ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException;

        boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;

        boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;

        boolean customerExist(String id) throws SQLException, ClassNotFoundException;

        boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;

        String generateNewCustomerID() throws SQLException, ClassNotFoundException;
    }


