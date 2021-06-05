package Model.Server;

import Model.Person;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.ConcurrentHashMap;

public class DataBase {
    private boolean firstTime = true;
    private static DataBase dataBase;
    private static File file = new File("F:\\AP-project\\src\\Model\\Server\\Data\\clientsAccount.txt");
    private static ConcurrentHashMap<Person, String> clientsAccount = new ConcurrentHashMap<>();
    private DataBase(){}
    public static DataBase getDataBase() {
        if (dataBase==null){
            if (file.exists()){
                try {
                    FileInputStream fis = new FileInputStream(file);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    clientsAccount = (ConcurrentHashMap<Person, String>) ois.readObject();
                    ois.close();
                    fis.close();
                }catch (IOException e){
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }else {
                dataBase = new DataBase();
            }
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
