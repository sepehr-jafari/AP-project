package Model.Message;

import Model.ClientAndServerAccess.ConnectionHandler;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ConnectMessage2 extends Message2{
    public static final long serialVersionUID = 12345678L;
    public final String username;
    public ConnectMessage2(String username) {

        this.username = username;
    }
    @Override
    public void handle(ConnectionHandler connectionHandler) {
        System.out.println("action: connect");
        System.out.println(username + " connect");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        connectionHandler.SendMessage(new TextMessage2("The user"+this.username+"connected."));
    }
}
