package Controller;

import Model.PageLoader;
import Model.ClientAndServerAccess.Person;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

public class ProfileItemController {
    public ImageView image;
    public Label username;
    public AnchorPane root;
    Person person;
    public ProfileItemController(Person person){
        try {
            new PageLoader().load("ProfileItem",this);
            this.person = person;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public AnchorPane init(){
        username.setText(person.getUsername());
        if (person.getImage()!=null){
            try {
                BufferedImage bufferedImage = toBufferedImage(person.getImage());
                Image photo = SwingFXUtils.toFXImage(bufferedImage, null);
                image.setImage(photo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            image.setImage(new Image(Paths.get("F:/images/user.png").toUri().toString()));
        }
        return root;

    }
    public static BufferedImage toBufferedImage(byte[] bytes)
            throws IOException {

        InputStream is = new ByteArrayInputStream(bytes);
        BufferedImage bi = ImageIO.read(is);
        return bi;

    }
    public void viewPost(ActionEvent actionEvent){
        try {
            ProfileInSearchController.setPerson(person);
            new PageLoader().load("ProfileInSearch");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
