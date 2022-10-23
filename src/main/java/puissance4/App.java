package main.java.puissance4;

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
            System.err.println(textColor.ANSI_RED+"Please try again."+textColor.RESET);
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
                System.out.println(textColor.ANSI_RED+"Veuillez choisir une entrée valide :)"+textColor.RESET );
                chooseCommunication();
                break;
        }
    }

    private static void chooseTypeOfCommunication() {
        int choice = App.promptForInt(textColor.ANSI_YELLOW+ "Choississez le type de connection :\n1 - Hôte de partie\n2 - Rejoindre une partie"+textColor.RESET );
        switch (choice) {
            case 1:
                new OnlinePlay();
                break;
            case 2:
                new Client();
                break;
            default:
                chooseCommunication();
                break;
        }
    }
}
