package Model.ClientAndServerAccess;

import java.io.Serializable;
import java.util.ArrayList;

public class Comment implements Serializable {
    private String commenterUsername;
    private String comment;
    private byte[] profileImage;

    public Comment(String commenterUsername, String comment, byte[] image) {
        this.commenterUsername = commenterUsername;
        this.comment = comment;
        profileImage = image;
    }

    public String getCommenterUsername() {
        return commenterUsername;
    }

    public String getComment() {
        return comment;
    }

    public byte[] getProfileImage() {
        return profileImage;
    }


}
