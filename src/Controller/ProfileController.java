package Controller;

import Model.Client.Client;
import Model.Main;
import Model.Message.GetProfileMessage;
import Model.PageLoader;
import Model.ClientAndServerAccess.Person;
import Model.ClientAndServerAccess.Post;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import javafx.scene.image.Image;
public class ProfileController {

    public ImageView profileImage;
    public ListView PostList;
    public Label username;
    public Label numberOfPosts;
    public Label numberOfFollowing;

    public static Person profile = TimeLineController.person;
    public Label numberOfFollower;
    private String Username = LoginController.getUsername();
    private ArrayList<Post> posts = new ArrayList<>();

    //    public void setProfileImage(ActionEvent actionEvent) {
//        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
//        fileChooser = new FileChooser();
//        this.filePath = fileChooser.showOpenDialog(stage);
//        try{
//            BufferedImage bufferedImage = ImageIO.read(filePath);
//            Image image = SwingFXUtils.toFXImage(bufferedImage,null);
//            profile.setProfile(image);
//            Main.client.getResponse(new SetProfileImageMessage(profile));
//        }catch (IOException e){
//            e.printStackTrace();
//        }
//    }
    @FXML
    public void initialize() {
        Client client = Main.client;
        profile = (Person) client.getResponse(new GetProfileMessage(Username,LoginController.getUsername())).getO();
        posts = profile.getPosts();
        username.setText(profile.getUsername());
        numberOfPosts.setText(""+posts.size());
        numberOfFollowing.setText(""+profile.getFollowing().size());
        numberOfFollower.setText(""+profile.getFollowers().size());
        if(profile.getImage()!=null){
            try {
                BufferedImage bufferedImage = toBufferedImage(profile.getImage());
                Image image = SwingFXUtils.toFXImage(bufferedImage,null);
                profileImage.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        PostList.setItems(FXCollections.observableArrayList(posts));
        PostList.setCellFactory(PostList -> new PostItem());

    }

    public void HomePage(ActionEvent actionEvent) {
        try {
            new PageLoader().load("TimeLine");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void EditPage(ActionEvent actionEvent) {
        try {
            new PageLoader().load("EditPage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // convert byte[] to BufferedImage
    public static BufferedImage toBufferedImage(byte[] bytes)
            throws IOException {

        InputStream is = new ByteArrayInputStream(bytes);
        BufferedImage bi = ImageIO.read(is);
        return bi;

    }

    public void searchPage(ActionEvent actionEvent) {
        try {
            new PageLoader().load("SearchPage");
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
