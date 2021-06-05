package Model.Client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private static ObjectOutputStream oos;
    private static ObjectInputStream ois;

    public static void RunClient() throws IOException {
        InetAddress ip = InetAddress.getByName("localhost");
        Socket socket = new Socket(ip,5060);
        OutputStream os = socket.getOutputStream();
        oos = new ObjectOutputStream(os);
        InputStream is = socket.getInputStream();
        ois = new ObjectInputStream(is);
        while (true){

        }
    }
    private void SendMessage(){

    }

}
