/*
-------------------------------------------------------------------------------------------------------------------------------
|   NAME : KHUSHI MANOJKUMAR DUGAR
|   ROLL NUMBER : 18 
|   COURSE : MCA 2
|   SUBJECT : ADVANCED NETWORKING 
|   ASSIGNMENT : Practical 1
-------------------------------------------------------------------------------------------------------------------------------
Question : Program to manage linked list using TCP protocol in Java

================
Server
================
*/
import java.io.*;
import java.net.*;
import java.util.*;

class TCPServer {

    private LinkedList<String> data = new LinkedList<>();

    public void start(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server started on port " + port);

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected from " + clientSocket.getInetAddress().getHostAddress());

            new Thread(() -> {
                try {
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String message;
                    while ((message = in.readLine()) != null) {
                        synchronized (data) {
                            data.add(message);
                            System.out.println("Received message: " + message);
                        }
                    }
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public static void main(String[] args) throws IOException {
        TCPServer server = new TCPServer();
        server.start(8000);
    }
}

/*
****************************************************************************************************************************************
Output:-

================
Server
================
Server started on port 8000
Client connected from 127.0.0.1
Received message: Hello, server!

*/