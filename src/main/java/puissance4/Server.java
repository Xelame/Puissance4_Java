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

public class Server {

    Grid grille;

    int playerSize = GameManager.choosePlayerNumber();

    static ArrayList<SocketChannel> players = new ArrayList<SocketChannel>();

    public Server() {
        getMyIpAddress();
        Run();
    }

    public void Run() {
        grille = new Grid(playerSize);
        try {
            ServerSocketChannel serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress(4004));
            System.out.println("En attente de joueur ...");
            connection(players, serverSocket);
        } catch (IOException e) {
            System.err.println(e.toString());
        }
        // players = shufflePlayers(players);
        Boolean isRunning = true;
        Joueurs currentJ = RandomBegin(playerSize);
        Map<Joueurs, SocketChannel> dictionary = new HashMap<Joueurs, SocketChannel>();
        for (int i = 0; i < playerSize; i++) {
            Joueurs player;
            if (i == 0) {
                player = Joueurs.J1;
            } else if (i == 1) {
                player = Joueurs.J2;
            } else {
                player = Joueurs.J3;
            }
            dictionary.put(player, players.get(i));
        }

        while (isRunning) {
            if (grille.isRunning(GameManager.getPlayerLetter(playerSize))) {
                isRunning = !isRunning;
            }
            SocketChannel havePlayed = null;
            grille.toString();

            for (SocketChannel socketChannel : players) {
                if (socketChannel == dictionary.get(currentJ)) { // if it's the turn to the playerSocket
                    switch (currentJ) {// we see what Player is the client and send him a X, O, V
                        case J1:
                            broadcast("Your turn X", socketChannel);
                            break;
                        case J2:
                            broadcast("Your turn O", socketChannel);
                            break;
                        default:
                            broadcast("Your turn V", socketChannel);
                            break;
                    }
                } else {
                }
            }

            String Message = "";


            for (SocketChannel socketChannel : players) {
                broadcast(Message, socketChannel);
            }

            Next(playerSize, currentJ);

            if (Message != "") {
                GameManager.JouerLigne(grille, String.valueOf(Message.charAt(5)), String.valueOf(Message.charAt(8)));
            }
        }

    }

    private void connection(ArrayList<SocketChannel> list, ServerSocketChannel serverSocket) throws IOException {
        while (list.size() < playerSize) {
            SocketChannel clientSocket = serverSocket.accept();
            broadcast(Integer.toString(playerSize), clientSocket);
            list.add(clientSocket);
            System.out.println("Un client s'est connecté\nTotal : " + list.size() + " / " + playerSize);
        }
    }

    public void listen(SocketChannel clientSocket) throws IOException {
        ByteBuffer bytes = ByteBuffer.allocate(1024);
        int bytesRead = clientSocket.read(bytes);
        if (bytesRead <= 0) {
            clientSocket.close();
            return;
        }
        String message = new String(bytes.array());
        System.out.println(message);
    }

    public void broadcast(String message, SocketChannel clientSocket) {
        try {
            ByteBuffer bytes = ByteBuffer.wrap(message.getBytes());
            while (bytes.hasRemaining()) {
                clientSocket.write(bytes);
            }
        } catch (IOException e) {
            System.err.println(e.toString());
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

    public static Joueurs RandomBegin(int playerNb) {
        Random rnd = new Random();
        int random = rnd.nextInt(playerNb);
        switch (random) {
            case 0:
                return Joueurs.J1;
            case 1:
                return Joueurs.J2;
            default:
                return Joueurs.J3;
        }
    }

    public static Joueurs Next(int playerNb, Joueurs player) {
        switch (player) {
            case J1:
                return Joueurs.J2;
            case J2:
                if (playerNb == 3) {
                    return Joueurs.J3;
                }
                return Joueurs.J1;
            case J3:
                return Joueurs.J1;
        }
        return Next(playerNb, player);
    }

}
