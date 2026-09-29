package Arena_Legends;

import java.util.Random;

public class Voleur extends Combattant {

    private int esquive;
    private Random random = new Random();

    public Voleur(String nom, int pvMax, int pv, int attaque, int defense, int esquive) {
        super(nom, pvMax, pv, attaque, defense);

        if (esquive < 10 || esquive > 40) {
            throw new IllegalArgumentException(
                "L'esquive doit être comprise entre 10 et 40%."
            );
        }

        this.esquive = esquive;
    }

    @Override
    public int attaquer(Combattant cible) {

        int degats = getAttaque();

        // 25% de chance de faire une double attaque
        if (random.nextInt(100) < 25) {
            degats *= 2;
        }

        cible.subirDegats(degats);

        return degats;
    }

    @Override
    public void subirDegats(int d) {

        // Chance d'esquiver complètement
        if (random.nextInt(100) < esquive) {
            return;
        }

        super.subirDegats(d);
    }

    @Override
    public String getClasse() {
        return "Voleur";
    }
}
