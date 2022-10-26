package main.java.puissance4;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.io.BufferedReader;

import java.io.InputStreamReader;

import javax.swing.text.StyledEditorKit.BoldAction;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
  
    
    //public static void main(String[] args){

        private SocketChannel clientSocket;
        private int NOMBRE_DE_JOUEUR ;
        private Grille grille;
    

    public Client(Boolean isLocal) {
        try {
            // Phase de connexion
            connectToServer(isLocal);

            // Etape 1 - 1ere reception : taille de la grille
            
            // Message de type : "Players X" où X est le nombre a récupéré ^^
            NOMBRE_DE_JOUEUR = Integer.parseInt(listen(clientSocket).split(" ")[1].trim());

            grille = Grille.getInstance(NOMBRE_DE_JOUEUR);

            System.out.println(grille.toString());

            run();
        } catch (IOException e) {
            System.err.println("Connexion perdu avec le serveur");
        }
    }

    private void run() throws IOException {
        Boolean isRunning = true;
        while (isRunning) {

            // Message de type : "Your turn X" où X est la lettre du joueur ^^
            String[] turncall = listen(clientSocket).split(" ");
            if (turncall[0].equals("Your")) {
                String character = String.valueOf(turncall[2]);
                int leChiffreQueNousDonneLeJoueur = grille.chooseColumn(character);

                while (grille.getColumn(leChiffreQueNousDonneLeJoueur).isFull()) {
                    System.out.println("La colonne est deja pleine !");
                    leChiffreQueNousDonneLeJoueur = grille.chooseColumn(character);
                }
                
                // Réponse de type : Turn X 0 où X est le joueur et 0 l'index
                write("Turn " + character + " " + leChiffreQueNousDonneLeJoueur, clientSocket);
            } else if (turncall[0].equals("Turn")) {
                grille.fillColumn(Integer.valueOf(turncall[2].trim()), String.valueOf(turncall[1].trim()));
                if (grille.isEnd(String.valueOf(turncall[1].trim()))) {
                    isRunning = !isRunning;
                    if (!grille.isFull()) {
                        System.out
                                .println(grille.toString() + "\nJoueur " + String.valueOf(turncall[1].trim()) + " a gagné !");
                    } else {
                        System.out.println(grille.toString() + "\nÉgalité à la BigFlop et AuLit XD PTDR");
                    }
                }
                System.out.println(grille.toString());
            } else {
                System.out.println(String.join(" ", turncall));
            }
        }
        // en boucle, on peut en attendre une suivante en attendant que quelqu'une
        // d'autre se connecte : pour l'instant on se connecte et hop ça arrête le
        // programme, faisons mieux !
    }

    public void connectToServer(Boolean isLocal) {
        String choice;
        if (isLocal) {
            choice = "localhost";
        } else {
            choice = App.promptForString("Entrer l'IP du serveur voulu :");
        }
        try {
            clientSocket = SocketChannel.open();
            clientSocket.connect(new InetSocketAddress(choice, 4004));
            System.out.println("Vous êtes connectée !");
        } catch (IOException e) {
            try {
                clientSocket.close();
            } catch (IOException e1) {
                System.err.println("Le Socket du client n'a pas pu être fermé");
            }
            System.out.println("L'adresse entrée est invalide");
            connectToServer(isLocal);
        }
    }

    public static String promptForString(){
        InputStreamReader bis = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(bis);
        try {
            return br.readLine();
        }
        catch(IOException e){
            System.err.println("Something went wrong : " + e.getMessage());
            System.err.println("Please retry : ");
            return promptForString();
        }
    }

    static public String listen(SocketChannel clientSocket) throws IOException {
        ByteBuffer bytes = ByteBuffer.allocate(1024);
        int bytesRead = clientSocket.read(bytes);
        if (bytesRead <= 0) {
            clientSocket.close();
            return "";
        }
        String message = new String(bytes.array());
        return message;
    }

    public void write(String message, SocketChannel socket) throws IOException {
        ByteBuffer bytes = ByteBuffer.wrap(message.getBytes());
        while (bytes.hasRemaining()) {
            socket.write(bytes);
        }
    }
}
