package puissance4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

    public static void main(String[] args) {
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
                System.out.println("Veuillez choisir une entrée valide :)");
                chooseCommunication();
                break;
        }
    }

    private static void chooseTypeOfCommunication() {
        int choice = App.promptForInt("Choississez le type de connection :\n1 - Hôte de partie\n2 - Rejoindre une partie");
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
