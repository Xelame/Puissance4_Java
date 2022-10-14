import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.channels.UnsupportedAddressTypeException;

public class Client {

    SocketChannel clientSocket;

    public Client() {
        try {
            clientSocket = SocketChannel.open();
            connectToServer();
            String message = GameManager.promptForString("choississez une lettre entre de a à g");
            ByteBuffer bytes = ByteBuffer.wrap(message.getBytes("UTF-8"));
            while (bytes.hasRemaining()) {
                clientSocket.write(bytes);
                clientSocket.close();
            }
        } catch (IOException e) {
            System.err.println(e.toString());
        }
        // en boucle, on peut en attendre une suivante en attendant que quelqu'une
        // d'autre se connecte : pour l'instant on se connecte et hop ça arrête le
        // programme, faisons mieux !
    }

    public void connectToServer() {
        String choice = GameManager.promptForString("Entrer l'IP du serveur voulu :");
        try {
            clientSocket.connect(new InetSocketAddress(choice, 4004));
        } catch(IOException e) {
            System.out.println("L'adresse entrée est invalide");
            connectToServer();
        }
    }
}
