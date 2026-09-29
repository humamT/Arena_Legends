package Arena_Legends;

import java.util.Scanner;
import java.util.Random;

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
            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
            } else {
                System.out.println("Entrée invalide.");
                scanner.next();
                choix = -1;
            }

            switch (choix) {
                case 1:
                    int nombreFace;

                    System.out.print("Nombre de faces (4-20) : ");
                    nombreFace = scanner.nextInt();

                    while (nombreFace < 4 || nombreFace > 20) {
                        System.out.println("Le nombre de faces doit être entre 4 et 20.");
                        System.out.print("Nombre de faces (4-20) : ");
                        nombreFace = scanner.nextInt();
                    }

                    dé dé = new dé(nombreFace);
                    int resultat = dé.lancer();
                    System.out.println("Le résultat du dé est : " + resultat);
                    break;

                case 2:
                    int points;

                    System.out.print("Nombre de points : ");
                    points = scanner.nextInt();

                    while (points < 0) {
                        System.out.println("Le nombre de points ne peut pas être négatif.");
                        System.out.print("Nombre de points : ");
                        points = scanner.nextInt();
                    }

                    if (points < 100) {
                        System.out.println("Rang : Bronze");
                    } else if (points < 500) {
                        System.out.println("Rang : Argent");
                    } else if (points < 1500) {
                        System.out.println("Rang : Or");
                    } else {
                        System.out.println("Rang : Légende");
                    }

                    break;

                case 3:
                    Random random = new Random();

                    int critiques = 0;
                    int serieActuelle = 0;
                    int meilleureSerie = 0;

                    for (int i = 0; i < 10000; i++) {

                        if (random.nextDouble() < 0.15) {
                            critiques++;
                            serieActuelle++;

                            if (serieActuelle > meilleureSerie) {
                                meilleureSerie = serieActuelle;
                            }
                        } else {
                            serieActuelle = 0;
                        }
                    }

                    double pourcentage = (critiques * 100.0) / 10000;

                    System.out.println("Nombre de critiques : " + critiques);
                    System.out.println("Pourcentage réel : " + pourcentage + "%");
                    System.out.println("Plus longue série de critiques : " + meilleureSerie);
                    
                    break;

                case 0:
                    System.out.println("Au revoir !");
                    break;

                case -1:
                    // Rien à faire : l'erreur a déjà été affichée
                    break;

                default:
                    System.out.println("Option invalide.");
                    break;
            }

        } while (choix != 0);

    }
}