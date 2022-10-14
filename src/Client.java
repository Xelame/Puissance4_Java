import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    public static void main(String[] args){
        try{
            SocketChannel clientSocket = SocketChannel.open();
            clientSocket.connect(new InetSocketAddress("localHost", 8000));
            /*String message = App.promptForString("choississez une lettre entre de a à g");
            ByteBuffer  bytes = ByteBuffer.wrap(message.getBytes("UTF-8"));
            while(bytes.hasRemaining()){
                clientSocket.write(bytes);
                clientSocket.close();

               
            }*/
            if(Server.nbClient<Server.typeOfgame){
                System.out.println("en attente d'autres joueurs... ("+Server.getNbClient()+"/"+Server.typeOfgame+")");
            }

            //TODO : wait for other client
        }
        catch(IOException e){
            System.err.println(e.toString());
        }
        //en boucle, on peut en attendre une suivante en attendant que quelqu'une d'autre se connecte : pour l'instant on se connecte et hop ça arrête le programme, faisons mieux !
    }
}

