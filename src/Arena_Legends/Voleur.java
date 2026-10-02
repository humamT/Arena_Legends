package Arena_Legends;

import java.util.Random;

public class Voleur extends Combattant {

    private int esquive;
    private Random random = new Random();

    public Voleur(String nom, int pvMax, int pv, int attaque, int defense, int esquive) {
        super(nom, pvMax, pv, attaque, controlerEsquive(defense, esquive));
        this.esquive = esquive;
    }

    private static int controlerEsquive(int defense, int esquive) {
        if (esquive < 10 || esquive > 40) {
            throw new IllegalArgumentException(
                "L'esquive doit être comprise entre 10 et 40%."
            );
        }

        return defense;
    }

    @Override
    public int attaquer(Combattant cible) {

        int degats = getAttaque();

        // 25% de chance de faire une double attaque
        if (random.nextInt(100) < 25) {
            degats *= 2;
        }

        return cible.subirDegats(degats);
    }

    @Override
    public int subirDegats(int d) {
        if (esquiveReussie()) {
            return 0;
        }

        return super.subirDegats(d);
    }

    @Override
    public int subirDegatsBruts(int d) {
        if (esquiveReussie()) {
            return 0;
        }

        return super.subirDegatsBruts(d);
    }

    private boolean esquiveReussie() {
        return random.nextInt(100) < esquive;
    }

    @Override
    public String getClasse() {
        return "Voleur";
    }
}
