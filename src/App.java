import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) throws Exception {
        Grille.getInstance();
    }

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

    public static int promptForInt(String prompt) {
        String response = promptForString(prompt);
        try {
            return Integer.parseInt(response);
        } catch (NumberFormatException e) {
            System.err.println("Please input a valid number.");
            return promptForInt(prompt);
        }
    }
}
