package puissance4;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {

    public SocketChannel clientSocket;
    private Grid grille;
    private int numberOfPlayer;

    public Client() {
        try {
            clientSocket = SocketChannel.open();
            connectToServer();

            numberOfPlayer = Integer.parseInt(String.valueOf(listen(clientSocket).charAt(0)));
            grille = new Grid(numberOfPlayer);

            Boolean isRunning = true;
            String turncall = listen(clientSocket);
            while (isRunning) {
                if (grille.isRunning(GameManager.getPlayerLetter(numberOfPlayer))) {
                    isRunning = !isRunning;
                }
                if (turncall.charAt(0) == 'Y') {
                    String response = GameManager.chooseColumn(grille, numberOfPlayer);
                    write(response, clientSocket);
                }
                String adversaire = listen(clientSocket);
                GameManager.JouerLigne(grille.content, turncall, adversaire);
                grille.toString();
                GameManager.turn++;
            }
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    public void connectToServer() {
        String choice = GameManager.promptForString("Entrer l'IP du serveur voulu :");
        try {
            clientSocket.connect(new InetSocketAddress(choice, 4004));
            System.out.println("Vous êtes connectée !");
        } catch (IOException e) {
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
