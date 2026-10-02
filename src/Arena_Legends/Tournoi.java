package Arena_Legends;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;

public class Tournoi {

    private ArrayList<Combattant> participants;

    public Combattant lancer() {

        ArrayList<Combattant> actuels = new ArrayList<>(participants);

        while (actuels.size() > 1) {

            Collections.shuffle(actuels);

            ArrayList<Combattant> gagnants = new ArrayList<>();

            for (int i = 0; i < actuels.size(); i += 2) {

                if (i + 1 >= actuels.size()) {
                    gagnants.add(actuels.get(i));
                    break;
                }

                Combattant a = actuels.get(i);
                Combattant b = actuels.get(i + 1);

                Combattant gagnant = duel(a, b, true);

                gagnants.add(gagnant);
            }

            for (Combattant gagnant : gagnants) {
                gagnant.preparerProchainMatch();
            }

            actuels = gagnants;
        }

        return actuels.get(0);
    }

    public Combattant lancerSilencieux() {

        ArrayList<Combattant> actuels = new ArrayList<>(participants);

        while (actuels.size() > 1) {

            Collections.shuffle(actuels);

            ArrayList<Combattant> gagnants = new ArrayList<>();

            for (int i = 0; i < actuels.size(); i += 2) {

                if (i + 1 >= actuels.size()) {
                    gagnants.add(actuels.get(i));
                    break;
                }

                Combattant a = actuels.get(i);
                Combattant b = actuels.get(i + 1);

                Combattant gagnant = duel(a, b, false);

                gagnants.add(gagnant);
            }

            for (Combattant gagnant : gagnants) {
                gagnant.preparerProchainMatch();
            }

            actuels = gagnants;
        }

        return actuels.get(0);
    }

    public ArrayList<Combattant> classement() {

        ArrayList<Combattant> classement = new ArrayList<>(participants);

        // Tri manuel : victoires décroissantes
        for (int i = 0; i < classement.size() - 1; i++) {

            for (int j = 0; j < classement.size() - 1 - i; j++) {

                if (classement.get(j).getVictoires() < classement.get(j + 1).getVictoires()) {

                    Combattant temporaire = classement.get(j);

                    classement.set(j, classement.get(j + 1));
                    classement.set(j + 1, temporaire);
                }
            }
        }

        return classement;
    }

    public void statsParClasse() {

        int victoiresGuerrier = 0;
        int victoiresMage = 0;
        int victoiresVoleur = 0;
        int victoiresPaladin = 0;

        for (Combattant combattant : participants) {

            switch (combattant.getClasse()) {

                case "Guerrier":
                    victoiresGuerrier += combattant.getVictoires();
                    break;

                case "Mage":
                    victoiresMage += combattant.getVictoires();
                    break;

                case "Voleur":
                    victoiresVoleur += combattant.getVictoires();
                    break;

                case "Paladin":
                    victoiresPaladin += combattant.getVictoires();
                    break;
            }
        }

        System.out.println("=== STATISTIQUES PAR CLASSE ===");
        System.out.println("Guerrier : " + victoiresGuerrier + " victoire(s)");
        System.out.println("Mage     : " + victoiresMage + " victoire(s)");
        System.out.println("Voleur   : " + victoiresVoleur + " victoire(s)");
        System.out.println("Paladin  : " + victoiresPaladin + " victoire(s)");
    }

    public Tournoi() {
        participants = new ArrayList<>();
    }

    public boolean inscrire(Combattant combattant) {

        if (participants.size() >= 8) {
            return false;
        }

        for (Combattant c : participants) {
            if (c.getNom().equalsIgnoreCase(combattant.getNom())) {
                return false;
            }
        }

        participants.add(combattant);
        return true;
    }

    public boolean desinscrire(String nom) {

        Iterator<Combattant> iterator = participants.iterator();

        while (iterator.hasNext()) {
            Combattant combattant = iterator.next();

            if (combattant.getNom().equalsIgnoreCase(nom)) {
                iterator.remove();
                return true;
            }
        }

        return false;
    }

    public ArrayList<Combattant> getParticipants() {
        return new ArrayList<>(participants);
    }

    public Combattant duel(Combattant a, Combattant b, boolean afficher) {

        Combattant attaquant;
        Combattant defenseur;

        if (a.getAttaque() >= b.getAttaque()) {
            attaquant = a;
            defenseur = b;
        } else {
            attaquant = b;
            defenseur = a;
        }

        int tour = 0;

        while (!a.estKO() && !b.estKO()) {

            tour++;

            if (afficher) {
                System.out.println("Tour " + tour + " : "
                        + attaquant.getNom() + " attaque !");
            }

            int degats = attaquant.attaquer(defenseur);

            if (afficher) {
                System.out.println(
                        attaquant.getNom() + " inflige "
                                + degats + " dégâts.");

                System.out.println(
                        defenseur.getNom() + " : "
                                + defenseur.getPv() + "/"
                                + defenseur.getPvMax() + " PV");
            }

            if (tour > 50) {

                double pourcentageA = (double) a.getPv() / a.getPvMax();

                double pourcentageB = (double) b.getPv() / b.getPvMax();

                if (pourcentageA >= pourcentageB) {
                    a.ajouterVictoire();
                    return a;
                } else {
                    b.ajouterVictoire();
                    return b;
                }
            }

            Combattant temporaire = attaquant;
            attaquant = defenseur;
            defenseur = temporaire;
        }

        if (a.estKO()) {
            b.ajouterVictoire();
            return b;
        } else {
            a.ajouterVictoire();
            return a;
        }
    }

}
