import java.util.ArrayList;

public class Grille {

    /**
     * L'instance (la seule l'unique) de notre classe
     */
    private static Grille instance;

    /**
     * La liste de lettre pour les possibles joueurs
     */
    private static final String[] LISTE_DE_JOUEUR = { "O", "X", "V" };

    /**
     * Le nombre de joueurs qui vont jouer
     */
    private static int nombreDeJoueur;

    /**
     * Méthode qui créer une seule unique instance de notre classe
     * 
     * @return L'unique instance de la classe
     * @see Grille
     */
    public static Grille getInstance() {
        if (instance == null) {
            instance = new Grille();
        }
        return instance;
    }

    /**
     * Size of our grid : width
     */
    private final int nombreDeColonne = GameManager.numberOfPlayer * 4;
    /**
     * Size of our grid : height
     */
    private final int nombreDeLigne = Math.round(GameManager.numberOfPlayer * 3.2f);

    /**
     * Des Constantes d'alphabet pour une lecture plus lisible de notre code vous
     * verrez 🦚
     */
    private final String ALPHABET_MINUSCULE = "abcdefghijklmnopqrstuvwxyz";

    /**
     * Des Constantes d'alphabet pour une lecture plus lisible de notre code vous
     * verrez 🦚
     */
    private final String ALPHABET_MAJUSCULE = ALPHABET_MINUSCULE.toUpperCase();

    /**
     * Le contenu de la grille
     */
    private ArrayList<Colonne> contenu;

