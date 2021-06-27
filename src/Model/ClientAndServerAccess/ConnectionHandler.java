package Model.ClientAndServerAccess;

import Model.Message.Message2;
import Model.ClientAndServerAccess.Person;
import Model.Server.DataBase;

import java.io.IOException;
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

    public DataBase getDataBase() {
        return dataBase;
    }

    public void SendMessage(Message2 message){
        try {
            oos.writeObject(message);
            oos.flush();
            oos.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObjectOutputStream getOutputStream() {
        return oos;
    }

    public ObjectInputStream getInputStream() {
        return ois;
    }
    public void close(){
        try {
            oos.close();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
