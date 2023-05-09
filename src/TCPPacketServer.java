import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPPacketServer {


    public static void main(String[] args) {
        System.out.println("Opening port...");
        try (
                ServerSocket serverSocket = new ServerSocket(8000);
                Socket socket = serverSocket.accept();
                ObjectInputStream inputFromClient = new ObjectInputStream(socket.getInputStream());
         ) {
            Packet packet = (Packet)inputFromClient.readObject();
            String line = packet.getData();
            while (!line.equals("CLOSE")) {
                System.out.println("Recieveing From Clients Packet's serialNo#"
                        + packet.getSerialNo()
                        + " and packet's Data is "
                        + packet.getData());
                packet = (Packet)inputFromClient.readObject();
                line = packet.getData();
            }
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
