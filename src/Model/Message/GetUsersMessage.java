package Model.Message;

import Model.ClientAndServerAccess.Person;
import Model.ClientAndServerAccess.ConnectionHandler;

import java.util.concurrent.ConcurrentLinkedQueue;

public class GetUsersMessage extends Message2{
    public static final long serialVersionUID =623658L;
    @Override
    public void handle(ConnectionHandler connectionHandler) {
        ConcurrentLinkedQueue<Person> users = connectionHandler.getDataBase().getListOfUsers();
        connectionHandler.SendMessage(new ObjectMessage(users));
    }
}
