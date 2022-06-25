package bo.custom.impl;

import bo.custom.ItemBO;
import dao.DAOFactory;
import dao.custom.ItemDAO;
import dto.CustomerDTO;
import dto.ItemDTO;
import entity.Customer;
import entity.Item;

import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
//    private final ItemDAO itemDAO = new ItemDAOImpl();

    @Override
    public ArrayList<ItemDTO> searchItems(String enteredText) throws SQLException, ClassNotFoundException {
        ArrayList<Item> items = itemDAO.searchItems(enteredText);
        ArrayList<ItemDTO> itemDTOS=new ArrayList<>();

        for (Item item : items) {
            itemDTOS.add(new ItemDTO(item.getCode(),
                    item.getDescription(),
                    item.getPackSize(),
                    item.getUnitPrice(),
                    item.getQtyOnHand(),
                    item.getDiscount()
            ));
        }
        return itemDTOS;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems() throws SQLException, ClassNotFoundException {
        ArrayList<Item> all = itemDAO.getAll();
        ArrayList<ItemDTO> allItems= new ArrayList<>();
        for (Item item : all) {
            allItems.add(new ItemDTO(item.getCode(),item.getDescription(),item.getPackSize(),item.getUnitPrice(),item.getQtyOnHand(),item.getDiscount()));
        }
        return allItems;
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(code);
    }

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.save(new Item(dto.getCode(),dto.getDescription(),dto.getPackSize(),dto.getUnitPrice(),dto.getQtyOnHand(),dto.getDiscount()));
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(dto.getCode(),dto.getDescription(),dto.getPackSize(),dto.getUnitPrice(),dto.getQtyOnHand(),dto.getDiscount()));
    }

    @Override
    public boolean itemExist(String code) throws SQLException, ClassNotFoundException {
        return itemDAO.exist(code);
    }

    @Override
    public String generateNewItemCode() throws SQLException, ClassNotFoundException {
        return itemDAO.generateNewID();
    }
}
