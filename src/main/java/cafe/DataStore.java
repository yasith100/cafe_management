import java.util.ArrayList;

// Simple in-memory storage for the whole app.
// This replaces the database - everything is kept in memory while the app is running.
// Data is lost when the app is closed, since there is no database anymore.
public class DataStore {

    // All menu items added through Menu Management
    public static ArrayList<MenuItem> menuItems = new ArrayList<>();

    // Used to generate simple increasing bill numbers
    public static int nextBillNo = 1;

    public static void addMenuItem(String name, double price) {
        menuItems.add(new MenuItem(name, price));
    }

    public static int generateBillNo() {
        return nextBillNo++;
    }
}
