import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BillingPage extends JFrame {

    private JTextField cafeNameField, addressField, phoneField;
    private JTextField billNoField, dateField, customerNameField, cashierNameField;
    private JTable itemsTable;
    private DefaultTableModel tableModel;
    private ArrayList<OrderItem> cart;

    public BillingPage(ArrayList<OrderItem> cart) {
        this.cart = cart;

        setTitle("Billing Page");
        setSize(500, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel titleLabel = new JLabel("Billing Page", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(160, 10, 200, 25);
        add(titleLabel);

        JLabel cafeNameLabel = new JLabel("Cafe Name");
        cafeNameLabel.setBounds(30, 50, 100, 25);
        add(cafeNameLabel);
        cafeNameField = new JTextField("My Cafe");
        cafeNameField.setBounds(160, 50, 250, 25);
        add(cafeNameField);

        JLabel addressLabel = new JLabel("Address");
        addressLabel.setBounds(30, 85, 100, 25);
        add(addressLabel);
        addressField = new JTextField();
        addressField.setBounds(160, 85, 250, 25);
        add(addressField);

        JLabel phoneLabel = new JLabel("Phone Number");
        phoneLabel.setBounds(30, 120, 100, 25);
        add(phoneLabel);
        phoneField = new JTextField();
        phoneField.setBounds(160, 120, 250, 25);
        add(phoneField);

        JLabel billNoLabel = new JLabel("Bill No");
        billNoLabel.setBounds(30, 165, 100, 25);
        add(billNoLabel);
        billNoField = new JTextField("Auto");
        billNoField.setBounds(160, 165, 150, 25);
        billNoField.setEditable(false);
        add(billNoField);

        JLabel dateLabel = new JLabel("Date");
        dateLabel.setBounds(30, 200, 100, 25);
        add(dateLabel);
        dateField = new JTextField(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        dateField.setBounds(160, 200, 150, 25);
        dateField.setEditable(false);
        add(dateField);

        JLabel customerLabel = new JLabel("Customer Name");
        customerLabel.setBounds(30, 235, 120, 25);
        add(customerLabel);
        customerNameField = new JTextField();
        customerNameField.setBounds(160, 235, 250, 25);
        add(customerNameField);

        JLabel cashierLabel = new JLabel("Cashier Name");
        cashierLabel.setBounds(30, 270, 120, 25);
        add(cashierLabel);
        cashierNameField = new JTextField();
        cashierNameField.setBounds(160, 270, 250, 25);
        add(cashierNameField);

        tableModel = new DefaultTableModel(new String[]{"Item Name", "Qty", "Unit Price", "Total"}, 0);
        itemsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(itemsTable);
        scrollPane.setBounds(30, 310, 420, 150);
        add(scrollPane);
        loadCartIntoTable();

        JButton generateBtn = new JButton("Generate Bill");
        generateBtn.setBounds(40, 480, 130, 30);
        add(generateBtn);

        JButton clearBtn = new JButton("Clear");
        clearBtn.setBounds(190, 480, 100, 30);
        add(clearBtn);

        JButton exitBtn = new JButton("Exit");
        exitBtn.setBounds(310, 480, 100, 30);
        add(exitBtn);

        JLabel thankYouLabel = new JLabel("Thank You !", SwingConstants.CENTER);
        thankYouLabel.setFont(new Font("Arial", Font.ITALIC, 16));
        thankYouLabel.setBounds(160, 530, 200, 25);
        add(thankYouLabel);

        generateBtn.addActionListener(e -> generateBill());
        clearBtn.addActionListener(e -> clearFields());
        exitBtn.addActionListener(e -> dispose());

        setVisible(true);
    }

    // Loads the items added in Order Management into the table
    private void loadCartIntoTable() {
        tableModel.setRowCount(0);
        for (OrderItem item : cart) {
            tableModel.addRow(new Object[]{item.itemName, item.qty, item.unitPrice, item.totalPrice});
        }
    }

    private double calculateTotal() {
        double total = 0;
        for (OrderItem item : cart) {
            total += item.totalPrice;
        }
        return total;
    }

    // Generates the bill in memory (no database). It just assigns a bill
    // number and shows the total - nothing is saved after the app closes.
    private void generateBill() {
        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No items in the order. Please add items in Order Management first");
            return;
        }

        double total = calculateTotal();
        int billNo = DataStore.generateBillNo();
        billNoField.setText(String.valueOf(billNo));

        JOptionPane.showMessageDialog(this, "Bill generated. Total: " + total);
        cart.clear();
        loadCartIntoTable();
    }

    private void clearFields() {
        customerNameField.setText("");
        cashierNameField.setText("");
        cart.clear();
        loadCartIntoTable();
    }
}
