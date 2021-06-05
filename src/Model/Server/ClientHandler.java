package Model.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable{
    private final ObjectOutputStream oos;
    private final ObjectInputStream ois;
    private ConnectionHandler ch;
    private final Socket socket;
    public ClientHandler(Socket socket, ObjectInputStream ois, ObjectOutputStream oos)
    {
        this.socket = socket;
        this.oos = oos;
        this.ois = ois;
        ch = new ConnectionHandler(oos, ois);

    }

    @Override
    public void run() {

    }
}
