package Model.Message;

import Model.Person;
import Model.Server.ConnectionHandler;
import Model.Server.DataBase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SingUpMessage2 extends Message2{
    public static final long serialVersionUID = 16519891324L;
    public final Person person;


    public SingUpMessage2(Person person) {
        this.person = person;

    }
    @Override
    public void handle(ConnectionHandler connectionHandler) {
        DataBase dataBase =connectionHandler.getDataBase();
        dataBase.AddClient_InClientHandler(person);
        dataBase.SaveUser(person);
        System.out.println("action: sign up");
        System.out.println(person.getUsername() + " register " + "account");
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        connectionHandler.SendMessage(new TextMessage2("The Account was created."));

    }
}
