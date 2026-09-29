package Arena_Legends;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int choix;
        do {
        System.out.println("=== ARENA LEGENDS ===");
        System.out.println("1. Lancer un dé ");
        System.out.println("2. Calculer un rang");
        System.out.println("3. Test de coup critique");
        System.out.println("0. Quitter");

        System.out.print("Choisissez une option : ");
        choix = scanner.nextInt();

        } while (choix != 0);

    }
}