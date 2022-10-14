import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class Server {
    public Server() {
        getMyIpAddress();
        Run();
    }

    public void Run() {
        GameManager.choosePlayerNumber();
        Grille grille = Grille.getInstance();
        try {
            int numberOfClient = 0;
            ArrayList<SocketChannel> list = new ArrayList<SocketChannel>();
            ServerSocketChannel serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress(4004));
            System.out.println("En attente de joueur ...");
            while (numberOfClient < GameManager.numberOfPlayer) {
                numberOfClient++;
                SocketChannel clientSocket = serverSocket.accept();
                System.out.println("Un client s'est connecté\nTotal : " + numberOfClient + " / " + GameManager.numberOfPlayer);
                list.add(clientSocket);
            }
        } catch (IOException e) {
            System.err.println(e.toString());
        }
    }

    public void Listen(SocketChannel clientSocket) throws IOException {
        ByteBuffer bytes = ByteBuffer.allocate(1024);
        int bytesRead = clientSocket.read(bytes);
        if (bytesRead <= 0) {
            clientSocket.close();
            return;
        }
        String message = new String(bytes.array());
        System.out.println(message);
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
}
