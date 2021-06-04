package Controller;

import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {
    @FXML
    public TextField Username_Field;
    public PasswordField Password_Field;
    public Button Login_Button;
    public Label WrongPassword_Label;
    public TextField Password_visible;

    public void LogIn(ActionEvent actionEvent) {
        String Username = Username_Field.getText();
        String Password = Password_Field.getText();
    }
    public void CreateAccount(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("CreateAccount");
    }

    public void ShowPass(ActionEvent actionEvent) {
        if (!Password_visible.isVisible()){
            Password_visible.setVisible(true);
            Password_Field.setVisible(false);
            Password_visible.setText(Password_Field.getText());
        }else {
            Password_visible.setVisible(false);
            Password_Field.setVisible(true);
            Password_Field.setText(Password_visible.getText());
        }
    }



}
