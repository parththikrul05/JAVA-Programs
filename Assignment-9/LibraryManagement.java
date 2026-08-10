import java.util.Scanner;

class Book {
    
    // Final variable: The ISBN is permanently assigned upon creation
    private final String ISBN;
    private String title;
    private String author;
    private double price;

    // Constructor initializes the final ISBN along with other attributes
    public Book(String ISBN, String title, String author, double price) {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    // Method to display all book details
    public void displayBookInfo() {
        System.out.println("\n--- Library Book Info ---");
        System.out.println("ISBN: " + ISBN);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: $" + price);
        System.out.println("-------------------------\n");
    }

    public void setPrice(double newPrice) {
        this.price = newPrice;
    }
}

public class LibraryManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Taking input for the book details
        System.out.print("Enter Book ISBN: ");
        String isbn = scanner.nextLine();

        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();

        System.out.print("Enter Author Name: ");
        String author = scanner.nextLine();

        System.out.print("Enter Book Price: ");
        double price = scanner.nextDouble();

        Book book1 = new Book(isbn, title, author, price);
        book1.displayBookInfo();

        System.out.print("Enter a new price to update the book: ");
        double newPrice = scanner.nextDouble();
        
        book1.setPrice(newPrice);
        System.out.println("After price update:");
        book1.displayBookInfo();

        scanner.close();
    }
}