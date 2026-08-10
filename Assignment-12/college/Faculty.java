package college;

public class Faculty {
    private int facultyId;
    private String name;
    private String department;

    // Constructor
    public Faculty(int facultyId, String name, String department) {
        this.facultyId = facultyId;
        this.name = name;
        this.department = department;
    }

    // Method to display faculty details
    public void displayFaculty() {
        System.out.println("Faculty ID: " + facultyId);
        System.out.println("Faculty Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("------------------------------");
    }
};
