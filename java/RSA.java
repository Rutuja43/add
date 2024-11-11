import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Scanner;

public class RSA {
    private BigInteger n, d, e;
    private int bitlen = 1024;

    // Constructor to generate RSA keys
    public RSA(int bits) {
        bitlen = bits;
        SecureRandom r = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(bitlen / 2, r);
        BigInteger q = BigInteger.probablePrime(bitlen / 2, r);
        n = p.multiply(q);
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));

        e = BigInteger.probablePrime(bitlen / 2, r);
        while (phi.gcd(e).intValue() > 1) {
            e = e.add(BigInteger.ONE);
        }

        d = e.modInverse(phi);
    }

    // Encrypt message (in bytes) as BigInteger
    public BigInteger encrypt(BigInteger message) {
        return message.modPow(e, n);
    }

    // Decrypt message (in bytes) as BigInteger
    public BigInteger decrypt(BigInteger encrypted) {
        return encrypted.modPow(d, n);
    }

    // Convert String to BigInteger
    public BigInteger stringToBigInteger(String message) {
        return new BigInteger(message.getBytes(StandardCharsets.UTF_8));
    }

    // Convert BigInteger back to String
    public String bigIntegerToString(BigInteger message) {
        return new String(message.toByteArray(), StandardCharsets.UTF_8);
    }

    public static void main(String[] args) {
        RSA rsa = new RSA(1024);
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the message to encrypt:");
        String inputMessage = sc.nextLine();
        System.out.println("\n");

        // Convert String to BigInteger
        BigInteger plaintext = rsa.stringToBigInteger(inputMessage);

        // Encrypt the message
        BigInteger ciphertext = rsa.encrypt(plaintext);
        System.out.println("Encrypted message:" + ciphertext);
        System.out.println("\n");

        // Decrypt the message
        BigInteger decryptedMessage = rsa.decrypt(ciphertext);
        String decryptedText = rsa.bigIntegerToString(decryptedMessage);
        System.out.println("Decrypted message: " + decryptedText);

        sc.close();
    }
}
