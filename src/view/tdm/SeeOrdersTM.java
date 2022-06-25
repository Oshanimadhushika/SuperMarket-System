package view.tdm;

public class SeeOrdersTM {
    private String orderId;
    private String CustomerId;

    public SeeOrdersTM() {
    }

    public SeeOrdersTM(String orderId, String customerId) {
        this.setOrderId(orderId);
        setCustomerId(customerId);
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String customerId) {
        CustomerId = customerId;
    }

    @Override
    public String toString() {
        return "SeeOrdersTM{" +
                "orderId='" + orderId + '\'' +
                ", CustomerId='" + CustomerId + '\'' +
                '}';
    }
}
