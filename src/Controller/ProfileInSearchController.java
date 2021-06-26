package Controller;

import Model.Client.Client;
import Model.Main;
import Model.Message.FollowMessage;
import Model.Message.UnfollowMessage;
import Model.PageLoader;
import Model.Person;
import Model.Post;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ProfileInSearchController {
    private static Person person;
    public Label username;
    public ListView<Post> postList;
    public ImageView profilePhoto;
    public Label numberOfFollowers;
    public Label numberOfPosts;
    public Label numberOfFollowing;
    public Button unfollowButton;
    public Button followButton;

    @FXML
    public void initialize(){
        numberOfPosts.setText(""+person.getPosts().size());
        numberOfFollowers.setText(""+person.getFollowers().size());
        numberOfFollowing.setText(""+person.getFollowing().size());
        username.setText(person.getUsername());
        if (checkIsFollowing(person)){
            unfollowButton.setVisible(true);
            followButton.setVisible(false);
        }
        if (person.getImage()!=null){
            try {
                BufferedImage bufferedImage = toBufferedImage(person.getImage());
                Image image = SwingFXUtils.toFXImage(bufferedImage,null);
                profilePhoto.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        postList.setItems(FXCollections.observableArrayList(person.getPosts()));
        postList.setCellFactory(postList-> new PostItem());
    }

    public static void setPerson(Person Person) {
        person = Person;

    }
    public static BufferedImage toBufferedImage(byte[] bytes)
            throws IOException {

        InputStream is = new ByteArrayInputStream(bytes);
        BufferedImage bi = ImageIO.read(is);
        return bi;

    }

    public void follow(ActionEvent actionEvent) {
        Client client = Main.client;
        client.getResponse(new FollowMessage(LoginController.getUsername(), person.getUsername()));
    }

    public void homePage(ActionEvent actionEvent) {
        try {
            new PageLoader().load("TimeLine");
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

    public void addPostPage(ActionEvent actionEvent) {
        try {
            new PageLoader().load("AddNewPost");
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
    private boolean checkIsFollowing(Person person){
        ArrayList<String> array = person.getFollowers();
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).equals(LoginController.getUsername())){
                return true;
            }
        }
        return false;
    }

    public void unfollow(ActionEvent actionEvent) {
        unfollowButton.setVisible(false);
        followButton.setVisible(true);
        Main.client.getResponse(new UnfollowMessage(LoginController.getUsername(),person.getUsername()));
    }
}
