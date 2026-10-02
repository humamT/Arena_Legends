package Arena_Legends;

public abstract class Combattant {
    private final String nom;
    private final int pvMax;
    private int pv;
    private final int attaque;
    private final int defense;

    private int[] historiqueDegats = new int[5];
    private int nombreDegats = 0;

    private static int nbCombattants = 0;

    public abstract int attaquer(Combattant cible);

    public abstract String getClasse();

    public int subirDegats(int d) {

        int degatsReels = d - defense;

        if (degatsReels < 1) {
            degatsReels = 1;
        }

        return appliquerDegats(degatsReels);
    }

    protected int subirDegatsBruts(int d) {
        return appliquerDegats(d);
    }

    private int appliquerDegats(int degats) {
        int avant = pv;

        pv -= degats;

        if (pv < 0) {
            pv = 0;
        }

        int infliges = avant - pv;
        enregistrerDegats(infliges);
        return infliges;
    }

    private void enregistrerDegats(int degats) {
        if (nombreDegats < 5) {
            historiqueDegats[nombreDegats] = degats;
            nombreDegats++;
        } else {
            for (int i = 0; i < 4; i++) {
                historiqueDegats[i] = historiqueDegats[i + 1];
            }

            historiqueDegats[4] = degats;
        }
    }

    public void preparerProchainMatch() {
        pv = pvMax;

        for (int i = 0; i < historiqueDegats.length; i++) {
            historiqueDegats[i] = 0;
        }

        nombreDegats = 0;
    }

    public void soigner(int s) {
        if (pv == 0) {
            return;
        }

        pv += s;

        if (pv > pvMax) {
            pv = pvMax;
        }
    }

    public boolean estKO() {
        return pv == 0;
    }

    public Combattant(String nom, int pvMax, int pv, int attaque, int defense) {
        if (nom == null || nom.length() < 3 || nom.length() > 15) {
            throw new IllegalArgumentException("Le nom doit contenir entre 3 et 15 caractères.");
        }

        if (pvMax < 50 || pvMax > 300) {
            throw new IllegalArgumentException("pvMax doit être entre 50 et 300.");
        }

        if (pv < 0 || pv > pvMax) {
            throw new IllegalArgumentException("pv doit être entre 0 et pvMax.");
        }

        if (attaque < 5 || attaque > 50) {
            throw new IllegalArgumentException("attaque doit être entre 5 et 50.");
        }

        if (defense < 0 || defense > 30) {
            throw new IllegalArgumentException("defense doit être entre 0 et 30.");
        }

        this.nom = nom;
        this.pvMax = pvMax;
        this.pv = pv;
        this.attaque = attaque;
        this.defense = defense;

        nbCombattants++;
    }

    public String getNom() {
        return nom;
    }

    public int getPvMax() {
        return pvMax;
    }

    public int getPv() {
        return pv;
    }

    public int getAttaque() {
        return attaque;
    }

    public int getDefense() {
        return defense;
    }

    public static int getNbCombattants() {
        return nbCombattants;
    }

    public int[] getHistoriqueDegats() {
        return historiqueDegats.clone();
    }

    private int victoires = 0;

    public int getVictoires() {
        return victoires;
    }
    
    public void ajouterVictoire() {
        victoires++;
    }
    @Override
    public String toString() {
        return nom + " [" + pv + "/" + pvMax + " PV] ATK " + attaque + " DEF " + defense + " Classe " + getClasse();
    }

}
