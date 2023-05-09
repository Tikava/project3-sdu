import java.net.*;
import java.io.*;

public class Clients {
    public static void main(String[] args) {

        String hostName = "localhost";
        int portNumber = 8000;

        try (
                Socket socket = new Socket(hostName, portNumber);
                PrintStream toServer = new PrintStream(socket.getOutputStream(), true);
                BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("Request sent successfully");
            String userInput;
            String response;
            while (true) {
                if ((response = fromServer.readLine()) != null) {
                    System.out.println("Server: " + response);
                }
                System.out.print("Client: ");
                if ((userInput = stdIn.readLine()) != null) {
                    toServer.println(userInput);
                }
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}
