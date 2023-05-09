import java.net.*;
import java.io.*;

public class MTServer {
    public static void main(String[] args) {

        int portNumber = 8000;
        System.out.println("Waiting for client request");
        try (
                ServerSocket serverSocket = new ServerSocket(portNumber);
                Socket clientSocket = serverSocket.accept();
                BufferedReader fromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintStream toClient = new PrintStream(clientSocket.getOutputStream(), true);
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("New client is pop up!");

            String inputLine;
            String outputLine;
            while (true) {
                System.out.print("Server: ");
                if ((outputLine = stdIn.readLine()) != null) {
                    toClient.println(outputLine);
                }

                System.out.print("Clients: ");
                if ((inputLine = fromClient.readLine()) != null) {
                    System.out.println(inputLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
