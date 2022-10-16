package main.java.puissance4;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.io.BufferedReader;

import java.io.InputStreamReader;

import javax.swing.text.StyledEditorKit.BoldAction;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    static SocketChannel  clientSocket = null;
    static Boolean firstCo = true;
    
    public static void main(String[] args){


        try{
            if(firstCo){
            clientSocket = SocketChannel.open();
            clientSocket.connect(new InetSocketAddress("localHost", 8000));
             //TODO : wait for other client
             
             System.out.println("En attente de joueur la partie va bientôt démarrer ...");
             firstCo = false;
            } else {
                boolean isCorrect = false;


                System.out.println("START");

                while(isCorrect){
                    String message = promptForString();
                    try {
                        send(message);
                        isCorrect = true;
                    }
                    catch(IOException e){
                        System.err.println(e.toString());
                    }
                    
                }

           
            //TODO : la partie démarre

            //TODO : si à moi de jouer j'écris un char sinon j'attends 
        }
             
             
               
            

           
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

    public static String promptForString(){
        InputStreamReader bis = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(bis);
        try {
            return br.readLine();
        }
        catch(IOException e){
            System.err.println("Something went wrong : " + e.getMessage());
            System.err.println("Please retry : ");
            return promptForString();
        }
    }

    public static void send(String message) throws IOException{
        ByteBuffer bytes = ByteBuffer.wrap(message.getBytes("UTF-8"));
        while(bytes.hasRemaining()){
            clientSocket.write(bytes);
        }
    }

 
}
