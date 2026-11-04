
package lab_week7;


public class MenuItem {

    private String name;
    private double price;
    private int quantity;

    public MenuItem(String name, double price, int quantity)
            throws InvalidQuantityException {

        this.name = name;

        if (price <= 0) {
            throw new IllegalArgumentException(
                    "Price must be positive!");
        }

        this.price = price;
        setQuantity(quantity);
    }

    
    public MenuItem(MenuItem other) {
        this.name = other.name;
        this.price = other.price;
        this.quantity = other.quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException(
                    "Price must be positive!");
        }

        this.price = price;
    }

    public void setQuantity(int quantity)
            throws InvalidQuantityException {

        if (quantity <= 0) {
            throw new InvalidQuantityException(
                    "Quantity must be positive!");
        }

        this.quantity = quantity;
    }

    public double getTotalPrice() {
        return price * quantity;
    }

    @Override
    public String toString() {
        return name + " | Price: " + price
                + " TL | Quantity: " + quantity
                + " | Total: " + getTotalPrice() + " TL";
    }
}
