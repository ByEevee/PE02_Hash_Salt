import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Fase2 {
    private static final Scanner scanner = App.scanner;

    private static final Path CARPETA = Path.of("docs");

    private static final String FITXER_MISSATGE = "missatge.txt";
    private static final String FITXER_CANVIAT = "missatgeCanviat.txt";
    private static final String FITXER_HASH = "missatge.hash";

    public static void main(String[] args) {
        System.out.println();
        System.out.println("===== PART 2: VERIFICACIÓ DE LA INTEGRITAT =====");

        try {
            System.out.println("--- EMISSOR ---");
            System.out.print("Entra el missatge a enviar: ");
            String missatge = scanner.nextLine();
            escriureFitxer(FITXER_MISSATGE, missatge);
            System.out.println("Missatge desat a " + CARPETA.resolve(FITXER_MISSATGE));

            String hashOriginal = Fase1.calcularHash(missatge);
            escriureFitxer(FITXER_HASH, hashOriginal);
            System.out.println("Hash original desat a " + CARPETA.resolve(FITXER_HASH) + ": " + hashOriginal);

            System.out.println();
            System.out.println("--- RECEPTOR ---");
            System.out.println("S'ha rebut un missatge.");

            System.out.print("Entra el nom del fitxer rebut (" + FITXER_MISSATGE + " o " + FITXER_CANVIAT + "): ");
            String nomFitxer = scanner.nextLine().trim();

            if (!Files.exists(CARPETA.resolve(nomFitxer))) {
                System.out.println("El fitxer " + CARPETA.resolve(nomFitxer) + " no existeix.");
                return;
            }

            String missatgeRebut = llegirFitxer(nomFitxer);
            String hashRebut = Fase1.calcularHash(missatgeRebut);
            String hashGuardat = llegirFitxer(FITXER_HASH).trim();

            System.out.println("Missatge rebut: " + missatgeRebut);
            System.out.println("Hash original (" + CARPETA.resolve(FITXER_HASH) + "): " + hashGuardat);
            System.out.println("Hash rebut    (" + CARPETA.resolve(nomFitxer) + "): " + hashRebut);

            if (hashRebut.equals(hashGuardat)) {
                System.out.println("Els hashes COINCIDEIXEN: el missatge és íntegre.");
            } else {
                System.out.println("Els hashes NO coincideixen: s'ha detectat una MODIFICACIÓ del missatge.");
            }
        } catch (IOException e) {
            System.out.println("Error treballant amb els fitxers: " + e.getMessage());
        }
    }

    private static void escriureFitxer(String nom, String contingut) throws IOException {
        Files.writeString(CARPETA.resolve(nom), contingut, StandardCharsets.UTF_8);
    }

    private static String llegirFitxer(String nom) throws IOException {
        return Files.readString(CARPETA.resolve(nom), StandardCharsets.UTF_8);
    }
}
