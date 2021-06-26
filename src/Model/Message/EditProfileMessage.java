package Model.Message;

import Model.PageLoader;
import Model.Person;
import Model.Server.ConnectionHandler;

import javafx.scene.image.Image;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;

public class EditProfileMessage extends Message2{
    public static final long serialVersionUID =764374L;
    private Image image;
    private String Name;
    private String Surname;
    private String Email;
    private String Password;
    private String Username;
    private byte[] Image;
    public EditProfileMessage(byte[] image, String name, String surname, String email, String password, String Username) {
        this.Image = image;
        Name = name;
        Surname = surname;
        Email = email;
        Password = password;
        this.Username=Username;
    }

    @Override
    public void handle(ConnectionHandler connectionHandler) {
        connectionHandler.getDataBase().EditUser(Image,Name,Surname,Email,Password,Username);
        connectionHandler.getDataBase().updateListOfUser();
        System.out.println("action: edit profile");
        System.out.println("message: " + Arrays.toString(Image));
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        connectionHandler.SendMessage(new TextMessage2("Changes was done"));
    }
}
