import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
//import java.util.Arrays;

public class Grid {
        
    
        int l;
        int c;
        int p;
        
        Grid(int l, int c, int p){
            this.l = l;
            this.c = c;
            this.p = p;
        }
    
        public static void ChoosePlayerNumber(){
            int players = App.promptForInt("Veuillez entrer le nombre de joueurs ?");
                if (players == 2){
                    Grid MyGrid = new Grid(6,8,2);
                    MyGrid.displayGrid(MyGrid.initGrid());
                    
            }
                if (players == 3){
                    Grid MyGrid = new Grid(10, 12, 3);
                    MyGrid.displayGrid(MyGrid.initGrid());
                }
        }
        
        public void displayGrid(ArrayList<ArrayList<String>> grid){
                for(int k = 0; k < this.l; k++){
                    System.out.print("#");
        
                    for (int j =0; j< this.c; j++){
                        System.out.print(grid.get(j).get(k));
                    }
                    System.out.print("#\n");
        
                }
        
                System.out.println("##########");
            }
    
            public ArrayList<ArrayList<String>> initGrid(){
                ArrayList<ArrayList<String>> grid = new ArrayList<ArrayList<String>>();
                for (int i = 0; i< this.c ;i++ ){
                    ArrayList<String> arrcolumn = new ArrayList<String>();
                    for (int j=0; j < this.l; j++){
                        arrcolumn.add(" ");
                    }
                    grid.add(arrcolumn);
                }
                return grid;
            }
    

    
    //info : choix de la colonne (a,b,c,d,e,f,g,h,i,j)
    //TODO : mettre en static
    public void setColumn (char letter, ArrayList<ArrayList<String>> grid ){
        //this.p
        //this.L
        //this.C

        
        //2 joueur 

        // en ascii a = 97 et z = 122 donc on va de a à j c'est à dire de 97 à 106
        if(this.p ==2){
            //ascii (int)letter) , si letter = 'a' (int)letter donne 97 (voir tableau ascii)
            if(((int)letter)<97 || ((int)letter)>106){
                //TODO : dire à l'utilisateur qu'il y a une erreur
                //rappel de la fonction :
                setColumn(letter, grid);
            }else {
               
                //si le caractère correspond à a on rempli la premiere colonne donc on fait letter - 96
                int column = (int)letter-96;

                //TODO : get la ligne ou peut aller la piece
                //TODO : reccupérer l'objet qu'il faut mettre dans le tableau
                if(columnIsFree()){
                grid.get(column).set(getTheLine(grid),);
                } else {
                //TODO : si on retourne -1 envoyer un message 
                }
                  
            }
            //if(this.L )
            
        }
        
       
    }

    public int getTheLine(ArrayList<ArrayList<String>> grid){


        //TODO : return value
      
        return 0;

    }

    public boolean columnIsFree(ArrayList<ArrayList<String>> grid){

    }




}
