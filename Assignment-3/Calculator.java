public class Calculator {
    static int cal_counter = 0;  //counter
    
    //Addition for float 
    public float add(float a, float b){
        cal_counter++;     
        return a + b;
    } 

    //Addition for integers(Method Overloading)
    public int add(int a, int b){
        cal_counter++;
        return a + b;
    }
    
    //Subtraction for float
    public float subtract(float a, float b){
        cal_counter++;
        return a - b;
    }
    
    //Multiplication for float
    public float multiply(float a, float b){
        cal_counter++;
        if(a == 0 || b == 0){
            return 0;
        }
        else{
            return a * b;
        }
    }
    
    //Division for float
    public float division(float a, float b){
        cal_counter++;
        if(b == 0){
            System.out.println("Cannot divide by zero");
            // A primitive float cannot return null. We return Float.NaN (Not a Number) instead.
            return Float.NaN; 
        }
        else{
            return a / b;
        }
    }

    //Main method
    public static void main(String[] args) {
        Calculator myCalc = new Calculator();  //Object creation

        
        //Displaying operations
        System.out.println("Integer Addition (10 + 5): " + myCalc.add(10, 5));
        System.out.println("\nFloat Addition (5.5 + 2.2): " + myCalc.add(5.5f, 2.2f));
        System.out.println("\nSubtraction (10.5 - 3.2): " + myCalc.subtract(10.5f, 3.2f));
        System.out.println("\nMultiplication (4.0 * 2.5): " + myCalc.multiply(4.0f, 2.5f));
        System.out.println("\nDivision (10.0 / 2.0): " + myCalc.division(10.0f, 2.0f) + "\n");
        System.out.println("Division by zero (5.0 / 0.0): " + myCalc.division(5.0f, 0.0f));
        System.out.println("Total calculations performed: " + Calculator.cal_counter);
    }
}