/*
-------------------------------------------------------------------------------------------------------------------------------
|   NAME : KHUSHI MANOJKUMAR DUGAR
|   ROLL NUMBER : 18 
|   COURSE : MCA 2
|   SUBJECT : ADVANCED NETWORKING 
|   ASSIGNMENT : Practical 1
-------------------------------------------------------------------------------------------------------------------------------
Question : Write a UDP program in which the client ask server that how many devices is connected to that server and then server
give the response on that


================
Client
================
*/
import java.io.IOException;
import java.net.*;

class UDPClient {
    private static final int SERVER_PORT = 12345;
    private static final String SERVER_HOST = "localhost";

    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            InetAddress serverAddress = InetAddress.getByName(SERVER_HOST);
            socket = new DatagramSocket();
            byte[] requestData = "GetConnectedDevicesCount".getBytes();

            DatagramPacket requestPacket = new DatagramPacket(requestData, requestData.length, serverAddress, SERVER_PORT);
            socket.send(requestPacket);

            byte[] responseData = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length);
            socket.receive(responsePacket);

            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println("Number of connected devices: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}

/*
****************************************************************************************************************************************
Output:-

Number of connected devices: 9

*/