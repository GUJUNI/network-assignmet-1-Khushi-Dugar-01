/*
-------------------------------------------------------------------------------------------------------------------------------
|   NAME : KHUSHI MANOJKUMAR DUGAR
|   ROLL NUMBER : 18 
|   COURSE : MCA 2
|   SUBJECT : ADVANCED NETWORKING 
|   ASSIGNMENT : Practical 1
-------------------------------------------------------------------------------------------------------------------------------
QUESTION : You will Write a Program That Performs Encryption/Decryption.
*/

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

class EncryptionDecryption {
    private static final String AES_ALGORITHM = "AES";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the secret key: ");
        String secretKey = scanner.nextLine();

        System.out.print("Enter the plain text: ");
        String plainText = scanner.nextLine();

        // Encrypt the plain text using the secret key
        String encryptedText = encrypt(plainText, secretKey);
        System.out.println("Encrypted text: " + encryptedText);

        // Decrypt the encrypted text using the secret key
        String decryptedText = decrypt(encryptedText, secretKey);
        System.out.println("Decrypted text: " + decryptedText);

        scanner.close();
    }

    private static String encrypt(String plainText, String secretKey) {
        try {
            SecretKey key = generateKey(secretKey);

            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, key);

            byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
            return Base64.getEncoder().encodeToString(encryptedBytes);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static String decrypt(String encryptedText, String secretKey) {
        try {
            SecretKey key = generateKey(secretKey);

            Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);

            byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);

        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static SecretKey generateKey(String secretKey) throws InvalidKeyException {
        return new SecretKeySpec(secretKey.getBytes(), AES_ALGORITHM);
    }
}

/*
****************************************************************************************************************************************
Output:-

Enter the secret key: mySecretKey
Enter the plain text: Hello, world!
Encrypted text: hG7I05GTMoakvsiG8rhdXw==
Decrypted text: Hello, world!

*/