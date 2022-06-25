package bo;


import bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerBOImpl(); // SuperBO bo =new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl(); // SuperBO bo = new ItemBOImpl();
            case PURCHASE_ORDER:
               return new PurchaseOrderBOImpl(); //SuperBO bo = new PurchaseOrderBOImpl();
            case MOST_MOVABLE:
               return new MostMovableItemBOImpl();
            case LEAST_MOVABLE:
                return new LeastMovableItemBOImpl();
            case ORDER:
                return new OrderBOImpl();
            case ORDER_Detail:
                return new OrderDetailBOImpl();
            default:
                return null;
        }
    }

    public enum BOTypes {
        CUSTOMER, ITEM, PURCHASE_ORDER ,MOST_MOVABLE,LEAST_MOVABLE,ORDER,ORDER_Detail
    }

}
