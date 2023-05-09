import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class TCPPacketClient {

    public static void main(String[] args) {

        try (
            Socket socket = new Socket("localhost", 8000);
            ObjectOutputStream toServer = new ObjectOutputStream(socket.getOutputStream());
            Scanner fromUser = new Scanner(System.in);
        ) {
            System.out.print("Enter the data packet: ");
            String line = fromUser.nextLine().toUpperCase();
            int serialNo = 1;

            while (!line.equals("CLOSE")) {
                Packet packet = new Packet(serialNo, line);

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
