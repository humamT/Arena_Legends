package Arena_Legends;

import java.util.Random;

public class StatsArene {

    public static double moyenne(int[] t) {
        int somme = 0;

        for (int i = 0; i < t.length; i++) {
            somme += t[i];
        }

        return (double) somme / t.length;
    }

    public static int max(int[] t) {
        int maximum = t[0];

        for (int i = 1; i < t.length; i++) {
            if (t[i] > maximum) {
                maximum = t[i];
            }
        }

        return maximum;
    }

    public static int min(int[] t) {
        int minimum = t[0];

        for (int i = 1; i < t.length; i++) {
            if (t[i] < minimum) {
                minimum = t[i];
            }
        }

        return minimum;
    }

    public static int trierDecroissant(int[] t) {
        int echanges = 0;

        for (int i = 0; i < t.length - 1; i++) {
            boolean echange = false;

            for (int j = 0; j < t.length - 1 - i; j++) {
                if (t[j] < t[j + 1]) {
                    int temp = t[j];
                    t[j] = t[j + 1];
                    t[j + 1] = temp;

                    echanges++;
                    echange = true;
                }
            }

            if (!echange) {
                break;
            }
        }

        return echanges;
    }

    public static int[] sansDoublons(int[] t) {
        int taille = 0;

        for (int i = 0; i < t.length; i++) {
            boolean existe = false;

            for (int j = 0; j < i; j++) {
                if (t[i] == t[j]) {
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                taille++;
            }
        }

        int[] resultat = new int[taille];
        int index = 0;

        for (int i = 0; i < t.length; i++) {
            boolean existe = false;

            for (int j = 0; j < i; j++) {
                if (t[i] == t[j]) {
                    existe = true;
                    break;
                }
            }

            if (!existe) {
                resultat[index] = t[i];
                index++;
            }
        }

        return resultat;
    }

    public static char[][] creerGrille() {
        char[][] grille = new char[8][8];

        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                grille[i][j] = '.';
            }
        }

        Random random = new Random();

        int obstacles = 0;

        while (obstacles < 6) {
            int ligne = random.nextInt(8);
            int colonne = random.nextInt(8);

            if (grille[ligne][colonne] == '.') {
                grille[ligne][colonne] = '#';
                obstacles++;
            }
        }

        int combattants = 0;

        while (combattants < 2) {
            int ligne = random.nextInt(8);
            int colonne = random.nextInt(8);

            if (grille[ligne][colonne] == '.') {
                if (combattants == 0) {
                    grille[ligne][colonne] = 'A';
                } else {
                    grille[ligne][colonne] = 'B';
                }

                combattants++;
            }
        }

        return grille;
    }

    public static int distance(char[][] grille) {
        int ligneA = -1;
        int colonneA = -1;
        int ligneB = -1;
        int colonneB = -1;

        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille[i].length; j++) {
                if (grille[i][j] == 'A') {
                    ligneA = i;
                    colonneA = j;
                } else if (grille[i][j] == 'B') {
                    ligneB = i;
                    colonneB = j;
                }
            }
        }

        return Math.abs(ligneA - ligneB) + Math.abs(colonneA - colonneB);
    }
}
