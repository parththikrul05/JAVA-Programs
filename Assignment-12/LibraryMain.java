import library.Book;
import java.util.Scanner; 

public class LibraryMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("-Library Management System -");
        
        // Ask the user how many books they want to enter
        System.out.print("\nEnter the number of books you want to add: ");
        int n = scanner.nextInt();
        
        // Create an array to hold 'n' Book objects
        Book[] books = new Book[n];
        
        // Loop to take input for each book
        for (int i = 0; i < n; i++) {
            System.out.println("\n-Enter Details for Book " + (i + 1) + "-");
            
            System.out.print("Enter Book ID: ");
            int bookId = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            
            System.out.print("Enter Title: ");
            String title = scanner.nextLine();
            
            System.out.print("Enter Author: ");
            String author = scanner.nextLine();
            
            System.out.print("Enter Price: ");
            double price = scanner.nextDouble();
            
            // Create a new Book object and store it in the array
            books[i] = new Book(bookId, title, author, price);
        }
        
        // Displaying all the stored books
        System.out.println("BOOK INFORMATION");
  
        for (int i = 0; i < n; i++) {
            System.out.println("Book " + (i + 1) + ":");
            books[i].displayBookDetails();
        }
        scanner.close();
    }
}