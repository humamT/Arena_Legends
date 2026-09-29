package Arena_Legends;

import java.util.Random;

public class dé {

    private int nombreFace;

    public dé(int nombreFace) {
        this.nombreFace = nombreFace;
    }

    public int lancer() {
        Random random = new Random();
        return random.nextInt(nombreFace) + 1;
    }

}
