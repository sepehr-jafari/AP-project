package Model;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {
    private String Name;
    private String SurName;
    private Date BirthDay;
    private String Country;
    private String Email;
    private String Username;
    private String Password;



    public String getPassword() {
        return Password;
    }

    public Person(String Name, String Surname, Date Birthday, String Country, String Email, String Username, String Password) {
        this.Name=Name;
        this.SurName = Surname;
        this.BirthDay = Birthday;
        this.Country = Country;
        this.Email = Email;
        this.Username = Username;
        this.Password = Password;
    }
}
