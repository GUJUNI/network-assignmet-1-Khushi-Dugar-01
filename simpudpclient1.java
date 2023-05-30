/*
-------------------------------------------------------------------------------------------------------------------------------
|   NAME : KHUSHI MANOJKUMAR DUGAR
|   ROLL NUMBER : 18 
|   COURSE : MCA 2
|   SUBJECT : ADVANCED NETWORKING 
|   ASSIGNMENT : Practical 1
-------------------------------------------------------------------------------------------------------------------------------
Question : UDP socket program in Java where client sends number of days, and server converts it to years, months and days

================
Client
================
*/
import java.net.*;
import java.io.*;

class Days_C {
    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket();

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the number of days: ");
            String daysStr = reader.readLine();
            byte[] sendData = daysStr.getBytes();

            InetAddress serverAddress = InetAddress.getByName("localhost");
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 5000);
            clientSocket.send(sendPacket);

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            String response = new String(receivePacket.getData()).trim();
            System.out.println("Server response: " + response);

            clientSocket.close();
        } catch (UnknownHostException e) {
            System.out.println("Error: unknown host");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

/*
****************************************************************************************************************************************
Output:-

Enter the number of days: 655
Server response: 1 years, 9 months, and 20 days

*/