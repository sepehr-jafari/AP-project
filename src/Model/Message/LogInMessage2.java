package Model.Message;

import Model.Person;
import Model.Server.ConnectionHandler;

import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LogInMessage2 extends Message2{
    public static final long serialVersionUID = 215548257L;
    private final String Username;
    private final String Password;

    public LogInMessage2(String username, String password) {
        this.Username = username;
        this.Password = password;
    }
    @Override
    public void handle(ConnectionHandler connectionHandler) {
        String UserWithThisPassExist = "false";
        ReadWriteLock Lock = new ReentrantReadWriteLock();
        Set<Person> personSet = connectionHandler.getDataBase().getData().keySet();
        Lock.readLock().lock();
        for (Person p: personSet) {
            if (Username.equals(p.getUsername())) {
                if (p.getPassword().equals(Password)){
                    UserWithThisPassExist = "true";
                    break;
                }

            }
        }
        Lock.readLock().unlock();
        connectionHandler.SendMessage(new TextMessage2(UserWithThisPassExist));

    }
}