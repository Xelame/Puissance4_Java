import java.util.ArrayList;

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
            if (this.p == 2){
                System.out.println("##########");
                System.out.println("abcdefgh");
            }
            if (this.p == 3){
                System.out.println("##############");
                System.out.println("a b c d e f g h");
            }
            System.out.println("  ");
            System.out.println("===============================");
            System.out.println("  ");
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
}


