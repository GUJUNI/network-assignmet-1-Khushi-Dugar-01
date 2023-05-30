/*
-------------------------------------------------------------------------------------------------------------------------------
|   NAME : KHUSHI MANOJKUMAR DUGAR
|   ROLL NUMBER : 18 
|   COURSE : MCA 2
|   SUBJECT : ADVANCED NETWORKING 
|   ASSIGNMENT : Practical 1
-------------------------------------------------------------------------------------------------------------------------------
Question : Generate a TCP program in java to search an integer array using binary search for client and server side both without threads

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

            // Read the array size from the user
            BufferedReader userInputReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the size of the array: ");
            int size = Integer.parseInt(userInputReader.readLine());
            writer.println(size);

            // Read the array elements from the user
            System.out.print("Enter the array elements (comma-separated): ");
            String elements = userInputReader.readLine();
            writer.println(elements);

            // Read the number to search from the user
            System.out.print("Enter the number to search: ");
            int number = Integer.parseInt(userInputReader.readLine());
            writer.println(number);

            // Receive the search result from the server
            boolean found = Boolean.parseBoolean(reader.readLine());
            if (found) {
                System.out.println("Number " + number + " found in the array.");
            } else {
                System.out.println("Number " + number + " not found in the array.");
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

================
Client
================

Connected to server: localhost:12345
Enter the size of the array: 5
Enter the array elements (comma-separated): 1,2,3,4,5
Enter the number to search: 7
Number 7 not found in the array.
Disconnected from server

*/