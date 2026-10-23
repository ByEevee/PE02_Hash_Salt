import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Fase1 {
    private static final Scanner scanner = App.scanner;

    public static void main(String[] args) {
        int opcio;

        do {
            System.out.println();
            System.out.println("===== PART 1: CÀLCUL DE RESUMS (SHA-256) =====");
            System.out.println("1. Calcular el hash d'un missatge");
            System.out.println("2. Comparar el hash de dos missatges");
            System.out.println("3. Modificar un missatge i comparar-ne el hash");
            System.out.println("4. Calcular diverses vegades el hash d'un mateix missatge");
            System.out.println("0. Tornar al menú principal");
            System.out.print("Opció: ");
            opcio = llegirEnter();

            switch (opcio) {
                case 1 : hashMissatge();
                    break;
                case 2 : compararMissatges();
                    break;
                case 3 : modificarMissatge();
                    break;
                case 4 : repetirHash();
                    break;
                case 0 : System.out.println("Tornant al menú principal...");
                    break;
                default : System.out.println("Opció no vàlida.");
            }
        } while (opcio != 0);
    }

    private static void hashMissatge() {
        System.out.print("Entra el missatge: ");
        String missatge = scanner.nextLine();

        System.out.println("Hash: " + calcularHash(missatge));
    }

    private static void compararMissatges() {
        System.out.print("Entra el primer missatge: ");
        String missatge1 = scanner.nextLine();
        System.out.print("Entra el segon missatge: ");
        String missatge2 = scanner.nextLine();

        String hash1 = calcularHash(missatge1);
        String hash2 = calcularHash(missatge2);

        System.out.println("Hash 1: " + hash1);
        System.out.println("Hash 2: " + hash2);

        if (hash1.equals(hash2)) {
            System.out.println("Els hashes COINCIDEIXEN.");
        } else {
            System.out.println("Els hashes NO coincideixen.");
        }
    }

    private static void modificarMissatge() {
        System.out.print("Entra el missatge original: ");
        String original = scanner.nextLine();
        String hashOriginal = calcularHash(original);
        System.out.println("Hash original:   " + hashOriginal);

        System.out.print("Entra el missatge modificat: ");
        String modificat = scanner.nextLine();
        String hashModificat = calcularHash(modificat);
        System.out.println("Hash modificat:  " + hashModificat);

        StringBuilder diferencies = new StringBuilder();
        int canvis = 0;
        for (int i = 0; i < hashOriginal.length(); i++) {
            if (hashOriginal.charAt(i) != hashModificat.charAt(i)) {
                diferencies.append('^');
                canvis++;
            } else {
                diferencies.append(' ');
            }
        }
        System.out.println("Diferències:     " + diferencies);
        System.out.println("Han canviat " + canvis + " de " + hashOriginal.length() + " caràcters hexadecimals.");
    }

    private static void repetirHash() {
        System.out.print("Entra el missatge: ");
        String missatge = scanner.nextLine();
        System.out.print("Quantes vegades vols calcular el hash? ");
        int vegades = llegirEnter();

        if (vegades < 1) {
            System.out.println("El nombre de vegades ha de ser com a mínim 1.");
            return;
        }

        String primerHash = calcularHash(missatge);
        boolean sempreIgual = true;

        for (int i = 1; i <= vegades; i++) {
            String hash = calcularHash(missatge);
            System.out.println("Hash " + i + ": " + hash);
            if (!hash.equals(primerHash)) {
                sempreIgual = false;
            }
        }

        if (sempreIgual) {
            System.out.println("El hash s'ha mantingut IGUAL totes les vegades.");
        } else {
            System.out.println("El hash ha CANVIAT en algun càlcul.");
        }
    }

    public static String calcularHash(String text) {
        try {
            // Convertim el text a un array de bytes utilitzant UTF-8
            byte[] bytesIn = text.getBytes(StandardCharsets.UTF_8);

            // Creem l'objecte per calcular el hash SHA-256
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");

            // Calcular el hash
            byte[] hashResult = sha256.digest(bytesIn);

            // Convertim el resultat a hexadecimal
            StringBuilder textOut = new StringBuilder();
            for (byte b : hashResult) {
                textOut.append(String.format("%02x", b & 0xFF));
            }
            return textOut.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No s'ha trobat l'algoritme SHA-256: " + e.getMessage(), e);
        }
    }

    private static int llegirEnter() {
        String linia = scanner.nextLine().trim();
        try {
            return Integer.parseInt(linia);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
