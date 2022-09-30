import java.util.ArrayList;

public class Grille {


    private static Grille instance;
    public int nombreDeColonne;
    public int nombreDeLigne;
    private static final String[] LISTE_DE_JOUEUR = { "O", "X", "V" };
    private final String ALPHABET_MINUSCULE = "abcdefghijklmnopqrstuvwxyz";
    private final String ALPHABET_MAJUSCULE = ALPHABET_MINUSCULE.toUpperCase();
    private static int nombreDeJoueur;
    private static ArrayList<ArrayList<String>> contenu;

    // Constructeur privée ( toi même tu sais ;) ) 
    private Grille() {
        nombreDeJoueur = choosePlayerNumber();
        System.out.println(toString());

        // TODO : Réfléchir au système de tour et de joueur

    }

    // Méthode qui créer une seule unique instance de notre classe
    public static Grille getInstance() {
        if (instance == null) {
            instance = new Grille();
        }
        return instance;
    }

    // Méthode pour afficher notre joli plateau de jeu
    public String toString() {
        String affichage = "\n";
        // TODO : Amélioration requise svp et return le tout en String :)
        for (int k = 0; k < nombreDeLigne; k++) {
            affichage += "#";

            for (int j = 0; j < nombreDeColonne; j++) {
                affichage += contenu.get(j).get(k);
            }

            affichage += "#\n";

        }

        affichage += "#".repeat(nombreDeColonne + 2) + "\n";
        affichage += " " + ALPHABET_MINUSCULE.substring(0, nombreDeColonne) + "\n";

        return affichage;
    }

    public int choosePlayerNumber() {
        // TODO : Demander au l'utilisateur combien sont-ils ?
        int players = App.promptForInt("Veuillez entrer le nombre de joueurs (2 ou 3)");
        if (2 <= players && players <= 3) {
            nombreDeColonne = players * 4;
            nombreDeLigne = Math.round(players * 3.3f);
            contenu = getSizeGrid(players);
        } else {
            System.err.println("Please input a valid number");
            choosePlayerNumber();
        }

        return players;
    }

    public ArrayList<ArrayList<String>> getSizeGrid(int numberOfPlayer) {
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
    public String getPlayer(int turn) {
        return LISTE_DE_JOUEUR[turn % nombreDeJoueur];
    }

    // Demande au Joueur concerné qu'elle coup il joue
    public String chooseColumn(int turn) {
        String choice = App.promptForString("Joueur " + getPlayer(turn) + " choisissez une colonne :\n" + toString());
        if (ALPHABET_MINUSCULE.substring(0, nombreDeColonne).contains(choice) || ALPHABET_MAJUSCULE.substring(0, nombreDeColonne).contains(choice)) {
            return choice;
        } else {
            System.err.println("Choisissez un emplacement valide (avec la lettre correspondante ");
            return chooseColumn(turn);
        }
    }
}
