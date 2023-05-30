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
Client
================
*/
import java.io.*;
import java.net.*;

class TCPClient {

    public void send(String host, int port, String message) throws IOException {
        Socket socket = new Socket(host, port);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(message);
        System.out.println("Sent message: " + message);
        socket.close();
    }

    public static void main(String[] args) throws IOException {
        TCPClient client = new TCPClient();
        client.send("localhost", 8000, "Hello, server!");
    }
}

/*
****************************************************************************************************************************************
Output:-

================
Client
================

Sent message: Hello, server!

*/