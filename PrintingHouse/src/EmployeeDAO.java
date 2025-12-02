import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    public List<Employee> getAllEmployees() {
        List<Employee> EmployeeList = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try (Connection conn = DataBase.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Employee Employee = new Employee(
                    rs.getInt("EmployeeId"),
                    rs.getString("EmployeePhone"),
                    rs.getString("EmployeeName")
                );
                EmployeeList.add(Employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return EmployeeList;
    }
}