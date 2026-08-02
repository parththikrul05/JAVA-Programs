import java.util.Scanner;
import java.util.ArrayList;

public class ToDoList {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<String> str = new ArrayList<String>();
        System.out.print("Enter Number of Tasks to complete: ");
        int n = sc.nextInt();
        sc.nextLine();
        
        for(int i = 0; i < n; i++){
            System.out.print("Enter Task " + (i + 1) + " to complete: ");
            String s = sc.nextLine();
            str.add(s);
        }
        StringBuffer toDoList = new StringBuffer();
        toDoList.append("\n--- Your To-Do List ---\n");
        for(int i = 0; i < str.size(); ++i){
            toDoList.append("Task ").append(i + 1).append(": ").append(str.get(i)).append("\n");
        }
        System.out.println(toDoList);
        
        sc.close();
    }
}