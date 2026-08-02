public class Student {
    int roll_number;
    String student_name;

    void print(){
        System.out.println("Name:"+student_name);
        System.out.println("Roll number:"+roll_number);
    }
    //Function overloading - Same function name but different number of parameters
    Student(){  //default Constructor
        student_name = "Raghav";
        roll_number = 202;
    }
    Student(int p,String n){ //Parameterized Constructor
        student_name=n;
        roll_number=p;
    } 
    Student(Student s){  //Copy constructor
        roll_number= s.roll_number;
        student_name = s.student_name;
    }

    Student(String n){
        student_name= n;
    }
    public static void main(String[] args) {
        System.out.println("---Default Constructor---");
        Student s1 = new Student();      //object creation
        s1.print();
        System.out.println("---Parameterized Constructor---");
        Student s2 = new Student(204,"Parth");      //object creation
        s2.print();
        System.out.println("---Copy Constructor---");
        Student s3 = new Student(s1);      //object creation
        s3.print();        

    }
}
