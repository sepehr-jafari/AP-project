package Controller;

import Model.Main;
import Model.Message.*;
import Model.PageLoader;
import Model.Post;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PostItemController {

    public AnchorPane root;
    public TextArea description;
    public Label username;
    public Label title;
    public Label date;
    public ImageView image;
    public ImageView postImage;
    public Label numOfRepost;
    public Label numOfLike;
    Post post;

    //each list item will have its exclusive controller in runtime so we set the controller as we load the fxml
    public PostItemController(Post post) throws IOException {
        new PageLoader().load("postItem", this);
        this.post = post;
    }

    //this anchor pane is returned to be set as the list view item
    public AnchorPane init() {
        username.setText(post.getUsername());
        title.setText(post.getTitle());
        date.setText(post.getDateOfPublish());
        description.setEditable(false);
        description.setText(post.getPostDescription());
        Long numberOfLike = (Long) Main.client.getResponse(new GetNumberOfLikeMessage(post.getUID())).getO();
        numOfLike.setText("" + numberOfLike);
        Long numberOfRepost = (Long) Main.client.getResponse(new GetNumberOfRepostMessage(post.getUID())).getO();
        numOfRepost.setText("" + numberOfRepost);
        try {
            if (post.getPostImageProfile()!=null) {
                BufferedImage bufferedImage = toBufferedImage(post.getPostImageProfile());
                Image photo = SwingFXUtils.toFXImage(bufferedImage, null);
                image.setImage(photo);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            BufferedImage bufferedImage_postImage = toBufferedImage(post.getImage());
            Image photo_postImage = SwingFXUtils.toFXImage(bufferedImage_postImage,null);
            postImage.setImage(photo_postImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //set another image dynamically

        return root;
    }
    public void repost(ActionEvent actionEvent){
        Main.client.getResponse(new AddRepostMessage(post.getUID(), post, LoginController.getUsername()));
        Main.client.getResponse(new RepostMessage(post, LoginController.getUsername()));
    }
    public void Like(ActionEvent actionEvent){

        Main.client.getResponse(new AddLikeMessage(post.getUID(),LoginController.getUsername(),post));

    }
    public void comments(ActionEvent actionEvent){
        try {
            CommentsController.setPost(post);
            CommentsController.setComments(post.getComments());
            new PageLoader().load("Comments");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static BufferedImage toBufferedImage(byte[] bytes)
            throws IOException {

        InputStream is = new ByteArrayInputStream(bytes);
        BufferedImage bi = ImageIO.read(is);
        return bi;

    }
}
