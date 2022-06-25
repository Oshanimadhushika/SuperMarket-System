package bo.custom;

import bo.SuperBO;
import dto.CustomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MostMoveableItemBO extends SuperBO {
    ArrayList<CustomDTO> getAllMostMovableItem() throws SQLException, ClassNotFoundException;
}
