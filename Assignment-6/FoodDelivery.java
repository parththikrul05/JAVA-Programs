
  // Interface for the anonymous classes to implement
interface DeliveryStatus {
    void updateStatus();
}

public class FoodDelivery {
    private String restaurantName;
    private String customerName;

    public FoodDelivery(String restaurantName, String customerName) {
        this.restaurantName = restaurantName;
        this.customerName = customerName;
    }

    // Inner Class: Handles order specifics
    class OrderDetails {
        private String foodItem;
        private double price;

        public OrderDetails(String foodItem, double price) {
            this.foodItem = foodItem;
            this.price = price;
        }

        public void printOrderReceipt() {
            System.out.println("Order Receipt");
            System.out.println("Customer: " + customerName);
            System.out.println("Restaurant: " + restaurantName);
            System.out.println("Item: " + foodItem);
            System.out.println("Total: $" + price);
            System.out.println("---------------------");
        }
    }

    public static void main(String[] args) {
        FoodDelivery app = new FoodDelivery("Burger King", "Alice");
        
        // Instantiate the inner class
        FoodDelivery.OrderDetails order = app.new OrderDetails("Whopper Meal", 12.50);
        order.printOrderReceipt();

        // Anonymous Class 1: Order Prepared Update
        DeliveryStatus preparedStatus = new DeliveryStatus() {
            public void updateStatus() {
                System.out.println("[Status Update]: Your food has been prepared and is awaiting pickup.");
            }
        };

        // Anonymous Class 2: Out for Delivery Update
        DeliveryStatus outForDeliveryStatus = new DeliveryStatus() {
            public void updateStatus() {
                System.out.println("[Status Update]: The driver is on the way to your location.");
            }
        };

        // Anonymous Class 3: Delivered Update
        DeliveryStatus deliveredStatus = new DeliveryStatus() {
            public void updateStatus() {
                System.out.println("[Status Update]: Your order has been delivered. Enjoy your meal!");
            }
        };

        // Execute the anonymous class methods
        preparedStatus.updateStatus();
        outForDeliveryStatus.updateStatus();
        deliveredStatus.updateStatus();
    }
}  
 
