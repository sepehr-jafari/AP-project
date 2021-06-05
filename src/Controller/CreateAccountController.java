package Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccountController {


    public PasswordField Password_Field;
    public PasswordField ConfirmPassword_Field;
    public TextField Password_Visible;
    public TextField ConfirmPassword_Visible;
    public TextField Name_Field;
    public TextField Surname_Field;
    public DatePicker Birthday_Field;
    public TextField Country_Field;
    public TextField Username_Field;
    public TextField Email_Field;
    public Text InvalidEmail_Field;
    public Text EmptyBirthdayField;
    public Text EmptyNameField;
    public Text EmptySurnameField;
    public Text EmptyCountryField;
    public Text EmptyEmailField;
    public Text EmptyUsernameField;
    public Text InvalidPassword;
    public Text EmptyPasswordField;
    public Text PasswordNotMatch;
    private String FinallyPassword;
    private boolean AllowToCreateAccount;
    private Boolean[] Array = new Boolean[8];

    public void ShowPass(ActionEvent actionEvent) {
        if (!(Password_Visible.isVisible()&&ConfirmPassword_Visible.isVisible())){
            Password_Visible.setVisible(true);
            ConfirmPassword_Visible.setVisible(true);
            Password_Field.setVisible(false);
            ConfirmPassword_Field.setVisible(false);
            Password_Visible.setText(Password_Field.getText());
            ConfirmPassword_Visible.setText(ConfirmPassword_Field.getText());
        }else {
            Password_Visible.setVisible(false);
            ConfirmPassword_Visible.setVisible(false);
            Password_Field.setVisible(true);
            ConfirmPassword_Field.setVisible(true);
            Password_Field.setText(Password_Visible.getText());
            ConfirmPassword_Field.setText(ConfirmPassword_Visible.getText());
        }

    }

    public void SingUp(ActionEvent actionEvent) {
        String Email = null;
        Date Birthday = null;
        String Name = null;
        String Surname = null;
        String Country = null;
        String Username = null;
        String Password = null;
        String ConfirmPassword = null;
        if (CheckEmail(Email_Field.getText())){
            Email = Email_Field.getText();
        }
        if (CheckBirthday(Birthday_Field.getValue())){
            Birthday = DateConvertor(Birthday_Field.getValue().toString());
        }
        if (CheckName(Name_Field.getText())){
            Name = Name_Field.getText();
        }
        if (CheckSurname(Surname_Field.getText())){
            Surname = Surname_Field.getText();
        }
        if (CheckCountry(Country_Field.getText())){
            Country = Country_Field.getText();
        }
        if (CheckUsername(Username_Field.getText())){
            Username=Username_Field.getText();
        }
        if (Password_Field.isVisible()){
            if (CheckPassword(Password_Field.getText()))
            Password = Password_Field.getText();
            FinallyPassword = Password;
        }else {
            if (CheckPassword(Password_Visible.getText())){
                Password=Password_Visible.getText();
                FinallyPassword = Password;
            }
        }if (ConfirmPassword_Field.isVisible()){
            CheckConfirmPassword(ConfirmPassword_Field.getText());
        }else {
            CheckConfirmPassword(ConfirmPassword_Visible.getText());
        }
        if (CheckStatus(Array)) {

        }
    }
    private Date DateConvertor(String Date){
        String[] splitDate = Date.split("-");
        int[] intDate = {Integer.parseInt(splitDate[0]),Integer.parseInt(splitDate[1]),Integer.parseInt(splitDate[2])};
        return new Date(intDate[0],intDate[1],intDate[2]);
    }

    private Boolean CheckEmail(String Email) {

        if (Email==""){
            EmptyEmailField.setVisible(true);
            Array[0]=false;
            return false;
        }else {
            EmptyEmailField.setVisible(false);
            String regex = "[A-Za-z0-9_%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(Email);
            if (matcher.matches()){
//            AllowToCreateAccount = true;
                InvalidEmail_Field.setVisible(false);
                Array[0] = true;
                return true;
            }else{
                InvalidEmail_Field.setVisible(true);
//            AllowToCreateAccount=false;
                Array[0] = false;
                return false;
            }
        }

    }
    private Boolean CheckBirthday(LocalDate Birthday){
        if (Birthday==null){
            EmptyBirthdayField.setVisible(true);
//            AllowToCreateAccount = false;
            Array[1]=false;
            return false;
        }
        EmptyBirthdayField.setVisible(false);
        Array[1]=true;
        return true;
    }
    private Boolean CheckName(String Name){
        if (Name == ""){
            EmptyNameField.setVisible(true);
//            AllowToCreateAccount=false;
            Array[2]=false;
            return false;
        }
        EmptyNameField.setVisible(false);
        Array[2]=true;
        return true;
    }
    private boolean CheckSurname(String Surname){
        if (Surname==""){
            EmptySurnameField.setVisible(true);
            Array[3]=false;
            return false;
        }
        EmptySurnameField.setVisible(false);
        Array[3]= true;
        return true;
    }
    private boolean CheckCountry(String Country){
        if (Country==""){
            EmptyCountryField.setVisible(true);
            Array[4]=false;
            return false;
        }
        EmptyCountryField.setVisible(false);
        Array[4]=true;
        return true;
    }
    private boolean CheckUsername(String Username){
        if (Username==""){
            EmptyUsernameField.setVisible(true);
            Array[5]=false;
            return false;
        }
        EmptyUsernameField.setVisible(false);
        Array[5]=true;
        return true;
    }
    private boolean CheckPassword(String Password){
        if (Password==""){
            EmptyPasswordField.setVisible(true);
            Array[6]=false;
            return false;
        }else {
            EmptyPasswordField.setVisible(false);
            String regex = "[0-9]{3,}";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(Password);
            if (Password.length() < 8 || matcher.matches()) {
                InvalidPassword.setVisible(true);
                Array[6] = false;
                return false;
            } else {
                InvalidPassword.setVisible(false);
                Array[6] = true;
                return true;
            }
        }
    }
    private boolean CheckConfirmPassword(String ConfirmPassword){
        if (ConfirmPassword.equals(FinallyPassword)==false){
            PasswordNotMatch.setVisible(true);
            Array[7]=false;
            return false;
        }else {
            PasswordNotMatch.setVisible(false);
            Array[7]=true;
            return true;
        }
    }
    private Boolean CheckStatus(Boolean[] Array){
        for (int i = 0; i < Array.length; i++) {
            if (Array[i]==false){
                return false;
            }
        }
        return true;
    }
}
