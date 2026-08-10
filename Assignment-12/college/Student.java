package college;

public class Student {
    private int studentId;
    private String name;
    private String course;

    // Constructor
    public Student(int studentId, String name, String course) {
        this.studentId = studentId;
        this.name = name;
        this.course = course;
    }

    // Method to display student details
    public void displayStudent() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Student Name: " + name);
        System.out.println("Course: " + course);
        System.out.println("------------------------------");
    }
}