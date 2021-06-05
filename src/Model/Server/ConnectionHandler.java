package Model.Server;

import Model.Person;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.ConcurrentHashMap;

public class ConnectionHandler {
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private DataBase dataBase = DataBase.getDataBase();
    private ConcurrentHashMap<Person, String> clientsAccount = dataBase.getData();
    public ConnectionHandler(ObjectOutputStream oos, ObjectInputStream ois){
        this.oos = oos;
        this.ois = ois;
    }
}
