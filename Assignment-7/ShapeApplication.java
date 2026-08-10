import java.util.Scanner;

class Shape {
    public double calculateArea() {
        return 0.0; 
    }
}

// Circle class inheriting from Shape
class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    // Overriding the method 
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

// Rectangle class inheriting from Shape
class Rectangle extends Shape {
    private double length;
    private double width;

    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    // Overriding the method 
    public double calculateArea() {
        return length * width;
    }
}

public class ShapeApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Shape Area Calculator ---");

        // Input for Circle
        System.out.print("Enter the radius of the circle: ");
        double radius = scanner.nextDouble();
        Shape circle = new Circle(radius);

        // Input for Rectangle
        System.out.print("Enter the length of the rectangle: ");
        double length = scanner.nextDouble();
        System.out.print("Enter the width of the rectangle: ");
        double width = scanner.nextDouble();
        Shape rectangle = new Rectangle(length, width);

    
        Shape genericShape = new Shape();

        // Outputs
        System.out.println("\n--- Results ---");
        System.out.printf("Area of Circle (radius %.2f): %.2f\n", radius, circle.calculateArea());
        System.out.printf("Area of Rectangle (%.2f x %.2f): %.2f\n", length, width, rectangle.calculateArea());
        System.out.printf("Area of Generic Shape: %.2f\n", genericShape.calculateArea());
        scanner.close();
    }
}