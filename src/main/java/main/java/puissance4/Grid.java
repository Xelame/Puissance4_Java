package puissance4;

import java.util.ArrayList;

public class Grid {

    /**
     * Size of our grid : width
     */
    public int columnNumber;
    /**
     * Size of our grid : height
     */
    private int lineNumber;

    /**
     * Le contenu de la grille
     */
    public ArrayList<Colonne> content;

    private int numberOfPlayer;

    /**
     * 
     * @see LocalManager.choosePlayerNumber()
     */
    public Grid(int playerNumber) {
        this.numberOfPlayer = playerNumber;
        columnNumber = playerNumber * 4;
        lineNumber = Math.round(playerNumber * 3.2f);
        createGrid();
    }

    /**
     * Méthode pour afficher notre joli plateau de jeu dans ce sens :
     * <img src="https://i.ibb.co/JB4RH0s/Grapihque.png"/>
     */
    public String toString() {
        String affichage = "\n";

        // Body
        for (int indexLigne = lineNumber - 1; indexLigne >= 0; indexLigne--) {

            // Line
            affichage += "#";
            String ligne = "";
            for (int indexColonne = 0; indexColonne < columnNumber; indexColonne++) {
                Colonne colonne = content.get(indexColonne);
                ligne += colonne.getArray()[indexLigne];
            }
            affichage += ligne;
            affichage += "#\n";

        }

        // Floor
        affichage += "#".repeat(columnNumber + 2) + "\n";
        affichage += " " + GameManager.ALPHABET_MINUSCULE.substring(0, columnNumber) + "\n";

        return affichage;
    }

    /**
     * Méthode qui ajoute la pièce du joueur sur la colonne selectionner
     * 
     * @param nomColonne a String correspond to the column choosed by user
     * @param turnNumber ... on te voit souvent toi
     */
    public void fillColumn(String nomColonne) {
        int column = -1;
        for (int index = 0; index < GameManager.ALPHABET_MINUSCULE.length(); index++) {
            if (GameManager.ALPHABET_MINUSCULE.split("")[index].equals(nomColonne.trim().toLowerCase())) {
                column = index;
            }
            
        }

        System.out.println(column);
        if (content.get(column).isFull()) {
            System.out.println("La colonne est déjà  complète !!");
            fillColumn(GameManager.chooseColumn(this, numberOfPlayer));
        } else {
            content.get(column).fill(GameManager.getPlayerLetter(numberOfPlayer));
        }
    }

    /**
     * Méthode qui test si la grille est pleine
     * 
     * @return Boolean response if the grid is full
     */
    public Boolean isFull() {
        for (Colonne column : content) {
            if (!column.isFull()) {
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
        content = new ArrayList<Colonne>();
            for (int i = 0; i < columnNumber; i++) {
                Colonne colonne = new Colonne(columnNumber);
                content.add(colonne);
            }
    }

    /**
     * 
     * @param playerLetter
     * @param inversed
     * @return
     */
    private Boolean diagonalWin(String playerLetter, Boolean inversed) {
        for (int colonne = inversed ? 3 : 0; colonne < (inversed ? columnNumber : columnNumber - 3); colonne++) {
            for (int ligne = inversed ? 3 : 0; ligne < (inversed ? lineNumber : lineNumber - 3); ligne++) {
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
            return content.get(colonne).getArray()[ligne].equals(playerLetter)
                    && content.get(colonne - 1).getArray()[ligne + 1].equals(playerLetter)
                    && content.get(colonne - 2).getArray()[ligne + 2].equals(playerLetter)
                    && content.get(colonne - 3).getArray()[ligne + 3].equals(playerLetter);
        }
        return content.get(colonne).getArray()[ligne].equals(playerLetter)
                && content.get(colonne + 1).getArray()[ligne + 1].equals(playerLetter)
                && content.get(colonne + 2).getArray()[ligne + 2].equals(playerLetter)
                && content.get(colonne + 3).getArray()[ligne + 3].equals(playerLetter);

    }

    /**
     * 
     * @param playerLetter
     * @return
     */
    private Boolean lineWin(String playerLetter) {
        for (int colonne = 0; colonne < columnNumber - 3; colonne++) {
            for (int ligne = 0; ligne < lineNumber; ligne++) {
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
        return content.get(colonne).getArray()[ligne].equals(playerLetter)
                && content.get(colonne + 1).getArray()[ligne].equals(playerLetter)
                && content.get(colonne + 2).getArray()[ligne].equals(playerLetter)
                && content.get(colonne + 3).getArray()[ligne].equals(playerLetter);
    }

    /**
     * 
     * @param playerLetter
     * @return
     */
    private Boolean columnWin(String playerLetter) {
        for (int colonne = 0; colonne < columnNumber; colonne++) {
            for (int ligne = 0; ligne < lineNumber - 3; ligne++) {
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
        return content.get(colonne).getArray()[ligne].equals(playerLetter)
                && content.get(colonne).getArray()[ligne + 1].equals(playerLetter)
                && content.get(colonne).getArray()[ligne + 2].equals(playerLetter)
                && content.get(colonne).getArray()[ligne + 3].equals(playerLetter);
    }

    public Boolean isRunning(String character) {
        return isFull()
                || diagonalWin(character, true)
                || diagonalWin(character, false)
                || lineWin(character)
                || columnWin(character);
    }

}
