package Model.Message;

import Model.Person;
import Model.Server.ConnectionHandler;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class GetProfileMessage extends Message2{
    public static final long serialVersionUID = 5846254545L;
    private String Username;
    private String mainUsername;
    private byte[] imageProfile;

    public GetProfileMessage(String username, String mainUsername) {
        Username = username;
        this.mainUsername=mainUsername;
    }

    @Override
    public void handle(ConnectionHandler connectionHandler) {
        ConcurrentLinkedQueue<Person> listOfPerson = connectionHandler.getDataBase().getListOfUsers();
        for (Person p: listOfPerson) {
            if (p.getUsername().equals(Username)){
                imageProfile = p.getImage();
                connectionHandler.SendMessage(new ObjectMessage(p));
                return;
            }

        }
        System.out.println("action: get profile");
        System.out.println(mainUsername + " get info " + Username);
        System.out.println("message: " + Username );
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        connectionHandler.SendMessage(new TextMessage2("Some thing is wrong"));
    }


}
