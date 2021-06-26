package Controller;

import Model.Client.Client;
import Model.Main;
import Model.Message.EditProfileMessage;
import Model.Message.GetProfileMessage;
import Model.PageLoader;
import Model.Person;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditPageController {
    public TextField name;
    public TextField surname;
    public DatePicker birthday;
    public TextField country;
    public TextField email;
    public Text invalidPassword;
    public PasswordField password;
    public Text PasswordsMatchError;
    public PasswordField confirmPassword;
    private FileChooser fileChooser;
    private File filePath;
    private Person profile;
    private Client client = Main.client;
    private String Username = LoginController.getUsername();
    private Image image = null;
    private byte[] Image;
    private boolean passwordCorrect=false;
    private boolean confirmPasswordMatch = false;
    String Password = null;
    public void setPhoto(ActionEvent actionEvent) {
        profile = (Person) client.getResponse(new GetProfileMessage(Username,LoginController.getUsername())).getO();
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        this.filePath = fileChooser.showOpenDialog(stage);
        try{
            BufferedImage bufferedImage = ImageIO.read(filePath);
//            this.image = SwingFXUtils.toFXImage(bufferedImage,null);
            this.Image = toByteArray(bufferedImage, "jpg");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void setChange(ActionEvent actionEvent) {
        String Name = null;
        String Surname = null;
        String Country = null;
        String Email = null;
        String ConfirmPassword = confirmPassword.getText();
        if (!(name.getText().equals(""))){
            Name = name.getText();
        }
        if (!(surname.getText().equals(""))){
            Surname = surname.getText();
        }
        if (!(country.getText().equals(""))){
            Country = country.getText();
        }
        if (!( email.getText().equals(""))){
            Email =  email.getText();
        }
        if (!(password.getText().equals(""))){
            Password = password.getText() ;
            if (CheckPassword(Password)){
                passwordCorrect = true;
                if (CheckConfirmPassword(ConfirmPassword)){
                    confirmPasswordMatch=true;
                }
            }
        }else {
            confirmPasswordMatch=true;
            passwordCorrect=true;
        }
        if (confirmPasswordMatch && passwordCorrect){
            Main.client.getResponse(new EditProfileMessage(Image,Name,Surname,Email,Password,Username));
            try {
                new PageLoader().load("Profile");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    private Date DateConvertor(String Date){
        String[] splitDate = Date.split("-");
        int[] intDate = {Integer.parseInt(splitDate[0]),Integer.parseInt(splitDate[1]),Integer.parseInt(splitDate[2])};
        return new Date(intDate[0],intDate[1],intDate[2]);
    }
   private boolean CheckPassword(String password){
       String regex = "[0-9]{3,}";
       Pattern pattern = Pattern.compile(regex);
       Matcher matcher = pattern.matcher(password);
       if (password.length()<8||!(matcher.matches())){
           invalidPassword.setVisible(true);

           return false;
       }else {
           invalidPassword.setVisible(false);

           return true;
       }
   }
   private boolean CheckConfirmPassword(String confirmPassword){
        if (confirmPassword.equals("")||!(confirmPassword.equals(Password))){
            PasswordsMatchError.setVisible(true);
            return false;
        }
        PasswordsMatchError.setVisible(false);
        return true;

   }
    // convert BufferedImage to byte[]
    private byte[] toByteArray(BufferedImage bi, String format)
            throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, format, baos);
        byte[] bytes = baos.toByteArray();
        return bytes;

    }
}
