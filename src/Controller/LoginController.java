package Controller;

import Model.Client.Client;
import Model.Main;
import Model.Message.*;
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
    private static String Username;

    public void LogIn(ActionEvent actionEvent) {
        String Password;
        Username = Username_Field.getText();
        if (!Password_visible.isVisible()) {
            Password = Password_Field.getText();
        }else {
            Password = Password_visible.getText();
        }
        Client client = Main.client;
        client.getResponse(new ConnectMessage2(Username));
        String response = client.getResponse(new LogInMessage2(Username, Password)).getP();
        if (response.equals("true")){
            try {
                client.setUsername(Username);
                new PageLoader().load("TimeLine");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            WrongPassword_Label.setVisible(true);
        }
    }

    public void CreateAccount(ActionEvent actionEvent) throws IOException {
        new PageLoader().load("CreateAccount");
    }

    public void ShowPass(ActionEvent actionEvent) {
        if (!Password_visible.isVisible()) {
            Password_visible.setVisible(true);
            Password_Field.setVisible(false);
            Password_visible.setText(Password_Field.getText());
        } else {
            Password_visible.setVisible(false);
            Password_Field.setVisible(true);
            Password_Field.setText(Password_visible.getText());
        }
    }

    public static String getUsername() {
        return Username;
    }

    public static void setUsername(String username) {
        Username = username;
    }
}
