import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class MainMenu extends JFrame {

    // Holds the items added while placing an order.
    // This list is passed to OrderManagement and then to BillingPage.
    private ArrayList<OrderItem> cart = new ArrayList<>();

    public MainMenu() {
        setTitle("Main Menu");
        setSize(500, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 60));

        JButton menuItemBtn = new JButton("Menu Item");
        JButton orderBtn = new JButton("Order");
        JButton billingBtn = new JButton("Billing");
        JButton logoutBtn = new JButton("Logout");

        add(menuItemBtn);
        add(orderBtn);
        add(billingBtn);
        add(logoutBtn);

        menuItemBtn.addActionListener(e -> new MenuManagement());
        orderBtn.addActionListener(e -> new OrderManagement(cart));
        billingBtn.addActionListener(e -> new BillingPage(cart));
        logoutBtn.addActionListener(e -> {
            new LoginPage();
            dispose();
        });

        setVisible(true);
    }
}
