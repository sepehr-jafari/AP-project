package Model;

import java.util.concurrent.ConcurrentHashMap;

public class DataBase {
    private static DataBase dataBase;
    private final ConcurrentHashMap<Person, String> clientsAccount = new ConcurrentHashMap<>();
    private DataBase(){}

    public static DataBase getDataBase() {
        if (dataBase==null){
            dataBase = new DataBase();
        }
        return dataBase;
    }
    public ConcurrentHashMap<Person, String> getData(){
        return clientsAccount;
    }
    public void addClient(Person person){
        clientsAccount.put(person, person.getPassword());
    }
}
