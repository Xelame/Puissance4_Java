import java.util.ArrayList;
import java.util.Enumeration;

public class Grille {

    public static Grille instance;
    public static int nombreDeColonne;
    public static int nombreDeLigne;
    public static ArrayList<String> listeDesJoueurs = new ArrayList<String>();
    private static int nombreDeJoueur;
    private static ArrayList<ArrayList<String>> contenu;

    private Grille() {
        nombreDeJoueur = ChoosePlayerNumber();
        // TODO : Réfléchir au système de tour et de joueur

        ToString();
    }

    public static Grille getInstance() {
        if (instance == null) {
            instance = new Grille();
        }
        return instance;
    }

    public static void ToString() {
        // TODO : Amélioration requise svp :)
        for(int k = 0; k < nombreDeLigne; k++){
            System.out.print("#");

            for (int j =0; j< nombreDeColonne; j++){
                System.out.print(contenu.get(j).get(k));
            }

            System.out.println("#");

        }

        System.out.println("#".repeat(nombreDeColonne+2));
    }

    public static int ChoosePlayerNumber() {
        // TODO : Demander au l'utilisateur combien sont-ils ?
        int players = App.promptForInt("Veuillez entrer le nombre de joueurs (2 ou 3)");
        if (2 <= players && players <= 3) {
            nombreDeColonne = players * 4;
            nombreDeLigne = Math.round(players * 3.3f);
            contenu = GetSizeGrid(players);
        } else {
            System.err.println("Please input a valid number");
            ChoosePlayerNumber();
        }

        return players;
    }

    public static ArrayList<ArrayList<String>> GetSizeGrid(int numberOfPlayer) {
        // TODO : Initialise la taille du Tableau en fonction du nombre de joueur
        ArrayList<ArrayList<String>> grid = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < nombreDeColonne; i++) {
            ArrayList<String> arrcolumn = new ArrayList<String>();
            for (int j = 0; j < nombreDeLigne; j++) {
                arrcolumn.add(" ");
            }
            grid.add(arrcolumn);
        }
        return grid;
    }

    // Fonction qui donne la lettre que joue un joueur en fonction du tour de jeu
    public static String GetPlayer(int turn) {
        return listeDesJoueurs.get(turn % nombreDeJoueur);
    }
}
