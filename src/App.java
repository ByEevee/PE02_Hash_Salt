import java.util.Scanner;

public class App {
    // Scanner compartit per totes les fases (només es tanca aquí en sortir)
    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcio;

        do {
            System.out.println();
            System.out.println("===== PE02 - HASH I SALT =====");
            System.out.println("1. Fase 1: Càlcul de resums");
            System.out.println("2. Fase 2: Verificació de la integritat dels missatges");
            System.out.println("3. Fase 3: Registre d'usuaris amb salt");
            System.out.println("4. Fase 4: Inici de sessió i verificació de contrasenyes");
            System.out.println("0. Sortir");
            System.out.print("Selecciona una fase: ");
            opcio = llegirEnter();

            switch (opcio) {
                case 1 : Fase1.main(args);
                    break;
                case 2 : Fase2.main(args);
                    break;
                case 3 : Fase3.main(args);
                    break;
                case 4 : Fase4.main(args);
                    break;
                case 0 : System.out.println("Adéu!");
                    break;
                default : System.out.println("Opció no vàlida.");
            }
        } while (opcio != 0);

        scanner.close();
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
