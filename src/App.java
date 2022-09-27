import java.util.ArrayList;
import java.util.List;

public class App {


    public static List<ArrayList<String>> game = new ArrayList<ArrayList<String>>();
   
    public static int sizeC = 8;
    public static int sizeL = 6;
    public static int sizeTab = sizeC*sizeL;

    public static void main(String[] args) {

        System.out.println("  ");
        System.out.println("=============================");
        System.out.println("=         New Game          =");
        System.out.println("=============================");
        System.out.println("  ");


        //start for  2 players  8x6 = 48
        
        for (int i = 0; i< sizeC ;i++ ){
            ArrayList<String> colonne = new ArrayList<String>();
        
            for (int j=0; j < sizeL; j++){
                colonne.add(" ");
            }
            game.add(colonne);
        
        }


        //display
        Grille.ToString();

       
        ArrayList<String> tmpC = new ArrayList<>();
        //get the colonne to save 
        int NumC = 1;

        game.get(4).set(1,"v");

        game.get(1).set(4,"x");

        game.get(0).set(4,"O");

        Grille.ToString();
    
        
   


   

    



    }



}