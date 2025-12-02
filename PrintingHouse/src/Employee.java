public class Employee {
    private int EmployeeId;
    private String EmployeePhone;
    private String EmployeeName;

    public Employee(int EmployeeId, String EmployeePhone, String EmployeeName) {
        this.EmployeeId = EmployeeId;
        this.EmployeePhone = EmployeePhone;
        this.EmployeeName = EmployeeName;
    }

    
    public int getEmployedId() { return EmployeeId; }
    public String getEmployeePhone() { return EmployeePhone; }
    public String getEmployeeName() { return EmployeeName; }
}