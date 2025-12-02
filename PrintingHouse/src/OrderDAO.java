import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<>();
        String sql = "SELECT * FROM orders";

        try (Connection conn = DataBase.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Order order = new Order(
                    rs.getInt("OrderId"),
                    rs.getInt("ProductId"),
                    rs.getInt("EmployeeId"), 
                    rs.getString("OrderType"),
                    rs.getDate("OrderDate")
                );
                orderList.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }
}