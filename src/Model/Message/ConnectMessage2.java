package Model.Message;

import Model.Server.ConnectionHandler;

public class ConnectMessage2 extends Message2{
    public static final long serialVersionUID = 12345678L;
    public final String username;
    public ConnectMessage2(String username) {

        this.username = username;
    }
    @Override
    public void handle(ConnectionHandler connectionHandler) {
        connectionHandler.SendMessage(new TextMessage2("The user"+this.username+"connected."));
    }
}
