
package lab_week7;


import java.util.ArrayList;

public class Order {

    private static int nextOrderNumber = 1001;

    public static final double MIN_ORDER = 100;
    public static final double DELIVERY_FEE = 20;

    private static int completedOrders = 0;
    private static double totalRevenue = 0;

    private int orderNumber;
    private Customer customer;
    private ArrayList<MenuItem> items;
    private Coupon coupon;
    private boolean completed;

    public Order(Customer customer) {

        this.customer = customer;
        this.items = new ArrayList<>();
        this.coupon = null;
        this.completed = false;

        orderNumber = nextOrderNumber;
        nextOrderNumber++;
    }

   
    public void addItem(MenuItem item)
            throws InvalidQuantityException {

        for (MenuItem current : items) {

            if (current.getName().equalsIgnoreCase(item.getName())) {

                int newQuantity =
                        current.getQuantity() + item.getQuantity();

                current.setQuantity(newQuantity);
                return;
            }
        }

        items.add(new MenuItem(item));
    }

   
    public void removeItem(String name)
            throws ItemNotFoundException {

        for (int i = 0; i < items.size(); i++) {

            if (items.get(i).getName().equalsIgnoreCase(name)) {

                items.remove(i);
                return;
            }
        }

        throw new ItemNotFoundException(
                name + " was not found!");
    }

    
    public void updateQuantity(String name, int quantity)
            throws ItemNotFoundException,
                   InvalidQuantityException {

        for (MenuItem item : items) {

            if (item.getName().equalsIgnoreCase(name)) {

                item.setQuantity(quantity);
                return;
            }
        }

        throw new ItemNotFoundException(
                name + " was not found!");
    }

   
    public double getSubtotal() {

        double subtotal = 0;

        for (MenuItem item : items) {
            subtotal += item.getTotalPrice();
        }

        return subtotal;
    }

   
    public ArrayList<MenuItem> getItems() {

        ArrayList<MenuItem> copy = new ArrayList<>();

        for (MenuItem item : items) {
            copy.add(new MenuItem(item));
        }

        return copy;
    }

  
    public void applyCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

   
    public double checkout() throws MinimumOrderException {

        if (completed) {
            throw new IllegalStateException(
                    "Order is already completed!");
        }

        double subtotal = getSubtotal();

        if (subtotal < MIN_ORDER) {
            throw new MinimumOrderException(
                    "Minimum order amount is 100 TL!");
        }

        double discount = 0;

        if (coupon != null &&
                subtotal >= coupon.getMinSubtotal()) {

            discount = subtotal * coupon.getDiscountRate();
        }

        double total = subtotal - discount + DELIVERY_FEE;

       
        System.out.println("\n========== RECEIPT ==========");

        System.out.println("Order Number: " + orderNumber);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Address: " + customer.getAddress());

        System.out.println("\nItems:");

        for (MenuItem item : items) {
            System.out.println(item);
        }

        System.out.println("-----------------------------");

        System.out.println("Subtotal: " + subtotal + " TL");

        if (discount > 0) {
            System.out.println("Coupon: " + coupon.getCode());
        } else {
            System.out.println("Coupon Discount: Not Applied");
        }

        System.out.println("Discount: " + discount + " TL");
        System.out.println("Delivery Fee: " + DELIVERY_FEE + " TL");
        System.out.println("Total: " + total + " TL");

        System.out.println("=============================");

        
        completed = true;
        completedOrders++;
        totalRevenue += total;

        return total;
    }

   
    public static int getCompletedOrders() {
        return completedOrders;
    }

    public static double getTotalRevenue() {
        return totalRevenue;
    }

    public int getOrderNumber() {
        return orderNumber;
    }
}