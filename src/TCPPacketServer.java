import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class TCPPacketServer {

    private static ObjectInputStream inputFromClient = null;
    private static ServerSocket serverSocket = null;
    private static Socket socket = null;
    private static Packet packet = null;

    public static void main(String[] args) {
        try {
            System.out.println("Opening port...\n");
            serverSocket = new ServerSocket(1024);
            socket = serverSocket.accept();

            inputFromClient = new ObjectInputStream(socket.getInputStream());
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }


        try {
            packet = (Packet)inputFromClient.readObject();
            String line = packet.getData();
            while (!line.equals("CLOSE")) {
                System.out.println("Recieveing From Clients Packet's serialNo#" + packet.getSerialNo() +
                        " and packet's Data is " + packet.getData());
                packet = (Packet)inputFromClient.readObject();
                line = packet.getData();
            }
        }
        catch (SocketException ex) {
            System.exit(1);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

    }
}
