package puissance4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {

    private SocketChannel clientSocket;
    private int NOMBRE_DE_JOUEUR ;
    private Grille grille;

    public Client() {
        try {
            // Phase de connexion
            connectToServer();

            // Etape 1 - 1ere reception : taille de la grille
            
            // Message de type : "Players X" où X est le nombre a récupéré ^^
            String aled = listen(clientSocket);
            System.out.println(aled);
            NOMBRE_DE_JOUEUR = Integer.parseInt(aled.split(" ")[1].trim());

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
            System.out.println(String.join(" ", turncall));
            if (turncall[0].equals("Your")) {
                String character = String.valueOf(turncall[2]);
                // Réponse de type : Turn X 0 où X est le joueur et 0 l'index
                write("Turn " + character + " " + grille.chooseColumn(character), clientSocket);
            }
            if (turncall[0].equals("Turn")) {
                System.out.println("Hello");
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
            }
        }
    }

    public void connectToServer() {
        String choice = App.promptForString("Entrer l'IP du serveur voulu :");
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
            connectToServer();
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
