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
Server
================
*/
import java.io.*;
import java.net.*;
import java.util.Random;

class UDPServer {
    private static final int SERVER_PORT = 12345;
    private static final int PASSWORD_LENGTH = 8;

    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(SERVER_PORT);
            System.out.println("UDP Server started on port " + SERVER_PORT);

            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String request = new String(receivePacket.getData(), 0, receivePacket.getLength());
                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                if (request.equals("PASSWORD_REQUEST")) {
                    // Create a thread to handle the password generation and response
                    Thread passwordThread = new Thread(() -> {
                        String password = generateRandomPassword();
                        byte[] sendData = password.getBytes();
                        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
                        try {
                            serverSocket.send(sendPacket);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                    // Start the password thread
                    passwordThread.start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String generateRandomPassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            int index = random.nextInt(characters.length());
            password.append(characters.charAt(index));
        }

        return password.toString();
    }
}

/*
****************************************************************************************************************************************
Output:-

================
Server
================
UDP Server started on port 12345

*/