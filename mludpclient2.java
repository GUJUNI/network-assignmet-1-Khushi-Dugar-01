/*
-------------------------------------------------------------------------------------------------------------------------------
|   NAME : KHUSHI MANOJKUMAR DUGAR
|   ROLL NUMBER : 18 
|   COURSE : MCA 2
|   SUBJECT : ADVANCED NETWORKING 
|   ASSIGNMENT : Practical 1
-------------------------------------------------------------------------------------------------------------------------------
Question : Write a program that return ASCII number of all character in the string it inputs in the TCP client-server program and using threads

================
Client
================
*/
import java.io.*;
import java.net.*;

class UDPClient {
    private static final int SERVER_PORT = 12345;
    private static final String SERVER_HOST = "localhost";

    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName(SERVER_HOST);

            // Send a request to the server
            byte[] sendData = "PASSWORD_REQUEST".getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
            clientSocket.send(sendPacket);

            // Receive the password from the server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            // Display the received password
            String password = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Generated Password: " + password);

            // Close the socket
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
****************************************************************************************************************************************
Output:-

Generated Password: 1H8J2M9f

*/