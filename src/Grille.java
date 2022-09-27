package src;

public class Grille {

    public static Grille instance;
    public static int nombreColonne;
    public static int nombreLigne;

    private Grille() {
        ChoosePlayerNumber();
        // TODO : Réfléchir au système de tour et de joueur
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
