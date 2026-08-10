import java.util.Scanner;

interface Printable {   //Declaring an interface
    void print();
}

class StudentInfo implements Printable {
    private String name;
    private int rollNo;

    public StudentInfo(String name, int rollNo) {    //Constructor 
        this.name = name;
        this.rollNo = rollNo;
    }

    public void print() {       //Method to print details
        System.out.println("Student Details:- Name: " + name + " | Roll No: " + rollNo);
    }
}

class EmployeeInfo implements Printable {     
    private String name;
    private int empId;

    public EmployeeInfo(String name, int empId) {
        this.name = name;
        this.empId = empId;
    }

    public void print() {
        System.out.println("Employee Details:- Name: " + name + " | Employee ID: " + empId);
    }
}

public class PrintableDemo {       //Main class
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-Enter Student Information-");
        System.out.print("Enter Student Name: ");
        String studentName = scanner.nextLine();
        System.out.print("Enter Student Roll Number: ");
        int rollNum = scanner.nextInt();

        scanner.nextLine();

        System.out.println("\n-Enter Employee Information-");
        System.out.print("Enter Employee Name: ");
        String employeeName = scanner.nextLine();
        System.out.print("Enter Employee ID: ");
        int empId = scanner.nextInt();

        Printable student = new StudentInfo(studentName, rollNum);
        Printable employee = new EmployeeInfo(employeeName, empId);

        System.out.println("\n-Printing Details-");
        student.print();
        employee.print();

        scanner.close();
    }
}