public class Grille {

    public static Grille instance;

    private Grille() {

    }

    public static Grille getInstance() {
        if (instance == null) {
            instance = new Grille();
        }
        return instance;
    }

    public static void ToString() {
        for(int k = 0; k < App.sizeL; k++){
            System.out.print("#");

            //TODO : verif que l'on ajoute au bonne endroit
            for (int l =0; l< App.sizeC; l++){
                System.out.print(App.game.get(l).get(k));
            }
            System.out.print("#\n");

        }

        System.out.println("##########");
        System.out.println("abcdefgh");
        System.out.println("  ");
        System.out.println("===============================");
        System.out.println("  ");
    }


    //info : choix de la colonne (a,b,c,d,e,f,g,h)
    public static void ChooseColumn (char column ){
        //this.P
        //this.L
        //this.C

        if(column >  )
    }
}
