package Model.Message;

import Model.Server.ConnectionHandler;

public class ObjectMessage extends Message2{
    public static final long serialVersionUID = 7634763497L;
    private Object object;

    public ObjectMessage(Object object) {
        this.object = object;
        super.setO(object);
    }
    @Override
    public void handle(ConnectionHandler connectionHandler) {
        //
    }

    public Object getObject() {
        return object;
    }
}
