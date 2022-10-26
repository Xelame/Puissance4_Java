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
     * Taille de notre grille : largeur
     */
    private final int NOMBRE_DE_COLONNE;
    /**
     * Taille de notre grille : hauteur
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
     * Méthode pour afficher notre joli plateau de jeu dans ce sens : <br>
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
     * Choix de la colonne dans laquelle le joueur veut jouer
     * @param playerLetter La lettre du joueur
     * @return L'index de la colonne choisie
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
     * Rempli si possible la colonne choisie par le joueur
     * @param indexColonne L'index de la colonne choisie
     * @param playerLetter La lettre du joueur
     * @return true si la colonne est jouable, false sinon
     */
    public Boolean fillColumn(int indexColonne, String playerLetter) {

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
     * @return true si la grille est pleine, false sinon
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
     * Création de la grille
     */
    private void createGrid() {
        contenu = new ArrayList<>();
        for (int i = 0; i < NOMBRE_DE_COLONNE; i++) {
            contenu.add(new Colonne(NOMBRE_DE_LIGNE));
        }
    }

    /**
     * Test si le joueur a gagné avec les diagonales
     * @param playerLetter La lettre du joueur
     * @param inversed Si true on test les diagonales inversées
     * @return true si le joueur a gagné, false sinon
     */
    private Boolean diagonalWin(String playerLetter, Boolean inversed) {
        for (int colonne = (inversed ? 3 : 0); colonne < (inversed ? NOMBRE_DE_COLONNE
                : NOMBRE_DE_COLONNE - 3); colonne++) {
            for (int ligne = 0; ligne < NOMBRE_DE_LIGNE - 3; ligne++) {
                if (checkdiagonal(colonne, ligne, playerLetter, inversed)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Regarde si 4 pions sont alignés dans une diagonale
     * @param colonne La colonne de départ
     * @param ligne La ligne de départ
     * @param playerLetter La lettre du joueur
     * @param inversed Si true on test les diagonales inversées
     * @return true si 4 pions sont alignés, false sinon
     * @see Grille#diagonalWin(String, Boolean)
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
     * Test si le joueur a gagné avec les lignes
     * @param playerLetter La lettre du joueur
     * @return true si le joueur a gagné, false sinon
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
     * Regarde si 4 pions sont alignés dans une ligne
     * @param colonne La colonne de départ
     * @param ligne La ligne de départ
     * @param playerLetter La lettre du joueur
     * @return true si 4 pions sont alignés, false sinon
     * @see Grille#lineWin(String)
     */
    private Boolean checkline(int colonne, int ligne, String playerLetter) {
        return contenu.get(colonne).getArray()[ligne].equals(playerLetter)
                && contenu.get(colonne + 1).getArray()[ligne].equals(playerLetter)
                && contenu.get(colonne + 2).getArray()[ligne].equals(playerLetter)
                && contenu.get(colonne + 3).getArray()[ligne].equals(playerLetter);
    }

    /**
     * Test si le joueur a gagné avec les colonnes
     * @param playerLetter La lettre du joueur
     * @return true si le joueur a gagné, false sinon
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
     * Regarde si 4 pions sont alignés dans une colonne
     * @param colonne La colonne de départ
     * @param ligne La ligne de départ
     * @param playerLetter La lettre du joueur
     * @return true si 4 pions sont alignés, false sinon
     * @see Grille#columnWin(String)
     */
    private Boolean checkcolumn(int colonne, int ligne, String playerLetter) {
        return contenu.get(colonne).getArray()[ligne].equals(playerLetter)
                && contenu.get(colonne).getArray()[ligne + 1].equals(playerLetter)
                && contenu.get(colonne).getArray()[ligne + 2].equals(playerLetter)
                && contenu.get(colonne).getArray()[ligne + 3].equals(playerLetter);
    }

    /**
     * Test si le joueur a gagné
     * @param playerLetter La lettre du joueur
     * @return true si le joueur a gagné, false sinon
     * @see Grille#columnWin(String)
     * @see Grille#lineWin(String)
     * @see Grille#diagonalWin(String, Boolean)
     */
    public Boolean isEnd(String playerLetter) {
        return isFull()
                || diagonalWin(playerLetter, true)
                || diagonalWin(playerLetter, false)
                || lineWin(playerLetter)
                || columnWin(playerLetter);
    }

    public Colonne getColumn(int indexColonne) {
        return contenu.get(indexColonne);
    }
}