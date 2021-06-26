package Model.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

public class Server {
    public final static int PORT = 2222;
    private static boolean isServerUp = true;
    private static final DataBase Data = DataBase.getDataBase();
    static {
        Data.InitializeClientsAccount();
        Data.InitializeListOfUsers();
        Data.InitializeLikesAndReposts();
    }
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);


        while (isServerUp) {
            Socket client= null;
            try {
                client = server.accept();

                OutputStream os = client.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                InputStream is = client.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                ConnectionHandler CH = new ConnectionHandler(oos,ois);
                Thread thread = new Thread(new ClientHandler(CH, client));
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


}
