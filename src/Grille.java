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
        System.out.println(GetPlayer(25));
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
        // TODO : Demander au l'utilisateur combien sont-ils ?
        return 0;
    }

    public static ArrayList<ArrayList<String>> GetSizeGrid(int numberOfPlayer) {
        // TODO : Initialise la taille du Tableau en fonction du nombre de joueur
        return new ArrayList<ArrayList<String>>();
    }

    // Fonction qui donne la lettre que joue un joueur en fonction du tour de jeu
    public static String GetPlayer(int turn) {
        return listeDesJoueurs.get(turn % nombreDeJoueur);
    }
}
