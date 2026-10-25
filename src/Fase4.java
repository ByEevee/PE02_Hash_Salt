import java.util.Scanner;

public class Fase4 {
    private static final Scanner scanner = App.scanner;

    public static void main(String[] args) {
        int opcio;

        do {
            System.out.println();
            System.out.println("===== PART 4: INICI DE SESSIÓ =====");
            System.out.println("1. Iniciar sessió");
            System.out.println("0. Tornar al menú principal");
            System.out.print("Opció: ");
            opcio = llegirEnter();

            switch (opcio) {
                case 1 : iniciarSessio();
                    break;
                case 0 : System.out.println("Tornant al menú principal...");
                    break;
                default : System.out.println("Opció no vàlida.");
            }
        } while (opcio != 0);
    }

    private static void iniciarSessio() {
        System.out.print("Nom d'usuari: ");
        String usuari = scanner.nextLine().trim();
        System.out.print("Contrasenya: ");
        String contrasenya = scanner.nextLine();

        if (!Fase3.usuaris.containsKey(usuari)) {
            System.out.println("Error: l'usuari " + usuari + " no està registrat.");
            return;
        }

        String salt = Fase3.usuaris.get(usuari)[0];
        String hashGuardat = Fase3.usuaris.get(usuari)[1];

        String hashCalculat = Fase3.calcularHashAmbSalt(contrasenya, salt);

        System.out.println("Hash guardat:  " + hashGuardat);
        System.out.println("Hash calculat: " + hashCalculat);

        if (hashCalculat.equals(hashGuardat)) {
            System.out.println("Accés permès. Benvingut/da, " + usuari + "!");
        } else {
            System.out.println("Error: contrasenya incorrecta. Accés denegat.");
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
