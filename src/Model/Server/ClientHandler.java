package Model.Server;

import Model.Message.Message2;

import java.io.*;

public class ClientHandler implements Runnable{
    volatile private ConnectionHandler ch;

    public ClientHandler(ConnectionHandler ch) {
        this.ch = ch;
    }



    @Override
    public void run() {
        while (true){
            try {
                Message2 message = (Message2) ch.getInputStream().readObject();
                message.handle(ch);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
