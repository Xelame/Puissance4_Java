import java.util.ArrayList;

public class Grille {

    private static Grille instance;
    private int nombreDeColonne;
    private int nombreDeLigne;
    private static final String[] LISTE_DE_JOUEUR = { "O", "X", "V" };
    private final String ALPHABET_MINUSCULE = "abcdefghijklmnopqrstuvwxyz";
    private final String ALPHABET_MAJUSCULE = ALPHABET_MINUSCULE.toUpperCase();
    private static int nombreDeJoueur;
    private static ArrayList<ArrayList<String>> contenu;

    // Constructeur privée ( toi même tu sais ;) )
    private Grille() {
        nombreDeJoueur = choosePlayerNumber();
        System.out.println(toString());

        // TODO : Trouver des éléments permetant d'utiliser des classe
        // car notre projet ne suis pas vraiment le principe de POO :/

    }

    // Méthode qui créer une seule unique instance de notre classe
    public static Grille getInstance() {
        if (instance == null) {
            instance = new Grille();
        }
        return instance;
    }

    // Méthode pour afficher notre joli plateau de jeu
    public String toString() {
        String affichage = "\n";
        for (int indexLigne = 0; indexLigne < nombreDeLigne; indexLigne++) {

            affichage += "#";

            for (int indexColonne = 0; indexColonne < nombreDeColonne; indexColonne++) {
                affichage += contenu.get(indexColonne).get(indexLigne);
            }

            affichage += "#\n";

        }

        affichage += "#".repeat(nombreDeColonne + 2) + "\n";
        affichage += " " + ALPHABET_MINUSCULE.substring(0, nombreDeColonne) + "\n";

        return affichage;
    }

    private int choosePlayerNumber() {
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

    private ArrayList<ArrayList<String>> getSizeGrid(int numberOfPlayer) {
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
    private String getPlayer(int turn) {
        return LISTE_DE_JOUEUR[turn % nombreDeJoueur];
    }

    // Demande au Joueur concerné qu'elle coup il joue
    private String chooseColumn(int turn) {
        String choice = App.promptForString("Joueur " + getPlayer(turn) + " choisissez une colonne :\n" + toString());
        if (ALPHABET_MINUSCULE.substring(0, nombreDeColonne).contains(choice)
                || ALPHABET_MAJUSCULE.substring(0, nombreDeColonne).contains(choice)) {
            return choice;
        } else {
            System.err.println("Choisissez un emplacement valide (avec la lettre correspondante ");
            return chooseColumn(turn);
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

    public int getTheLine(ArrayList<ArrayList<String>> grid){


        //TODO : return value
      
        return 0;

    }

    public boolean columnIsFree(ArrayList<ArrayList<String>> grid){

    }

}
