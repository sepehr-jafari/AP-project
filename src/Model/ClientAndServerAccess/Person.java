package Model.ClientAndServerAccess;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import Model.ClientAndServerAccess.Post;
import javafx.scene.image.Image;

public class Person implements Serializable {
    private String Name;
    private String SurName;
    private Date BirthDay;
    private String Country;
    private String Email;
    private String Username;
    private String Password;
    private ArrayList<Post> posts ;
    private ArrayList<String> followers ;
    private ArrayList<String> following ;

    private Image profile;
    private byte[] image;

    public String getPassword() {
        return Password;
    }

    public Person(String Name, String Surname, Date Birthday, String Country, String Email, String Username, String Password) {
        this.Name=Name;
        this.SurName = Surname;
        this.BirthDay = Birthday;
        this.Country = Country;
        this.Email = Email;
        this.Username = Username;
        this.Password = Password;
        this.posts = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
    }

    public String getUsername() {
        return Username;
    }

    public void setPost(Post post) {
        this.posts.add(post);
    }
    public void setFollower(String username){
        this.followers.add(username);
    }
    public void setFollowing(String following){
        this.following.add(following);
    }

    public ArrayList<String> getFollowers() {
        return followers;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }
    public void setProfile(Image profile) {
        this.profile = profile;
    }

    public Image getProfile() {
        return profile;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setSurName(String surName) {
        SurName = surName;
    }

    public void setBirthDay(Date birthDay) {
        BirthDay = birthDay;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setImage(byte[] image) {
        this.image = null;
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    public ArrayList<String> getFollowing() {
        return following;
    }
    public void removeFollowing(String following){
        this.following.remove(following);
    }
    public void removeFollowers(String follower){
        this.followers.remove(follower);
    }
}
