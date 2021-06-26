package Model.Server;

import Model.Message.Message2;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{
    volatile private ConnectionHandler ch;
    volatile private Socket socket;

    public ClientHandler(ConnectionHandler ch, Socket socket) {
        this.ch = ch;
        this.socket = socket;
    }



    @Override
    public void run() {
        while (true){
            try {
                Message2 message = (Message2) ch.getInputStream().readObject();
                message.handle(ch);
            } catch (IOException e) {

                ch.close();
                try {
                    socket.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                break;

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
}
