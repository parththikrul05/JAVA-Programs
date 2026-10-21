package Exp28.Exercise;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

interface Calculator extends Remote {
    int add(int a, int b) throws RemoteException;
    int subtract(int a, int b) throws RemoteException;
    int multiply(int a, int b) throws RemoteException;
    int divide(int a, int b) throws RemoteException;
}

class CalculatorImpl extends UnicastRemoteObject implements Calculator {

    CalculatorImpl() throws RemoteException {
        super();
    }

    public int add(int a, int b) throws RemoteException {
        return a + b;
    }

    public int subtract(int a, int b) throws RemoteException {
        return a - b;
    }

    public int multiply(int a, int b) throws RemoteException {
        return a * b;
    }

    public int divide(int a, int b) throws RemoteException {
        return a / b;
    }
}

public class Exp1 {
    public static void main(String[] args) {
        try {
            // Start RMI Registry
            LocateRegistry.createRegistry(1099);

            // Create remote object
            CalculatorImpl obj = new CalculatorImpl();

            // Register remote object
            Naming.rebind("CalculatorService", obj);

            System.out.println("Calculator RMI Server is running...");

            System.out.println("Addition: " + obj.add(20, 10));
            System.out.println("Subtraction: " + obj.subtract(20, 10));
            System.out.println("Multiplication: " + obj.multiply(20, 10));
            System.out.println("Division: " + obj.divide(20, 10));

            System.exit(0);
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}