package Model.Message;

import Model.Server.ConnectionHandler;

public class TextMessage2 extends Message2{
    public static final long serialVersionUID = 65493216598L;
    private String text;

    public TextMessage2(String text) {
        this.text = text;
        super.setP(text);
    }

    @Override
    public void handle(ConnectionHandler connectionHandler) {
        //
    }

}
