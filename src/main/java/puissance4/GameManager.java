package puissance4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class GameManager {

    /**
     * Des Constantes d'alphabet pour une lecture plus lisible de notre code vous
     * verrez 🦚
     */
    public static final String ALPHABET_MINUSCULE = "abcdefghijklmnopqrstuvwxyz";

    /**
     * Des Constantes d'alphabet pour une lecture plus lisible de notre code vous
     * verrez 🦚
     */
    public static final String ALPHABET_MAJUSCULE = ALPHABET_MINUSCULE.toUpperCase();

    /**
     * La liste de lettre pour les possibles joueurs
     */
    private static final String[] LISTE_DE_JOUEUR = { "O", "X", "V" };

    public static int turn;

    public static void main(String[] args) throws Exception {
        GameManager manager = new GameManager();
        manager.chooseCommunication();
    }

    // Méthode pour Afficher proprement une question et récuperer une valeur de type
    // String :)
    public static String promptForString(String prompt) {
        System.out.println(prompt);
        System.out.print("> ");
        InputStreamReader bts = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(bts);
        try {
            return br.readLine();
        } catch (IOException e) {
            System.err.println("Something went wrong : " + e.getMessage());
            System.err.println("Please try again.");
            return promptForString(prompt);
        }
    }

    // Méthode pour Afficher proprement une question et récuperer une valeur de type
    // Integer :)
    public static int promptForInt(String prompt) {
        String response = promptForString(prompt);
        try {
            return Integer.parseInt(response);
        } catch (NumberFormatException e) {

            return promptForInt(prompt);
        }
    }

    public void localPlay() {
        int numberOfPlayer = choosePlayerNumber();
        Grid grille = new Grid(numberOfPlayer);
        Boolean isRunning = true;
        while (isRunning) {
            String laLettreQueNousDonneLeJoueur = chooseColumn(grille, numberOfPlayer);
            grille.fillColumn(laLettreQueNousDonneLeJoueur);
            if (grille.isRunning(getPlayerLetter(numberOfPlayer))) {
                isRunning = !isRunning;
                if (!grille.isFull()) {
                    System.out.println(grille.toString() + "\nJoueur " + getPlayerLetter(numberOfPlayer) + " a gagné !");
                } else {
                    System.out.println(grille.toString() + "\nÉgalité à la BigFlop et AuLit XD PTDR");
                }
            }
            turn++;
        } 
        
    }

    public void chooseCommunication() {
        int choice = promptForInt("Puissance 4 - Multiplayer \n1 - Local\n2 - Reseau");
        switch (choice) {
            case 1:
                localPlay();
                break;
            case 2:
                chooseTypeOfCommunication();
                break;
            default:
                System.out.println("Veuillez choisir une entrée valide :)");
                chooseCommunication();
                break;
        }
    }

    public void chooseTypeOfCommunication() {
        int choice = promptForInt("Choississez le type de connection :\n1 - Hôte de partie\n2 - Rejoindre une partie");
        switch (choice) {
            case 1:
                new Server();
                break;
            case 2:
                new Client();
                break;
            default:
                chooseCommunication();
                break;
        }
    }

    /**
     * Ask the user how many players are there
     * 
     * @return Number of player choosen
     */
    public static int choosePlayerNumber() {
        int entryOfPlayer = promptForInt("Veuillez entrer le nombre de joueurs (2 ou 3)");
        if (entryOfPlayer != 2 && entryOfPlayer != 3) {
            System.err.println("Please input a valid number");
            choosePlayerNumber();
        }
        return entryOfPlayer;
    }

    /**
     * Ask the current player which column he choose
     * 
     * @param turn
     * @return a Letter corresponding to the column choosen
     */
    static public String chooseColumn(Grid grille, int numberOfPlayer) {
        String choice = GameManager
                .promptForString("Joueur " + getPlayerLetter(numberOfPlayer) + " choisissez une colonne :\n" + grille.toString());
        if ((ALPHABET_MINUSCULE.substring(0, grille.columnNumber).contains(choice)
                || ALPHABET_MAJUSCULE.substring(0, grille.columnNumber).contains(choice))
                && choice.length() > 0) {
            return choice;
        } else {
            System.err.println("Choisissez un emplacement valide (avec la lettre correspondante) ");
            return chooseColumn(grille, numberOfPlayer);
        }
    }

    /**
     * Méthode qui donne la lettre que joue un joueur en fonction du tour de jeu
     * 
     * @param turn
     * @return a Letter corresponding to the player
     */
    public static String getPlayerLetter(int numberOfPlayer) {
        return LISTE_DE_JOUEUR[turn % numberOfPlayer];
    }

    public static void JouerLigne(Grid Grid,String player, String indexString ) {
        System.out.println("Hello : " + indexString);
        int index = "abcdefghijklm".indexOf(indexString.trim());
        Grid.content.get(index).fill(player);
    }
}
