package Model.Message;

import Model.Server.ConnectionHandler;

import java.io.Serializable;

public abstract class Message2 implements Serializable {
    protected String p;
    protected Object o;
    public abstract void handle(ConnectionHandler connectionHandler);
    public void setP(String P){
        this.p=P;
    }
    public String getP(){
        return p;
    }

    public void setO(Object o) {
        this.o = o;
    }

    public Object getO() {
        return o;
    }
}
