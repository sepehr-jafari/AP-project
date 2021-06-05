package Model.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(5056);
        Socket client;
        while (true) {
            client = null;
            try {
                client = server.accept();
                OutputStream os = client.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);
                InputStream is = client.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                Thread thread = new Thread(new ClientHandler(client, ois, oos));
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


}
