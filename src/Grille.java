import java.util.ArrayList;

public class Grille {

    // L'instance (La seule l'unique) d'UNE grille ^^
    private static Grille instance;

    // Sa taille jusque la vous suivé :)
    private int nombreDeColonne;
    private int nombreDeLigne;

    // La liste de lettre pour les possible joueur (c'est pas très compréhensible my fault ^^')
    private static final String[] LISTE_DE_JOUEUR = { "O", "X", "V" };

    // Des Constantes d'alphabet pour une lecture plus lisible de notre code vous verrez 🦚
    private final String ALPHABET_MINUSCULE = "abcdefghijklmnopqrstuvwxyz";
    private final String ALPHABET_MAJUSCULE = ALPHABET_MINUSCULE.toUpperCase();

    // Le nombre de joueurs qui vont jouer
    private static int nombreDeJoueur;

    // Le contenu de la grille 
    private ArrayList<ArrayList<String>> contenu;
    
    //info : les joueurs vont soit de 0 à 1 (2j) ou de 0 à 2 (3j)
    private static int numeroPLayer =0;
    private static int column =0;

    // Constructeur privée ( toi même tu sais ;) )
    private Grille() {
        nombreDeJoueur = choosePlayerNumber();
        System.out.println(toString());

        // TODO : Trouver des éléments permetant d'utiliser des classe
        // car notre projet ne suis pas vraiment le principe de POO :/ 💭

    }

    // Méthode qui créer une seule unique instance de notre classe
    public static Grille getInstance() {
        if (instance == null) {
            instance = new Grille();
        }
        return instance;
    }

    // Méthode pour afficher notre joli plateau de jeu
    // Liste de départ : [[ ,  ,  ,  ,  ,  ,  ], [ ,  ,  ,  ,  ,  ,  ], ..., [ ,  ,  ,  ,  ,  ,  ]]
    /* Résultat : 
    [⎴ ⎴ ⎴ ⎴ ⎴ ⎴ ⎴ ⎴|
    |                |  ^
    |                |  |
    |                |  |
    |                |
    |⎵,⎵,⎵,⎵,⎵,⎵,⎵,⎵]
    */
    public String toString() {
        String affichage = "\n";

        // Body
        for (int indexLigne = nombreDeLigne-1; indexLigne >= 0; indexLigne--) {

            // Line
            affichage += "#";
            String ligne = "";
            for (int indexColonne = 0; indexColonne < nombreDeColonne; indexColonne++) {
                ligne += contenu.get(indexColonne).get(indexLigne);
            }
            affichage += ligne;
            affichage += "#\n";

        }

        // Floor
        affichage += "#".repeat(nombreDeColonne + 2) + "\n";
        affichage += " " + ALPHABET_MINUSCULE.substring(0, nombreDeColonne) + "\n";

        return affichage;
    }

    private int choosePlayerNumber() {
        // TODO : Demander au l'utilisateur combien sont-ils ? ✅
        int players = App.promptForInt("Veuillez entrer le nombre de joueurs (2 ou 3)");
        if (2 <= players && players <= 3) {
            nombreDeColonne = players * 4;
            nombreDeLigne = Math.round(players * 3.2f);
            getSizeGrid(players);
        } else {
            System.err.println("Please input a valid number");
            choosePlayerNumber();
        }

        return players;
    }

    private void getSizeGrid(int numberOfPlayer) {
        contenu = new ArrayList<>();
        // TODO : Initialise la taille du Tableau en fonction du nombre de joueur ✅
        for (int i = 0; i < nombreDeColonne; i++) {
            ArrayList<String> colonne = new ArrayList<String>();
            for (int j = 0; j < nombreDeLigne; j++) {
                colonne.add(" ");
            }
            contenu.add(colonne);
        }
    }

    // Fonction qui donne la lettre que joue un joueur en fonction du tour de jeu
    private String getPlayerLetter(int turn) {
        return LISTE_DE_JOUEUR[turn % nombreDeJoueur];
    }

    // Demande au Joueur concerné qu'elle coup il joue
    private String chooseColumn(int turn) {
        String choice = App.promptForString("Joueur " + getPlayerLetter(turn) + " choisissez une colonne :\n" + toString());
        if ((ALPHABET_MINUSCULE.substring(0, nombreDeColonne).contains(choice) || ALPHABET_MAJUSCULE.substring(0, nombreDeColonne).contains(choice)) && choice.length() > 0) {
            return choice;
        } else {
            System.err.println("Choisissez un emplacement valide (avec la lettre correspondante) ");
            return chooseColumn(turn);
        }
    }

    // TODO : La loop de jeu ✅
    public void Play() {
        int turnNumber = 0;
        // TODO : Mettre la condition de fin ici
        while (turnNumber < 50) {
            // TODO : Faire l'interaction voulu :)
            String laLettreQueNousDonneLeJoueur = chooseColumn(turnNumber);
            turnNumber++;
        }
    }
}
