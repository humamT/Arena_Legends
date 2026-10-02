package Arena_Legends;

public class Mage extends Combattant {

    private int mana = 100;

    public Mage(String nom, int pvMax, int pv, int attaque, int defense) {
        super(nom, pvMax, pv, attaque, defense);
    }

    @Override
    public int attaquer(Combattant cible) {

        int degats;

        if (mana >= 30) {
            mana -= 30;
            degats = getAttaque() * 2;
            return cible.subirDegatsBruts(degats);
        }

        degats = getAttaque() / 2;
        mana += 15;
        return cible.subirDegats(degats);
    }

    @Override
    public void preparerProchainMatch() {
        mana = 100;
        super.preparerProchainMatch();
    }

    @Override
    public String getClasse() {
        return "Mage";
    }
}
