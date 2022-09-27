import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Arrays;

public class App {


    public static ArrayList<ArrayList<String>> game = new ArrayList<ArrayList<String>>();
   
    public static int sizeC = 8;
    public static int sizeL = 6;
    public static int sizeTab = sizeC*sizeL;

    public static void main(String[] args) {

        Grid.ChoosePlayerNumber();

        

      
        char letter = 'a';
        System.out.println( (int)letter+" = "+ letter);
        

        //display
       

     
        //game.get(4).set(1,"v");

      

     
    
        

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