package Arena_Legends;

public class Guerrier extends Combattant {
    private int rage = 0;

    public Guerrier(String nom, int pvMax, int pv, int attaque, int defense) {
        super(nom, pvMax, pv, attaque, defense);
    }

    @Override
    public int attaquer(Combattant cible) {
        rage += 20;
        int degats = getAttaque();
        if (rage >= 100) {
            degats *= 2;
            rage = 0;
        }
        cible.subirDegats(degats);
        return degats;
    }

    @Override
    public String getClasse() {
        return "Guerrier";
    }
}