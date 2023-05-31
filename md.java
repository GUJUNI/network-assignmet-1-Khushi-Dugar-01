/*
-------------------------------------------------------------------------------------------------------------------------------
|   NAME : KHUSHI MANOJKUMAR DUGAR
|   ROLL NUMBER : 18 
|   COURSE : MCA 2
|   SUBJECT : ADVANCED NETWORKING 
|   ASSIGNMENT : Practical 1
-------------------------------------------------------------------------------------------------------------------------------
Question : Write a program to compute a message digest for a file of any type and any size.
*/

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class md {

    public static void main(String[] args) {
        // Specify the path to the file
        String filePath = "Hello.txt";

        try {
            byte[] digest = computeFileDigest(filePath);
            String hexDigest = convertToHexString(digest);

            System.out.println("File Digest (SHA-256): " + hexDigest);
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }

    private static byte[] computeFileDigest(String filePath) throws NoSuchAlgorithmException, IOException {
        // Create a MessageDigest instance with SHA-256 algorithm
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        // Read the file and update the digest
        try (InputStream inputStream = new FileInputStream(filePath)) {
            byte[] buffer = new byte[8192];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                md.update(buffer, 0, bytesRead);
            }
        }

        // Compute the final digest
        return md.digest();
    }

    private static String convertToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}

/*
****************************************************************************************************************************************
Output:-

File Digest (SHA-256): d0e340db5a8f6aad1a6d4dec48335799b96ec09348e0b14654ac2c2c578ff03d

*/