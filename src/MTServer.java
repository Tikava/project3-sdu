import java.net.*;
import java.io.*;

public class MTServer {
    public static void main(String[] args) {

        int portNumber = 1024;
        System.out.println("Waiting for client request");
        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter outToClient = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("New client is pop up!");

            String inputLine;
            String outputLine;
            while (true) {
                System.out.print("Server: ");
                if ((outputLine = stdIn.readLine()) != null) {
                    outToClient.println(outputLine);
                }

                System.out.print("Clients: ");
                if ((inputLine = inFromClient.readLine()) != null) {
                    System.out.println(inputLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
