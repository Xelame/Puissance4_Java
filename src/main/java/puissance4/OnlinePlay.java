package puissance4;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class OnlinePlay implements Runnable {

    /**
     * La liste de lettre pour les possibles joueurs
     */
    private final String[] LISTE_DE_JOUEUR = { "O", "X", "V" };

    private final int NOMBRE_DE_JOUEUR;

    private int currentTurn;

    private Grille grille;

    private ServerSocketChannel serverSocket;

    static ArrayList<SocketChannel> connexions = new ArrayList<SocketChannel>();

    public OnlinePlay() {
        NOMBRE_DE_JOUEUR = choosePlayerNumber();
        currentTurn = RandomBegin();
    }

    @Override
    public void run() {
        getMyIpAddress();
        launch();
    }

    /**
     * Méthode qui donne la lettre que joue un joueur en fonction du tour de jeu
     * 
     * @return a Letter corresponding to the player
     */
    private String getPlayerLetter() {
        return LISTE_DE_JOUEUR[currentTurn % NOMBRE_DE_JOUEUR];
    }

    public void launch() {
        // Hop la grille
        grille = Grille.getInstance(NOMBRE_DE_JOUEUR);

        // Phase de connexion
        try {
            serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress(4004));

            connection(serverSocket);
        } catch (IOException e) {
            System.err.println("Problème durant les connexions");
        }

        // Phase d'initialisation
        Boolean isRunning = true;
        Map<String, SocketChannel> dictionary = new HashMap<String, SocketChannel>();
        for (int i = 0; i < NOMBRE_DE_JOUEUR; i++) {
            dictionary.put(LISTE_DE_JOUEUR[i], connexions.get(i));
        }

        // Phase de jeu
        while (isRunning) {

            SocketChannel currentPlayer = null;

            for (SocketChannel socketChannel : connexions) {
                if (socketChannel == dictionary.get(getPlayerLetter())) { // if it's the turn to the playerSocket
                    try {
                        writeToSocket("Your Turn " + getPlayerLetter(), socketChannel);
                        currentPlayer = socketChannel;
                    } catch (IOException e) {
                        System.err.println("L'envoi du message d'indication a échoué");
                    }
                }
            }

            try {
                String[] response = listen(currentPlayer).split(" ");
                broadcast(String.join(" ", response));

                if (grille.isEnd(getPlayerLetter())) {
                    isRunning = !isRunning;
                }

                currentTurn++;
            } catch (IOException e) {
                System.err.println("Protocole incorrect");
            }
        }
    }

    private void connection(ServerSocketChannel serverSocket) throws IOException {
        while (connexions.size() < NOMBRE_DE_JOUEUR) {
            SocketChannel clientSocket = serverSocket.accept();
            connexions.add(clientSocket);
            writeToSocket("Players " + Integer.toString(NOMBRE_DE_JOUEUR), clientSocket);
            broadcast("[SERVEUR] Un client s'est connecté\n[SERVEUR] Total : " + connexions.size() + " / " + NOMBRE_DE_JOUEUR);
        }
    }

    public String listen(SocketChannel clientSocket) throws IOException {
        ByteBuffer bytes = ByteBuffer.allocate(1024);
        int bytesRead = clientSocket.read(bytes);
        if (bytesRead <= 0) {
            clientSocket.close();
            return "";
        }
        return new String(bytes.array());
    }

    public void broadcast(String message) {
        try {

            for (SocketChannel socketChannel : connexions) {
                writeToSocket(message, socketChannel);
            }

        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    public void writeToSocket(String message, SocketChannel socket) throws IOException {
        ByteBuffer bytes = ByteBuffer.wrap(message.getBytes());
        while (bytes.hasRemaining()) {
            socket.write(bytes);
        }
        try {
            // On attend sinon too fast/h
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Qui peut me stopper !");
        }
    }

    public void getMyIpAddress() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            String ipAddress = ip.getHostAddress();
            System.out.println("Les joueurs peuvent se connectés à cette adresse : " + ipAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    private int RandomBegin() {
        Random rnd = new Random();
        int random = rnd.nextInt(NOMBRE_DE_JOUEUR);
        return random;
    }

    /**
     * Ask the user how many players are there
     * 
     * @return Number of player choosen
     */
    private int choosePlayerNumber() {
        int players = App.promptForInt("Veuillez entrer le nombre de joueurs (2 ou 3)");
        if (players != 2 && players != 3) {
            System.err.println("Please input a valid number");
            choosePlayerNumber();
        }
        return players;
    }


}
