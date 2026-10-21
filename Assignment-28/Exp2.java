package Exp28.Exercise;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

interface Temperature extends Remote {
    double celsiusToFahrenheit(double celsius)
            throws RemoteException;

    double fahrenheitToCelsius(double fahrenheit)
            throws RemoteException;
}

class TemperatureImpl extends UnicastRemoteObject
        implements Temperature {

    TemperatureImpl() throws RemoteException {
        super();
    }

    public double celsiusToFahrenheit(double celsius)
            throws RemoteException {
        return (celsius * 9 / 5) + 32;
    }

    public double fahrenheitToCelsius(double fahrenheit)
            throws RemoteException {
        return (fahrenheit - 32) * 5 / 9;
    }
}

public class Exp2 {
    public static void main(String[] args) {
        try {
            // Start RMI Registry
            LocateRegistry.createRegistry(1099);

            // Create remote object
            TemperatureImpl obj = new TemperatureImpl();

            // Register remote object
            Naming.rebind("TemperatureService", obj);

            System.out.println("Temperature RMI Server is running...");

            double celsius = 25;

            double fahrenheit =
                    obj.celsiusToFahrenheit(celsius);

            System.out.println(
                celsius + " Celsius = "
                + fahrenheit + " Fahrenheit"
            );

            System.out.println(
                fahrenheit + " Fahrenheit = "
                + obj.fahrenheitToCelsius(fahrenheit)
                + " Celsius"
            );

            System.exit(0);
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}