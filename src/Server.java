import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class Server {
<<<<<<< HEAD
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
=======
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
>>>>>>> cb6fe0a7870ab1300f48251e51f2663fe9acb4f3
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

<<<<<<< HEAD
    public static int getNbClient(){
        return nbClient;
=======
    public void getMyIpAddress() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            String ipAddress = ip.getHostAddress();
            System.out.println("Les joueurs peuvent se connectés à cette adresse : " + ipAddress);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
>>>>>>> cb6fe0a7870ab1300f48251e51f2663fe9acb4f3
    }
}
