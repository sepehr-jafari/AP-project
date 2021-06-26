package Controller;

import Model.Main;
import Model.Message.GetUsersMessage;
import Model.PageLoader;
import Model.Person;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SearchPageController {

    public ListView<Person> personsList;


    @FXML
    public void initialize(){
        ConcurrentLinkedQueue<Person> users = (ConcurrentLinkedQueue<Person>) Main.client.getResponse(new GetUsersMessage()).getO();
        Person[] array = users.toArray(Person[]::new);
        ArrayList<Person> usersArrayList = new ArrayList<>();
        Collections.addAll(usersArrayList,array);
        personsList.setItems(FXCollections.observableArrayList(usersArrayList));
        personsList.setCellFactory(personsList-> new ProfileItem());
    }

    public void searchPage(ActionEvent actionEvent) {
        try {
            new PageLoader().load("SearchPage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void homePage(ActionEvent actionEvent) {
        try {
            new PageLoader().load("TimeLine");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPostPage(ActionEvent actionEvent) {
        try {
            new PageLoader().load("AddNewPost");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void profilePage(ActionEvent actionEvent) {
        try {
            new PageLoader().load("Profile");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
