package src;

import java.util.ArrayList;

public class Grille {

    public static Grille instance;
    public static int nombreColonne;
    public static int nombreLigne;
    public static ArrayList<String> listeDesJoueurs = new ArrayList<String>();
    private static int nombreDeJoueur = 2;

    private Grille() {
        ChoosePlayerNumber();
        // TODO : Réfléchir au système de tour et de joueur
        listeDesJoueurs.add("O");
        listeDesJoueurs.add("X");
        listeDesJoueurs.add("V");
    }

    public static Grille getInstance() {
        if (instance == null) {
            instance = new Grille();
        }
        return instance;
    }

    public static String ToString() {
        // TODO : Retourner sa valeur en type `String` ici pour afficher de la grille :)
        return "";
    }

    public static int ChoosePlayerNumber() {
        // TODO : Demander au l'utilisateur combien sont-ils ? Et Initialise la taille
        // du Tableau en conséquence :)
        return 0;
    }
}
