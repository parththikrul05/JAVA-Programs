import java.util.Scanner;

public class Mark {
    public static void main(String[] args){
       Scanner sc = new Scanner(System.in);  //Scanner class called for input
       
       System.out.print("Enter Marks for subject 1(out of 100):");
       String a = sc.next();
       int m1 = Integer.parseInt(a);
       
       System.out.print("Enter Marks for subject 2(out of 100):");
       String b = sc.next();
       int m2 = Integer.parseInt(b);  
       
       System.out.print("Enter Marks for subject 3(out of 100):");
       String c = sc.next();
       int m3 = Integer.parseInt(c);  
       
       //condition: Check if any marks are less than 0 or greater than 100
       if (m1 < 0 || m1 > 100 || m2 < 0 || m2 > 100 || m3 < 0 || m3 > 100) {
           System.out.println("Error: Invalid marks entered! Marks must be between 0 and 100.");
       } else {
           // Calculate total only if marks are valid
           int total = m1 + m2 + m3;
           double percentage = (total / 300.0) * 100;
           
           System.out.println("Total Marks of student(out of 300):" + total + "/300");
           System.out.println("Percentage: " + percentage + "%");
       }
       
       sc.close();
    }
}