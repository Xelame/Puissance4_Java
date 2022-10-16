package main.java.puissance4;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Server {

    public static int  nbClient =0;
    public static int  typeOfgame =2;
    public static SocketChannel clientSocket;
    public static  boolean loose = false;

    
   

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
            ArrayList<Client> listClient = new ArrayList<Client>();
            ServerSocketChannel serverSocket = ServerSocketChannel.open();
            serverSocket.bind(new InetSocketAddress(8000));
            System.out.println("En attente de joueur ...");
            while (numberOfClient < GameManager.numberOfPlayer) {
                numberOfClient++;
                Client client = new Client();
                SocketChannel clientSocket = serverSocket.accept();                
                System.out.println("Un client s'est connecté\nTotal : " + numberOfClient + " / " + GameManager.numberOfPlayer);
                list.add(clientSocket);
                listClient.add(client);
               
            }

            //TODO : envoi signal à chaque joueur --> la partie démarre
            while(!loose){            
            for (int i=0; i < list.size(); i++){
              list.get(i).open();

              

                
            //TODO : tant que loose == false, on fait tourner les joueurs
            
            //TODO : select player who play

            //TODO : demande d'écrire

            //TODO : reçoit lettre

            //TODO : actualise pour les autres joueurs (le notre est fait au préalable si donnée envoyé aux autres joueurs)

            //TODO : si pas victoire on passe au joueur suivant ... etc...
               
      
            }


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
