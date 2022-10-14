import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.channels.UnsupportedAddressTypeException;

public class Client {
    static SocketChannel  clientSocket = null;
    
    public static void main(String[] args){


        try{
             clientSocket = SocketChannel.open();
            clientSocket.connect(new InetSocketAddress("localHost", 8000));
             //TODO : wait for other client
             System.out.println("En attente de joueur la partie va bientôt démarrer ...");

             
             
               
            

           
        }
        catch(IOException e){
            System.err.println(e.toString());
        }
        // en boucle, on peut en attendre une suivante en attendant que quelqu'une
        // d'autre se connecte : pour l'instant on se connecte et hop ça arrête le
        // programme, faisons mieux !
    }

    public void connectToServer() {
        String choice = GameManager.promptForString("Entrer l'IP du serveur voulu :");
        try {
            clientSocket.connect(new InetSocketAddress(choice, 8000));
        } catch(IOException e) {
            System.out.println("L'adresse entrée est invalide");
            connectToServer();
        }
    }

 
}