    /**
     * Constructeur privée (nécéssite d'avoir choisie le nombre de joueur au préalable)
     * @see GameManager.choosePlayerNumber()
     */
    private Grille() {
        // FIXME : Mettre la demande de choix de joueur ici je pense
        try {
            createGrid();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    /**
     * Méthode pour afficher notre joli plateau de jeu dans ce sens :
     * <img src="https://i.ibb.co/JB4RH0s/Grapihque.png"/>
     */
    public String toString() {
        String affichage = "\n";

        // Body
        for (int indexLigne = nombreDeLigne - 1; indexLigne >= 0; indexLigne--) {

            // Line
            affichage += "#";
            String ligne = "";
            for (int indexColonne = 0; indexColonne < nombreDeColonne; indexColonne++) {
                Colonne colonne = contenu.get(indexColonne);
                ligne += colonne.getArray()[indexLigne];
            }
            affichage += ligne;
            affichage += "#\n";

        }

        // Floor
        affichage += "#".repeat(nombreDeColonne + 2) + "\n";
        affichage += " " + ALPHABET_MINUSCULE.substring(0, nombreDeColonne) + "\n";

        return affichage;
    }

    /**
     * Méthode qui ajoute la pièce du joueur sur la colonne selectionner
     * 
     * @param nomColonne a String correspond to the column choosed by user
     * @param turnNumber ... on te voit souvent toi
     */
    public void fillColumn(String nomColonne, int turnNumber) {

        // ! Bien vu, hélas méthode déprécié malgré les comms (＞﹏＜)
        // en ascii a = 97 et z = 122 donc on va de a à j c'est à dire de 97 à 106
        // si le caractère correspond à a on rempli la premiere colonne donc on fait
        // letter - 97 car on commence à 0

        // Ici on donne la position de la lettre dans l'alphabet (où a = 0, b = 1,
        // etc...)
        int column = ALPHABET_MINUSCULE.indexOf(nomColonne.toLowerCase());

        if (contenu.get(column).isFull()) {
            System.out.println("La colonne est déjà  complète !!");
            fillColumn(chooseColumn(turnNumber), turnNumber);
        } else {
            contenu.get(column).fill(getPlayerLetter(turnNumber));
            ;
        }
    }

    /**
     * Méthode qui test si la grille est pleine
     * 
     * @return Boolean response if the grid is full
     */
    public Boolean isFull() {
        for (int column = 0; column < contenu.size(); column++) {
            if (!contenu.get(column).isFull()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Fill the grid of empty slots in terms of number of players
     * 
     */
    private void createGrid() throws Exception {
        contenu = new ArrayList<Colonne>();
        if (nombreDeColonne == 0 || nombreDeLigne == 0) {
            throw new Exception("Le nombre de joueur n'as pas été choisi au préalable");
        } else {
            for (int i = 0; i < nombreDeColonne; i++) {
                Colonne colonne = new Colonne(nombreDeLigne);
                contenu.add(colonne);
            }
        }
    }

    /**
     * Méthode qui donne la lettre que joue un joueur en fonction du tour de jeu
     * 
     * @param turn
     * @return a Letter corresponding to the player
     */
    String getPlayerLetter(int turn) {
        return LISTE_DE_JOUEUR[turn % nombreDeJoueur];
    }

    /**
     * Ask the current player which column he choose
     * 
     * @param turn
     * @return a Letter corresponding to the column choosen
     */
    public String chooseColumn(int turn) {
        String choice = GameManager
                .promptForString("Joueur " + getPlayerLetter(turn) + " choisissez une colonne :\n" + toString());
        if ((ALPHABET_MINUSCULE.substring(0, nombreDeColonne).contains(choice)
                || ALPHABET_MAJUSCULE.substring(0, nombreDeColonne).contains(choice))
                && choice.length() > 0) {
            return choice;
        } else {
            System.err.println("Choisissez un emplacement valide (avec la lettre correspondante) ");
            return chooseColumn(turn);
        }
    }

    /**
     * 
     * @param playerLetter
     * @param inversed
     * @return
     */
    private Boolean diagonalWin(String playerLetter, Boolean inversed) {
        for (int colonne = inversed ? 3 : 0; colonne < (inversed ? nombreDeColonne : nombreDeColonne - 3); colonne++) {
            for (int ligne = inversed ? 3 : 0; ligne < (inversed ? nombreDeLigne : nombreDeLigne - 3); ligne++) {
                if (checkdiagonal(colonne, ligne, playerLetter, inversed)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 
     * @param colonne
     * @param ligne
     * @param playerLetter
     * @param inversed
     * @return
     */
    private Boolean checkdiagonal(int colonne, int ligne, String playerLetter, Boolean inversed) {
        if (inversed) {
            return contenu.get(colonne).getArray()[ligne].equals(playerLetter)
                    && contenu.get(colonne - 1).getArray()[ligne + 1].equals(playerLetter)
                    && contenu.get(colonne - 2).getArray()[ligne + 2].equals(playerLetter)
                    && contenu.get(colonne - 3).getArray()[ligne + 3].equals(playerLetter);
        }
        return contenu.get(colonne).getArray()[ligne].equals(playerLetter)
                && contenu.get(colonne + 1).getArray()[ligne + 1].equals(playerLetter)
                && contenu.get(colonne + 2).getArray()[ligne + 2].equals(playerLetter)
                && contenu.get(colonne + 3).getArray()[ligne + 3].equals(playerLetter);

    }

    /**
     * 
     * @param playerLetter
     * @return
     */
    private Boolean lineWin(String playerLetter) {
        for (int colonne = 0; colonne < nombreDeColonne - 3; colonne++) {
            for (int ligne = 0; ligne < nombreDeLigne; ligne++) {
                if (checkline(colonne, ligne, playerLetter)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 
     * @param colonne
     * @param ligne
     * @param playerLetter
     * @return
     */
    private Boolean checkline(int colonne, int ligne, String playerLetter) {
        return contenu.get(colonne).getArray()[ligne].equals(playerLetter)
                && contenu.get(colonne + 1).getArray()[ligne].equals(playerLetter)
                && contenu.get(colonne + 2).getArray()[ligne].equals(playerLetter)
                && contenu.get(colonne + 3).getArray()[ligne].equals(playerLetter);
    }

    /**
     * 
     * @param playerLetter
     * @return
     */
    private Boolean columnWin(String playerLetter) {
        for (int colonne = 0; colonne < nombreDeColonne; colonne++) {
            for (int ligne = 0; ligne < nombreDeLigne - 3; ligne++) {
                if (checkcolumn(colonne, ligne, playerLetter)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 
     * @param colonne
     * @param ligne
     * @param playerLetter
     * @return
     */
    private Boolean checkcolumn(int colonne, int ligne, String playerLetter) {
        return contenu.get(colonne).getArray()[ligne].equals(playerLetter)
                && contenu.get(colonne).getArray()[ligne + 1].equals(playerLetter)
                && contenu.get(colonne).getArray()[ligne + 2].equals(playerLetter)
                && contenu.get(colonne).getArray()[ligne + 3].equals(playerLetter);
    }

    public Boolean isRunning(String character) {
        return isFull()
            || diagonalWin(character, true)
            || diagonalWin(character, false)
            || lineWin(character)
            || columnWin(character);
    }

}
