package puissance4;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class LocalPlay {

    /**
     * La liste de lettre pour les possibles joueurs
     */
    private final String[] LISTE_DE_JOUEUR = { textColor.ANSI_CYAN+"O"+textColor.RESET,textColor.ANSI_PURPLE+ "X"+textColor.RESET,textColor. ANSI_GREEN+ "V"+textColor.RESET };

    private final int NOMBRE_DE_JOUEUR;

    private int currentTurn = 0;

    private Grille grille;

    public LocalPlay() {
        NOMBRE_DE_JOUEUR = choosePlayerNumber();
        run();
    }

    /**
     * Méthode qui donne la lettre que joue un joueur en fonction du tour de jeu
     * 
     * @return a Letter corresponding to the player
     */
    private String getPlayerLetter() {
        return LISTE_DE_JOUEUR[currentTurn % NOMBRE_DE_JOUEUR];
    }

    public void run() {
        grille = Grille.getInstance(NOMBRE_DE_JOUEUR);
        Boolean isRunning = true;
        String currentPlayerLetter = "";
        while (isRunning) {
            currentPlayerLetter = getPlayerLetter();
            int leChiffreQueNousDonneLeJoueur = grille.chooseColumn(currentPlayerLetter);
            while (!grille.fillColumn(leChiffreQueNousDonneLeJoueur, currentPlayerLetter)) {
                leChiffreQueNousDonneLeJoueur = grille.chooseColumn(currentPlayerLetter);
            }
            if (grille.isEnd(currentPlayerLetter)) {
                isRunning = !isRunning;
                if (!grille.isFull()) {
                    System.out
                            .println(grille.toString() + "\nJoueur " + currentPlayerLetter + " a gagné !");
                } else {
                    System.out.println(grille.toString() + "\nÉgalité à la BigFlop et AuLit XD PTDR");
                }
            }
            currentTurn++;
        }
    }

    /**
     * Ask the user how many players are there
     * 
     * @return Number of player choosen
     */
    private int choosePlayerNumber() {
        int players = App.promptForInt("Veuillez entrer le nombre de joueurs (2 ou 3)");
        if (players != 2 && players != 3) {
            System.err.println("Please input a valid number");
            choosePlayerNumber();
        }
        return players;
    }

    
}
