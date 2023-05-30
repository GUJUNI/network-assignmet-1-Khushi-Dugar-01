/*
-------------------------------------------------------------------------------------------------------------------------------
|   NAME : KHUSHI MANOJKUMAR DUGAR
|   ROLL NUMBER : 18
|   COURSE : MCA 2
|   SUBJECT : ADVANCED NETWORKING
|   ASSIGNMENT : Practical 1
-------------------------------------------------------------------------------------------------------------------------------
QUESTION : Write a UDP program in which the client ask server that how many devices is connected to that server and then server
give the response on that

================
Server
================
*/
import java.io.IOException;
import java.net.*;

class UDPServer {
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            socket = new DatagramSocket(SERVER_PORT);
            System.out.println("UDP Server started on port " + SERVER_PORT);

            while (true) {
                byte[] requestData = new byte[1024];
                DatagramPacket requestPacket = new DatagramPacket(requestData, requestData.length);
                socket.receive(requestPacket);

                String request = new String(requestPacket.getData(), 0, requestPacket.getLength());
                System.out.println("Received request from client: " + request);

                if (request.equals("GetConnectedDevicesCount")) {
                    // Simulating the count of connected devices
                    int count = (int) (Math.random() * 10);

                    String response = Integer.toString(count);
                    byte[] responseData = response.getBytes();

                    InetAddress clientAddress = requestPacket.getAddress();
                    int clientPort = requestPacket.getPort();
                    DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);
                    socket.send(responsePacket);
                }
            }

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

================
Server
================
UDP Server started on port 12345
Received request from client: GetConnectedDevicesCount

*/