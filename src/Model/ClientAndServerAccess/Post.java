package Model.ClientAndServerAccess;

import Model.ClientAndServerAccess.Comment;

import java.io.Serializable;
import java.util.ArrayList;

public class Post implements Serializable {
    public static final long serialVersionUID = 57882353L;
    private String Title;
    private String Username;
    private String PostDescription;
    private String dateOfPublish;
    private byte[] image;
    private byte[] postImageProfile;
    private volatile Integer numberOfRepost = 0;
    private volatile Integer numberOfLike = 0;
    private ArrayList<String> usernamesThatLike = new ArrayList<>();
    private ArrayList<String> usernamesThatRepost = new ArrayList<>();
    private String UID;
    private ArrayList<Comment> comments = new ArrayList<>();
    public Post(String title, String username, String postDescription, String date) {
        Title = title;
        Username = username;
        PostDescription = postDescription;
        dateOfPublish = date;

    }

    public String getUsername() {
        return Username;
    }

    public String getTitle() {
        return Title;
    }

    public String getPostDescription() {
        return PostDescription;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    public String getDateOfPublish() {
        return dateOfPublish;
    }

    public byte[] getPostImageProfile() {
        return postImageProfile;
    }

    public void setPostImageProfile(byte[] postImageProfile) {
        this.postImageProfile = postImageProfile;
    }

    public Integer getNumberOfRepost() {
        return numberOfRepost;
    }
    public void increaseNumberOfRepost(){
        ++numberOfRepost;
    }

    public Integer getNumberOfLike() {
        return numberOfLike;
    }
    public void increaseNumberOfLike(){
        ++numberOfLike;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }
    public void setComment(Comment comment){
        comments.add(comment);
    }
    public ArrayList<Comment> getComments(){
        return comments;
    }

    public ArrayList<String> getUsernamesThatLike() {
        return usernamesThatLike;
    }
    public void setUsernamesThatLike(String username){
        usernamesThatLike.add(username);
    }
    public void setUsernamesThatRepost(String username){
        usernamesThatRepost.add(username);
    }

    public ArrayList<String> getUsernamesThatRepost() {
        return usernamesThatRepost;
    }
}
