package Model.Server;

import Model.Person;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Semaphore;

public class DataBase {
    private static DataBase dataBase;
    private static File file;
    private final static String fileAddress ="F:\\AP-project\\src\\Model\\Server\\Data\\clientsAccount.txt";

    private ConcurrentHashMap<Person, String> clientsAccount;
    private final Semaphore LockForAddingClient_FinalList = new Semaphore(1);
    private final Semaphore LockForInitializeDatabase = new Semaphore(1);
    private final Semaphore LockForAddingClient_ClientHandler = new Semaphore(1);

    private DataBase() {
        clientsAccount = new ConcurrentHashMap<>();
    }

    public static DataBase getDataBase() {
        if (dataBase == null) {
            dataBase = new DataBase();
            file = new File(fileAddress);
            if (!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return dataBase;
    }

    public ConcurrentHashMap<Person, String> getData() {
        if (clientsAccount.isEmpty()&&file.length()!=0) {
            try {
                LockForInitializeDatabase.acquire();
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                this.clientsAccount = (ConcurrentHashMap<Person, String>) ois.readObject();
                ois.close();
                fis.close();
                LockForInitializeDatabase.release();
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }

        }
        return clientsAccount;
    }

    public void AddClient_InClientHandler(Person person) {
        try {
            LockForAddingClient_ClientHandler.acquire();
            clientsAccount.put(person, person.getPassword());
            FileOutputStream os = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(clientsAccount);
            oos.close();
            os.close();
            LockForAddingClient_ClientHandler.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
