/*
-------------------------------------------------------------------------------------------------------------------------------
|   NAME : KHUSHI MANOJKUMAR DUGAR
|   ROLL NUMBER : 18 
|   COURSE : MCA 2
|   SUBJECT : ADVANCED NETWORKING 
|   ASSIGNMENT : Practical 1
-------------------------------------------------------------------------------------------------------------------------------
Question : Generate a TCP program in java to sort an integer array using merge sort for client and server side both without threads

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

                // Sort the array using merge sort
                mergeSort(array, 0, size - 1);

                // Send the sorted array back to the client
                writer.println(arrayToString(array));

                // Close the client connection
                clientSocket.close();
                System.out.println("Client disconnected: " + clientSocket.getInetAddress().getHostAddress());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);

            merge(array, left, mid, right);
        }
    }

    private static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; ++i) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            rightArray[j] = array[mid + 1 + j];
        }

        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    private static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            if (i != array.length - 1) {
                sb.append(",");
            }
        }

        return sb.toString();
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