package Arena_Legends;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int choix;

        do {

            System.out.println("=== ARENA LEGENDS ===");
            System.out.println("1. Lancer un dé");
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
                        System.out.println(
                                "Le nombre de faces doit être entre 4 et 20.");

                        System.out.print("Nombre de faces (4-20) : ");
                        nombreFace = scanner.nextInt();
                    }

                    dé dé = new dé(nombreFace);
                    int resultat = dé.lancer();

                    System.out.println(
                            "Le résultat du dé est : " + resultat);

                    break;

                case 2:
                    int points;

                    System.out.print("Nombre de points : ");
                    points = scanner.nextInt();

                    while (points < 0) {
                        System.out.println(
                                "Le nombre de points ne peut pas être négatif.");

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

                    System.out.println(
                            "Nombre de critiques : " + critiques);

                    System.out.println(
                            "Pourcentage réel : " + pourcentage + "%");

                    System.out.println(
                            "Plus longue série de critiques : "
                                    + meilleureSerie);

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

        // =====================================================
        // TOURNOI FINAL
        // =====================================================

        System.out.println();
        System.out.println("===== TOURNOI FINAL =====");

        Tournoi tournoi = new Tournoi();

        tournoi.inscrire(
                new Guerrier("Thor", 150, 150, 30, 10));

        tournoi.inscrire(
                new Guerrier("Kratos", 140, 140, 35, 8));

        tournoi.inscrire(
                new Mage("Gandalf", 100, 100, 25, 5));

        tournoi.inscrire(
                new Mage("Merlin", 110, 110, 22, 8));

        tournoi.inscrire(
                new Voleur("Zed", 120, 120, 28, 5, 30));

        tournoi.inscrire(
                new Voleur("Ezio", 115, 115, 30, 6, 25));

        tournoi.inscrire(
                new Paladin("Arthur", 160, 160, 25, 12));

        tournoi.inscrire(
                new Paladin("Lancelot", 150, 150, 28, 10));

        // Lancer le tournoi
        Combattant champion = tournoi.lancer();

        // Afficher le champion
        System.out.println();
        System.out.println("===== CHAMPION =====");
        System.out.println(champion);

        // Afficher le classement
        System.out.println();
        System.out.println("===== CLASSEMENT =====");

        ArrayList<Combattant> classement = tournoi.classement();

        for (int i = 0; i < classement.size(); i++) {

            Combattant c = classement.get(i);

            System.out.println(
                    (i + 1)
                            + ". "
                            + c.getNom()
                            + " - "
                            + c.getClasse()
                            + " - "
                            + c.getVictoires()
                            + " victoire(s)");
        }

        // Statistiques par classe
        System.out.println();

        tournoi.statsParClasse();

        // =====================================================
        // 100 SIMULATIONS
        // =====================================================

        System.out.println();
        System.out.println("===== 100 SIMULATIONS =====");

        int victoiresGuerrier = 0;
        int victoiresMage = 0;
        int victoiresVoleur = 0;
        int victoiresPaladin = 0;

        for (int i = 0; i < 100; i++) {

            // Nouveau tournoi à chaque simulation
            Tournoi t = new Tournoi();

            t.inscrire(
                    new Guerrier("Thor", 150, 150, 30, 10));

            t.inscrire(
                    new Guerrier("Kratos", 140, 140, 35, 8));

            t.inscrire(
                    new Mage("Gandalf", 100, 100, 25, 5));

            t.inscrire(
                    new Mage("Merlin", 110, 110, 22, 8));

            t.inscrire(
                    new Voleur("Zed", 120, 120, 28, 5, 30));

            t.inscrire(
                    new Voleur("Ezio", 115, 115, 30, 6, 25));

            t.inscrire(
                    new Paladin("Arthur", 160, 160, 25, 12));

            t.inscrire(
                    new Paladin("Lancelot", 150, 150, 28, 10));

            // Tournoi sans affichage
            Combattant champion100 = t.lancerSilencieux();

            // Compter la classe du champion
            switch (champion100.getClasse()) {

                case "Guerrier":
                    victoiresGuerrier++;
                    break;

                case "Mage":
                    victoiresMage++;
                    break;

                case "Voleur":
                    victoiresVoleur++;
                    break;

                case "Paladin":
                    victoiresPaladin++;
                    break;
            }
        }

        // Résultats des 100 simulations
        System.out.println(
                "Guerrier : " + victoiresGuerrier);

        System.out.println(
                "Mage     : " + victoiresMage);

        System.out.println(
                "Voleur   : " + victoiresVoleur);

        System.out.println(
                "Paladin  : " + victoiresPaladin);

        scanner.close();
    }
}