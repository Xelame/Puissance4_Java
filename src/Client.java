import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;


public class Client {

/*
    public static void main(String [] args) {

        try{
        SocketChannel socketChannel = SocketChannel.open();

        socketChannel.connect(new InetSocketAddress("localhost",8000));


        Thread clientThread = new Thread();//TODO

        clientThread.start();

        while (true){
            String message  = promptForString();
            //TODO : 
            
        }

        }catch (IOException e){
            System.err.println(e.toString());
        }
    }*/

    public static String promptForString(){
        
        System.out.print(">");
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
}
    

