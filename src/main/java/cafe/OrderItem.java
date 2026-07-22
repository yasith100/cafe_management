// Represents a single item within an order
public class OrderItem {
    String itemName;
    int qty;
    double unitPrice;
    double totalPrice;

    public OrderItem(String itemName, int qty, double unitPrice) {
        this.itemName = itemName;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.totalPrice = qty * unitPrice;
    }
}
