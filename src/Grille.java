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

        System.out.print("##########");
    }
}
