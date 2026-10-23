import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Hash01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entra text: ");
        String textIn = scanner.nextLine();

        try {
            // Convertim el text a un array de bytes utilitzant UTF-8
            byte[] bytesIn = textIn.getBytes(StandardCharsets.UTF_8);

            // Creem l'objecte per calcular el hash SHA-512
            MessageDigest sha512 = MessageDigest.getInstance("SHA-512");

            // Calculem el hash
            byte[] hashResult = sha512.digest(bytesIn);

            // Convertim el resultat a hexadecimal i el mostrem per pantalla
            StringBuilder textOut = new StringBuilder();
            for (int i = 0; i < hashResult.length; i++) {
                if (i > 0) {
                    textOut.append("-");
                }
                textOut.append(String.format("%02X", hashResult[i] & 0xFF));
            }

            System.out.println("Hash " + textOut);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("No s'ha trobat l'algoritme SHA-512: " + e.getMessage());
        }

        scanner.close();
    }
}
