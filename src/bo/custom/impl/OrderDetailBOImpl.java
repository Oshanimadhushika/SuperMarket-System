package bo.custom.impl;

import bo.custom.OrderDetailBO;
import dao.DAOFactory;
import dao.custom.OrderDAO;
import dao.custom.OrderDetailDAO;
import dao.custom.QueryDAO;
import dao.custom.impl.OrderDAOImpl;
import dao.custom.impl.OrderDetailDAOImpl;
import dto.OrderDetailDTO;
import entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailBOImpl implements OrderDetailBO {
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);
    @Override
    public ArrayList<OrderDetailDTO> getAllOrderDetails() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<OrderDetailDTO> searchOrderDetails(String enteredText) throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetails> orderDetails = queryDAO.searchOrderDetail(enteredText);


        ArrayList<OrderDetailDTO> orDetailDto=new ArrayList<>();

        for (OrderDetails ordersList : orderDetails) {

            orDetailDto.add(new OrderDetailDTO(ordersList.getOid(),
                    ordersList.getItemCode(),
                    ordersList.getQty(),
                    ordersList.getUnitPrice(),
                    ordersList.getDiscount(),
                    ordersList.getTotal()
            ));
        }
        return orDetailDto;
    }
}
