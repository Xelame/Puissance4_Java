import java.util.ArrayList;

public class Grille {

    private static Grille instance;
    private int nombreDeColonne;
    private int nombreDeLigne;
    private static final String[] LISTE_DE_JOUEUR = { "O", "X", "V" };
    private final String ALPHABET_MINUSCULE = "abcdefghijklmnopqrstuvwxyz";
    private final String ALPHABET_MAJUSCULE = ALPHABET_MINUSCULE.toUpperCase();
    private static int nombreDeJoueur;
    private static ArrayList<ArrayList<String>> contenu;

    // Constructeur privée ( toi même tu sais ;) )
    private Grille() {
        nombreDeJoueur = choosePlayerNumber();
        System.out.println(toString());

        // TODO : Trouver des éléments permetant d'utiliser des classe
        // car notre projet ne suis pas vraiment le principe de POO :/

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
        for (int indexLigne = 0; indexLigne < nombreDeLigne; indexLigne++) {

            affichage += "#";

            for (int indexColonne = 0; indexColonne < nombreDeColonne; indexColonne++) {
                affichage += contenu.get(indexColonne).get(indexLigne);
            }

            affichage += "#\n";

        }

        affichage += "#".repeat(nombreDeColonne + 2) + "\n";
        affichage += " " + ALPHABET_MINUSCULE.substring(0, nombreDeColonne) + "\n";

        return affichage;
    }

    private int choosePlayerNumber() {
        // TODO : Demander au l'utilisateur combien sont-ils ?
        int players = App.promptForInt("Veuillez entrer le nombre de joueurs (2 ou 3)");
        if (2 <= players && players <= 3) {
            nombreDeColonne = players * 4;
            nombreDeLigne = Math.round(players * 3.2f);
            contenu = getSizeGrid(players);
        } else {
            System.err.println("Please input a valid number");
            choosePlayerNumber();
        }

        return players;
    }

    private ArrayList<ArrayList<String>> getSizeGrid(int numberOfPlayer) {
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
    private String getPlayer(int turn) {
        return LISTE_DE_JOUEUR[turn % nombreDeJoueur];
    }

    // Demande au Joueur concerné qu'elle coup il joue
    private String chooseColumn(int turn) {
        String choice = App.promptForString("Joueur " + getPlayer(turn) + " choisissez une colonne :\n" + toString());
        if (ALPHABET_MINUSCULE.substring(0, nombreDeColonne).contains(choice)
                || ALPHABET_MAJUSCULE.substring(0, nombreDeColonne).contains(choice)) {
            return choice;
        } else {
            System.err.println("Choisissez un emplacement valide (avec la lettre correspondante ");
            return chooseColumn(turn);
        }
    }

    public boolean checkPlayerWin(String symbol){
        // check if there 4 same symbols in each column.
        int nbSymbolPerLine = 0; 
        for (int i = 0; i < nombreDeColonne-1; i++){
            for (int j = 0; j < nombreDeLigne-1; j++){
                if (contenu.get(i).get(j) == symbol){
                    nbSymbolPerLine +=1;
                    if (nbSymbolPerLine == 4){
                        return true;
                    }
                } else {
                    nbSymbolPerLine = 0;
                }
            }
            nbSymbolPerLine = 0;
        }  

        //----------------------------

        int nbSymbolPerColumn = 0;
        for (int l = 0; l < nombreDeLigne-1; l++){
            for (int k = 0; k < nombreDeColonne-1; k++){
                if (contenu.get(k).get(l) == symbol){
                    nbSymbolPerColumn +=1;
                    if (nbSymbolPerColumn == 4){
                        return true;
                    }
                }
            }
            nbSymbolPerColumn = 0;
        }

        //check diagonale
        return false;
    }


}
