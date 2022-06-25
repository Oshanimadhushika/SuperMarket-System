package entity;

import java.time.LocalDate;

public class Order {

     private String orderId;
     private LocalDate date;
     private String customerID;

    public Order(String orderId, String customerID) {
        this.orderId = orderId;
        this.customerID = customerID;
    }

    public Order() {
    }

    public Order(String orderId, LocalDate date, String customerID) {
        this.setOrderId(orderId);
        this.setDate(date);
        this.setCustomerID(customerID);
    }



    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }
}
