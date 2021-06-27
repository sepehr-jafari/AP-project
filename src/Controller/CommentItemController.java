package Controller;

import Model.ClientAndServerAccess.Comment;
import Model.PageLoader;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CommentItemController {
    public AnchorPane root;
    public TextArea commentText;
    public Label username;
    public ImageView profileImage;
   Comment comment;
    public CommentItemController(Comment comment)  {
        try {
            new PageLoader().load("CommentItem",this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.comment = comment;
    }
    public AnchorPane init(){
        commentText.setEditable(false);
        commentText.setText(comment.getComment());
        username.setText(comment.getCommenterUsername());
        if (comment.getProfileImage()!=null){
            try {
                BufferedImage bufferedImage = toBufferedImage(comment.getProfileImage());
                Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                profileImage.setImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return root;
    }
    public static BufferedImage toBufferedImage(byte[] bytes)
            throws IOException {

        InputStream is = new ByteArrayInputStream(bytes);
        BufferedImage bi = ImageIO.read(is);
        return bi;

    }
}
