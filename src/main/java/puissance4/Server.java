package puissance4;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
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
            while(isRunning) {
                if (grille.isRunning(GameManager.getPlayerLetter(playerSize))) {
                    isRunning = !isRunning;
                }
                SocketChannel havePlayed = null;
                for (int index = 0; index < players.size() ; index++) {
                    SocketChannel player = players.get(index);
                    if (GameManager.turn % playerSize == index) {
                        havePlayed = player;
                        broadcast("Your turn " + GameManager.getPlayerLetter(playerSize), player);
                    } else {
                        broadcast("Turn", player);
                    }
                }
                String Message = "";
                try {
                    Message = Client.listen(havePlayed);
                } catch (IOException e) {
                    System.err.println("Player disconnected");
                }

                for (SocketChannel socketChannel : players) {
                    broadcast(Message, socketChannel);
                }

                GameManager.turn++;

                if (Message != "") {

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

    private ArrayList<SocketChannel> shufflePlayers(ArrayList<SocketChannel> array) {
        Random rand = new Random();
		for (int i = 0; i < array.size(); i++) {
			int randomIndexToSwap = rand.nextInt(array.size());
			SocketChannel temp = array.get(randomIndexToSwap);
			array.set(randomIndexToSwap, array.get(i));
			array.set(i, temp);
		}
        return array;
    }
}
