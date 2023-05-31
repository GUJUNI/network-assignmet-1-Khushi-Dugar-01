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

class TCPServer {
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("TCP Server started on port " + SERVER_PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("New client connected: " + clientSocket.getInetAddress().getHostAddress());

                // Create a new thread to handle client requests
                Thread clientThread = new Thread(() -> {
                    try {
                        // Get the input and output streams
                        BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                        // Read the input string from the client
                        String inputString = reader.readLine();
                        System.out.println("Received string from client: " + inputString);

                        // Convert each character to ASCII number and send it back to the client
                        for (char c : inputString.toCharArray()) {
                            int ascii = (int) c;
                            writer.println(ascii);
                        }

                        // Close the client socket
                        clientSocket.close();
                        System.out.println("Client disconnected: " + clientSocket.getInetAddress().getHostAddress());

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                // Start the client thread
                clientThread.start();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
****************************************************************************************************************************************
Output:-

================
Server
================
TCP Server started on port 12345
New client connected: 127.0.0.1
Received string from client: khushi dugar
Client disconnected: 127.0.0.1

*/