import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Fase3 {
    private static final Scanner scanner = App.scanner;

    public static final Map<String, String[]> usuaris = new HashMap<>();

    private static final SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        int opcio;

        do {
            System.out.println();
            System.out.println("===== PART 3: REGISTRE D'USUARIS AMB SALT =====");
            System.out.println("1. Registrar un usuari");
            System.out.println("2. Mostrar els usuaris registrats");
            System.out.println("0. Tornar al menú principal");
            System.out.print("Opció: ");
            opcio = llegirEnter();

            switch (opcio) {
                case 1 : registrarUsuari();
                    break;
                case 2 : mostrarUsuaris();
                    break;
                case 0 : System.out.println("Tornant al menú principal...");
                    break;
                default : System.out.println("Opció no vàlida.");
            }
        } while (opcio != 0);
    }

    private static void registrarUsuari() {
        System.out.print("Nom d'usuari: ");
        String usuari = scanner.nextLine().trim();

        if (usuari.isEmpty()) {
            System.out.println("El nom d'usuari no pot estar buit.");
            return;
        }
        if (usuaris.containsKey(usuari)) {
            System.out.println("L'usuari " + usuari + " ja està registrat.");
            return;
        }

        System.out.print("Contrasenya: ");
        String contrasenya = scanner.nextLine();

        String salt = generarSalt();
        String hash = calcularHashAmbSalt(contrasenya, salt);

        usuaris.put(usuari, new String[] { salt, hash });

        System.out.println("Usuari " + usuari + " registrat correctament.");
        System.out.println("Salt: " + salt);
        System.out.println("Hash: " + hash);
    }

    private static void mostrarUsuaris() {
        if (usuaris.isEmpty()) {
            System.out.println("No hi ha cap usuari registrat.");
            return;
        }

        for (Map.Entry<String, String[]> entrada : usuaris.entrySet()) {
            System.out.println("Usuari: " + entrada.getKey());
            System.out.println("  Salt: " + entrada.getValue()[0]);
            System.out.println("  Hash: " + entrada.getValue()[1]);
        }
    }

    public static String generarSalt() {
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return aHexadecimal(salt);
    }

    public static String calcularHashAmbSalt(String contrasenya, String salt) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");

            sha256.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] hashResult = sha256.digest(contrasenya.getBytes(StandardCharsets.UTF_8));

            return aHexadecimal(hashResult);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("No s'ha trobat l'algoritme SHA-256: " + e.getMessage(), e);
        }
    }

    private static String aHexadecimal(byte[] bytes) {
        StringBuilder textOut = new StringBuilder();
        for (byte b : bytes) {
            textOut.append(String.format("%02x", b & 0xFF));
        }
        return textOut.toString();
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
