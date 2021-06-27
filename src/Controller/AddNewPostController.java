package Controller;

import Model.Client.Client;
import Model.Main;
import Model.Message.AddPostMessage;
import Model.Message.GetProfileMessage;
import Model.PageLoader;
import Model.ClientAndServerAccess.Person;
import Model.ClientAndServerAccess.Post;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNewPostController {

    public TextArea PostDescription;
    public TextField PostTitle;
    private final String Username = LoginController.getUsername();
    public ImageView photo;
    private byte[] Image;
    private Client client = Main.client;
    private FileChooser fileChooser;
    private File filePath;
    private boolean postHasPhoto = false;

    public void PublishPost(ActionEvent actionEvent) {
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        String publishDate = formatter.format(date);
        Post post = new Post(PostTitle.getText(), Username,PostDescription.getText(),publishDate);
        post.setPostImageProfile(ProfileController.profile.getImage());
        post.setUID("" + date.getTime());
        if (postHasPhoto){
            post.setImage(Image);
        }
        Main.client.getResponse(new AddPostMessage(post));
        PostDescription.setText("");
        PostTitle.setText("");
        photo.setImage(new Image(Paths.get("F:/images/images (2).png").toUri().toString()));

    }

    public void HomePage(ActionEvent actionEvent) {
        try {
            new PageLoader().load("TimeLine");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addPhoto(ActionEvent actionEvent) {
        postHasPhoto = true;
        Person profile = (Person) client.getResponse(new GetProfileMessage(Username,LoginController.getUsername())).getO();
        Stage stage = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        this.filePath = fileChooser.showOpenDialog(stage);
        try{
            BufferedImage bufferedImage = ImageIO.read(filePath);
            photo.setImage(SwingFXUtils.toFXImage(bufferedImage,null));
            this.Image = toByteArray(bufferedImage, "jpg");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    private byte[] toByteArray(BufferedImage bi, String format)
            throws IOException {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, format, baos);
        byte[] bytes = baos.toByteArray();
        return bytes;

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
