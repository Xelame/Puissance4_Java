import java.util.ArrayList;

import javax.sound.sampled.Line;

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
    private int nombreDeColonne;
    /**
     * Size of our grid : height
     */
    private int nombreDeLigne;

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
    private ArrayList<ArrayList<String>> contenu;

    /**
     * Constructeur privée ( toi même tu sais ;) )
     */
    private Grille() {
        nombreDeJoueur = choosePlayerNumber();
        System.out.println(toString());

        // TODO : Trouver des éléments permetant d'utiliser des classe
        // car notre projet ne suis pas vraiment le principe de POO :/ 💭

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
                ligne += contenu.get(indexColonne).get(indexLigne);
            }
            affichage += ligne;
            affichage += "#\n";

        }

        // Floor
        //affichage += "#".repeat(nombreDeColonne + 2) + "\n";
        affichage += " " + ALPHABET_MINUSCULE.substring(0, nombreDeColonne) + "\n";

        return affichage;
    }

    // TODO : La loop de jeu ✅
    public void Play() {
        int turnNumber = 0;
        Boolean isRunning = true;
        // TODO : Mettre la condition de fin ici
        while (isRunning) {
            // TODO : Faire l'interaction voulu :)
            String laLettreQueNousDonneLeJoueur = chooseColumn(turnNumber);
            setColumn(laLettreQueNousDonneLeJoueur, turnNumber);
            if (isFull()
                    || diagonalWin(getPlayerLetter(turnNumber), true)
                    || diagonalWin(getPlayerLetter(turnNumber), false)
                    || lineWin(getPlayerLetter(turnNumber))
                    || columnWin(getPlayerLetter(turnNumber))) {
                isRunning = !isRunning;
            }
            turnNumber++;
        }
    }

    /**
     * Méthode qui ajoute la pièce du joueur sur la colonne selectionner
     * @param nomColonne a String correspond to the column choosed by user 
     * @param turnNumber ... on te voit souvent toi
     */
    public void setColumn(String nomColonne, int turnNumber) {

        // ! Bien vu, hélas méthode déprécié malgré les comms (＞﹏＜)
        // en ascii a = 97 et z = 122 donc on va de a à j c'est à dire de 97 à 106
        // si le caractère correspond à a on rempli la premiere colonne donc on fait
        // letter - 97 car on commence à 0

        // Ici on donne la position de la lettre dans l'alphabet (où a = 0, b = 1,
        // etc...)
        int column = ALPHABET_MINUSCULE.indexOf(nomColonne.toLowerCase());

        if (columnIsNotFull(column)) {

            contenu.get(column).set(getTheLine(column, 0), getPlayerLetter(turnNumber));

        } else {
            System.out.println("La colonne est déjà  complète !!");
            setColumn(chooseColumn(turnNumber), turnNumber);
        }
    }

    /**
     * Méthode récursive pour poser la lettre au sommet de la colonne
     * @param column Index of the current column
     * @param line Index which increment in order to obtain top
     * @return Index of at the top of the column 
     */
    private int getTheLine(int column, int line) {

        if (contenu.get(column).get(line) != " ") {
            return getTheLine(column, line + 1);
        } else {
            return line;
        }
    }

    /**
     * Méthode qui test si la grille est pleine
     * @return Boolean response if the grid is full
     */
    public Boolean isFull() {
        for (int column = 0; column < contenu.size(); column++) {
            if (columnIsNotFull(column)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Ask the user how many players are there
     * @return Number of player choosen
     */
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

    /**
     * Fill the grid of empty slots in terms of number of players
     * @param numberOfPlayer
     */
    private void getSizeGrid(int numberOfPlayer) {
        contenu = new ArrayList<>();
        for (int i = 0; i < nombreDeColonne; i++) {
            ArrayList<String> colonne = new ArrayList<String>();
            for (int j = 0; j < nombreDeLigne; j++) {
                colonne.add(" ");
            }
            contenu.add(colonne);
        }
    }

    /**
     * Méthode qui donne la lettre que joue un joueur en fonction du tour de jeu
     * @param turn
     * @return a Letter corresponding to the player
     */
    private String getPlayerLetter(int turn) {
        return LISTE_DE_JOUEUR[turn % nombreDeJoueur];
    }

    /**
     * Ask the current player which column he choose
     * @param turn
     * @return a Letter corresponding to the column choosen
     */
    private String chooseColumn(int turn) {
        String choice = App
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
     * Méthode pour savoir si une colonne n'est pas pleine ( et prêt a l'emploi ;) )
     * @param column
     * @return
     */
    private boolean columnIsNotFull(int column) {
        return contenu.get(column).contains(" ");
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
            return contenu.get(colonne).get(ligne).equals(playerLetter)
                    && contenu.get(colonne - 1).get(ligne + 1).equals(playerLetter)
                    && contenu.get(colonne - 2).get(ligne + 2).equals(playerLetter)
                    && contenu.get(colonne - 3).get(ligne + 3).equals(playerLetter);
        }
        return contenu.get(colonne).get(ligne).equals(playerLetter)
                && contenu.get(colonne + 1).get(ligne + 1).equals(playerLetter)
                && contenu.get(colonne + 2).get(ligne + 2).equals(playerLetter)
                && contenu.get(colonne + 3).get(ligne + 3).equals(playerLetter);

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
        return contenu.get(colonne).get(ligne).equals(playerLetter)
                && contenu.get(colonne + 1).get(ligne).equals(playerLetter)
                && contenu.get(colonne + 2).get(ligne).equals(playerLetter)
                && contenu.get(colonne + 3).get(ligne).equals(playerLetter);
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
        return contenu.get(colonne).get(ligne).equals(playerLetter)
                && contenu.get(colonne).get(ligne + 1).equals(playerLetter)
                && contenu.get(colonne).get(ligne + 2).equals(playerLetter)
                && contenu.get(colonne).get(ligne + 3).equals(playerLetter);
    }
}
