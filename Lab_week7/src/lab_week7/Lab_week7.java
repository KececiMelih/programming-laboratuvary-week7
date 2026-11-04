
package lab_week7;


public class Lab_week7 {



    public static void main(String[] args) {

        
        Customer customer1 = new Customer(
                "melih",
                "05551112233",
                "Ataturk Street",
                "Talas",
                "Kayseri"
        );

        Customer customer2 = new Customer(
                "ahmet",
                "05554445566",
                "Cumhuriyet Street",
                "Melikgazi",
                "Kayseri"
        );

      
        try {

            MenuItem burger = new MenuItem("Burger", 150, 1);
            MenuItem pizza = new MenuItem("Pizza", 200, 1);
            MenuItem fries = new MenuItem("Fries", 40, 1);

            Coupon coupon1 = new Coupon("SAVE20", 0.20, 500);
            Coupon coupon2 = new Coupon("SAVE10", 0.10, 500);

            
            Order order1 = new Order(customer1);

            System.out.println("Order 1: "
                    + order1.getOrderNumber());

           
            order1.addItem(burger);
            order1.addItem(new MenuItem("Burger", 150, 2));
            order1.addItem(pizza);
            order1.addItem(fries);

            
            try {
                order1.updateQuantity("Pizza", 2);
            } catch (ItemNotFoundException e) {
                System.out.println(e.getMessage());
            }

            
            try {
                order1.removeItem("Fries");
            } catch (ItemNotFoundException e) {
                System.out.println(e.getMessage());
            }

            
            order1.applyCoupon(coupon1);

           
            try {
                order1.checkout();
            } catch (MinimumOrderException e) {
                System.out.println(e.getMessage());
            }

           
            Order order2 = new Order(customer2);

            System.out.println("\nOrder 2: "
                    + order2.getOrderNumber());

            order2.addItem(burger);
            order2.addItem(fries);

            
            order2.applyCoupon(coupon2);

            try {
                order2.checkout();
            } catch (MinimumOrderException e) {
                System.out.println(e.getMessage());
            }

          
            try {
                order2.removeItem("Hamburger");
            } catch (ItemNotFoundException e) {
                System.out.println("\nItem Error: "
                        + e.getMessage());
            }

           
            try {
                burger.setQuantity(0);
            } catch (InvalidQuantityException e) {
                System.out.println("\nQuantity Error: "
                        + e.getMessage());
            }

           
            Order order3 = new Order(customer1);

            System.out.println("\nOrder 3: "
                    + order3.getOrderNumber());

            order3.addItem(new MenuItem("Coffee", 50, 1));

           
            try {
                order3.checkout();
            } catch (MinimumOrderException e) {
                System.out.println("\nMinimum Order Error: "
                        + e.getMessage());
            }

          
            System.out.println("\n========== STATISTICS ==========");

            System.out.println("Completed Orders: "
                    + Order.getCompletedOrders());

            System.out.println("Total Revenue: "
                    + Order.getTotalRevenue() + " TL");

            System.out.println("================================");

        } catch (InvalidQuantityException e) {
            System.out.println("Quantity Error: " + e.getMessage());
        }
    }
}