import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPPacketClient {

    private static ObjectOutputStream toServer = null;
    private static Scanner fromUser = null;
    private static Packet packet = null;

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 1024);
            toServer = new ObjectOutputStream(socket.getOutputStream());
            fromUser = new Scanner(System.in);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            System.out.print("Enter the data packet: ");
            String line = fromUser.nextLine().toUpperCase();
            int serialNo = 1;

            while (!line.equals("CLOSE")) {
                packet = new Packet(serialNo, line);

                toServer.writeObject(packet);
                toServer.flush();

                System.out.println("FROM SERVER:Packet SerialNo#" + serialNo + " is recieved");

                System.out.print("Enter the data packet: ");
                line = fromUser.next().toUpperCase();
                serialNo++;
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
