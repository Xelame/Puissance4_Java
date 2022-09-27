import java.util.ArrayList;
import java.util.List;

public class App {


    public static List<ArrayList<String>> game = new ArrayList<ArrayList<String>>();
   
    public static int sizeC = 8;
    public static int sizeL = 6;
    public static int sizeTab = sizeC*sizeL;

    public static void main(String[] args) {

        


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
    
        for (int m=0; m<8;m++){
            tmpC.set(m, game.get(m).get(NumC));       
        }

        game.set(NumC,tmpC);
        
   


   

    



    }



}