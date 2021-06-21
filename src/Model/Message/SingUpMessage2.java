package Model.Message;

import Model.Person;
import Model.Server.ConnectionHandler;

public class SingUpMessage2 extends Message2{
    public static final long serialVersionUID = 16519891324L;
    public final Person person;


    public SingUpMessage2(Person person) {
        this.person = person;

    }
    @Override
    public void handle(ConnectionHandler connectionHandler) {
        connectionHandler.getDataBase().AddClient_InClientHandler(person);
        connectionHandler.SendMessage(new TextMessage2("The Account was created."));

    }
}
