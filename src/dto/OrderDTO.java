package dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class OrderDTO {
    private String orderId;
    private LocalDate date;
    private String customerID;

    private List<OrderDetailDTO> orderDetails;

    private String customerName;
    private BigDecimal orderTotal;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, LocalDate date, String customerID) {
        this.setOrderId(orderId);
        this.setDate(date);
        this.setCustomerID(customerID);
    }

    public OrderDTO(String orderId, LocalDate date, String customerID, List<OrderDetailDTO> orderDetails) {
        this.orderId = orderId;
        this.date = date;
        this.customerID = customerID;
        this.setOrderDetails(orderDetails);
    }

    public OrderDTO(String orderId, LocalDate date, String customerID, List<OrderDetailDTO> orderDetails, String customerName, BigDecimal orderTotal) {
        this.orderId = orderId;
        this.date = date;
        this.customerID = customerID;
        this.setOrderDetails(orderDetails);
        this.setCustomerName(customerName);
        this.setOrderTotal(orderTotal);
    }

    public OrderDTO(String orderId, String customerID) {
        this.orderId = orderId;
        this.customerID = customerID;
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

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId='" + orderId + '\'' +
                ", date=" + date +
                ", customerID='" + customerID + '\'' +
                '}';
    }

    public List<OrderDetailDTO> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetailDTO> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }
}
