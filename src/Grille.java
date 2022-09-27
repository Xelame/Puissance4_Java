public class Grille {

    public static Grille instance;

    private Grille() {

    }

    public static getInstance() {
        if (instance = null) {
            instance = new Grille();
        }
        return instance;
    }

    public static void ToString() {
        // TODO : Afficher de la grille ou retourner sa valeur en type `String` ici :)
    }
}
