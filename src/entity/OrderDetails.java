package entity;

import java.math.BigDecimal;

public class OrderDetails {
    private String oid;
    private String itemCode;
    private int qty;
    private BigDecimal unitPrice;
    private BigDecimal discount;
    private double total;


    public OrderDetails(String oid, String itemCode, int qty, BigDecimal unitPrice, BigDecimal discount, double total) {
        this.oid = oid;
        this.itemCode = itemCode;
        this.qty = qty;
        this.setUnitPrice(unitPrice);
        this.discount = discount;
        this.total = total;
    }



    public OrderDetails() {
    }

    public OrderDetails(String oid, String itemCode, int qty, BigDecimal discount) {
        this.setOid(oid);
        this.setItemCode(itemCode);
        this.setQty(qty);
        this.setDiscount(discount);
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }
}
