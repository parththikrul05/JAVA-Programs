import java.util.Scanner;
// Parent Class
class Employee {
    int id;
    String name;
    double salary;

    // Parent constructor
    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Parent method
    public void displayDetails() {
        System.out.println("Employee ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Base Salary: $" + salary);
    }
}

// Child Class
class Manager extends Employee {
    String department;
    double bonus;

    // Child constructor
    public Manager(int id, String name, double salary, String department, double bonus) {
        // 1. Using super() to invoke the parent constructor
        super(id, name, salary); 
        this.department = department;
        this.bonus = bonus;
    }

    // display method (removed @Override as requested)
    public void displayDetails() {
        // 2. Using super.methodName() to call the parent's method
        super.displayDetails(); 
        System.out.println("Department: " + department);
        System.out.println("Bonus: $" + bonus);
        System.out.println("Total Compensation: $" + (salary + bonus));
        System.out.println("-------------------------");
    }
}

// Main class to test
public class EmployeeManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Taking inputs
        System.out.print("Enter Manager ID: ");
        int id = scanner.nextInt();
        
        scanner.nextLine(); 
        
        System.out.print("Enter Manager Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Base Salary: ");
        double salary = scanner.nextDouble();
        
        scanner.nextLine(); 
        
        System.out.print("Enter Department: ");
        String department = scanner.nextLine();
        
        System.out.print("Enter Bonus: ");
        double bonus = scanner.nextDouble();

        
        Manager mgr = new Manager(id, name, salary, department, bonus);
        
        System.out.println("\n--- Manager Details ---");
        mgr.displayDetails();
        
        scanner.close(); 
    }
}