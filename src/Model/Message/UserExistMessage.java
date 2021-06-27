package Model.Message;

import Model.ClientAndServerAccess.Person;
import Model.ClientAndServerAccess.ConnectionHandler;

import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UserExistMessage extends Message2{
    private final String Username;

    public UserExistMessage(String username) {
        Username = username;
    }

    @Override
    public void handle(ConnectionHandler connectionHandler) {
        String UsernameAlreadySelected = "false";
        ReadWriteLock Lock = new ReentrantReadWriteLock();
        Set<Person> setOfPerson = connectionHandler.getDataBase().getData().keySet();
        Lock.readLock().lock();
        for (Person p: setOfPerson) {
            if (p.getUsername().equals(Username)){
                UsernameAlreadySelected = "true";
                break;
            }

        }
        Lock.readLock().unlock();
        connectionHandler.SendMessage(new TextMessage2(UsernameAlreadySelected));

    }
}
