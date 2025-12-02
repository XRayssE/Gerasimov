import java.sql.Date;

public class Order {
    private int orderId;
    private int productId;
    private int EmployeeId; 
    private String orderType;
    private Date orderDate;

    public Order(int orderId, int productId, int EmployeeId, String orderType, Date orderDate) {
        this.orderId = orderId;
        this.productId = productId;
        this.EmployeeId = EmployeeId;  
        this.orderType = orderType;
        this.orderDate = orderDate;
    }

   
    public int getOrderId() { return orderId; }
    public int getProductId() { return productId; }
    public int getEmployeeId() { return EmployeeId; }  
    public String getOrderType() { return orderType; }
    public Date getOrderDate() { return orderDate; }
}