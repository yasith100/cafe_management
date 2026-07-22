import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MenuManagement extends JFrame {

    private JTextField itemNameField, priceField;
    private JTable itemsTable;
    private DefaultTableModel tableModel;

    public MenuManagement() {
        setTitle("Menu Management");
        setSize(420, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel itemNameLabel = new JLabel("Item Name");
        itemNameLabel.setBounds(30, 30, 100, 25);
        add(itemNameLabel);

        itemNameField = new JTextField();
        itemNameField.setBounds(150, 30, 180, 25);
        add(itemNameField);

        JLabel priceLabel = new JLabel("Price");
        priceLabel.setBounds(30, 70, 100, 25);
        add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(150, 70, 180, 25);
        add(priceField);

        JButton addBtn = new JButton("Add");
        addBtn.setBounds(60, 110, 100, 30);
        add(addBtn);

        JButton clearBtn = new JButton("Clear");
        clearBtn.setBounds(200, 110, 100, 30);
        add(clearBtn);

        JButton closeBtn = new JButton("Close");
        closeBtn.setBounds(130, 150, 100, 30);
        add(closeBtn);

        // Table showing all menu items currently stored in memory
        tableModel = new DefaultTableModel(new String[]{"Item Name", "Price"}, 0);
        itemsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(itemsTable);
        scrollPane.setBounds(30, 200, 340, 170);
        add(scrollPane);

        addBtn.addActionListener(e -> addItem());
        clearBtn.addActionListener(e -> clearFields());
        closeBtn.addActionListener(e -> dispose());

        loadItemsIntoTable();
        setVisible(true);
    }

    private void addItem() {
        String itemName = itemNameField.getText().trim();
        String priceText = priceField.getText().trim();

        if (itemName.isEmpty() || priceText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both Item Name and Price");
            return;
        }

        try {
            double price = Double.parseDouble(priceText);
            DataStore.addMenuItem(itemName, price);
            loadItemsIntoTable();
            JOptionPane.showMessageDialog(this, "Item added");
            clearFields();
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Price must be a number");
        }
    }

    // Refreshes the table from the in-memory list
    private void loadItemsIntoTable() {
        tableModel.setRowCount(0);
        for (MenuItem item : DataStore.menuItems) {
            tableModel.addRow(new Object[]{item.name, item.price});
        }
    }

    private void clearFields() {
        itemNameField.setText("");
        priceField.setText("");
    }
}
