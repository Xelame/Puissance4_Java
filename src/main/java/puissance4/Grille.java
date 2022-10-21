package puissance4;
import java.util.ArrayList;

public class Grille {

    /**
     * L'instance (la seule l'unique) de notre classe
     */
    private static Grille instance;

    /**
     * Le nombre de joueurs qui vont jouer
     */
    private final int NOMBRE_DE_JOUEUR;

    /**
     * Size of our grid : width
     */
    private final int NOMBRE_DE_COLONNE;
    /**
     * Size of our grid : height
     */
    private final int NOMBRE_DE_LIGNE;

    /**
     * Des Constantes d'alphabet pour une lecture plus lisible de notre code vous
     * verrez 🦚
     */
    public final String ALPHABET_MINUSCULE = "abcdefghijklmnopqrstuvwxyz";

    /**
     * Le contenu de la grille
     */
    private ArrayList<Colonne> contenu;

    /**
     * Constructeur privée ( toi même tu sais ;) )
     */
    private Grille(int nombreDeJoueur) {
        NOMBRE_DE_JOUEUR = nombreDeJoueur;
        NOMBRE_DE_COLONNE = NOMBRE_DE_JOUEUR * 4;
        NOMBRE_DE_LIGNE = Math.round(NOMBRE_DE_JOUEUR * 3.2f);
        createGrid();
    }

    /**
     * Méthode qui créer une seule unique instance de notre classe
     * 
     * @return L'unique instance de la classe
     * @see Grille
     */
    public static Grille getInstance(int nombreDeJoueur) {
        if (instance == null) {
            instance = new Grille(nombreDeJoueur);
        }
        return instance;
    }

    /**
     * Méthode pour afficher notre joli plateau de jeu dans ce sens :
     * <img src="https://i.ibb.co/JB4RH0s/Grapihque.png"/>
     */
    public String toString() {
        String affichage = "\n";

        // Body
        for (int indexLigne = NOMBRE_DE_LIGNE - 1; indexLigne >= 0; indexLigne--) {

            // Line
            affichage += "#";
            String ligne = "";
            for (int indexColonne = 0; indexColonne < NOMBRE_DE_COLONNE; indexColonne++) {
                ligne += contenu.get(indexColonne).getArray()[indexLigne];
            }
            affichage += ligne;
            affichage += "#\n";

        }

        // Floor
        affichage += "#".repeat(NOMBRE_DE_COLONNE + 2) + "\n";
        affichage += " " + ALPHABET_MINUSCULE.substring(0, NOMBRE_DE_COLONNE) + "\n";

        return affichage;
    }

    /**
     * Ask the current player which column he choose
     *
     * @return a Letter corresponding to the column choosen
     */
    public int chooseColumn(String playerLetter) {
        String choice = App
                .promptForString("Joueur " + playerLetter + " choisissez une colonne :\n" + toString());
        int indexChoosen = ALPHABET_MINUSCULE.indexOf(choice.toLowerCase());
        if (0 <= indexChoosen && indexChoosen <= NOMBRE_DE_COLONNE && choice.length() > 0) {
            return indexChoosen;
        } else {
            System.err.println("Choisissez un emplacement valide (avec la lettre correspondante) ");
            return chooseColumn(playerLetter);
        }
    }

    /**
     * Méthode qui ajoute la pièce du joueur sur la colonne selectionner
     * 
     * @param indexColonne a String correspond to the column choosed by user
     * @param turnNumber   ... on te voit souvent toi
     */
    public Boolean fillColumn(int indexColonne, String playerLetter) {

        // ! Bien vu, hélas méthode déprécié malgré les comms (＞﹏＜)
        // en ascii a = 97 et z = 122 donc on va de a à j c'est à dire de 97 à 106
        // si le caractère correspond à a on rempli la premiere colonne donc on fait
        // letter - 97 car on commence à 0

        // Ici on donne la position de la lettre dans l'alphabet (où a = 0, b = 1,
        // etc...)

        Colonne columnChoosen = contenu.get(indexColonne);

        if (columnChoosen.isFull()) {
            System.out.println("La colonne est déjà  complète !!");
            return false;
        } else {
            columnChoosen.fill(playerLetter);

            return true;
        }
    }

    /**
     * Méthode qui test si la grille est pleine
     * 
     * @return Boolean response if the grid is full
     */
    public Boolean isFull() {
        for (int column = 0; column < contenu.size(); column++) {
            Colonne currentColumn = contenu.get(column);
            if (!currentColumn.isFull()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Fill the grid of empty slots in terms of number of players
     * 
     */
    private void createGrid() {
        contenu = new ArrayList<>();
        for (int i = 0; i < NOMBRE_DE_COLONNE; i++) {
            contenu.add(new Colonne(NOMBRE_DE_LIGNE));
        }
    }

    /**
     * 
     * @param playerLetter
     * @param inversed
     * @return
     */
    private Boolean diagonalWin(String playerLetter, Boolean inversed) {
        for (int colonne = inversed ? 3 : 0; colonne < (inversed ? NOMBRE_DE_COLONNE
                : NOMBRE_DE_COLONNE - 3); colonne++) {
            for (int ligne = inversed ? 3 : 0; ligne < (inversed ? NOMBRE_DE_LIGNE
                    : NOMBRE_DE_LIGNE - 3); ligne++) {
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
        for (int colonne = 0; colonne < NOMBRE_DE_COLONNE - 3; colonne++) {
            for (int ligne = 0; ligne < NOMBRE_DE_LIGNE; ligne++) {
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
        for (int colonne = 0; colonne < NOMBRE_DE_COLONNE; colonne++) {
            for (int ligne = 0; ligne < NOMBRE_DE_LIGNE - 3; ligne++) {
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

    public Boolean isEnd(String playerLetter) {
        return isFull()
                || diagonalWin(playerLetter, true)
                || diagonalWin(playerLetter, false)
                || lineWin(playerLetter)
                || columnWin(playerLetter);
    }
}