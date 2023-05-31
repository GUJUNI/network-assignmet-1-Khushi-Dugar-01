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

                // Get the input and output streams
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);

                // Read the array size from the client
                int size = Integer.parseInt(reader.readLine());

                // Read the array elements from the client
                int[] array = new int[size];
                String[] elements = reader.readLine().split(",");
                for (int i = 0; i < size; i++) {
                    array[i] = Integer.parseInt(elements[i]);
                }

                // Read the number to search from the client
                int number = Integer.parseInt(reader.readLine());

                // Search the array using binary search
                boolean found = binarySearch(array, number);

                // Send the search result back to the client
                writer.println(found);

                // Close the client connection
                clientSocket.close();
                System.out.println("Client disconnected: " + clientSocket.getInetAddress().getHostAddress());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean binarySearch(int[] array, int number) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (array[mid] == number) {
                return true;
            }

            if (array[mid] < number) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return false;
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
Client disconnected: 127.0.0.1

*/