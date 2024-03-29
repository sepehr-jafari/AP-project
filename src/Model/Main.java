package Model;

import Model.Client.Client;
import Model.PageLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Client client;
    @Override
    public void start(Stage primaryStage) throws Exception{
        PageLoader.initStage(primaryStage);
        new PageLoader().load("Login");
    }


    public static void main(String[] args) {
        client = new Client();
        launch(args);
    }
}
