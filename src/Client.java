import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.InetSocketAddress;
<<<<<<< HEAD
import java.net.ServerSocket;
import java.util.ArrayList;
=======
>>>>>>> cb6fe0a7870ab1300f48251e51f2663fe9acb4f3
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.channels.UnsupportedAddressTypeException;

public class Client {
<<<<<<< HEAD
    public static void main(String[] args){
        try{
            SocketChannel clientSocket = SocketChannel.open();
            clientSocket.connect(new InetSocketAddress("localHost", 8000));
            /*String message = App.promptForString("choississez une lettre entre de a à g");
            ByteBuffer  bytes = ByteBuffer.wrap(message.getBytes("UTF-8"));
            while(bytes.hasRemaining()){
=======

    SocketChannel clientSocket;

    public Client() {
        try {
            clientSocket = SocketChannel.open();
            connectToServer();
            String message = GameManager.promptForString("choississez une lettre entre de a à g");
            ByteBuffer bytes = ByteBuffer.wrap(message.getBytes("UTF-8"));
            while (bytes.hasRemaining()) {
>>>>>>> cb6fe0a7870ab1300f48251e51f2663fe9acb4f3
                clientSocket.write(bytes);
                clientSocket.close();

               
            }*/
            if(Server.nbClient<Server.typeOfgame){
                System.out.println("en attente d'autres joueurs... ("+Server.getNbClient()+"/"+Server.typeOfgame+")");
            }
<<<<<<< HEAD

            //TODO : wait for other client
        }
        catch(IOException e){
=======
        } catch (IOException e) {
>>>>>>> cb6fe0a7870ab1300f48251e51f2663fe9acb4f3
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
