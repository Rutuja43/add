import java.util.Scanner;

public class TransportationCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the plaintext message: ");
        String message = scanner.nextLine();

        System.out.print("Enter the number of columns (encryption key length): ");
        int keyLength = scanner.nextInt();
        scanner.nextLine(); // Consume newline left over

        // Encryption
        String encryptedMessage = encrypt(message, keyLength);
        System.out.println("Encrypted message: " + encryptedMessage);

        // Decryption
        String decryptedMessage = decrypt(encryptedMessage, keyLength);
        System.out.println("Decrypted message: " + decryptedMessage);

        scanner.close();
    }

    public static String encrypt(String message, int keyLength) {
        // Calculate number of rows needed
        int rows = (int) Math.ceil((double) message.length() / keyLength);

        // Create a 2D array to hold the message in rows and columns
        char[][] matrix = new char[rows][keyLength];

        // Fill the matrix with the message characters
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < keyLength; j++) {
                if (index < message.length()) {
                    matrix[i][j] = message.charAt(index);
                    index++;
                } else {
                    matrix[i][j] = ' '; // Padding with spaces if message length is less than rows * columns
                }
            }
        }

        // Build the encrypted message by reading columns row-wise
        StringBuilder encryptedMessage = new StringBuilder();
        for (int col = 0; col < keyLength; col++) {
            for (int row = 0; row < rows; row++) {
                encryptedMessage.append(matrix[row][col]);
            }
        }

        return encryptedMessage.toString();
    }

    public static String decrypt(String encryptedMessage, int keyLength) {
        // Calculate number of rows needed
        int rows = encryptedMessage.length() / keyLength;

        // Create a 2D array to hold the encrypted message in rows and columns
        char[][] matrix = new char[rows][keyLength];

        // Fill the matrix with the encrypted message characters column-wise
        int index = 0;
        for (int col = 0; col < keyLength; col++) {
            for (int row = 0; row < rows; row++) {
                matrix[row][col] = encryptedMessage.charAt(index);
                index++;
            }
        }

        // Build the decrypted message by reading rows column-wise
        StringBuilder decryptedMessage = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < keyLength; j++) {
                decryptedMessage.append(matrix[i][j]);
            }
        }

        // Trim any trailing spaces that were added during encryption
        return decryptedMessage.toString().trim();
    }
}
