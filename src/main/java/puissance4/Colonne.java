package puissance4;

import java.util.ArrayList;

public final class Colonne {

    /**
     * Le contenu de la colonne
     */
    private ArrayList<String> body;

    /**
     * Constructeur de la classe
     */
    public Colonne(int height) {
        body = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            body.add(" ");
        }
    }

    /**
     * Constructeur de la classe avec un contenu par défaut
     */
    public Colonne(int height, ArrayList<String> body) {
        if (height == body.size()) {
            this.body = body;
        } else {
            body = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                body.add(" ");
            }
        }
    }

    /**
     * Méthode qui test si la colonne est pleine
     * 
     * @return true si la colonne est pleine, false sinon
     */
    public Boolean isFull() {
        return body.indexOf(" ") == -1;
    }

    public void fill(String character) {
        if (!isFull()) {
            int peek = body.indexOf(" ");
            body.set(peek, character);
        }
    }

    /**
     * Méthode qui retourne le contenu de la colonne sous forme de liste de String
     * 
     * @return Le contenu de la colonne sous forme de liste de String
     */
    public Object[] getArray() {
        return body.toArray();
    }
}