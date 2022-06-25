package dao.custom.impl;

import dao.SQLUtil;
import dao.custom.QueryDAO;
import entity.CustomEntity;
import entity.OrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDAOImpl implements QueryDAO {


    @Override
    public ArrayList<OrderDetails> searchOrderDetail(String enteredText) throws SQLException, ClassNotFoundException {
        ResultSet result = SQLUtil.executeQuery("SELECT `OrderDetails`.oid,`OrderDetails`.itemCode,`OrderDetails`.qty,`Item`.unitPrice,`OrderDetails`.discount from `OrderDetails` INNER JOIN  Item on `OrderDetails`.itemCode = `Item`.code where `OrderDetails`.oid LIKE ? OR `OrderDetails`.itemCode LIKE ? OR `OrderDetails`.qty LIKE ? OR `OrderDetails`.discount LIKE ?;", enteredText, enteredText, enteredText, enteredText);
        ArrayList<OrderDetails> orList = new ArrayList<>();


        while (result.next()) {
            double total=(result.getInt(3)*result.getDouble(4)-result.getDouble(5));
            orList.add(new OrderDetails(result.getString(1),
                    result.getString(2),
                    result.getInt(3),
                    result.getBigDecimal(4),
                    result.getBigDecimal(5),
                    total

            ));
        }
        return orList;
    }

    @Override
    public ArrayList<CustomEntity> mostMovableItem() throws SQLException, ClassNotFoundException {

        ResultSet rst = SQLUtil.executeQuery("SELECT `Item`.code,description,unitPrice,qtyOnHand,`OrderDetails`.qty from `Item` inner join `OrderDetails` on `Item`.code = `OrderDetails`.itemCode GROUP BY code ORDER BY qty DESC");
        //"SELECT Item.ItemCode,Description,UnitPrice,QtyOnHand,SUM(OrderQTY) from Item inner join OrderDetail on Item.ItemCode = OrderDetail.ItemCode GROUP BY ItemCode ORDER BY SUM(OrderQTY) DESC"
        ArrayList<CustomEntity> mostMovable = new ArrayList();
        while (rst.next()) {
            mostMovable.add(new CustomEntity(rst.getString(1),
                    rst.getString(2),
                    rst.getBigDecimal(3),
                    rst.getInt(4),
                    rst.getInt(5)));
        }
        return mostMovable;
    }

    @Override
    public ArrayList<CustomEntity> leastMovableItem() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.executeQuery("SELECT `Item`.code,description,unitPrice,qtyOnHand,`OrderDetails`.qty from `Item` inner join `OrderDetails` on `Item`.code = `OrderDetails`.itemCode GROUP BY code ORDER BY qty ");
        ArrayList<CustomEntity> leastMovable = new ArrayList();
        while (rst.next()) {
            leastMovable.add(new CustomEntity(rst.getString(1),
                    rst.getString(2),
                    rst.getBigDecimal(3),
                    rst.getInt(4),
                    rst.getInt(5)));
        }
        return leastMovable;
    }
}
