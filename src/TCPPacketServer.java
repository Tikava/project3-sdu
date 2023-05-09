import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPPacketServer {


    public static void main(String[] args) {
        System.out.println("Opening port...");
        try (
                ServerSocket serverSocket = new ServerSocket(1024);
                Socket socket = serverSocket.accept();
                ObjectInputStream fromClient = new ObjectInputStream(socket.getInputStream());
         ) {
            Packet packet = (Packet)fromClient.readObject();
            String line = packet.getData();
            while (!line.equals("CLOSE")) {
                System.out.println("Recieveing From Clients Packet's serialNo#"
                        + packet.getSerialNo()
                        + " and packet's Data is "
                        + packet.getData());
                packet = (Packet)fromClient.readObject();
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
