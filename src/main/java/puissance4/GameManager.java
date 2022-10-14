package main.java.puissance4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameManager {

    static int numberOfPlayer;

    public static void main(String[] args) throws Exception {
        chooseCommunication();
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

    public static void localPlay() {
        choosePlayerNumber();
        Grille grille = Grille.getInstance();
        int turnNumber = 0;
        Boolean isRunning = true;
       
        while (isRunning) {
            String laLettreQueNousDonneLeJoueur = grille.chooseColumn(turnNumber);
            grille.fillColumn(laLettreQueNousDonneLeJoueur, turnNumber);
            if (grille.isRunning(grille.getPlayerLetter(turnNumber))) {
                isRunning = !isRunning;
            }
            turnNumber++;
        }

        //TODO : savoir qui win !
        //TODO : tel player win
        System.out.println("\n" + textColor.ANSI_YELLOW +"  win !! "+textColor.ANSI_RESET);    

    }

    public static void chooseCommunication() {

        System.out.println("\n" + "Welcome in Puissance 4 - Multiplayer," + textColor.ANSI_YELLOW +"  make a choice : "+textColor.ANSI_RESET);       
        int choice = promptForInt(textColor.ANSI_YELLOW+"1"+textColor.ANSI_RESET +" - Local\n"+textColor.ANSI_YELLOW+"2"+textColor.ANSI_RESET +" - Reseau");
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

    public static void chooseTypeOfCommunication() {
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
    public static void choosePlayerNumber() {
        numberOfPlayer = promptForInt("Veuillez entrer le nombre de joueurs ("+textColor.ANSI_YELLOW +"2"+textColor.ANSI_RESET+" ou "+textColor.ANSI_YELLOW +"3"+textColor.ANSI_RESET+")");
        if (numberOfPlayer != 2 && numberOfPlayer != 3) {
            System.err.println("Please input a valid number");
            choosePlayerNumber();
        }
    }

    // TODO : La Liaison entre les joueurs en réseau

}
