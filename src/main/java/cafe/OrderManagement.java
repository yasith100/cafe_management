import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;

public class OrderManagement extends JFrame {

    private JTextField customerNameField, quantityField, unitPriceField, totalPriceField;
    private JComboBox<String> itemCombo;
    private ArrayList<OrderItem> cart;
    private HashMap<String, Double> priceMap = new HashMap<>();

    public OrderManagement(ArrayList<OrderItem> cart) {
        this.cart = cart;

        setTitle("Order Management");
        setSize(420, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel customerLabel = new JLabel("Customer Name");
        customerLabel.setBounds(30, 20, 120, 25);
        add(customerLabel);

        customerNameField = new JTextField();
        customerNameField.setBounds(170, 20, 180, 25);
        add(customerNameField);

        JLabel itemLabel = new JLabel("Select Items");
        itemLabel.setBounds(30, 60, 120, 25);
        add(itemLabel);

        itemCombo = new JComboBox<>();
        itemCombo.setBounds(170, 60, 180, 25);
        add(itemCombo);

        JLabel qtyLabel = new JLabel("Quantity");
        qtyLabel.setBounds(30, 100, 120, 25);
        add(qtyLabel);

        quantityField = new JTextField();
        quantityField.setBounds(170, 100, 180, 25);
        add(quantityField);

        JLabel unitPriceLabel = new JLabel("Unit Price");
        unitPriceLabel.setBounds(30, 140, 120, 25);
        add(unitPriceLabel);

        unitPriceField = new JTextField();
        unitPriceField.setBounds(170, 140, 180, 25);
        unitPriceField.setEditable(false);
        add(unitPriceField);

        JLabel totalPriceLabel = new JLabel("Total Price");
        totalPriceLabel.setBounds(30, 180, 120, 25);
        add(totalPriceLabel);

        totalPriceField = new JTextField();
        totalPriceField.setBounds(170, 180, 180, 25);
        totalPriceField.setEditable(false);
        add(totalPriceField);

        JButton addOrderBtn = new JButton("Add Order");
        addOrderBtn.setBounds(60, 240, 120, 30);
        add(addOrderBtn);

        JButton clearBtn = new JButton("Clear");
        clearBtn.setBounds(220, 240, 120, 30);
        add(clearBtn);

        JButton closeBtn = new JButton("Close");
        closeBtn.setBounds(150, 280, 100, 30);
        add(closeBtn);

        loadMenuItems();

        itemCombo.addActionListener(e -> fillPriceAndTotal());
        quantityField.addActionListener(e -> fillPriceAndTotal());
        addOrderBtn.addActionListener(e -> addOrder());
        clearBtn.addActionListener(e -> clearFields());
        closeBtn.addActionListener(e -> dispose());

        setVisible(true);
    }

    // Loads menu items from the in-memory DataStore instead of a database
    private void loadMenuItems() {
        if (DataStore.menuItems.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No menu items yet. Please add items in Menu Management first.");
            return;
        }

        for (MenuItem item : DataStore.menuItems) {
            priceMap.put(item.name, item.price);
            itemCombo.addItem(item.name);
        }
    }

    private void fillPriceAndTotal() {
        String selected = (String) itemCombo.getSelectedItem();
        if (selected == null) return;

        double price = priceMap.getOrDefault(selected, 0.0);
        unitPriceField.setText(String.valueOf(price));

        try {
            int qty = Integer.parseInt(quantityField.getText().trim());
            totalPriceField.setText(String.valueOf(qty * price));
        } catch (NumberFormatException nfe) {
            totalPriceField.setText("");
        }
    }

    private void addOrder() {
        String selected = (String) itemCombo.getSelectedItem();
        String qtyText = quantityField.getText().trim();

        if (selected == null || qtyText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select an item and enter Quantity");
            return;
        }

        try {
            int qty = Integer.parseInt(qtyText);
            double price = priceMap.getOrDefault(selected, 0.0);

            cart.add(new OrderItem(selected, qty, price));
            JOptionPane.showMessageDialog(this, "Item added to the order");
            clearFields();
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(this, "Quantity must be a number");
        }
    }

    private void clearFields() {
        quantityField.setText("");
        unitPriceField.setText("");
        totalPriceField.setText("");
    }
}
