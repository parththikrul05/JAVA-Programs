import java.util.Scanner;

// Product interface defining required behaviors
interface IProduct {
    void displayDetails();
    double calculateDiscount(); 
}

// Abstract class holding common product properties
abstract class BaseProduct implements IProduct {
    protected String productId;
    protected String name;
    protected double basePrice;

    public BaseProduct(String productId, String name, double basePrice) {
        this.productId = productId;
        this.name = name;
        this.basePrice = basePrice;
    }

    protected double getFinalPrice() {
        return basePrice - calculateDiscount();
    }
}

// Electronic Product
class Electronic extends BaseProduct {
    private int warrantyMonths;

    public Electronic(String productId, String name, double basePrice, int warrantyMonths) {
        super(productId, name, basePrice);
        this.warrantyMonths = warrantyMonths;
    }

    public double calculateDiscount() {
        // 10% discount on electronics
        return basePrice * 0.10;
    }

    public void displayDetails() {
        System.out.println("[Electronic] " + name + " (ID: " + productId + ")");
        System.out.println("Warranty: " + warrantyMonths + " months");
        System.out.println("Original Price: $" + basePrice);
        System.out.println("Final Price after discount: $" + getFinalPrice());
        System.out.println();
    }
}

// Clothing Product
class Clothing extends BaseProduct {
    private String size;
    private String material;

    public Clothing(String productId, String name, double basePrice, String size, String material) {
        super(productId, name, basePrice);
        this.size = size;
        this.material = material;
    }

    public double calculateDiscount() {
        // 20% clearance discount on clothing
        return basePrice * 0.20;
    }

    public void displayDetails() {
        System.out.println("[Clothing] " + name + " (ID: " + productId + ")");
        System.out.println("Size: " + size + " | Material: " + material);
        System.out.println("Original Price: $" + basePrice);
        System.out.println("Final Price after discount: $" + getFinalPrice());
        System.out.println();
    }
}

// Grocery Product
class Grocery extends BaseProduct {
    private String expirationDate;

    public Grocery(String productId, String name, double basePrice, String expirationDate) {
        super(productId, name, basePrice);
        this.expirationDate = expirationDate;
    }

    public double calculateDiscount() {
        return 0;
    }

    public void displayDetails() {
        System.out.println("[Grocery] " + name + " (ID: " + productId + ")");
        System.out.println("Expiry : " + expirationDate);
        System.out.println("Price: $" + getFinalPrice());
        System.out.println();
    }
}

// Main class to run the e-commerce system
public class ECommerceSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--Enter Electronic Product Details--");
        System.out.print("Product ID: ");
        String eId = scanner.nextLine();
        System.out.print("Product Name: ");
        String eName = scanner.nextLine();
        System.out.print("Base Price: ");
        double ePrice = scanner.nextDouble();
        System.out.print("Warranty (in months): ");
        int warranty = scanner.nextInt();
        scanner.nextLine();

        IProduct laptop = new Electronic(eId, eName, ePrice, warranty);

        System.out.println("\n--Enter Clothing Product Details--");
        System.out.print("Product ID: ");
        String cId = scanner.nextLine();
        System.out.print("Product Name: ");
        String cName = scanner.nextLine();
        System.out.print("Base Price: ");
        double cPrice = scanner.nextDouble();
        scanner.nextLine(); 
        System.out.print("Size (e.g., S, M, L, XL): ");
        String size = scanner.nextLine();
        System.out.print("Material: ");
        String material = scanner.nextLine();

        IProduct shirt = new Clothing(cId, cName, cPrice, size, material);

        System.out.println("\n--Enter Grocery Product Details--");
        System.out.print("Product ID: ");
        String gId = scanner.nextLine();
        System.out.print("Product Name: ");
        String gName = scanner.nextLine();
        System.out.print("Base Price: ");
        double gPrice = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Expiration Date (YYYY-MM-DD): ");
        String expDate = scanner.nextLine();

        IProduct apple = new Grocery(gId, gName, gPrice, expDate);
        System.out.println("\nE-Commerce Product Catalog   ");
        laptop.displayDetails();
        shirt.displayDetails();
        apple.displayDetails();
        scanner.close();
    }
}