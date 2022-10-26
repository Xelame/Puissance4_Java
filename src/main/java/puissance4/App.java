package puissance4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

    public static void main(String[] args) {
        chooseCommunication();
    }

    /**
     * Méthode qui permet d'échanger avec l'utilisateur et avoir une chaines de caractères en retour 
     * @return Chaine de caractères saisie par l'utilisateur
     */
    public static String promptForString(String prompt) {
        System.out.println(prompt);
        System.out.print("> ");
        InputStreamReader bts = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(bts);
        try {
            return br.readLine();
        } catch (IOException e) {
            System.err.println("Something went wrong : " + e.getMessage());
            System.err.println(textColor.ANSI_RED+"Please try again."+textColor.RESET);
            return promptForString(prompt);
        }
    }

    /** Méthode qui permet d'échanger avec l'utilisateur et avoir un entier en retour 
     * @return L'entier entré par l'utilisateur
     */
    public static int promptForInt(String prompt) {
        String response = promptForString(prompt);
        try {
            return Integer.parseInt(response);
        } catch (NumberFormatException e) {

            return promptForInt(prompt);
        }
    }

    /**
     * Méthode qui permet de choisir le mode de communication entre les joueurs
     * @see LocalPlay
     * @see App#chooseTypeOfCommunication()
     */
    private static void chooseCommunication() {
        int choice = promptForInt("Puissance 4 - Multiplayer \n1 - Local\n2 - Reseau");
        switch (choice) {
            case 1:
                new LocalPlay();
                break;
            case 2:
                chooseTypeOfCommunication();
                break;
            default:
                System.out.println(textColor.ANSI_RED+"Veuillez choisir une entrée valide :)"+textColor.RESET );
                chooseCommunication();
                break;
        }
    }

    /**
     * Méthode qui permet de choisir entre le mode serveur et le mode client
     * @see OnlinePlay
     * @see Client
     */
    private static void chooseTypeOfCommunication() {
        int choice = App.promptForInt(textColor.ANSI_YELLOW+ "Choississez le type de connection :\n1 - Hôte de partie\n2 - Rejoindre une partie"+textColor.RESET );
        switch (choice) {
            case 1:
                Thread serveur = new Thread(new OnlinePlay());
                serveur.start();
                new Client(true);
                serveur.interrupt();
                break;
            case 2:
                new Client(false);
                break;
            default:
                chooseCommunication();
                break;
        }
    }
}
