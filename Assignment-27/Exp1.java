package Exp27.Exercise;
import java.io.*;
import java.net.*;

public class Exp1 {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(5000);
            System.out.println("Waiting for client...");
            Socket socket = server.accept();
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            );
            PrintWriter out = new PrintWriter(
                socket.getOutputStream(), true
            );
            System.out.println("Client: " + in.readLine());
            out.println("Hello Client, message received.");
            socket.close();
            server.close();
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}