import java.util.ArrayList;

public class Grille {

    public static Grille instance;
    public static int nombreDeColonne;
    public static int nombreDeLigne;
    public static ArrayList<String> listeDesJoueurs = new ArrayList<String>();
    private static int nombreDeJoueur;
    private static ArrayList<ArrayList<String>> contenu;

    private Grille() {
        nombreDeJoueur = choosePlayerNumber();
        // TODO : Réfléchir au système de tour et de joueur

        toString();
    }

    public static Grille getInstance() {
        if (instance == null) {
            instance = new Grille();
        }
        return instance;
    }

    public static void toString() {
        // TODO : Amélioration requise svp et return le tout en String :)
        for(int k = 0; k < nombreDeLigne; k++){
            System.out.print("#");

            for (int j =0; j < nombreDeColonne; j++){
                System.out.print(contenu.get(j).get(k));
            }

            System.out.println("#");

        }

        System.out.println("#".repeat(nombreDeColonne+2));
        System.out.println(" " + "abcdefghijklmnop".substring(0, nombreDeColonne) + " ");
    }

    public static int choosePlayerNumber() {
        // TODO : Demander au l'utilisateur combien sont-ils ?
        int players = App.promptForInt("Veuillez entrer le nombre de joueurs (2 ou 3)");
        if (2 <= players && players <= 3) {
            nombreDeColonne = players * 4;
            nombreDeLigne = Math.round(players * 3.3f);
            contenu = getSizeGrid(players);
        } else {
            System.err.println("Please input a valid number");
            choosePlayerNumber();
        }

        return players;
    }

    public static ArrayList<ArrayList<String>> getSizeGrid(int numberOfPlayer) {
        // TODO : Initialise la taille du Tableau en fonction du nombre de joueur
        ArrayList<ArrayList<String>> grid = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < nombreDeColonne; i++) {
            ArrayList<String> arrcolumn = new ArrayList<String>();
            for (int j = 0; j < nombreDeLigne; j++) {
                arrcolumn.add(" ");
            }
            grid.add(arrcolumn);
        }
        return grid;
    }

    // Fonction qui donne la lettre que joue un joueur en fonction du tour de jeu
    public static String getPlayer(int turn) {
        return listeDesJoueurs.get(turn % nombreDeJoueur);
    }

    public static String chooseColumn(int turn) {
        App.promptForString("Joueur " + getPlayer(turn) + " choisissez une colonne :\n");
    }
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
