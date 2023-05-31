/*
-------------------------------------------------------------------------------------------------------------------------------
|   NAME : KHUSHI MANOJKUMAR DUGAR
|   ROLL NUMBER : 18 
|   COURSE : MCA 2
|   SUBJECT : ADVANCED NETWORKING 
|   ASSIGNMENT : Practical 1
-------------------------------------------------------------------------------------------------------------------------------
Question : Generate a timer in UDP client-server program and using threads

================
Client
================
*/
import java.io.*;
import java.net.*;

class UDPClient {
    private static final int SERVER_PORT = 12345;
    private static final String SERVER_HOST = "localhost";
    private static final int TIMEOUT = 5000; // Timeout in milliseconds

    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName(SERVER_HOST);

            // Create a thread to handle the timer
            Thread timerThread = new Thread(() -> {
                try {
                    Thread.sleep(TIMEOUT);
                    clientSocket.close();
                    System.out.println("Timeout: Connection closed");
                } catch (InterruptedException e) {
                    // Timer thread interrupted
                }
            });

            // Start the timer thread
            timerThread.start();

            // Send the message to the server
            String message = "Hello, server!";
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
            clientSocket.send(sendPacket);

            // Receive the response from the server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            // Cancel the timer thread as the response is received
            timerThread.interrupt();

            // Display the response from the server
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Response from server: " + response);

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

Response from server: Hello, server!

*/