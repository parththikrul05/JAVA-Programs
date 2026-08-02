public class Restaurant {
    static int totalOrders = 0;  //counter for orders 
    static double totalRevenue = 0.0;   //calculates overall revenue

    // Calculates the bill including a percentage-based service charge.
    public double calculateBill(double baseAmount, double serviceChargePercent) {
        totalOrders++; // Increment static counter
        double serviceCharge = baseAmount * (serviceChargePercent / 100);
        double finalBill = baseAmount + serviceCharge;
        totalRevenue += finalBill;
        return finalBill;
    }

    // 2. Takeaway Order
    // Calculates the bill including a flat packaging fee.
    public double calculateBill(double baseAmount, int packagingFee) {
        totalOrders++;
        double finalBill = baseAmount + packagingFee;
        totalRevenue += finalBill;
        return finalBill;
    }

    // 3. Delivery Order
    // Calculates the bill including a delivery fee based on distance.
    public double calculateBill(double baseAmount, double distanceInKm, double ratePerKm) {
        totalOrders++;
        double deliveryFee = distanceInKm * ratePerKm;
        double finalBill = baseAmount + deliveryFee;
        totalRevenue += finalBill;
        return finalBill;
    }

    public static void main(String[] args) {
        Restaurant billingSystem = new Restaurant();

        System.out.println("---Processing Orders---");
        double dineInBill = billingSystem.calculateBill(100.0, 10.0);
        System.out.println("Dine-in Bill: $" + dineInBill);

        
        double takeawayBill = billingSystem.calculateBill(50.0, 5);
        System.out.println("Takeaway Bill: $" + takeawayBill);

        double deliveryBill = billingSystem.calculateBill(75.0, 4.5, 1.5);
        System.out.println("Delivery Bill: $" + deliveryBill);

       
        double dineInBill2 = billingSystem.calculateBill(150.0, 10.0);
        System.out.println("Dine-in Bill 2: $" + dineInBill2);

        System.out.println("\n--- End of Day Summary ---");
        System.out.println("Total Orders Processed: " + Restaurant.totalOrders);
        System.out.println("Total Revenue Generated: $" + Restaurant.totalRevenue);
    }
}

