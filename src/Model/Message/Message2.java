package Model.Message;

import Model.Server.ConnectionHandler;

import java.io.Serializable;

public abstract class Message2 implements Serializable {
    protected String p;
    public abstract void handle(ConnectionHandler connectionHandler);
    public void setP(String P){
        this.p=P;
    }
    public String getP(){
        return p;
    }
}
