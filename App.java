import java.util.ArrayList;
import java.util.List;

public class App {


    public static ArrayList<ArrayList<String>> set = new ArrayList<ArrayList<String>>();
    public static int sizeC = 8;
    public static int sizeL = 6;
    public static int sizeSet = sizeC*sizeL;

    public static void main(String[] args) {


        //start for  2 players  8x6 = 48
        
        for (int i = 0; i< sizeC ;i++ ){
            ArrayList<String> colonne = new ArrayList<String>();
        
            for (int j=0; j < sizeL; j++){
                colonne.add(" ");
            }
            set.add(colonne);
        
        }


        //display
        for(int k = 0; k < sizeL; k++){
            System.out.print("#");

            //TODO : verif que l'on ajoute au bonne endroit
            for (int l =0; l< sizeC; l++){
                System.out.print(set.get(l).get(k));
            }
            System.out.print("#\n");

        }

        System.out.print("##########");
        
   


   

    



    }



}