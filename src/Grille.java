import java.util.ArrayList;

public class Grille {

    // L'instance (La seule l'unique) d'UNE grille ^^
    private static Grille instance;

    // Sa taille jusque la vous suivé :)
    private int nombreDeColonne;
    private int nombreDeLigne;

    // La liste de lettre pour les possible joueur (c'est pas très compréhensible my fault ^^')
    private static final String[] LISTE_DE_JOUEUR = { "O", "X", "V" };

    // Des Constantes d'alphabet pour une lecture plus lisible de notre code vous verrez 🦚
    private final String ALPHABET_MINUSCULE = "abcdefghijklmnopqrstuvwxyz";
    private final String ALPHABET_MAJUSCULE = ALPHABET_MINUSCULE.toUpperCase();

    // Le nombre de joueurs qui vont jouer
    private static int nombreDeJoueur;

    // Le contenu de la grille 
    private static ArrayList<ArrayList<String>> contenu;
    
    //info : les joueurs vont soit de 0 à 1 (2j) ou de 0 à 2 (3j)
    private static int numeroPLayer =0;
    private static int column =0;

    // Constructeur privée ( toi même tu sais ;) )
    private Grille() {
        nombreDeJoueur = choosePlayerNumber();
        System.out.println(toString());

        // TODO : Trouver des éléments permetant d'utiliser des classe
        // car notre projet ne suis pas vraiment le principe de POO :/ 💭

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
        // TODO : Demander au l'utilisateur combien sont-ils ? ✅
        int players = App.promptForInt("Veuillez entrer le nombre de joueurs (2 ou 3)");
        if (2 <= players && players <= 3) {
            nombreDeColonne = players * 4;
            nombreDeLigne = Math.round(players * 3.2f);
            contenu = getSizeGrid(players);
        } else {
            System.err.println("Please input a valid number");
            choosePlayerNumber();
        }

        return players;
    }

    private ArrayList<ArrayList<String>> getSizeGrid(int numberOfPlayer) {
        // TODO : Initialise la taille du Tableau en fonction du nombre de joueur ✅
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
    private String getPlayerLetter(int turn) {
        return LISTE_DE_JOUEUR[turn % nombreDeJoueur];
    }

    // Demande au Joueur concerné qu'elle coup il joue
    private String chooseColumn(int turn) {
        String choice = App.promptForString("Joueur " + getPlayerLetter(turn) + " choisissez une colonne :\n" + toString());
        if (ALPHABET_MINUSCULE.substring(0, nombreDeColonne).contains(choice)
                || ALPHABET_MAJUSCULE.substring(0, nombreDeColonne).contains(choice)) {
            return choice;
        } else {
            System.err.println("Choisissez un emplacement valide (avec la lettre correspondante ");
            return chooseColumn(turn);
        }
    }

    public boolean checkPlayerWin(String symbol){
        // check if there 4 same symbols in each column.
        int nbSymbolPerLine = 0; 
        for (int i = 0; i < nombreDeColonne-1; i++){
            for (int j = 0; j < nombreDeLigne-1; j++){
                if (contenu.get(i).get(j) == symbol){
                    nbSymbolPerLine +=1;
                    if (nbSymbolPerLine == 4){
                        return true;
                    }
                } else {
                    nbSymbolPerLine = 0;
                }
            }
            nbSymbolPerLine = 0;
        }  

        //----------------------------

        int nbSymbolPerColumn = 0;
        for (int l = 0; l < nombreDeLigne-1; l++){
            for (int k = 0; k < nombreDeColonne-1; k++){
                if (contenu.get(k).get(l) == symbol){
                    nbSymbolPerColumn +=1;
                    if (nbSymbolPerColumn == 4){
                        return true;
                    }
                }
            }
            nbSymbolPerColumn = 0;
        }

        //check diagonale
        return false;
    }

    public boolean columnIsFree(){
                if(contenu.get(0).get(column) == " "){
                    return true;
                } 
                
                return false;
                
        

    }
 

    //info : si choix de la colonne (a,b,c,d,e,f,g,h,i,j) --> affichage column
   
    public void setColumn (String nomColonne, int turnNumber){
     
        // en ascii a = 97 et z = 122 donc on va de a à j c'est à dire de 97 à 106              
                //si le caractère correspond à a on rempli la premiere colonne donc on fait letter - 97 car on commence à 0
                char letter =  nomColonne.charAt(0);
                column = (int)letter-97;

                 if(columnIsFree()){

                    contenu.get(column).set(getTheLine(1),getPlayerLetter(turnNumber));

                } else {
                System.out.println("La colonne est déjà  complète  ");
                chooseColumn(numeroPLayer);
                toString();

                }
                  
          
        
       
    }

    public int getTheLine(int line){

        if((contenu.get(line).get(column) == " " ) && (line < nombreDeLigne)){
            return getTheLine(line+1);

        }else {
            return line-1;
        }

    }

    // TODO : La loop de jeu ✅
    public void Play() {
        int turnNumber = 0;
        // TODO : Mettre la condition de fin ici
        while (true) {
            // TODO : Faire l'interaction voulu :)
            String laLettreQueNousDonneLeJoueur = chooseColumn(turnNumber);
            System.out.println(laLettreQueNousDonneLeJoueur);
            setColumn(laLettreQueNousDonneLeJoueur, turnNumber);
            turnNumber++;
        }
    }

    

}
