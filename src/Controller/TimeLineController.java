package Controller;

import Model.Client.Client;
import Model.Main;
import Model.Message.GetPostsMessage;
import Model.Message.GetProfileMessage;
import Model.PageLoader;
import Model.Person;
import Model.Post;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class TimeLineController {
    public ListView<Post> PostList;
    private ArrayList<ArrayList<Post>> posts = new ArrayList<>();
    private String Username = LoginController.getUsername();
    private static String UsernameSignUpSide;
    public static Person person;
    private static boolean comeFromSignUp = false;

    @FXML
    public void initialize(){
        Client client = Main.client;
        if (comeFromSignUp){
            Username = UsernameSignUpSide;
            LoginController.setUsername(UsernameSignUpSide);
        }
        person = (Person) client.getResponse(new GetProfileMessage(Username,LoginController.getUsername())).getO();

        posts = (ArrayList<ArrayList<Post>>) client.getResponse(new GetPostsMessage(person)).getO();
        if(posts!=null) {
            ArrayList<Post> listOfPosts = createListOfPosts(posts);
            PostList.setItems(FXCollections.observableArrayList(listOfPosts));
            PostList.setCellFactory(PostList -> new PostItem());
        }


    }

    public void AddNewPost(ActionEvent actionEvent) {
        try {
            new PageLoader().load("AddNewPost");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void HomePage(ActionEvent actionEvent) {
        try {
            new PageLoader().load("TimeLine");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Post> createListOfPosts(ArrayList<ArrayList<Post>> posts){
        ArrayList<Post> result = new ArrayList<>();
        for (ArrayList<Post> post : posts) {
            result.addAll(post);
        }
        return result;
    }

    public void profilePage(ActionEvent actionEvent) {
        try {
            new PageLoader().load("Profile");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void searchPage(ActionEvent actionEvent) {
        try {
            new PageLoader().load("SearchPage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void setComeFromSignUp(Boolean signUp, String username){
        comeFromSignUp = signUp;
        UsernameSignUpSide = username;
    }
}
