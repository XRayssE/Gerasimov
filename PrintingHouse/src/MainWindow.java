import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainWindow extends JFrame {
    private JTabbedPane tabbedPane;
    private JTable tableEmployees, tableProducts, tableOrders;
    
    public MainWindow() {
        
        super("Система управления типографией");
        
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
      
        DataBase.testConnection();
        
        
        tabbedPane = new JTabbedPane();
        
       
        tableEmployees = new JTable();
        tableProducts = new JTable();
        tableOrders = new JTable();
         
        
        tabbedPane.addTab("Сотрудники", createTablePanel(tableEmployees, "Сотрудники"));
        tabbedPane.addTab("Продукция", createTablePanel(tableProducts, "Продукция"));
        tabbedPane.addTab("Заказы", createTablePanel(tableOrders, "Заказы"));
        
      
        JPanel buttonPanel = new JPanel();
        JButton refreshButton = new JButton("Обновить данные");
        JButton addOrderButton = new JButton("Новый заказ");
        JButton exitButton = new JButton("Выход");
        
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAllData();
            }
        });
        
        addOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showAddOrderDialog();
            }
        });
        
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        buttonPanel.add(refreshButton);
        buttonPanel.add(addOrderButton);
        buttonPanel.add(exitButton);
        
    
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(tabbedPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(mainPanel);
        
       
        loadAllData();
    }
    
    private JPanel createTablePanel(JTable table, String title) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(title, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
       
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        
        return panel;
    }
    
    private void loadAllData() {
        loadEmployees();
        loadProducts();
        loadOrders();
    }
    
    private void loadEmployees() {
        EmployeeDAO EmployeeDAO = new EmployeeDAO();
        List<Employee> employees = EmployeeDAO.getAllEmployees();
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("ФИО");
        model.addColumn("Телефон");
        
        for (Employee employee : employees) {
            model.addRow(new Object[]{
                employee.getEmployedId(),
                employee.getEmployeeName(),
                employee.getEmployeePhone()
            });
        }
        
        tableEmployees.setModel(model);
    }
    
    private void loadProducts() {
        ProductDAO productDAO = new ProductDAO();
        List<Product> products = productDAO.getAllProducts();
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Название продукции");
        model.addColumn("Тираж");
        model.addColumn("Цена");
        
        for (Product product : products) {
            model.addRow(new Object[]{
                product.getProductId(),
                product.getProductName(),
                product.getProductEdition(),
                product.getProductPrice() + " руб."
            });
        }
        
        tableProducts.setModel(model);
    }
    
    private void loadOrders() {
        OrderDAO orderDAO = new OrderDAO();
        List<Order> orders = orderDAO.getAllOrders();
        
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID заказа");
        model.addColumn("ID продукции");
        model.addColumn("ID сотрудника");
        model.addColumn("Тип заказа");
        model.addColumn("Дата заказа");
        
        for (Order order : orders) {
            model.addRow(new Object[]{
                order.getOrderId(),
                order.getProductId(),
                order.getEmployeeId(),
                order.getOrderType(),
                order.getOrderDate()
            });
        }
        
        tableOrders.setModel(model);
    }
    
    private void showAddOrderDialog() {
      
        JDialog dialog = new JDialog(this, "Новый заказ", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JTextField productIdField = new JTextField();
        JTextField employeeIdField = new JTextField();
        JTextField orderTypeField = new JTextField();
        JTextField orderDateField = new JTextField();
        
        panel.add(new JLabel("ID продукции:"));
        panel.add(productIdField);
        panel.add(new JLabel("ID сотрудника:"));
        panel.add(employeeIdField);
        panel.add(new JLabel("Тип заказа:"));
        panel.add(orderTypeField);
        panel.add(new JLabel("Дата заказа (гггг-мм-дд):"));
        panel.add(orderDateField);
        
        JButton saveButton = new JButton("Сохранить");
        JButton cancelButton = new JButton("Отмена");
        
        saveButton.addActionListener(e -> {
            try {
                int productid = Integer.parseInt(productIdField.getText());
                int employeeId = Integer.parseInt(employeeIdField.getText());
                String orderType = orderTypeField.getText();
                String orderDate = orderDateField.getText();
                
                
                JOptionPane.showMessageDialog(dialog, "Заказ добавлен!", "Успех", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
                loadOrders(); 
                
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Проверьте правильность введенных данных!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        cancelButton.addActionListener(e -> dialog.dispose());
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        
        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
               
                UIManager.setLookAndFeel(UIManager.getLookAndFeel());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            MainWindow window = new MainWindow();
            window.setVisible(true);
        });
    }
}