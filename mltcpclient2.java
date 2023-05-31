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

class TCPClient {
    private static final int SERVER_PORT = 12345;
    private static final String SERVER_HOST = "localhost";

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            System.out.println("Connected to server: " + SERVER_HOST + ":" + SERVER_PORT);

            // Get the input and output streams
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            // Read the input string from the user
            BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the string: ");
            String inputString = userInputReader.readLine();

            // Send the input string to the server
            writer.println(inputString);

            // Receive the ASCII numbers from the server
            String line;
            while ((line = reader.readLine()) != null) {
                int ascii = Integer.parseInt(line);
                System.out.println("ASCII: " + ascii);
            }

            // Close the socket
            socket.close();
            System.out.println("Disconnected from server");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
****************************************************************************************************************************************
Output:-

Connected to server: localhost:12345
Enter the string: khushi dugar
ASCII: 107
ASCII: 104
ASCII: 117
ASCII: 115
ASCII: 104
ASCII: 105
ASCII: 32
ASCII: 100
ASCII: 117
ASCII: 103
ASCII: 97
ASCII: 114
Disconnected from server

*/