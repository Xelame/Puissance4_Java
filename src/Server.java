import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Server {
    public static int  nbClient =0;
    public static int  typeOfgame =2;
    public static SocketChannel clientSocket;
    public static ArrayList<SocketChannel> clientSocketList = new ArrayList<SocketChannel>(); //list de client /!\ on peut faire un clientSocket.getLocalAddress() !!

    public static void main(String[] args){
   
        try{
        ServerSocketChannel serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress(8000));
        while(true){
           
            while(nbClient<typeOfgame){
            //TODO : accepter 2 ou 3 client mais pas plus

            // TODO :  verif que en reseau il n'y ait pas deux clients du même ordi??
            nbClient = clientSocketList.size();
            System.out.println("nbclient = "+ nbClient);
            SocketChannel clientSocket = serverSocket.accept();
            System.out.println(" un client s'est connecté = " +clientSocket+" | END adress : "+ clientSocket.getLocalAddress());
            clientSocketList.add(clientSocket);

           
            //Listen(clientSocket);
            //clientSocket.close();
            }
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

    public static int getNbClient(){
        return nbClient;
    }
}
