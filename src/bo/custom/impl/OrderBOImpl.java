package bo.custom.impl;

import bo.custom.OrderBO;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dao.custom.OrderDAO;
import dao.custom.impl.OrderDAOImpl;
import dto.OrderDTO;
import entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {
    //OrderDAO orderDAO = new OrderDAOImpl();
   private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);

    @Override
    public ArrayList<OrderDTO> getAllOrders() throws SQLException, ClassNotFoundException {
        ArrayList<Order> all = orderDAO.getAll() ;
        ArrayList<OrderDTO> allOrder = new ArrayList<>();

        for (Order order : all) {
            allOrder.add(new OrderDTO(order.getOrderId(),order.getCustomerID()));
        }
        return allOrder;
    }

    @Override
    public boolean deleteOrders(String id) throws SQLException, ClassNotFoundException {
        return orderDAO.delete(id);
    }

    @Override
    public ArrayList<OrderDTO> getAllSearchOrder(String enteredText) throws SQLException, ClassNotFoundException {
        ArrayList<Order> order = orderDAO.searchOrder(enteredText);

        ArrayList<OrderDTO> orDto = new ArrayList<>();

        for (Order orderList : order) {

            orDto.add(new OrderDTO(
                    orderList.getOrderId(),
                    orderList.getCustomerID()
            ));
        }
        return orDto;
    }
}
