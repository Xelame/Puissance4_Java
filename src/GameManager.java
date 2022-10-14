public class GameManager {
    // TODO : Mettre ici la création d'une grille
    Grille grille = Grille.getInstance();

    public GameManager() {
    }
    
    // TODO : La loop de jeu ✅
    public void Play() {
        int turnNumber = 0;
        Boolean isRunning = true;
        // TODO : Mettre la condition de fin ici
        while (isRunning) {
            // TODO : Faire l'interaction voulu :)
            String laLettreQueNousDonneLeJoueur = grille.chooseColumn(turnNumber);
            grille.fillColumn(laLettreQueNousDonneLeJoueur, turnNumber);
            if (grille.isRunning(grille.getPlayerLetter(turnNumber))) {
                isRunning = !isRunning;
            }
            turnNumber++;
        }
    }

    public void chooseCommunication() {
        int choice = App.promptForInt("Puissance 4 - Multiplayer \n1 - Local\n2 - Reseau");
        switch (choice) {
            case 1:
                
                break;
            case 2:
                break;
            default:

                break;
        }
    }

    // TODO : La Liaison entre les joueurs en réseau


    
    
}
