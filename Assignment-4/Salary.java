import java.util.Scanner;

public class Salary {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter total number of employees: ");
        int n = sc.nextInt();
        sc.nextLine();
        
        System.out.println("\n---Enter Details for " + n + " Employees---");
        
        // Loop to take input for 'n' employees
        for (int i = 1; i <= n; i++) {
            System.out.println("\nEmployee " + i + ":");
            
            System.out.print("Enter Employee ID: ");
            String empID = sc.nextLine();
            
            System.out.print("Enter Base Salary: ");
            String baseInput = sc.nextLine();
            // Converting String to Float Wrapper Object
            Float base_salary = Float.valueOf(baseInput); 
            
            System.out.print("Enter Bonus Amount: ");
            String bonusInput = sc.nextLine();
            // Converting String to Float Wrapper Object
            Float bonus_amt = Float.valueOf(bonusInput);

            if (base_salary < 0 || bonus_amt < 0) {
                System.out.println("Error: Base Salary and Bonus Amount cannot be negative. Skipping this employee.");
            } else {
                // Unboxing wrapper objects for calculation
                float total = base_salary + bonus_amt;
                
                System.out.println("\n--- Payroll Details for " + empID + " ---");
                System.out.println("Employee ID: " + empID);
                System.out.println("Base Salary: " + base_salary);
                System.out.println("Bonus Amount: " + bonus_amt);
                System.out.println("Amount received by Employee: " + total);
            }
        }
        
        sc.close();
    }
}