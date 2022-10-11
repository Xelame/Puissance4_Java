import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {
    public static void main(String[] args){
        try{
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(8000));
        while(true){
            SocketChannel clientSocket = serverSocket.accept();
            System.out.println(" un client s'est connecté");
            Listen(clientSocket);
            clientSocket.close();
        }
        }
        catch(IOException e){
            System.err.println(e.toString());
        }
    }

    static void Listen(SocketChannel clientSocket) throws IOException{
        ByteBuffer bytes = ByteBuffer.allocate(1024);
        int bytesRead = clientSocket.read(bytes);
        if(bytesRead <= 0){
            clientSocket.close();
            return;
        }
        String message = new String(bytes.array(), "UTF-8");
        System.out.println(message);
    }
}
