package bo.custom.impl;

import bo.custom.MostMoveableItemBO;
import dao.DAOFactory;
import dao.custom.QueryDAO;
import dto.CustomDTO;
import entity.CustomEntity;

import java.sql.SQLException;
import java.util.ArrayList;

public class MostMovableItemBOImpl implements MostMoveableItemBO {
    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);
    @Override
    public ArrayList<CustomDTO> getAllMostMovableItem() throws SQLException, ClassNotFoundException {
        ArrayList<CustomEntity> all = queryDAO.mostMovableItem();
        ArrayList<CustomDTO> allMovable = new ArrayList<>();
        for (CustomEntity ent : all) {
            allMovable.add(new CustomDTO(ent.getCode(), ent.getDescription(), ent.getUnitPrice(), ent.getQtyOnHand(), ent.getQty()));
        }
        return allMovable;
    }
}
