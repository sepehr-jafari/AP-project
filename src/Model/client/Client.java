package Model.Client;

import Model.Message.Message2;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static String serverAddress = "localhost";
    public static final int PORT = 2222;
    private  ObjectOutputStream oos;
    private  ObjectInputStream ois;
    private Socket socket;
    private String Username;

    public Client() {
        try {
            InetAddress ip = InetAddress.getByName(serverAddress);
            socket = new Socket(ip, PORT);

            OutputStream os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
            oos.flush();
            InputStream is = socket.getInputStream();
            ois = new ObjectInputStream(is);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public Message2 getResponse(){
        Message2 message = null;
        try {
            if (ois!=null) {
                message = (Message2) ois.readObject();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return message;
    }
    public void SendMessage(Message2 message2){

        try {
            if (oos!=null) {
                oos.writeObject(message2);
                oos.flush();
                oos.reset();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public ObjectInputStream getOis() {
        return ois;
    }
    public Message2 getResponse(Message2 toSend){
        Object message2 = null;
        try {
            if (oos!=null) {
                oos.writeObject(toSend);
                oos.flush();
                oos.reset();
                message2 = ois.readObject();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (message2 instanceof Message2){
            return (Message2) message2;
        }
        return null;
    }
}
